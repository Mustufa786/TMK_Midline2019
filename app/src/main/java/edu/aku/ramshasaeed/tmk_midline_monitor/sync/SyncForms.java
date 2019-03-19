package edu.aku.ramshasaeed.tmk_midline_monitor.sync;

/**
 * Created by hassan.naqvi on 12/2/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import edu.aku.ramshasaeed.tmk_midline_monitor.contracts.FormsContract;
import edu.aku.ramshasaeed.tmk_midline_monitor.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline_monitor.core.MainApp;

/**
 * Created by hassan.naqvi on 7/26/2016.
 */
public class SyncForms extends AsyncTask<Void, Void, String> {

    private static final String TAG = "SyncForms";
    Boolean flag = false;
    private Context mContext;
    private ProgressDialog pd;


    public SyncForms(Context context, Boolean flag) {
        mContext = context;
        this.flag = flag;
    }

    public SyncForms(Context context) {
        mContext = context;
    }

    public static void longInfo(String str) {
        if (str.length() > 4000) {
            Log.i(TAG, str.substring(0, 4000));
            longInfo(str.substring(4000));
        } else
            Log.i("TAG: ", str);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(mContext);
        pd.setTitle("Please wait... Processing Forms");
        pd.show();
    }


    @Override
    protected String doInBackground(Void... params) {
        try {
            String url;
           /* if (flag) {
                url = MainApp._HOST_URL + FormsContract.FormsTable._URL;
            } else {
                url = MainApp._HOST_URL + "_" + FormsContract.FormsTable._URL;
            }*/
            url = MainApp._HOST_URL + FormsContract.FormsTable._URL;

            Log.d(TAG, "doInBackground: URL " + url);
            return downloadUrl(url);
        } catch (IOException e) {
            return "Unable to upload data. Server may be down.";
        }
    }

    private String downloadUrl(String myurl) {
        String line = "No Response";

        DatabaseHelper db = new DatabaseHelper(mContext);
        Collection<FormsContract> Forms;
       /* if (flag) {
            Forms = db.getUnsyncedForms();
        } else {
            Forms = db.getFormsSg();
        }*/

            Forms = db.getUnsyncedForms();

        Log.d(TAG, String.valueOf(Forms.size()));

        if (Forms.size() > 0) {

            HttpURLConnection connection = null;
            try {
                String request = myurl;
                //String request = "http://10.1.42.30:3000/Forms";

                URL url = new URL(request);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                int HttpResult = connection.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    JSONArray jsonSync = new JSONArray();
                    connection = (HttpURLConnection) url.openConnection();

                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setInstanceFollowRedirects(false);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("charset", "utf-8");
                    connection.setUseCaches(false);
                    connection.connect();


                    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

//            pd.setMessage("Total Forms: " );

                    for (FormsContract fc : Forms) {
                        //if (fc.getIstatus().equals("1")) {
                        jsonSync.put(fc.toJSONObject());
                        //}
                    }
                    wr.writeBytes(jsonSync.toString().replace("\uFEFF", "") + "\n");
                    longInfo(jsonSync.toString().replace("\uFEFF", "") + "\n");
                    wr.flush();


                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            connection.getInputStream(), StandardCharsets.UTF_8));
                    StringBuffer sb = new StringBuffer();

                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    System.out.println("" + sb.toString());
                    return sb.toString();
                } else {
                    System.out.println(connection.getResponseMessage());
                    return connection.getResponseMessage();
                }
            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
            }
        } else {
            return "No new records to sync";
        }
        return line;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        int sSynced = 0;
        String sSyncedError = "";
        JSONArray json = null;
        try {
            json = new JSONArray(result);
            DatabaseHelper db = new DatabaseHelper(mContext);
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObject = new JSONObject(json.getString(i));
                if (jsonObject.getString("status").equals("1") && jsonObject.getString("error").equals("0")) {
                    db.updateSyncedForms(jsonObject.getString("id"));
                    sSynced++;
                } else {
                    sSyncedError += "\nError: " + jsonObject.getString("message");
                }
            }
            Toast.makeText(mContext, sSynced + " Forms synced." + String.valueOf(json.length() - sSynced) + " Errors: " + sSyncedError, Toast.LENGTH_SHORT).show();

            pd.setMessage(sSynced + " Forms synced." + String.valueOf(json.length() - sSynced) + " Errors: " + sSyncedError);
            pd.setTitle("Done uploading Forms data");
            pd.show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "Failed Sync " + result, Toast.LENGTH_SHORT).show();

            pd.setMessage(result);
            pd.setTitle("Forms Sync Failed");
            pd.show();


        }
    }
}