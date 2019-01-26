package edu.aku.ramshasaeed.tmk_midline.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.os.ResultReceiver;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.aku.ramshasaeed.tmk_midline.R;
import edu.aku.ramshasaeed.tmk_midline.contracts.AreasContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.FormsContract;
import edu.aku.ramshasaeed.tmk_midline.core.AndroidDatabaseManager;
import edu.aku.ramshasaeed.tmk_midline.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline.databinding.ActivityMainBinding;


public class MainActivity extends Activity {

    private final String TAG = "MainActivity";

    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    AlertDialog.Builder builder;
    String m_Text = "";
    ProgressDialog mProgressDialog;
    ArrayList<String> lablesAreas;
    Collection<AreasContract> AreasList;
    Map<String, String> AreasMap;
    private ProgressDialog pd;
    private Boolean exit = false;
    private String rSumText = "";
    ActivityMainBinding bi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bi.setCallback(this);

//        ButterKnife.bind(this);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        bi.lblheader.setText("Welcome! You're assigned to block ' " + MainApp.regionDss + " '" + MainApp.userName);

        if (MainApp.admin) {
            bi.adminsec.setVisibility(View.VISIBLE);
        } else {
            bi.adminsec.setVisibility(View.GONE);
        }


        /*TagID Start*/
        sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        editor = sharedPref.edit();

        builder = new AlertDialog.Builder(MainActivity.this);
        ImageView img = new ImageView(getApplicationContext());
        img.setImageResource(R.drawable.tagimg);
        img.setPadding(0, 15, 0, 15);
        builder.setCustomTitle(img);

        final EditText input = new EditText(MainActivity.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                if (!m_Text.equals("")) {
                    editor.putString("tagName", m_Text);
                    editor.commit();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        if (sharedPref.getString("tagName", null) == "" || sharedPref.getString("tagName", null) == null) {
            builder.show();
        }
        /*TagID End*/


        DatabaseHelper db = new DatabaseHelper(this);
        Collection<FormsContract> todaysForms = db.getTodayForms();
        Collection<FormsContract> unsyncedForms = db.getUnsyncedForms();

        rSumText += "TODAY'S RECORDS SUMMARY\r\n";

        rSumText += "=======================\r\n";
        rSumText += "\r\n";
        rSumText += "Total Forms Today: " + todaysForms.size() + "\r\n";
        rSumText += "\r\n";
        if (todaysForms.size() > 0) {
            rSumText += "\tFORMS' LIST: \r\n";
            String iStatus;
            rSumText += "--------------------------------------------------\r\n";
            rSumText += "[ DSS_ID ] \t[Form Status] \t[Sync Status]----------\r\n";
            rSumText += "--------------------------------------------------\r\n";

            for (FormsContract fc : todaysForms) {
                if (fc.getIstatus() != null) {
                    switch (fc.getIstatus()) {
                        case "1":
                            iStatus = "\tComplete";
                            break;
                        case "2":
                            iStatus = "\tIncomplete";
                            break;
                        case "3":
                            iStatus = "\tRefused";
                            break;
                        case "4":
                            iStatus = "\tRefused";
                            break;
                        default:
                            iStatus = "\tN/A";
                    }
                } else {
                    iStatus = "\tN/A";
                }

                rSumText += fc.getDSSID();

                rSumText += " " + iStatus + " ";

                rSumText += (fc.getSynced() == null ? "\t\tNot Synced" : "\t\tSynced");
                rSumText += "\r\n";
                rSumText += "--------------------------------------------------\r\n";
            }
        }


        if (MainApp.admin) {
            bi.adminsec.setVisibility(View.VISIBLE);
            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            rSumText += "Last Data Download: \t" + syncPref.getString("LastDownSyncServer", "Never Updated");
            rSumText += "\r\n";
            rSumText += "Last Data Upload: \t" + syncPref.getString("LastUpSyncServer", "Never Synced");
            rSumText += "\r\n";
            rSumText += "\r\n";
            rSumText += "Unsynced Forms: \t" + unsyncedForms.size();
            rSumText += "\r\n";
        }
        Log.d(TAG, "onCreate: " + rSumText);
        bi.recordSummary.setText(rSumText);


//        Fill spinner

        lablesAreas = new ArrayList<>();
        AreasMap = new HashMap<>();
        lablesAreas.add("Select Area..");

        AreasList = db.getAllAreas(String.valueOf(MainApp.ucCode));
        for (AreasContract Areas : AreasList) {
            lablesAreas.add(Areas.getArea());
            AreasMap.put(Areas.getArea(), Areas.getAreacode());
        }

        bi.spAreas.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lablesAreas));

