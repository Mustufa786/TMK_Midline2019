package edu.aku.ramshasaeed.tmk_midline.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.aku.ramshasaeed.tmk_midline.R;
import edu.aku.ramshasaeed.tmk_midline.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline.databinding.ActivitySectionEBinding;
import edu.aku.ramshasaeed.tmk_midline.validation.ClearClass;
import edu.aku.ramshasaeed.tmk_midline.validation.ValidatorClasss;

public class SectionEActivity extends AppCompatActivity {

    ActivitySectionEBinding bi;

    Map<String, String> childsMap;
    ArrayList<String> lstChild;
    String MotherName, MotherID;

    int count_child_5y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_e);
        bi.setCallback(this);
        this.setTitle(getResources().getString(R.string.thaheading));
        ValidatorClasss.setScrollViewFocus(bi.svsece);

        settingAdapter();


    }

    private void settingAdapter() {
        //        get data from sec B

        childsMap = new HashMap<>();
        lstChild = new ArrayList<>();

        childsMap.put("....", "");
        lstChild.add("....");


        for (byte i = 0; i < MainApp.familyMembersList.size(); i++) {
            int Age = Integer.parseInt(MainApp.familyMembersList.get(i).getage());
            if (Age < 5) {
                childsMap.put(MainApp.familyMembersList.get(i).getname(), MainApp.familyMembersList.get(i).getserialNo());
                lstChild.add(MainApp.familyMembersList.get(i).getname());
//                MotherName =  MainApp.familyMembersList.get(i).getmotherId();
                count_child_5y++;
            }
        }

        bi.tha03.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lstChild));
        bi.tha03.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    MotherID = MainApp.familyMembersList.get(Integer.valueOf(childsMap.get(bi.tha03.getSelectedItem().toString())) - 1).getmotherId();
                    for (int j = 0; j < MainApp.familyMembersList.size(); j++) {
                        if (MainApp.familyMembersList.get(j).getserialNo().equals(MotherID)) {
                            MotherName = MainApp.familyMembersList.get(j).getname();
                        }
                    }
                    bi.motherName.setText(MotherName);
                } else {
                    bi.motherName.setText(null);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bi.tha01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.tha01a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpll02, null);

            }
        });

        bi.tha06.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == bi.tha06a.getId()) {
                    bi.fldgrpte07.setVisibility(View.GONE);
                    ClearClass.ClearAllFields(bi.tha07, null);

                    bi.fldGrpll08.setVisibility(View.VISIBLE);
                } else {
                    bi.fldgrpte07.setVisibility(View.VISIBLE);

                    bi.fldGrpll08.setVisibility(View.GONE);
                    ClearClass.ClearAllFields(bi.fldGrpll08, null);
                }

            }
        });

        bi.tha13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.tha13a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpll14, null);
            }
        });

        bi.tha20.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.tha20c.getId())
                    ClearClass.ClearAllFields(bi.fldgrpte25, null);
            }
        });

        bi.tha32.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.tha32b.getId())
                    ClearClass.ClearAllFields(bi.fldGrpll20, null);
            }
        });

        bi.tha33.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.tha33b.getId())
                    ClearClass.ClearAllFields(bi.fldgrpte34, null);
            }
        });

    }

    public void BtnContinue() {

        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (UpdateDB()) {
                Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();

                finish();

                Intent secNext = new Intent(this, SectionFActivity.class);
                startActivity(secNext);
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSE();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject sE = new JSONObject();

        sE.put("tha01", bi.tha01a.isChecked() ? "1"
                : bi.tha01b.isChecked() ? "2"
                : bi.tha0198.isChecked() ? "98" : "0");

        sE.put("tha02", bi.tha02.getText().toString());
        if (bi.tha01a.isChecked()) {
            sE.put("tha03", bi.tha03.getSelectedItem().toString().equals("....") ? "97" : bi.tha03.getSelectedItem().toString());
            sE.put("tha03serial", childsMap.get(bi.tha03.getSelectedItem().toString()));
        }
        sE.put("tha03mname", bi.motherName.getText().toString());
        sE.put("tha04", bi.tha04.getText().toString());

        sE.put("tha05", bi.tha05a.isChecked() ? "1"
                : bi.tha05b.isChecked() ? "2"
                : bi.tha0598.isChecked() ? "98" : "0");

        sE.put("tha06", bi.tha06a.isChecked() ? "1"
                : bi.tha06b.isChecked() ? "2"
                : bi.tha0698.isChecked() ? "98" : "0");

        sE.put("tha07a", bi.tha07a.isChecked() ? "1" : "0");
        sE.put("tha07b", bi.tha07b.isChecked() ? "2" : "0");
        sE.put("tha07c", bi.tha07c.isChecked() ? "3" : "0");
        sE.put("tha07d", bi.tha07d.isChecked() ? "4" : "0");
        sE.put("tha07e", bi.tha07e.isChecked() ? "5" : "0");
        sE.put("tha07f", bi.tha07f.isChecked() ? "6" : "0");
        sE.put("tha07g", bi.tha07g.isChecked() ? "7" : "0");
        sE.put("tha07h", bi.tha07h.isChecked() ? "8" : "0");
        sE.put("tha0796", bi.tha0796.isChecked() ? "96" : "0");
        sE.put("tha0796x", bi.tha0796x.getText().toString());

        sE.put("tha08", bi.tha08.getText().toString());

        sE.put("tha09", bi.tha09a.isChecked() ? "1"
                : bi.tha09b.isChecked() ? "2"
                : bi.tha09c.isChecked() ? "3"
                : bi.tha09d.isChecked() ? "4"
                : "0");
        sE.put("tha10", bi.tha10a.isChecked() ? "1"
                : bi.tha10b.isChecked() ? "2"
                : bi.tha10c.isChecked() ? "3"
                : bi.tha10d.isChecked() ? "4"
                : bi.tha10e.isChecked() ? "5"
                : bi.tha10f.isChecked() ? "6"
                : bi.tha10g.isChecked() ? "7"
                : bi.tha10h.isChecked() ? "8"
                : bi.tha10i.isChecked() ? "9"
                : bi.tha10j.isChecked() ? "10"
                : bi.tha10k.isChecked() ? "11"
                : "0");

       /* sE.put("tha11", bi.tha11a.isChecked() ? "1"
                : bi.tha11b.isChecked() ? "2"
                : bi.tha11c.isChecked() ? "3"
                : bi.tha11d.isChecked() ? "4"
                : bi.tha11e.isChecked() ? "5"
                : bi.tha11f.isChecked() ? "6"
                : bi.tha11g.isChecked() ? "7"
                : bi.tha11h.isChecked() ? "8"
                : bi.tha11i.isChecked() ? "9"
                : bi.tha11j.isChecked() ? "10"
                : "0");*/
       //sholud be multiple response
        sE.put("tha11a", bi.tha11a.isChecked() ? "1" : "0");
        sE.put("tha11b", bi.tha11b.isChecked() ? "2" : "0");
        sE.put("tha11c", bi.tha11c.isChecked() ? "3" : "0");
        sE.put("tha11d", bi.tha11d.isChecked() ? "4" : "0");
        sE.put("tha11e", bi.tha11e.isChecked() ? "5" : "0");
        sE.put("tha11f", bi.tha11f.isChecked() ? "6" : "0");
        sE.put("tha11g", bi.tha11g.isChecked() ? "7" : "0");
        sE.put("tha11h", bi.tha11h.isChecked() ? "8" : "0");
        sE.put("tha11i", bi.tha11i.isChecked() ? "9" : "0");
        sE.put("tha11j", bi.tha11j.isChecked() ? "10" : "0");

        sE.put("tha12", bi.tha12a.isChecked() ? "1"
                : bi.tha12b.isChecked() ? "2"
                : "0");
        sE.put("tha13", bi.tha13a.isChecked() ? "1"
                : bi.tha13b.isChecked() ? "2"
                : "0");
        sE.put("tha14", bi.tha14a.isChecked() ? "1"
                : bi.tha14b.isChecked() ? "2"
                : bi.tha14c.isChecked() ? "3"
                : "0");
        sE.put("tha18", bi.tha18a.isChecked() ? "1"
                : bi.tha18b.isChecked() ? "2"
                : bi.tha18c.isChecked() ? "3"
                : bi.tha18d.isChecked() ? "4"
                : bi.tha18e.isChecked() ? "5"
                : "0");

        sE.put("tha19a", bi.tha19a.isChecked() ? "1" : "0");
        sE.put("tha19b", bi.tha19b.isChecked() ? "2" : "0");
        sE.put("tha19c", bi.tha19c.isChecked() ? "3" : "0");
        sE.put("tha19d", bi.tha19d.isChecked() ? "4" : "0");
        sE.put("tha19e", bi.tha19e.isChecked() ? "5" : "0");
        sE.put("tha19f", bi.tha19f.isChecked() ? "6" : "0");
        sE.put("tha19g", bi.tha19g.isChecked() ? "7" : "0");
        sE.put("tha19h", bi.tha19h.isChecked() ? "8" : "0");

        sE.put("tha20", bi.tha20a.isChecked() ? "1"
                : bi.tha20b.isChecked() ? "2"
                : bi.tha20c.isChecked() ? "3"
                : "0");

        sE.put("tha20hr", bi.tha20hr.getText().toString());
        sE.put("tha20d", bi.tha20day.getText().toString());


        sE.put("tha25", bi.tha25a.isChecked() ? "1"
                : bi.tha25b.isChecked() ? "2"
                : bi.tha25c.isChecked() ? "2"
                : "0");
        sE.put("tha32", bi.tha32a.isChecked() ? "1"
                : bi.tha32b.isChecked() ? "2"
                : "0");
        sE.put("tha33", bi.tha33a.isChecked() ? "1"
                : bi.tha33b.isChecked() ? "2"
                : "0");

        sE.put("tha34", bi.tha34a.isChecked() ? "1"
                : bi.tha34b.isChecked() ? "2"
                : bi.tha34c.isChecked() ? "3"
                : bi.tha34d.isChecked() ? "4"
                : bi.tha34e.isChecked() ? "5"
                : bi.tha34f.isChecked() ? "6"
                : bi.tha34g.isChecked() ? "7"
                : bi.tha34h.isChecked() ? "8"
                : bi.tha34i.isChecked() ? "9"
                : bi.tha34j.isChecked() ? "10"
                : bi.tha34k.isChecked() ? "11"
                : "0");

        MainApp.fc.setsE(String.valueOf(sE));
    }

    private boolean formValidation() {
        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();
        if (!ValidatorClasss.EmptyRadioButton(this, bi.tha01, bi.tha01a, getString(R.string.tha01))) {
            return false;
        }

        if (bi.tha01a.isChecked()) {
            if (!ValidatorClasss.EmptyTextBox(this, bi.tha02, getString(R.string.tha02))) {
                return false;
            }
            if (!ValidatorClasss.EmptySpinner(this, bi.tha03, getString(R.string.tha03))) {
                return false;
            }
            if (!ValidatorClasss.EmptyTextBox(this, bi.tha04, getString(R.string.tha04))) {
                return false;
            }
            if (!ValidatorClasss.EmptyRadioButton(this, bi.tha05, bi.tha05a, getString(R.string.tha05))) {
                return false;
            }
            if (!ValidatorClasss.EmptyRadioButton(this, bi.tha06, bi.tha06a, getString(R.string.tha06))) {
                return false;
            }
            if (!bi.tha06a.isChecked()) {
                if (!ValidatorClasss.EmptyCheckBox(this, bi.tha07, bi.tha0796, bi.tha0796x, getString(R.string.tha07))) {
                    return false;
                }

            } else {
                if (!ValidatorClasss.EmptyTextBox(this, bi.tha08, getString(R.string.tha08))) {
                    return false;
                }
                if (!ValidatorClasss.EmptyRadioButton(this, bi.tha09, bi.tha09a, getString(R.string.tha09))) {
                    return false;
                }
                if (!ValidatorClasss.EmptyRadioButton(this, bi.tha10, bi.tha10a, getString(R.string.tha10))) {
                    return false;
                }
                if (!ValidatorClasss.EmptyCheckBox(this, bi.tha11, bi.tha11a, getString(R.string.tha11))) {
                    return false;
                }
                if (!ValidatorClasss.EmptyRadioButton(this, bi.tha12, bi.tha12a, getString(R.string.tha12))) {
                    return false;
                }
                if (!ValidatorClasss.EmptyRadioButton(this, bi.tha13, bi.tha13a, getString(R.string.tha13))) {
                    return false;
                }
                if (bi.tha13a.isChecked()) {
                    if (!ValidatorClasss.EmptyRadioButton(this, bi.tha14, bi.tha14a, getString(R.string.tha14))) {
                        return false;
                    }
                    if (!ValidatorClasss.EmptyRadioButton(this, bi.tha18, bi.tha18a, getString(R.string.tha18))) {
                        return false;
                    }
                    if (!ValidatorClasss.EmptyCheckBox(this, bi.tha19, bi.tha19a, getString(R.string.tha19))) {
                        return false;
                    }
                    if (!ValidatorClasss.EmptyRadioButton(this, bi.tha20, bi.tha20a, bi.tha20hr, getString(R.string.tha20))) {
                        return false;
                    }
                    if (bi.tha20a.isChecked()) {
                        if (!ValidatorClasss.EmptyRadioButton(this, bi.tha20, bi.tha20a, bi.tha20hr, getString(R.string.tha20))) {
                            return false;
                        }
                    } else if (bi.tha20b.isChecked()) {
                        if (!ValidatorClasss.EmptyRadioButton(this, bi.tha20, bi.tha20b, bi.tha20day, getString(R.string.tha20))) {
                            return false;
                        }
                    }
                    if (!bi.tha20c.isChecked()) {

                        if (!ValidatorClasss.EmptyRadioButton(this, bi.tha25, bi.tha25a, getString(R.string.tha25))) {
                            return false;
                        }
                    }
                }
            }
        }
        if (!ValidatorClasss.EmptyRadioButton(this, bi.tha32, bi.tha32a, getString(R.string.tha32))) {
            return false;
        }
        if (bi.tha32a.isChecked()) {

            if (!ValidatorClasss.EmptyRadioButton(this, bi.tha33, bi.tha33a, getString(R.string.tha33))) {
                return false;
            }
            if (bi.tha33a.isChecked()) {
                return ValidatorClasss.EmptyRadioButton(this, bi.tha34, bi.tha34a, getString(R.string.tha34));
            }
        }
        return true;
    }

    public void BtnEnd() {
        MainApp.endActivity(this, this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
