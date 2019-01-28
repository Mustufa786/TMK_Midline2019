package edu.aku.ramshasaeed.tmk_midline.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.aku.ramshasaeed.tmk_midline.R;
import edu.aku.ramshasaeed.tmk_midline.contracts.BLRandomContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.FormsContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.VillagesContract;
import edu.aku.ramshasaeed.tmk_midline.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline.core.MainApp;

public class SectionAActivity extends Activity {

    private static final String TAG = SectionAActivity.class.getName();
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    Boolean flag;
/*
    @BindView(R.id.ta01)
    EditText ta01;
    @BindView(R.id.ta02)
    RadioGroup ta02;
    @BindView(R.id.ta02a)
    RadioButton ta02a;
    @BindView(R.id.ta02b)
    RadioButton ta02b;
    @BindView(R.id.ta02c)
    RadioButton ta02c;*/
    /*    @BindView(R.id.ta03)
        RadioGroup ta03;
        @BindView(R.id.ta03a)
        RadioButton ta03a;
        @BindView(R.id.ta03b)
        RadioButton ta03b;
        @BindView(R.id.ta03c)
        RadioButton ta03c;
        @BindView(R.id.ta04)
        Spinner ta04;*/
   /* @BindView(R.id.ta05h)
    EditText ta05h;
    @BindView(R.id.ta05u)
    EditText ta05u;
    @BindView(R.id.ta06)
    EditText ta06;
    @BindView(R.id.ta07)
    EditText ta07;
    @BindView(R.id.ta08)
    EditText ta08;
    @BindView(R.id.ta09)
    RadioGroup ta09;
    @BindView(R.id.ta09a)
    RadioButton ta09a;
    @BindView(R.id.ta09b)
    RadioButton ta09b;
    @BindView(R.id.ta09c)
    RadioButton ta09c;
    @BindView(R.id.btn_Continue)
    Button btn_Continue;
    @BindView(R.id.btn_End)
    Button btn_End;

    @BindView(R.id.hh_name)
    TextView hhName;
    @BindView(R.id.checkHHHeadpresent)
    CheckBox checkHHHeadpresent;
    @BindView(R.id.fldGrpt03)
    LinearLayout fldGrpt03;*/

    Collection<BLRandomContract> selected;

    DatabaseHelper db;
    String[] ucs = new String[]{"....",
            "Allah Yar Turk",
            "Bulri Shah Karim",
            "Dando",
            "Ghulam Shah Bagrani",
            "Jinhan Soomro",
            "Lakhat",
            "Mullakatiar",
            "Nazarpur",
            "Shaikh Bhirkio",
            "Saeed Khan Lund",
            "Saeed Matto",
            "Saeedpur",
            "Tando Saeendad",
            "Tando Ghulam Hyder",
            "TMK 01",
            "TMK 02",
            "TMK 03"};


    int ucsPos = 0;

    Collection<VillagesContract> village;
    Map<String, String> villageMap;

    ArrayList<String> lablesSubVillages;
    Collection<VillagesContract> SubVillagesList;
    Map<String, String> SubVillagesMap;