        bi.spAreas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (bi.spAreas.getSelectedItemPosition() != 0) {
                    MainApp.areaCode = Integer.valueOf(AreasMap.get(bi.spAreas.getSelectedItem().toString()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void openForm(View v) {

        if (bi.spAreas.getSelectedItemPosition() != 0) {

            if (sharedPref.getString("tagName", null) != "" && sharedPref.getString("tagName", null) != null && !MainApp.userName.equals("0000")) {
               /* Intent oF = new Intent(MainActivity.this, SectionAActivity.class).putExtra("flag", true);
                startActivity(oF);*/
            } else {

                builder = new AlertDialog.Builder(MainActivity.this);
                ImageView img = new ImageView(getApplicationContext());
                img.setImageResource(R.drawable.tagimg);
                img.setPadding(0, 15, 0, 15);
                builder.setCustomTitle(img);

                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        if (!m_Text.equals("")) {
                            editor.putString("tagName", m_Text);
                            editor.commit();

                            if (!MainApp.userName.equals("0000")) {
                              /*  Intent oF = new Intent(MainActivity.this, SectionAActivity.class).putExtra("flag", true);
                                startActivity(oF);*/
                            }
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please select data from combobox!!", Toast.LENGTH_LONG).show();
        }
    }

    public void openForm1(View v) {

        if (bi.spAreas.getSelectedItemPosition() != 0) {

            if (sharedPref.getString("tagName", null) != "" && sharedPref.getString("tagName", null) != null && !MainApp.userName.equals("0000")) {
                /*Intent oF = new Intent(MainActivity.this, SectionAActivity.class).putExtra("flag", false);
                startActivity(oF);*/
            } else {

                builder = new AlertDialog.Builder(MainActivity.this);
                ImageView img = new ImageView(getApplicationContext());
                img.setImageResource(R.drawable.tagimg);
                img.setPadding(0, 15, 0, 15);
                builder.setCustomTitle(img);

                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        if (!m_Text.equals("")) {
                            editor.putString("tagName", m_Text);
                            editor.commit();

                            if (!MainApp.userName.equals("0000")) {
                              /*  Intent oF = new Intent(MainActivity.this, SectionAActivity.class).putExtra("flag", false);
                                startActivity(oF);*/
                            }
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please select data from combobox!!", Toast.LENGTH_LONG).show();
        }
    }
public void openMembers(){

}

    public void openA(View v) {
      /*  Intent iA = new Intent(this, SectionAActivity.class);
        startActivity(iA);*/
    }

    public void openB(View v) {
       /* Intent iB = new Intent(this, SectionBActivity.class);
        startActivity(iB);*/
    }

    public void openC(View v) {
       /* Intent iC = new Intent(this, SectionCActivity.class);
        startActivity(iC);*/
    }

    public void openD(View v) {
       /* Intent iD = new Intent(this, SectionDActivity.class);
        startActivity(iD);*/
    }

    public void openE(View v) {
       /* Intent iE = new Intent(this, SectionEActivity.class);
        startActivity(iE);*/
    }

    public void openF(View v) {
        /*Intent iF = new Intent(this, SectionFActivity.class);
        startActivity(iF);*/
    }

    public void openG(View v) {
       /* Intent iG = new Intent(this, SectionGActivity.class);
        startActivity(iG);*/
    }

    public void openI(View v) {
       /* Intent iI = new Intent(this, SectionIActivity.class);
        startActivity(iI);*/
    }

    public void openJ(View v) {
        /*Intent iJ = new Intent(this, SectionJActivity.class);
        startActivity(iJ);*/
    }

    public void openK(View v) {
        /*Intent iK = new Intent(this, SectionKActivity.class);
        startActivity(iK);*/
    }

    public void openL(View v) {
      /*  Intent iL = new Intent(this, SectionLActivity.class);
        startActivity(iL);*/
    }

    public void openM(View v) {
       /* Intent iM = new Intent(this, SectionMActivity.class);
        startActivity(iM);*/
    }


    public void openHA(View v) {
       /* Intent iB = new Intent(this, SectionHAActivity.class);
        startActivity(iB);*/
    }

    public void openHB(View v) {
       /* Intent iB = new Intent(this, SectionHBActivity.class);
        startActivity(iB);*/
    }

    public void opendownload(View v) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MainActivity.this);
        alertDialogBuilder
                .setMessage("Are you sure to download new app??")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // this is how you fire the downloader
                                mProgressDialog = new ProgressDialog(MainActivity.this);
                                mProgressDialog.setMessage("Downloading TMK APK..");
                                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                mProgressDialog.setIndeterminate(false);
                                mProgressDialog.setProgress(0);
                                mProgressDialog.show();
                              /*  Intent intent = new Intent(MainActivity.this, DownloadFileService.class);
                                intent.putExtra("url", "url of the file to download");
                                intent.putExtra("receiver", new DownloadReceiver(new Handler()));
                                startService(intent);*/
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


    public void testGPS(View v) {

        SharedPreferences sharedPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
        Log.d("MAP", "testGPS: " + sharedPref.getAll().toString());
        Map<String, ?> allEntries = sharedPref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("Map", entry.getKey() + ": " + entry.getValue().toString());
        }

    }

    public void updateApp(View v) {
        v.setBackgroundColor(Color.GREEN);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MainActivity.this);
        alertDialogBuilder
                .setMessage("Are you sure to download new app??")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // this is how you fire the downloader
                                try {
                                    URL url = new URL(MainApp._UPDATE_URL);
                                    HttpURLConnection c = (HttpURLConnection) url.openConnection();
                                    c.setRequestMethod("GET");
                                    c.setDoOutput(true);
                                    c.connect();

                                    String PATH = Environment.getExternalStorageDirectory() + "/download/";
                                    File file = new File(PATH);
                                    file.mkdirs();
                                    File outputFile = new File(file, "app.apk");
                                    FileOutputStream fos = new FileOutputStream(outputFile);

                                    InputStream is = c.getInputStream();

                                    byte[] buffer = new byte[1024];
                                    int len1 = 0;
                                    while ((len1 = is.read(buffer)) != -1) {
                                        fos.write(buffer, 0, len1);
                                    }
                                    fos.close();
                                    is.close();//till here, it works fine - .apk is download to my sdcard in download file

                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + "app.apk")), "application/vnd.android.package-archive");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);

                                } catch (IOException e) {
                                    Toast.makeText(getApplicationContext(), "Update error!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void openDB(View v) {
        Intent dbmanager = new Intent(getApplicationContext(), AndroidDatabaseManager.class);
        startActivity(dbmanager);
    }

    public void CheckCluster(View v) {
     /*   Intent cluster_list = new Intent(getApplicationContext(), FormsList.class);
        cluster_list.putExtra("dssid", MainApp.regionDss);
        startActivity(cluster_list);*/

    }

    public void syncServer(View view) {

        // Require permissions INTERNET & ACCESS_NETWORK_STATE
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {


            Toast.makeText(getApplicationContext(), "Syncing Forms", Toast.LENGTH_SHORT).show();
//            new SyncForms(this, true).execute();

            /*Toast.makeText(getApplicationContext(), "Syncing Family Members", Toast.LENGTH_SHORT).show();
            new SyncFamilyMembers(this).execute();

            Toast.makeText(getApplicationContext(), "Syncing MWRAs", Toast.LENGTH_SHORT).show();
            new SyncMwras(this).execute();

            Toast.makeText(getApplicationContext(), "Syncing Deceased Mother", Toast.LENGTH_SHORT).show();
            new SyncDeceasedMother(this).execute();

            Toast.makeText(getApplicationContext(), "Syncing Deceased Child", Toast.LENGTH_SHORT).show();
            new SyncDeceasedChild(this).execute();

            Toast.makeText(getApplicationContext(), "Syncing IM", Toast.LENGTH_SHORT).show();
            new SyncIM(this).execute();
*/
            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = syncPref.edit();

            editor.putString("LastUpSyncServer", dtToday);

            editor.apply();

        } else {
            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
        }

    }

    public void syncDevice(View view) {

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            // Sync Random
            /*new GetBLRandom(this).execute();*/

            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = syncPref.edit();

            editor.putString("LastDownSyncServer", dtToday);

            editor.apply();
        } else {
            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity

            startActivity(new Intent(this, LoginActivity.class));

        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

    private class DownloadReceiver extends ResultReceiver {
        public DownloadReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
         /*   if (resultCode == DownloadFileService.UPDATE_PROGRESS) {
                int progress = resultData.getInt("progress");
                mProgressDialog.setMax(100);
                if (progress == 100) {
                    mProgressDialog.dismiss();

                    File file = new File(Environment.getExternalStorageDirectory() + "/" + "tmk_bl_sep_25.apk");
                    if (file.exists()) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                } else {
                    mProgressDialog.setProgress(progress);
                }
            }*/
        }
    }
}
