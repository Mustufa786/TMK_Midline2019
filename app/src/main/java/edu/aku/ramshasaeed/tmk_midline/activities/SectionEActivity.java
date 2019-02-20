package edu.aku.ramshasaeed.tmk_midline.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
import edu.aku.ramshasaeed.tmk_midline.validation.ValidatorClass;

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
        this.setTitle(getResources().getString(R.string.teheading));
        ValidatorClass.setScrollViewFocus(bi.svsece);

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

        bi.te03.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lstChild));
/*        bi.te03.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                  if(i != 0){
                                                      MotherID = MainApp.familyMembersList.get(Integer.valueOf(childsMap.get(lstChild.get(i)))).getmotherId();
                                                      for (int j = 0; j <MainApp.familyMembersList.size();j++){
                                                          if(MainApp.familyMembersList.get(j).getserialNo().equals(MotherID)){
                                                              MotherName =    MainApp.familyMembersList.get(j).getname();
                                                          }
                                                      }
                                                      bi.motherName.setText(MotherName);
                                                  }else{
                                                      bi.motherName.setText(null);

                                                  }

                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> adapterView) {

                                              }
                                          });*/

        bi.te01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.te01a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpll02, null);

            }
        });

        bi.te06.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == bi.te06a.getId()) {
                    bi.fldgrpte07.setVisibility(View.GONE);
                    ClearClass.ClearAllFields(bi.te07, null);

                    bi.fldGrpll08.setVisibility(View.VISIBLE);
                } else {
                    bi.fldgrpte07.setVisibility(View.VISIBLE);

                    bi.fldGrpll08.setVisibility(View.GONE);
                    ClearClass.ClearAllFields(bi.fldGrpll08, null);
                }

            }
        });

        bi.te13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.te13a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpll14, null);
            }
        });

        bi.te17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.te17c.getId())
                    ClearClass.ClearAllFields(bi.fldgrpte18, null);
            }
        });

        bi.te19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.te19b.getId())
                    ClearClass.ClearAllFields(bi.fldGrpll20, null);
            }
        });

        bi.te20.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.te20b.getId())
                    ClearClass.ClearAllFields(bi.fldgrpte21, null);
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

        sE.put("te01", bi.te01a.isChecked() ? "1"
                : bi.te01b.isChecked() ? "2"
                : bi.te0198.isChecked() ? "98" : "0");

        sE.put("te02", bi.te02.getText().toString());
        sE.put("te03", bi.te03.getSelectedItem().toString().equals("....")? "97":bi.te03.getSelectedItem().toString());
        sE.put("te03mname", bi.motherName.getText().toString());
        sE.put("te04", bi.te04.getText().toString());

        sE.put("te05", bi.te05a.isChecked() ? "1"
                : bi.te05b.isChecked() ? "2"
                : bi.te0598.isChecked() ? "98" : "0");

        sE.put("te06", bi.te06a.isChecked() ? "1"
                : bi.te06b.isChecked() ? "2"
                : bi.te0698.isChecked() ? "98" : "0");

        sE.put("te07a", bi.te07a.isChecked() ? "1" : "0");
        sE.put("te07b", bi.te07b.isChecked() ? "2" : "0");
        sE.put("te07c", bi.te07c.isChecked() ? "3" : "0");
        sE.put("te07d", bi.te07d.isChecked() ? "4" : "0");
        sE.put("te07e", bi.te07e.isChecked() ? "5" : "0");
        sE.put("te07f", bi.te07f.isChecked() ? "6" : "0");
        sE.put("te07g", bi.te07g.isChecked() ? "7" : "0");
        sE.put("te07h", bi.te07h.isChecked() ? "8" : "0");
        sE.put("te0796", bi.te0796.isChecked() ? "96" : "0");
        sE.put("te0796x", bi.te0796x.getText().toString());

        sE.put("te08", bi.te08.getText().toString());

        sE.put("te09", bi.te09a.isChecked() ? "1"
                : bi.te09b.isChecked() ? "2"
                : bi.te09c.isChecked() ? "3"
                : bi.te09d.isChecked() ? "4"
                : "0");
        sE.put("te10", bi.te10a.isChecked() ? "1"
                : bi.te10b.isChecked() ? "2"
                : bi.te10c.isChecked() ? "3"
                : bi.te10d.isChecked() ? "4"
                : bi.te10e.isChecked() ? "5"
                : bi.te10f.isChecked() ? "6"
                : bi.te10g.isChecked() ? "7"
                : bi.te10h.isChecked() ? "8"
                : bi.te10i.isChecked() ? "9"
                : bi.te10j.isChecked() ? "10"
                : bi.te10k.isChecked() ? "11"
                : "0");

        sE.put("te11", bi.te11a.isChecked() ? "1"
                : bi.te11b.isChecked() ? "2"
                : bi.te11c.isChecked() ? "3"
                : bi.te11d.isChecked() ? "4"
                : bi.te11e.isChecked() ? "5"
                : bi.te11f.isChecked() ? "6"
                : bi.te11g.isChecked() ? "7"
                : bi.te11h.isChecked() ? "8"
                : bi.te11i.isChecked() ? "9"
                : bi.te11j.isChecked() ? "10"
                : "0");

        sE.put("te12", bi.te12a.isChecked() ? "1"
                : bi.te12b.isChecked() ? "2"
                : "0");
        sE.put("te13", bi.te13a.isChecked() ? "1"
                : bi.te13b.isChecked() ? "2"
                : "0");
        sE.put("te14", bi.te14a.isChecked() ? "1"
                : bi.te14b.isChecked() ? "2"
                : "0");
        sE.put("te15", bi.te15a.isChecked() ? "1"
                : bi.te15b.isChecked() ? "2"
                : bi.te15c.isChecked() ? "3"
                : bi.te15d.isChecked() ? "4"
                : bi.te15e.isChecked() ? "5"
                : "0");

        sE.put("te16a", bi.te16a.isChecked() ? "1" : "0");
        sE.put("te16b", bi.te16b.isChecked() ? "2" : "0");
        sE.put("te16c", bi.te16c.isChecked() ? "3" : "0");
        sE.put("te16d", bi.te16d.isChecked() ? "4" : "0");
        sE.put("te16e", bi.te16e.isChecked() ? "5" : "0");
        sE.put("te16f", bi.te16f.isChecked() ? "6" : "0");
        sE.put("te16g", bi.te16g.isChecked() ? "7" : "0");
        sE.put("te16h", bi.te16h.isChecked() ? "8" : "0");

        sE.put("te17", bi.te17a.isChecked() ? "1"
                : bi.te17b.isChecked() ? "2"
                : bi.te17c.isChecked() ? "3"
                : "0");

        sE.put("te17hr", bi.te17hr.getText().toString());
        sE.put("te17d", bi.te17day.getText().toString());


        sE.put("te18", bi.te18a.isChecked() ? "1"
                : bi.te18b.isChecked() ? "2"
                : bi.te18c.isChecked() ? "2"
                : "0");
        sE.put("te19", bi.te19a.isChecked() ? "1"
                : bi.te19b.isChecked() ? "2"
                : "0");
        sE.put("te20", bi.te20a.isChecked() ? "1"
                : bi.te20b.isChecked() ? "2"
                : "0");

        sE.put("te21", bi.te21a.isChecked() ? "1"
                : bi.te21b.isChecked() ? "2"
                : bi.te21c.isChecked() ? "3"
                : bi.te21d.isChecked() ? "4"
                : bi.te21e.isChecked() ? "5"
                : bi.te21f.isChecked() ? "6"
                : bi.te21g.isChecked() ? "7"
                : bi.te21h.isChecked() ? "8"
                : bi.te21i.isChecked() ? "9"
                : bi.te21j.isChecked() ? "10"
                : bi.te21k.isChecked() ? "11"
                : "0");

        MainApp.fc.setsE(String.valueOf(sE));
    }

    private boolean formValidation() {
        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();
        if (!ValidatorClass.EmptyRadioButton(this, bi.te01, bi.te01a, getString(R.string.te01))) {
            return false;
        }

        if (bi.te01a.isChecked()) {
            if (!ValidatorClass.EmptyTextBox(this, bi.te02, getString(R.string.te02))) {
                return false;
            }
            if (!ValidatorClass.EmptySpinner(this, bi.te03, getString(R.string.te03))) {
                return false;
            }
            if (!ValidatorClass.EmptyTextBox(this, bi.te04, getString(R.string.te04))) {
                return false;
            }
            if (!ValidatorClass.EmptyRadioButton(this, bi.te05, bi.te05a, getString(R.string.te05))) {
                return false;
            }
            if (!ValidatorClass.EmptyRadioButton(this, bi.te06, bi.te06a, getString(R.string.te06))) {
                return false;
            }
            if (!bi.te06a.isChecked()) {
                if (!ValidatorClass.EmptyCheckBox(this, bi.te07, bi.te0796, bi.te0796x, getString(R.string.te07))) {
                    return false;
                }

            } else {
                if (!ValidatorClass.EmptyTextBox(this, bi.te08, getString(R.string.te08))) {
                    return false;
                }
                if (!ValidatorClass.EmptyRadioButton(this, bi.te09, bi.te09a, getString(R.string.te09))) {
                    return false;
                }
                if (!ValidatorClass.EmptyRadioButton(this, bi.te10, bi.te10a, getString(R.string.te10))) {
                    return false;
                }
                if (!ValidatorClass.EmptyCheckBox(this, bi.te11, bi.te11a, getString(R.string.te11))) {
                    return false;
                }
                if (!ValidatorClass.EmptyRadioButton(this, bi.te12, bi.te12a, getString(R.string.te12))) {
                    return false;
                }
                if (!ValidatorClass.EmptyRadioButton(this, bi.te13, bi.te13a, getString(R.string.te13))) {
                    return false;
                }
                if (bi.te13a.isChecked()) {
                    if (!ValidatorClass.EmptyRadioButton(this, bi.te14, bi.te14a, getString(R.string.te14))) {
                        return false;
                    }
                    if (!ValidatorClass.EmptyRadioButton(this, bi.te15, bi.te15a, getString(R.string.te15))) {
                        return false;
                    }
                    if (!ValidatorClass.EmptyCheckBox(this, bi.te16, bi.te16a, getString(R.string.te16))) {
                        return false;
                    }
                    if (!ValidatorClass.EmptyRadioButton(this, bi.te17, bi.te17a, bi.te17hr, getString(R.string.te17))) {
                        return false;
                    }
                    if (bi.te17a.isChecked()) {
                        if (!ValidatorClass.EmptyRadioButton(this, bi.te17, bi.te17a, bi.te17hr, getString(R.string.te17))) {
                            return false;
                        }
                    } else if (bi.te17b.isChecked()) {
                        if (!ValidatorClass.EmptyRadioButton(this, bi.te17, bi.te17b, bi.te17day, getString(R.string.te17))) {
                            return false;
                        }
                    }
                    if (!bi.te17c.isChecked()) {

                        if (!ValidatorClass.EmptyRadioButton(this, bi.te18, bi.te18a, getString(R.string.te18))) {
                            return false;
                        }
                    }
                }
            }
        }
        if (!ValidatorClass.EmptyRadioButton(this, bi.te19, bi.te19a, getString(R.string.te19))) {
            return false;
        }
        if (bi.te19a.isChecked()) {

            if (!ValidatorClass.EmptyRadioButton(this, bi.te20, bi.te20a, getString(R.string.te20))) {
                return false;
            }
            if (bi.te20a.isChecked()) {
                return ValidatorClass.EmptyRadioButton(this, bi.te21, bi.te21a, getString(R.string.te21));
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
