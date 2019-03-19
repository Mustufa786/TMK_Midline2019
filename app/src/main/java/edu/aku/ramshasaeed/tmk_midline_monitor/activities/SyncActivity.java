package edu.aku.ramshasaeed.tmk_midline_monitor.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.aku.ramshasaeed.tmk_midline_monitor.R;
import edu.aku.ramshasaeed.tmk_midline_monitor.adapter.SyncListAdapter;
import edu.aku.ramshasaeed.tmk_midline_monitor.adapter.UploadListAdapter;
import edu.aku.ramshasaeed.tmk_midline_monitor.contracts.FamilyMembersContract;
import edu.aku.ramshasaeed.tmk_midline_monitor.contracts.FormsContract;
import edu.aku.ramshasaeed.tmk_midline_monitor.contracts.FormsContract.FormsTable;
import edu.aku.ramshasaeed.tmk_midline_monitor.contracts.SectionIIMContract;
import edu.aku.ramshasaeed.tmk_midline_monitor.contracts.SyncModel;
import edu.aku.ramshasaeed.tmk_midline_monitor.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline_monitor.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline_monitor.databinding.ActivitySyncBinding;
import edu.aku.ramshasaeed.tmk_midline_monitor.get.GetAllData;
import edu.aku.ramshasaeed.tmk_midline_monitor.sync.SyncAllData;
import edu.aku.ramshasaeed.tmk_midline_monitor.sync.SyncDevice;

public class SyncActivity extends AppCompatActivity {
    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;
    String DirectoryName;
    DatabaseHelper db;
    SyncListAdapter syncListAdapter;
    UploadListAdapter uploadListAdapter;
    ActivitySyncBinding bi;
    SyncModel model;
    SyncModel uploadmodel;
    List<SyncModel> list;
    List<SyncModel> uploadlist;
    Boolean listActivityCreated;
    Boolean uploadlistActivityCreated;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_sync);
        bi.setCallback(this);

//        bi.setCallback(SyncActivity.this);
        list = new ArrayList<>();
        uploadlist = new ArrayList<>();
        bi.noItem.setVisibility(View.VISIBLE);
        bi.noDataItem.setVisibility(View.VISIBLE);
        listActivityCreated = true;
        uploadlistActivityCreated = true;
        db = new DatabaseHelper(this);
        dbBackup();
        bi.btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSyncDataClick();
            }
        });
        bi.btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncServer();
            }
        });
        setAdapter();
        setUploadAdapter();
    }

    public void onSyncDataClick() {

        // Require permissions INTERNET & ACCESS_NETWORK_STATE
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            new SyncDevice(SyncActivity.this, true).execute();
            new syncData(SyncActivity.this).execute();


        } else {
            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
        }
    }

    void setAdapter() {
        syncListAdapter = new SyncListAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        bi.rvSyncList.setLayoutManager(mLayoutManager);
        bi.rvSyncList.setItemAnimator(new DefaultItemAnimator());
        bi.rvSyncList.setAdapter(syncListAdapter);
        syncListAdapter.notifyDataSetChanged();
        if (syncListAdapter.getItemCount() > 0) {
            bi.noItem.setVisibility(View.GONE);
        } else {
            bi.noItem.setVisibility(View.VISIBLE);
        }
    }

    void setUploadAdapter() {
        uploadListAdapter = new UploadListAdapter(uploadlist);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        bi.rvUploadList.setLayoutManager(mLayoutManager2);
        bi.rvUploadList.setItemAnimator(new DefaultItemAnimator());
        bi.rvUploadList.setAdapter(uploadListAdapter);
        uploadListAdapter.notifyDataSetChanged();
        if (uploadListAdapter.getItemCount() > 0) {
            bi.noDataItem.setVisibility(View.GONE);
        } else {
            bi.noDataItem.setVisibility(View.VISIBLE);
        }
    }


/*

    public class syncData extends AsyncTask<String, String, String> {

        private Context mContext;

        public syncData(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected String doInBackground(String... strings) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    new SyncDevice(SyncActivity.this,false).execute();
//                  getting Enum Blocks
                    Toast.makeText(SyncActivity.this, "Sync Enum Blocks", Toast.LENGTH_SHORT).show();

                    if (listActivityCreated){
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "EnumBlock", SyncListAdapter, list).execute();
                    bi.noItem.setVisibility(View.GONE);

//                  getting Users!!
                    Toast.makeText(SyncActivity.this, "Sync Users", Toast.LENGTH_SHORT).show();

                    if (listActivityCreated){
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "User", SyncListAdapter, list).execute();

//                   getting BL Random
                    Toast.makeText(SyncActivity.this, "Sync BL Random", Toast.LENGTH_SHORT).show();
                    if (listActivityCreated){
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "BLRandom", SyncListAdapter, list).execute();

//                    Getting App Version
                    Toast.makeText(SyncActivity.this, "Sync App Version", Toast.LENGTH_SHORT).show();
                    if (listActivityCreated){
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "VersionApp", SyncListAdapter, list).execute();
                    */