    /*
        @BindView(R.id.spSubVillages)
        Spinner spSubVillages;

        @BindView(R.id.newHHheadname)
        EditText newHHheadname;

        @BindView(R.id.fldGrpt03a)
        LinearLayout fldGrpt03a;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_a);


        db = new DatabaseHelper(this);
        flag = getIntent().getExtras().getBoolean("flag");


        MainApp.familyMembersList = new ArrayList<>();

        lablesSubVillages = new ArrayList<>();
        SubVillagesMap = new HashMap<>();
        lablesSubVillages.add("Select Sub Village..");

        SubVillagesList = db.getVillage(String.valueOf(MainApp.areaCode));

        if (SubVillagesList.size() != 0) {
            for (VillagesContract vil : SubVillagesList) {
                lablesSubVillages.add(vil.getVillagename());
                SubVillagesMap.put(vil.getVillagename(), vil.getVillagecode());
            }
        }
/*
        spSubVillages.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lablesSubVillages));

        spSubVillages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (spSubVillages.getSelectedItemPosition() != 0) {
                    MainApp.cluster = SubVillagesMap.get(spSubVillages.getSelectedItem().toString());

                    ta01.setText(MainApp.cluster);

                    ta06.setText(spSubVillages.getSelectedItem().toString());
                } else {

                    ta01.setText(null);

                    ta06.setText("N/A");

                    fldGrpt03.setVisibility(View.GONE);

                    hhName.setText(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

/*
        ta09.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.ta09a) {
                    btn_Continue.setVisibility(View.VISIBLE);
                    btn_End.setVisibility(View.GONE);
                } else {
                    btn_Continue.setVisibility(View.GONE);
                    btn_End.setVisibility(View.VISIBLE);
                }
            }
        });

        ta05h.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                fldGrpt03.setVisibility(View.GONE);

                hhName.setText(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
*/
//        Checkbox validate
/*
        checkHHHeadpresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    fldGrpt03a.setVisibility(View.GONE);
                    newHHheadname.setText(null);
                } else {
                    fldGrpt03a.setVisibility(View.VISIBLE);
                }
            }
        });

*/
    }

    //    @OnClick(R.id.checkHH)
    void onCheckHHClick() {
        //TODO implement
/*

        if (!ta01.getText().toString().trim().isEmpty() && !ta05h.getText().toString().trim().isEmpty()) {

            selected = db.getAllBLRandom(ta01.getText().toString(), ta05h.getText().toString().toUpperCase());

            if (selected.size() != 0) {

                for (BLRandomContract rnd : selected) {
                    MainApp.selectedHead = new BLRandomContract(rnd);
                }

                hhName.setText(MainApp.selectedHead.getHhhead().toUpperCase());

                fldGrpt03.setVisibility(View.VISIBLE);
            } else {
                hhName.setText(null);

                fldGrpt03.setVisibility(View.GONE);

                Toast.makeText(this, "No Head found in this HH.", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Not found.", Toast.LENGTH_SHORT).show();
        }
*/

    }

    //    @OnClick(R.id.btn_End)
    void onBtnEndClick() {
        //TODO implement
        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();

                finish();

                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //    @OnClick(R.id.btn_Continue)
    void onBtnContinueClick() {
        //TODO implement

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();

                finish();

                if (flag) {
//                    startActivity(new Intent(this, SectionBActivity.class));
                } else {
//                    startActivity(new Intent(this, SectionMActivity.class));
                }
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

        MainApp.fc = new FormsContract();

        MainApp.fc.setDevicetagID(sharedPref.getString("tagName", null));
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));

        JSONObject sa = new JSONObject();
