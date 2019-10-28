package edu.aku.ramshasaeed.tmk_midline.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.aku.ramshasaeed.tmk_midline.R;
import edu.aku.ramshasaeed.tmk_midline.contracts.FamilyMembersContract;
import edu.aku.ramshasaeed.tmk_midline.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline.databinding.ActivitySectionHBinding;
import edu.aku.ramshasaeed.tmk_midline.validation.ClearClass;
import edu.aku.ramshasaeed.tmk_midline.validation.ValidatorClasss;

public class SectionHActivity extends AppCompatActivity {
    Map<String, FamilyMembersContract> childsMap;
    ArrayList<String> lstChild;

    int position;
    Long months;

    ActivitySectionHBinding bi;

    FamilyMembersContract fm_child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_h);
        bi.setCallback(this);
        this.setTitle(getResources().getString(R.string.thheading));
        ValidatorClasss.setScrollViewFocus(bi.svsech);

        childsMap = new HashMap<>();
        lstChild = new ArrayList<>();

        childsMap.put("....", new FamilyMembersContract());
        lstChild.add("....");

       /* for (byte i = 0; i < MainApp.familyMembersList.size(); i++) {
            if (MainApp.familyMembersList.get(i).getAgeLess5().equals("3")) {
                childsMap.put(MainApp.familyMembersList.get(i).getName(), new FamilyMembersContract(MainApp.familyMembersList.get(i)));
                lstChild.add(MainApp.familyMembersList.get(i).getName());
            }
        }

        bi.tj01.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lstChild));
*/
        bi.tj0296.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (bi.tj0296.isChecked()) {
                    bi.tj0296x.setVisibility(View.VISIBLE);
                    bi.tj0296x.requestFocus();
                } else {
                    bi.tj0296x.setText(null);
                    bi.tj0296x.setVisibility(View.GONE);
                }
            }
        });


        bi.tj04a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (bi.tj04b.isChecked()) {

                    bi.tj05a.setChecked(false);
                    bi.tj05b.setChecked(false);
                    bi.tj05c.setChecked(false);
                    bi.tj05d.setChecked(false);
                    bi.tj05e.setChecked(false);
                    bi.tj05f.setChecked(false);
                    bi.tj05g.setChecked(false);
                    bi.tj05h.setChecked(false);

                    bi.fldGrpti05.setVisibility(View.GONE);
                } else {
                    bi.fldGrpti05.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.tj0796.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (bi.tj0796.isChecked()) {
                    bi.tj0796x.setVisibility(View.VISIBLE);
                    bi.tj0796x.requestFocus();
                } else {
                    bi.tj0796x.setText(null);
                    bi.tj0796x.setVisibility(View.GONE);
                }
            }
        });

        bi.tj0896.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (bi.tj0896.isChecked()) {
                    bi.tj0896x.setVisibility(View.VISIBLE);
                    bi.tj0896x.requestFocus();
                } else {
                    bi.tj0896x.setText(null);
                    bi.tj0896x.setVisibility(View.GONE);
                }
            }
        });


        bi.tj1496.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (bi.tj1496.isChecked()) {
                    bi.tj1496x.setVisibility(View.VISIBLE);
                    bi.tj1496x.requestFocus();
                } else {
                    bi.tj1496x.setText(null);
                    bi.tj1496x.setVisibility(View.GONE);
                }
            }
        });

//        03

        bi.tj03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.tj03a) {
                    bi.tj03m.setVisibility(View.VISIBLE);

                    bi.tj03h.setText(null);
                    bi.tj03h.setVisibility(View.GONE);
                    bi.tj03d.setText(null);
                    bi.tj03d.setVisibility(View.GONE);
                } else if (i == R.id.tj03b) {
                    bi.tj03m.setText(null);
                    bi.tj03m.setVisibility(View.GONE);

                    bi.tj03h.setVisibility(View.VISIBLE);
                    bi.tj03d.setText(null);
                    bi.tj03d.setVisibility(View.GONE);
                } else if (i == R.id.tj03c) {
                    bi.tj03m.setText(null);
                    bi.tj03m.setVisibility(View.GONE);
                    bi.tj03h.setText(null);
                    bi.tj03h.setVisibility(View.GONE);

                    bi.tj03d.setVisibility(View.VISIBLE);
                } else {
                    bi.tj03m.setText(null);
                    bi.tj03m.setVisibility(View.GONE);
                    bi.tj03h.setText(null);
                    bi.tj03h.setVisibility(View.GONE);
                    bi.tj03d.setText(null);
                    bi.tj03d.setVisibility(View.GONE);
                }
            }
        });

