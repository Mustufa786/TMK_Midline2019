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
import android.widget.CompoundButton;
import android.widget.RadioGroup;
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
import edu.aku.ramshasaeed.tmk_midline.validation.ClearClass;
import edu.aku.ramshasaeed.tmk_midline.validation.ValidatorClasss;

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
        ValidatorClasss.setScrollViewFocus(bi.svseca);
        db = new DatabaseHelper(this);
        MainApp.members_f_m = new ArrayList<>();

        MainApp.familyMembersList = new ArrayList<>();
        MainApp.childUnder2 = new ArrayList<>();
        MainApp.childUnder5 = new ArrayList<>();
        MainApp.serial_no = 0;

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

                if (bi.spSubVillages.getSelectedItemPosition() != 0) {
                    MainApp.cluster = SubVillagesMap.get(bi.spSubVillages.getSelectedItem().toString());

                    bi.ta01.setText(MainApp.cluster);

                    bi.ta06.setText(bi.spSubVillages.getSelectedItem().toString());
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
                /*if (i == R.id.ta09a) {
                    bi.btnContinue.setVisibility(View.VISIBLE);
                    bi.fldGrpRespInfo.setVisibility(View.VISIBLE);
                    bi.btnEnd.setVisibility(View.GONE);
                } else {
                    bi.btnContinue.setVisibility(View.GONE);
                    bi.fldGrpRespInfo.setVisibility(View.GONE);
                    bi.tc03.setText(null);
                    bi.tc04.clearCheck();
                    bi.tc05.setText(null);
                    bi.btnEnd.setVisibility(View.VISIBLE);
                }*/

                if (i != bi.ta09a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpRespInfo, null);

            }
        });

        bi.ta05h.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bi.fldGrpt03.setVisibility(View.GONE);
                bi.hhName.setText(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//        Checkbox validate
        bi.checkHHHeadpresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.fldGrpt03a.setVisibility(View.GONE);
                    bi.newHHheadname.setText(null);
                } else {
                    bi.fldGrpt03a.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void onCheckHHClick() {

        if (formValidation(false)) {
            if (!db.checkFormAlreadyFilled(bi.ta01.getText().toString(), bi.ta05h.getText().toString().toUpperCase())) {

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
                Toast.makeText(this, "This form is already filled!!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Not found.", Toast.LENGTH_SHORT).show();
        }


    }

    public void onBtnEndClick() {
        if (formValidation(true)) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {

                finish();
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onBtnContinueClick() {
        if (formValidation(true)) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {

                finish();

                startActivity(new Intent(this, SectionBActivity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void SaveDraft() throws JSONException {

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

        MainApp.fc = new FormsContract();

        MainApp.fc.setdevicetagID(sharedPref.getString("tagName", null));
        MainApp.fc.setformDate(dtToday);
        MainApp.fc.setuser(MainApp.userName);
        MainApp.fc.setdeviceid(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setappVer(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fc.setcluster_no(bi.ta01.getText().toString());
        MainApp.fc.sethhno(bi.ta05h.getText().toString());

        JSONObject sa = new JSONObject();
        MainApp.cluster = bi.ta01.getText().toString();
        MainApp.hhno = bi.ta05h.getText().toString();

//        MainApp.billno = bi.ta05u.getText().toString();

        sa.put("rndid", MainApp.selectedHead.get_ID());
        sa.put("luid", MainApp.selectedHead.getLUID());
        sa.put("randDT", MainApp.selectedHead.getRandomDT());
        sa.put("hh03", MainApp.selectedHead.getStructure());
        sa.put("hh07", MainApp.selectedHead.getExtension());
        sa.put("hhhead", MainApp.selectedHead.getHhhead());
        sa.put("hhheadpresent", bi.checkHHHeadpresent.isChecked() ? "1" : "2");
        sa.put("hhheadpresentnew", bi.newHHheadname.getText().toString());

        sa.put("ta01", bi.ta01.getText().toString());
        sa.put("ta02", bi.ta02a.isChecked() ? "1" : bi.ta02b.isChecked() ? "2" : bi.ta02c.isChecked() ? "3" : "0");
        sa.put("ta03", MainApp.talukaCode);
        sa.put("ta04", MainApp.ucCode);
        sa.put("ta04A", MainApp.areaCode);
        sa.put("ta05h", bi.ta05h.getText().toString());
        sa.put("tc03", bi.tc03.getText().toString());
        sa.put("tc04", bi.tc04a.isChecked() ? "1" : bi.tc04b.isChecked() ? "2" : "0");
        sa.put("tc05", bi.tc05.getText().toString());
//        sa.put("ta05u", bi.ta05u.getText().toString());
        sa.put("ta06", bi.ta06.getText().toString());
        sa.put("ta07", bi.ta07.getText().toString());
        sa.put("ta08", bi.ta08.getText().toString());
        sa.put("ta09", bi.ta09a.isChecked() ? "1" : bi.ta09b.isChecked() ? "2" : bi.ta09c.isChecked() ? "3" : "0");
       /* sa.put("ta10", bi.ta10a.isChecked() ? "1" : bi.ta10b.isChecked() ? "2" : "0");
        sa.put("ta11", bi.ta11.getText().toString());*/
//        sa.put("app_version", MainApp.versionName + "." + MainApp.versionCode);

        MainApp.fc.setsA(String.valueOf(sa));
        setGPS();

    }

    private boolean UpdateDB() {

        Long updcount = db.addForm(MainApp.fc);
        MainApp.fc.set_ID(String.valueOf(updcount));

        if (updcount != 0) {

            MainApp.fc.set_UID(
                    (MainApp.fc.getdeviceid() + MainApp.fc.get_ID()));
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

            MainApp.fc.setgpsLat(GPSPref.getString("Latitude", "0"));
            MainApp.fc.setgpsLng(GPSPref.getString("Longitude", "0"));
            MainApp.fc.setgpsAcc(GPSPref.getString("Accuracy", "0"));
//            AppMain.fc.setGpsTime(GPSPref.getString(date, "0")); // Timestamp is converted to date above
            MainApp.fc.setgpsDT(date); // Timestamp is converted to date above

            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }

    public boolean formValidation(boolean flag) {

        if (!ValidatorClasss.EmptyTextBox(this, bi.ta01, getString(R.string.ta01))) {
            return false;
        }
        if (!ValidatorClasss.EmptyTextBox(this, bi.ta05h, getString(R.string.ta05h))) {
            return false;
        }
        if (!ValidatorClasss.PatternTextBox(this, bi.ta05h, getString(R.string.ta05h), "[0-9]{3,3}-[^0-9]{1,1}", "XXX-X")) {
            return false;
        }

        if (flag) {
            if (!bi.checkHHHeadpresent.isChecked()) {
                if (!ValidatorClasss.EmptyTextBox(this, bi.newHHheadname, "New Head Name")) {
                    return false;
                }
            }
            if (!ValidatorClasss.EmptyRadioButton(this, bi.ta02, bi.ta02a, getString(R.string.ta02))) {
                return false;
            }
            if (!ValidatorClasss.EmptyTextBox(this, bi.ta06, getString(R.string.ta06))) {
                return false;
            }
            if (!ValidatorClasss.EmptyTextBox(this, bi.ta07, getString(R.string.ta07))) {
                return false;
            }
            if (!ValidatorClasss.EmptyTextBox(this, bi.ta08, getString(R.string.ta08))) {
                return false;
            }
            if (!ValidatorClasss.EmptyRadioButton(this, bi.ta09, bi.ta09a, getString(R.string.ta09))) {
                return false;
            }
            if (bi.ta09a.isChecked()) {
                if (!ValidatorClasss.EmptyTextBox(this, bi.tc03, getString(R.string.tc03))) {
                    return false;
                }
                if (!ValidatorClasss.EmptyRadioButton(this, bi.tc04, bi.tc04a, getString(R.string.tc04))) {
                    return false;
                }
                if (!ValidatorClasss.EmptyTextBox(this, bi.tc05, getString(R.string.tc05))) {
                    return false;
                }
                if (!ValidatorClasss.RangeTextBox(this, bi.tc05, 14, 99, getString(R.string.tc05), " years")) {
                    return false;
                }
                int ageofResp = Integer.parseInt(bi.tc05.getText().toString());
                if (ageofResp < 14) {
                    Toast.makeText(this, "Respondant age must be geater then 14 years", Toast.LENGTH_LONG);
                    bi.tc05.setError("Respondant age must be geater then 14 years");
                    return false;
                } else {
                    bi.tc05.setError(null);
                }
                /*
                if (!ValidatorClasss.EmptyRadioButton(this, bi.ta10, bi.ta10a, getString(R.string.ta10))) {
                    return false;
                }
                if (!ValidatorClasss.EmptyTextBox(this, bi.ta11, getString(R.string.ta11))) {
                    return false;
                }
                return ValidatorClasss.RangeTextBox(this, bi.ta11, 1, 25, getString(R.string.ta11), " Under 5");*/

            }

        }

        return true;
    }
}