/*
        MainApp.cluster = ta01.getText().toString();
        MainApp.hhno = ta05h.getText().toString();
        MainApp.billno = ta05u.getText().toString();

        sa.put("rndid", MainApp.selectedHead.get_ID());
        sa.put("luid", MainApp.selectedHead.getLUID());
        sa.put("randDT", MainApp.selectedHead.getRandomDT());
        sa.put("hh03", MainApp.selectedHead.getStructure());
        sa.put("hh07", MainApp.selectedHead.getExtension());
        sa.put("hhhead", MainApp.selectedHead.getHhhead());
        sa.put("hhheadpresent", checkHHHeadpresent.isChecked() ? "1" : "2");
        sa.put("hhheadpresentnew", newHHheadname.getText().toString());

        sa.put("ta01", ta01.getText().toString());
        sa.put("ta02", ta02a.isChecked() ? "1" : ta02b.isChecked() ? "2" : ta02c.isChecked() ? "3" : "0");
        sa.put("ta03", MainApp.talukaCode);
        sa.put("ta04", MainApp.ucCode);
        sa.put("ta04A", MainApp.areaCode);
        sa.put("ta05h", ta05h.getText().toString());
        sa.put("ta05u", ta05u.getText().toString());
        sa.put("ta06", ta06.getText().toString());
        sa.put("ta07", ta07.getText().toString());
        sa.put("ta08", ta08.getText().toString());
        sa.put("ta09", ta09a.isChecked() ? "1" : ta09b.isChecked() ? "2" : ta09c.isChecked() ? "3" : "0");
        sa.put("app_version", MainApp.versionName + "." + MainApp.versionCode);

*/
        MainApp.fc.setsA(String.valueOf(sa));

        setGPS();

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {

        Long updcount = db.addForm(MainApp.fc);
        MainApp.fc.set_ID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.fc.setUID(
                    (MainApp.fc.getDeviceID() + MainApp.fc.get_ID()));
            db.updateFormID();

            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void setGPS() {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);

        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");
            String dt = GPSPref.getString("Time", "0");

            if (lat == "0" && lang == "0") {
                Toast.makeText(this, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            MainApp.fc.setGpsLat(GPSPref.getString("Latitude", "0"));
            MainApp.fc.setGpsLng(GPSPref.getString("Longitude", "0"));
            MainApp.fc.setGpsAcc(GPSPref.getString("Accuracy", "0"));
//            AppMain.fc.setGpsTime(GPSPref.getString(date, "0")); // Timestamp is converted to date above
            MainApp.fc.setGpsDT(date); // Timestamp is converted to date above

            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }
    }

    public boolean formValidation() {
        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

//        01
        /*
        if (ta01.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.ta01), Toast.LENGTH_SHORT).show();
            ta01.setError("This data is Required! ");    // Set Error on last radio button
            ta01.requestFocus();
            Log.i(TAG, "ta01: This data is Required!");
            return false;
        } else {
            ta01.setError(null);
        }
*/
//        03
/*        if (ta03.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.ta03), Toast.LENGTH_SHORT).show();
            ta03c.setError("This data is Required!");    // Set Error on last radio button
            ta03c.setFocusableInTouchMode(true);
            ta03c.setFocusable(true);
            ta03c.requestFocus();
            Log.i(TAG, "ta03: This data is Required!");
            return false;
        } else {
            ta03c.setError(null);
        }*/

//        04
/*        if (ta04.getSelectedItem() == "....") {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.ta04), Toast.LENGTH_SHORT).show();
            ((TextView) ta04.getSelectedView()).setText("This Data is Required");
            ((TextView) ta04.getSelectedView()).setTextColor(Color.RED);
            ta04.requestFocus();
            Log.i(TAG, "ta04: This Data is Required!");
            return false;
        } else {
            ((TextView) ta04.getSelectedView()).setError(null);
        }*/

//        05
        /*
        if (ta05h.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.ta05h), Toast.LENGTH_SHORT).show();
            ta05h.setError("This data is Required! ");    // Set Error on last radio button
            ta05h.requestFocus();
            Log.i(TAG, "ta05h: This data is Required!");
            return false;
        } else {
            ta05h.setError(null);
        }
*/
//        New HHHead
/*
        if (!checkHHHeadpresent.isChecked()) {
            if (newHHheadname.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): New head name", Toast.LENGTH_SHORT).show();
                newHHheadname.setError("This data is Required! ");    // Set Error on last radio button
                newHHheadname.requestFocus();
                Log.i(TAG, "newHHheadname: This data is Required!");
                return false;
            } else {
                newHHheadname.setError(null);
            }
        }
*/
//        02
        /*
        if (ta02.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.ta02), Toast.LENGTH_SHORT).show();
            ta02c.setError("This data is Required!");    // Set Error on last radio button

            ta02c.setFocusable(true);
            ta02c.setFocusableInTouchMode(true);
            ta02c.requestFocus();
            Log.i(TAG, "ta02: This data is Required!");
            return false;
        } else {
            ta02c.setError(null);
        }


        if (ta05u.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.ta05u), Toast.LENGTH_SHORT).show();
            ta05u.setError("This data is Required! ");    // Set Error on last radio button
            ta05u.requestFocus();
            Log.i(TAG, "ta05u: This data is Required!");
            return false;
        } else {
            ta05u.setError(null);
        }
*/
//        06
        /*
        if (ta06.getText().toString().equals("N/A")) {
            Toast.makeText(this, "ERROR(invalid): " + getString(R.string.ta06), Toast.LENGTH_SHORT).show();
            ta06.setError("Change cluster no! ");    // Set Error on last radio button
            ta06.requestFocus();
            Log.i(TAG, "ta06: Change cluster no!");
            return false;
        } else {
            ta06.setError(null);
        }
*/
//        07

/*        if (ta07.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.ta07), Toast.LENGTH_SHORT).show();
            ta07.setError("This data is Required! ");    // Set Error on last radio button

            Log.i(TAG, "ta07: This data is Required!");
            return false;
        } else {
            ta07.setError(null);
        } */

//        08

/*        if (ta08.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.ta08), Toast.LENGTH_SHORT).show();
            ta08.setError("This data is Required! ");    // Set Error on last radio button

            Log.i(TAG, "ta08: This data is Required!");
            return false;
        } else {
            ta08.setError(null);
        }*/

//        09
        /*
        if (ta09.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.ta09), Toast.LENGTH_SHORT).show();
            ta09c.setError("This data is Required!");    // Set Error on last radio button
            ta09c.setFocusable(true);
            ta09c.setFocusableInTouchMode(true);
            ta09c.requestFocus();
            Log.i(TAG, "ta09: This data is Required!");
            return false;
        } else {
            ta09c.setError(null);
        }

        return true;
    }
*/
        return true;
    }
}

