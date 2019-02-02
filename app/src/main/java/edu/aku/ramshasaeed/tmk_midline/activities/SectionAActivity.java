package edu.aku.ramshasaeed.tmk_midline.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
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
import edu.aku.ramshasaeed.tmk_midline.databinding.ActivitySectionABinding;
import edu.aku.ramshasaeed.tmk_midline.validation.validatorClass;

public class SectionAActivity extends Activity {
    ActivitySectionABinding bi;
    private static final String TAG = SectionAActivity.class.getName();
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());
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
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_a);
        bi.setCallback(this);
        validatorClass.setScrollViewFocus(bi.svseca);
        db = new DatabaseHelper(this);

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

        bi.spSubVillages.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lablesSubVillages));

        bi.spSubVillages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if ( bi.spSubVillages.getSelectedItemPosition() != 0) {
                    MainApp.cluster = SubVillagesMap.get( bi.spSubVillages.getSelectedItem().toString());

                    bi.ta01.setText(MainApp.cluster);

                    bi.ta06.setText( bi.spSubVillages.getSelectedItem().toString());
                } else {

                    bi.ta01.setText(null);

                    bi.ta06.setText("N/A");

                    bi.fldGrpt03.setVisibility(View.GONE);

                    bi.hhName.setText(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        bi.ta09.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.ta09a) {
                    bi.btnContinue.setVisibility(View.VISIBLE);
                    bi.btnEnd.setVisibility(View.GONE);
                } else {
                    bi.btnContinue.setVisibility(View.GONE);
                    bi.btnEnd.setVisibility(View.VISIBLE);
                }
            }
        });
/*
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

    public void onCheckHHClick() {

        if (!bi.ta01.getText().toString().trim().isEmpty() && !bi.ta05h.getText().toString().trim().isEmpty()) {

            selected = db.getAllBLRandom(bi.ta01.getText().toString(), bi.ta05h.getText().toString().toUpperCase());

            if (selected.size() != 0) {

                for (BLRandomContract rnd : selected) {
                    MainApp.selectedHead = new BLRandomContract(rnd);
                }

                bi.hhName.setText(MainApp.selectedHead.getHhhead().toUpperCase());

                bi.fldGrpt03.setVisibility(View.VISIBLE);
            } else {
                bi.hhName.setText(null);

                bi.fldGrpt03.setVisibility(View.GONE);

                Toast.makeText(this, "No Head found in this HH.", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Not found.", Toast.LENGTH_SHORT).show();
        }


    }

    void onBtnEndClick() {
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

    public void onBtnContinueClick() {
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

                startActivity(new Intent(this, SectionDActivity.class));

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

        return true;
    }
}