/*Toast.makeText(Menu2Activity.this, "Sync Family Members", Toast.LENGTH_LONG).show();
                    new GetAllData(mContext, "FamilyMembers").execute();*//*


                    listActivityCreated = false;
                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

//                    populateSpinner(mContext);

                    editor.putBoolean("flag", true);
                    editor.commit();

                    dbBackup();

                }
            }, 1200);
        }
    }
*/

    public void syncServer() {

        // Require permissions INTERNET & ACCESS_NETWORK_STATE
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            DatabaseHelper db = new DatabaseHelper(this);
            //syncStatus.setText(null);
            new SyncDevice(this, false).execute();
            Toast.makeText(getApplicationContext(), "Syncing Forms", Toast.LENGTH_SHORT).show();
            if (uploadlistActivityCreated) {
                uploadmodel = new SyncModel();
                uploadmodel.setstatusID(0);
                uploadlist.add(uploadmodel);
            }

            new SyncAllData(
                    this,
                    "Forms",
                    "updateSyncedForms",
                    FormsContract.class,
                    MainApp._HOST_URL + FormsTable._URL,
                    db.getUnsyncedForms(),0,uploadListAdapter,uploadlist
            ).execute();

            Toast.makeText(getApplicationContext(), "Syncing Family Members", Toast.LENGTH_SHORT).show();
            if (uploadlistActivityCreated){
                uploadmodel = new SyncModel();
                uploadmodel.setstatusID(0);
                uploadlist.add(uploadmodel);
            }

            new SyncAllData(
                    this,
                    "FamilyMembers",
                    "updateFamilyMember",
                    FamilyMembersContract.class,
                    MainApp._HOST_URL + FamilyMembersContract.familyMembers._URL,
                    db.getUnsyncedFamilyMembers(), 1, uploadListAdapter, uploadlist
            ).execute();

            Toast.makeText(getApplicationContext(), "Syncing IM", Toast.LENGTH_SHORT).show();
            if (uploadlistActivityCreated){
                uploadmodel = new SyncModel();
                uploadmodel.setstatusID(0);
                uploadlist.add(uploadmodel);
            }
            new SyncAllData(
                    this,
                    "IM",
                    "updateIM",
                    SectionIIMContract.class,
                    MainApp._HOST_URL + SectionIIMContract.singleIm._URL,
                    db.getUnsyncedIM(),2,uploadListAdapter,uploadlist
            ).execute();



            uploadlistActivityCreated = false;

            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = syncPref.edit();

            editor.putString("LastUpSyncServer", dtToday);

            editor.apply();

        } else {
            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
        }

    }

    public void dbBackup() {

        sharedPref = getSharedPreferences("src", MODE_PRIVATE);
        editor = sharedPref.edit();

        if (sharedPref.getBoolean("flag", false)) {

            String dt = sharedPref.getString("dt", new SimpleDateFormat("dd-MM-yy").format(new Date()));

            if (dt != new SimpleDateFormat("dd-MM-yy").format(new Date())) {
                editor.putString("dt", new SimpleDateFormat("dd-MM-yy").format(new Date()));

                editor.commit();
            }

            File folder = new File(Environment.getExternalStorageDirectory() + File.separator + DatabaseHelper.PROJECT_NAME);
            boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdirs();
            }
            if (success) {

                DirectoryName = folder.getPath() + File.separator + sharedPref.getString("dt", "");
                folder = new File(DirectoryName);
                if (!folder.exists()) {
                    success = folder.mkdirs();
                }
                if (success) {

                    try {
                        File dbFile = new File(this.getDatabasePath(DatabaseHelper.DATABASE_NAME).getPath());
                        FileInputStream fis = new FileInputStream(dbFile);

                        String outFileName = DirectoryName + File.separator +
                                DatabaseHelper.DB_NAME;

                        // Open the empty db as the output stream
                        OutputStream output = new FileOutputStream(outFileName);

                        // Transfer bytes from the inputfile to the outputfile
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            output.write(buffer, 0, length);
                        }
                        // Close the streams
                        output.flush();
                        output.close();
                        fis.close();
                    } catch (IOException e) {
                        Log.e("dbBackup:", e.getMessage());
                    }

                }

            } else {
                Toast.makeText(this, "Not create folder", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public class syncData extends AsyncTask<String, String, String> {

        private Context mContext;

        public syncData(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected String doInBackground(String... strings) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

//                  getting Enum Blocks
                    Toast.makeText(SyncActivity.this, "Sync Talukas", Toast.LENGTH_SHORT).show();

                    if (listActivityCreated) {
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "Talukas", syncListAdapter, list).execute();
                    bi.noItem.setVisibility(View.GONE);

//                  getting UCs!!
                    Toast.makeText(SyncActivity.this, "Sync UCs", Toast.LENGTH_SHORT).show();

                    if (listActivityCreated) {
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "UCs", syncListAdapter, list).execute();

//                   getting Areas
                    Toast.makeText(SyncActivity.this, "Sync Areas", Toast.LENGTH_SHORT).show();
                    if (listActivityCreated) {
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "Areas", syncListAdapter, list).execute();

//                   getting Villages
                    Toast.makeText(SyncActivity.this, "Sync Villages", Toast.LENGTH_SHORT).show();
                    if (listActivityCreated) {
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "Villages", syncListAdapter, list).execute();

//                   getting User
                    Toast.makeText(SyncActivity.this, "Sync Users", Toast.LENGTH_SHORT).show();
                    if (listActivityCreated) {
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "Users", syncListAdapter, list).execute();

//                    Getting App Version
                    Toast.makeText(SyncActivity.this, "Sync VersionApp", Toast.LENGTH_SHORT).show();
                    if (listActivityCreated) {
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "VersionApp", syncListAdapter, list).execute();
//                    new GetAllData(mContext, "FamilyMembers").execute();
                    listActivityCreated = false;
                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

//                    populateSpinner(mContext);

                    editor.putBoolean("flag", true);
                    editor.commit();

                    dbBackup();

                }
            }, 1200);
        }
    }

}