//        04
        bi.tj04.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.tj04a) {
                    bi.fldGrpti05.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpti05.setVisibility(View.GONE);
                    bi.tj05a.setChecked(false);
                    bi.tj05b.setChecked(false);
                    bi.tj05c.setChecked(false);
                    bi.tj05d.setChecked(false);
                    bi.tj05e.setChecked(false);
                    bi.tj05f.setChecked(false);
                    bi.tj05g.setChecked(false);
                    bi.tj05h.setChecked(false);
                }
            }
        });

//        06
        bi.tj06.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.tj06a) {
                    bi.fldGrpti07.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpti07.setVisibility(View.GONE);
                    bi.tj07a.setChecked(false);
                    bi.tj07b.setChecked(false);
                    bi.tj07c.setChecked(false);
                    bi.tj07d.setChecked(false);
                    bi.tj07e.setChecked(false);
                    bi.tj07f.setChecked(false);
                    bi.tj07g.setChecked(false);
                    bi.tj07h.setChecked(false);
                    bi.tj0796.setChecked(false);
                    bi.tj0796x.setText(null);
                }
            }
        });

//        10
        bi.tj10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.tj10a) {
                    bi.fldGrpti11.setVisibility(View.VISIBLE);
                } else {
                    bi.tj11.clearCheck();
                    bi.fldGrpti11.setVisibility(View.GONE);
                }
            }
        });

//        11
        bi.tj11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.tj11a) {
                    bi.tj11d.setVisibility(View.VISIBLE);

                    bi.tj11m.setText(null);
                    bi.tj11m.setVisibility(View.GONE);
                } else if (i == R.id.tj11b) {
                    bi.tj11d.setText(null);
                    bi.tj11d.setVisibility(View.GONE);

                    bi.tj11m.setVisibility(View.VISIBLE);
                } else {
                    bi.tj11d.setText(null);
                    bi.tj11d.setVisibility(View.GONE);

                    bi.tj11m.setText(null);
                    bi.tj11m.setVisibility(View.GONE);
                }
            }
        });

//        12
        bi.tj12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.tj12a) {
                    bi.tj12d.setVisibility(View.VISIBLE);

                    bi.tj12m.setText(null);
                    bi.tj12m.setVisibility(View.GONE);
                } else if (i == R.id.tj12b) {
                    bi.tj12d.setText(null);
                    bi.tj12d.setVisibility(View.GONE);

                    bi.tj12m.setVisibility(View.VISIBLE);
                } else {
                    bi.tj12d.setText(null);
                    bi.tj12d.setVisibility(View.GONE);

                    bi.tj12m.setText(null);
                    bi.tj12m.setVisibility(View.GONE);
                }
            }
        });

