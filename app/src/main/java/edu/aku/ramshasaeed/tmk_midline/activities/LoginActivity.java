package edu.aku.ramshasaeed.tmk_midline.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.aku.ramshasaeed.tmk_midline.R;
import edu.aku.ramshasaeed.tmk_midline.contracts.TalukasContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.UCsContract;
import edu.aku.ramshasaeed.tmk_midline.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline.databinding.ActivityLoginBinding;

import static java.lang.Thread.sleep;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements LoaderCallbacks<Cursor> {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "test1234:test1234", "testS12345:testS12345", "bar@example.com:world"
    };
    // Spinners
    ArrayAdapter<String> dataAdapter;

    ArrayList<String> lablesTalukas;
    Collection<TalukasContract> TalukasList;
    Map<String, String> talukasMap;

    ArrayList<String> lablesUCs;
    Collection<UCsContract> UcsList;
    Map<String, String> ucsMap;


    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    String DirectoryName;

    DatabaseHelper db;

    private UserLoginTask mAuthTask = null;
    ActivityLoginBinding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_login);
        bi.setCallback(this);
//        ButterKnife.bind(this);

        try {
            long installedOn = this
                    .getPackageManager()
                    .getPackageInfo("edu.aku.ramshasaeed.tmk_midline", 0)
                    .lastUpdateTime;
            MainApp.versionCode = this
                    .getPackageManager()
                    .getPackageInfo("edu.aku.ramshasaeed.tmk_midline", 0)
                    .versionCode;
            MainApp.versionName = this
                    .getPackageManager()
                    .getPackageInfo("edu.aku.ramshasaeed.tmk_midline", 0)
                    .versionName;
            bi.txtinstalldate.setText("Ver. " + MainApp.versionName + "." + String.valueOf(MainApp.versionCode) + " \r\n( Last Updated: " + new SimpleDateFormat("dd MMM. yyyy").format(new Date(installedOn)) + " )");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        // Set up the login form.
//        mEmailView = findViewById(R.id.email);
        populateAutoComplete();
        settingIMEI();
        Target viewTarget = new ViewTarget(R.id.syncData, this);

        new ShowcaseView.Builder(this)
                .setTarget(viewTarget)
                .setStyle(R.style.CustomShowcaseTheme)
                .setContentText("\n\nPlease Sync Data before login...")
                .singleShot(42)
                .build();

//        mPasswordView = findViewById(R.id.password);
        bi.password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        db = new DatabaseHelper(this);

        populateSpinner(this);

//        DB backup

        dbBackup();


//        Testing visibility
        if (Integer.valueOf(MainApp.versionName.split("\\.")[0]) > 0) {
            bi.testing.setVisibility(View.GONE);
        } else {
            bi.testing.setVisibility(View.VISIBLE);
        }
    }

    private void settingIMEI() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        MainApp.IMEI = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();

    }

    @Override
    protected void onStart() {
        super.onStart();
        populateSpinner(this);
    }

    public void populateSpinner(Context context) {

        final Context mContext = context;

        // Populate Talukas list
        TalukasList = db.getAllTalukas();

        lablesTalukas = new ArrayList<>();
        talukasMap = new HashMap<>();

        lablesTalukas.add("Select Taluka...");

        for (TalukasContract taluka : TalukasList) {
            lablesTalukas.add(taluka.getTaluka());

            talukasMap.put(taluka.getTaluka(), taluka.getTalukacode());
        }

        bi.spTaluka.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_dropdown_item, lablesTalukas));

        bi.spTaluka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Populate UCs list

                if (bi.spTaluka.getSelectedItemPosition() != 0) {
                    MainApp.talukaCode = Integer.valueOf(talukasMap.get(bi.spTaluka.getSelectedItem().toString()));
                }

                lablesUCs = new ArrayList<>();
                ucsMap = new HashMap<>();
                lablesUCs.add("Select UC..");

                if (bi.spTaluka.getSelectedItemPosition() != 0) {
                    UcsList = db.getAllUCs(String.valueOf(MainApp.talukaCode));
                    for (UCsContract ucs : UcsList) {
                        lablesUCs.add(ucs.getUcs());
                        ucsMap.put(ucs.getUcs(), ucs.getUccode());
                    }
                }

                bi.spUCs.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_dropdown_item, lablesUCs));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bi.spUCs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Populate UCs list

                if (bi.spUCs.getSelectedItemPosition() != 0) {
                    MainApp.ucCode = Integer.valueOf(ucsMap.get(bi.spUCs.getSelectedItem().toString()));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void dbBackup() {

        sharedPref = getSharedPreferences("dss01", MODE_PRIVATE);
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

    public void onSyncDataClick() {
        //TODO implement

        // Require permissions INTERNET & ACCESS_NETWORK_STATE
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

//            if (TalukasList.size() == 0) {
            new syncData(this, true).execute();
//            } else {
            /*if (spTalukas.getSelectedItemPosition() != 0
                        &&
                        spUCs.getSelectedItemPosition() != 0) {
                    new syncData(this, false).execute();
                }*/
//            }
        } else {
            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
        }
    }

    private void populateAutoComplete() {
        getLoaderManager().initLoader(0, null, this);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        bi.email.setError(null);
        bi.password.setError(null);

        // Store values at the time of the login attempt.
        String email = bi.email.getText().toString();
        String password = bi.password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            bi.password.setError(getString(R.string.error_invalid_password));
            focusView = bi.password;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            bi.email.setError(getString(R.string.error_field_required));
            focusView = bi.email;
            cancel = true;
        } /*else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }*/

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);


        }

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 7;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
/*
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
            */
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
           /* mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);*/
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    //    @OnClick(R.id.showPassword)
    public void onShowPasswordClick() {
        //TODO implement
        if (bi.password.getTransformationMethod() == null) {
            bi.password.setTransformationMethod(new PasswordTransformationMethod());
            bi.password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_black_24dp, 0, 0, 0);
        } else {
            bi.password.setTransformationMethod(null);
            bi.password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_open_black_24dp, 0, 0, 0);
        }
    }

    public void gotoMain() {

//        finish();
        if (bi.spUCs.getSelectedItemPosition() != 0 && bi.spTaluka.getSelectedItemPosition() != 0) {
            attemptLogin();
        } else {
            Toast.makeText(getApplicationContext(), "Please Sync Data or select from combobox!!", Toast.LENGTH_LONG).show();
        }
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }


        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                DatabaseHelper db = new DatabaseHelper(LoginActivity.this);
                if ((mEmail.equals("dmu@aku") && mPassword.equals("aku?dmu")) || db.Login(mEmail, mPassword)
                        || (mEmail.equals("test1234") && mPassword.equals("test1234"))) {
                    MainApp.userName = mEmail;
                    MainApp.admin = mEmail.contains("@");

                    Intent iLogin = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(iLogin);

                } else {
                    bi.password.setError(getString(R.string.error_incorrect_password));
                    bi.password.requestFocus();
                    Toast.makeText(LoginActivity.this, mEmail + " " + mPassword, Toast.LENGTH_SHORT).show();
                }
            } else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        LoginActivity.this);
                alertDialogBuilder
                        .setMessage("GPS is disabled in your device. Enable it?")
                        .setCancelable(false)
                        .setPositiveButton("Enable GPS",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        Intent callGPSSettingIntent = new Intent(
                                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                        startActivity(callGPSSettingIntent);
                                    }
                                });
                alertDialogBuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();

            }

        }


        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

    public class syncData extends AsyncTask<String, String, String> {

        Boolean flag = false;
        private Context mContext;

        public syncData(Context mContext, Boolean flag) {
            this.mContext = mContext;
            this.flag = flag;
        }

        @Override
        protected String doInBackground(String... strings) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    if (flag) {
                        startActivity(new Intent(LoginActivity.this, SyncActivity.class));
                      /*  Toast.makeText(LoginActivity.this, "Sync Talukas", Toast.LENGTH_LONG).show();
                        new GetTalukas(mContext).execute();
                        Toast.makeText(LoginActivity.this, "Sync UC's", Toast.LENGTH_LONG).show();
                        new GetUCs(mContext).execute();
                        Toast.makeText(LoginActivity.this, "Sync Areas", Toast.LENGTH_LONG).show();
                        new GetAreas(mContext).execute();
                        Toast.makeText(LoginActivity.this, "Sync Villages", Toast.LENGTH_LONG).show();
                        new GetVillages(mContext).execute();
                        Toast.makeText(LoginActivity.this, "Sync User", Toast.LENGTH_LONG).show();
                        new GetUsers(mContext).execute();*/


                    } else {
                        Toast.makeText(LoginActivity.this, "Sync BL Random", Toast.LENGTH_LONG).show();
                        //  new GetBLRandom(mContext).execute();
                    }
                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    if (flag) {
                        populateSpinner(mContext);
                    }
                    editor.putBoolean("flag", true);
                    editor.commit();

                    dbBackup();

                }
            }, 1200);
        }
    }


}