//        13
        bi.tj13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i != R.id.tj13c) {
                    ClearClass.ClearAllFields(bi.fldGrpll14, null);
                }
            }
        });

        bi.tj01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Get Youngest Child
        fm_child = MainApp.familyMembersList.get(MainApp.young_child.getSerial() - 1);
        bi.name.setText(fm_child.getname().toUpperCase());

    }

    public void onBtnContinueClick() {
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
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSH();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

/*        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

        MainApp.ims = new SectionJIMContract();

        MainApp.ims.setDevicetagID(sharedPref.getString("tagName", null));
        MainApp.ims.setFormDate(MainApp.dtToday);
        MainApp.ims.setUser(MainApp.userName);
        MainApp.ims.setDeviceId(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));*/

        JSONObject sJ = new JSONObject();

        sJ.put("tjchildSerial", fm_child.getserialNo());
        sJ.put("tjchildName", fm_child.getname());
        sJ.put("tjmotherSerial", fm_child.getmotherId());

        sJ.put("tj01", bi.name.getText().toString());

        sJ.put("tj02", bi.tj02a.isChecked() ? "1" : bi.tj02b.isChecked() ? "2" : bi.tj02c.isChecked() ? "3"
                : bi.tj02d.isChecked() ? "4" : bi.tj02e.isChecked() ? "5" : bi.tj02f.isChecked() ? "6"
                : bi.tj02g.isChecked() ? "7" : bi.tj02h.isChecked() ? "8" : bi.tj02i.isChecked() ? "9"
                : bi.tj0296.isChecked() ? "96" : "0");
        sJ.put("tj0296x", bi.tj0296x.getText().toString());

        sJ.put("tj03", bi.tj03a.isChecked() ? "1" : bi.tj03b.isChecked() ? "2" : bi.tj03c.isChecked() ? "3"
                : bi.tj0397.isChecked() ? "97" : bi.tj0398.isChecked() ? "98" : "0");
        sJ.put("tj03m", bi.tj03m.getText().toString());
        sJ.put("tj03h", bi.tj03h.getText().toString());
        sJ.put("tj03d", bi.tj03d.getText().toString());

        sJ.put("tj04", bi.tj04a.isChecked() ? "1" : bi.tj04b.isChecked() ? "2" : "0");

        sJ.put("tj05a", bi.tj05a.isChecked() ? "1" : "0");
        sJ.put("tj05b", bi.tj05b.isChecked() ? "2" : "0");
        sJ.put("tj05c", bi.tj05c.isChecked() ? "3" : "0");
        sJ.put("tj05d", bi.tj05d.isChecked() ? "4" : "0");
        sJ.put("tj05e", bi.tj05e.isChecked() ? "5" : "0");
        sJ.put("tj05f", bi.tj05f.isChecked() ? "6" : "0");
        sJ.put("tj05g", bi.tj05g.isChecked() ? "7" : "0");
        sJ.put("tj05h", bi.tj05h.isChecked() ? "8" : "0");

        sJ.put("tj06", bi.tj06a.isChecked() ? "1" : bi.tj06b.isChecked() ? "2" : "0");

        sJ.put("tj07a", bi.tj07a.isChecked() ? "1" : "0");
        sJ.put("tj07b", bi.tj07b.isChecked() ? "2" : "0");
        sJ.put("tj07c", bi.tj07c.isChecked() ? "3" : "0");
        sJ.put("tj07d", bi.tj07d.isChecked() ? "4" : "0");
        sJ.put("tj07e", bi.tj07e.isChecked() ? "5" : "0");
        sJ.put("tj07f", bi.tj07f.isChecked() ? "6" : "0");
        sJ.put("tj07g", bi.tj07g.isChecked() ? "7" : "0");
        sJ.put("tj07h", bi.tj07h.isChecked() ? "8" : "0");
        sJ.put("tj0796", bi.tj0796.isChecked() ? "96" : "0");
        sJ.put("tj0796x", bi.tj0796x.getText().toString());

        sJ.put("tj08a", bi.tj08a.isChecked() ? "1" : "0");
        sJ.put("tj08b", bi.tj08b.isChecked() ? "2" : "0");
        sJ.put("tj08c", bi.tj08c.isChecked() ? "3" : "0");
        sJ.put("tj08d", bi.tj08d.isChecked() ? "4" : "0");
        sJ.put("tj08e", bi.tj08e.isChecked() ? "5" : "0");
        sJ.put("tj08f", bi.tj08f.isChecked() ? "6" : "0");
        sJ.put("tj08g", bi.tj08g.isChecked() ? "7" : "0");
        sJ.put("tj08h", bi.tj08h.isChecked() ? "8" : "0");
        sJ.put("tj08i", bi.tj08i.isChecked() ? "9" : "0");
        sJ.put("tj0896", bi.tj0896.isChecked() ? "96" : "0");
        sJ.put("tj0896x", bi.tj0896x.getText().toString());

        sJ.put("tj09", bi.tj09a.isChecked() ? "1" : bi.tj09b.isChecked() ? "2" : "0");
        sJ.put("tj10", bi.tj10a.isChecked() ? "1" : bi.tj10b.isChecked() ? "2" : "0");

        sJ.put("tj11", bi.tj11a.isChecked() ? "1" : bi.tj11b.isChecked() ? "2"
                : bi.tj1198.isChecked() ? "98" : "0");
        sJ.put("tj11d", bi.tj11d.getText().toString());
        sJ.put("tj11m", bi.tj11m.getText().toString());

        sJ.put("tj12", bi.tj12a.isChecked() ? "1" : bi.tj12b.isChecked() ? "2" : bi.tj12c.isChecked() ? "3"
                : bi.tj1298.isChecked() ? "98" : "0");
        sJ.put("tj12d", bi.tj12d.getText().toString());
        sJ.put("tj12m", bi.tj12m.getText().toString());

        sJ.put("tj13", bi.tj13a.isChecked() ? "1" : bi.tj13b.isChecked() ? "2"
                : bi.tj13c.isChecked() ? "97" : "0");
        sJ.put("tj13d", bi.tj13d.getText().toString());
        sJ.put("tj13m", bi.tj13m.getText().toString());

        sJ.put("tj14", bi.tj14a.isChecked() ? "1" : bi.tj14b.isChecked() ? "2" : bi.tj14c.isChecked() ? "3"
                : bi.tj14d.isChecked() ? "4" : bi.tj14e.isChecked() ? "5" : bi.tj14f.isChecked() ? "6"
                : bi.tj14g.isChecked() ? "7" : bi.tj14h.isChecked() ? "8" : bi.tj14i.isChecked() ? "9" : bi.tj14j.isChecked() ? "10"
                : bi.tj14k.isChecked() ? "11" : bi.tj14l.isChecked() ? "12" : bi.tj1496.isChecked() ? "96" : "0");
        sJ.put("tj1496x", bi.tj1496x.getText().toString());

        MainApp.fc.setsH(String.valueOf(sJ));
    }

    public boolean formValidation() {
     /*   if (!ValidatorClasss.EmptySpinner(this, bi.tj01, getString(R.string.tj01))) {
            return false;
        }*/
        if (!ValidatorClasss.EmptyRadioButton(this, bi.tj02, bi.tj0296, bi.tj0296x, getString(R.string.tj02))) {
            return false;
        }
        if (!ValidatorClasss.EmptyRadioButton(this, bi.tj03, bi.tj0398, getString(R.string.tj03))) {
            return false;
        }
        if (!ValidatorClasss.EmptyRadioButton(this, bi.tj04, bi.tj04a, getString(R.string.tj04))) {
            return false;
        }
        if (bi.tj04a.isChecked()) {

            if (!ValidatorClasss.EmptyCheckBox(this, bi.fldGrpti05, bi.tj05a, getString(R.string.tj05))) {
                return false;
            }
        }

        if (!ValidatorClasss.EmptyRadioButton(this, bi.tj06, bi.tj06a, getString(R.string.tj06))) {
            return false;
        }
        if (bi.tj06a.isChecked()) {

            if (!ValidatorClasss.EmptyCheckBox(this, bi.fldGrpti07, bi.tj0796, bi.tj0796x, getString(R.string.tj07))) {
                return false;
            }
        }

        if (!ValidatorClasss.EmptyCheckBox(this, bi.fldGrpti08, bi.tj0896, bi.tj0896x, getString(R.string.tj08))) {
            return false;
        }
        if (!ValidatorClasss.EmptyRadioButton(this, bi.tj09, bi.tj09a, getString(R.string.tj09))) {
            return false;
        }
        if (!ValidatorClasss.EmptyRadioButton(this, bi.tj10, bi.tj10a, getString(R.string.tj10))) {
            return false;
        }
        if (bi.tj10a.isChecked()) {

            if (!ValidatorClasss.EmptyRadioButton(this, bi.tj11, bi.tj11a, bi.tj11d, getString(R.string.tj11) + getString(R.string.day))) {
                return false;
            }
            if (!ValidatorClasss.EmptyRadioButton(this, bi.tj11, bi.tj11b, bi.tj11m, getString(R.string.tj11) + getString(R.string.month))) {
                return false;
            }
        }

        if (!ValidatorClasss.EmptyRadioButton(this, bi.tj12, bi.tj12a, bi.tj12d, getString(R.string.tj12) + getString(R.string.day))) {
            return false;
        }
        if (!ValidatorClasss.EmptyRadioButton(this, bi.tj12, bi.tj12b, bi.tj12m, getString(R.string.tj12) + getString(R.string.month))) {
            return false;
        }

        if (!ValidatorClasss.EmptyRadioButton(this, bi.tj13, bi.tj13a, bi.tj13d, getString(R.string.tj13) + getString(R.string.day))) {
            return false;
        }
        if (!ValidatorClasss.EmptyRadioButton(this, bi.tj13, bi.tj13b, bi.tj13m, getString(R.string.tj13) + getString(R.string.month))) {
            return false;
        }
        if (!bi.tj13c.isChecked()) {

            return ValidatorClasss.EmptyRadioButton(this, bi.tj14, bi.tj1496, bi.tj1496x, getString(R.string.tj14));
        }


        return true;

    }

    public void BtnEnd() {
        //TODO implement
        MainApp.endActivity(this, this);
    }

    @Override
    public void onBackPressed() {

        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
