package edu.aku.ramshasaeed.tmk_midline_monitor.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.ramshasaeed.tmk_midline_monitor.R;
import edu.aku.ramshasaeed.tmk_midline_monitor.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline_monitor.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline_monitor.databinding.ActivitySectionIBinding;
import edu.aku.ramshasaeed.tmk_midline_monitor.validation.ValidatorClasss;


public class SectionIActivity extends AppCompatActivity {
    ActivitySectionIBinding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_i);
        bi.setCallback(this);
        this.setTitle(getResources().getString(R.string.tiheading));
        ValidatorClasss.setScrollViewFocus(bi.svseci);


        bi.tk0196.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bi.tk0196x.setVisibility(View.VISIBLE);
                } else {
                    bi.tk0196x.setVisibility(View.GONE);
                    bi.tk0196x.setText(null);
                }
            }
        });

        /*bi.tk0296.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bi.tk0296x.setVisibility(View.VISIBLE);
                } else {
                    bi.tk0296x.setVisibility(View.GONE);
                    bi.tk0296x.setText(null);
                }
            }
        });

        bi.tk03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.tk03c) {
                    bi.fldGrptk04.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrptk04.setVisibility(View.GONE);
                    bi.tk04.setText(null);
                    bi.tk0498.setChecked(false);
                    bi.tk05.clearCheck();
                }
            }
        });

        bi.tk0498.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bi.tk04.setVisibility(View.GONE);
                    bi.tk04.setText(null);
                } else {
                    bi.tk04.setVisibility(View.VISIBLE);
                }
            }
        });*/

        bi.tk08.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (bi.tk08a.isChecked()) {
                    bi.fldGrptk09.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrptk09.setVisibility(View.GONE);
                    bi.tk09.clearCheck();
                    bi.tk0996x.setText(null);
                }
            }
        });

        bi.tk0996.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bi.tk0996x.setVisibility(View.VISIBLE);
                } else {
                    bi.tk0996x.setVisibility(View.GONE);
                    bi.tk0996x.setText(null);
                }
            }
        });

        bi.tk1096.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bi.tk1096x.setVisibility(View.VISIBLE);
                } else {
                    bi.tk1096x.setVisibility(View.GONE);
                    bi.tk1096x.setText(null);
                }
            }
        });

        /*bi.tk12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (bi.tk12a.isChecked()) {
                    bi.fldGrptk13.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrptk13.setVisibility(View.GONE);
                    bi.tk13.clearCheck();
                    bi.tk14.clearCheck();
                }
            }
        });*/

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        JSONObject sK = new JSONObject();

        sK.put("tk01", bi.tk01a.isChecked() ? "1" : bi.tk01b.isChecked() ? "2" : bi.tk01c.isChecked() ? "3" : bi.tk01d.isChecked() ? "4"
                : bi.tk01e.isChecked() ? "5" : bi.tk01f.isChecked() ? "6" : bi.tk01g.isChecked() ? "7" : bi.tk01h.isChecked() ? "8"
                : bi.tk01i.isChecked() ? "9" : bi.tk01j.isChecked() ? "10" : bi.tk01k.isChecked() ? "11" : bi.tk01l.isChecked() ? "12"
                : bi.tk01m.isChecked() ? "13" : bi.tk01n.isChecked() ? "14" : bi.tk0196.isChecked() ? "96" : "0");
        sK.put("tk0196x", bi.tk0196x.getText().toString());

        /*sK.put("tk02", bi.tk02a.isChecked() ? "1" : bi.tk02b.isChecked() ? "2" : bi.tk02c.isChecked() ? "3" : bi.tk02d.isChecked() ? "4"
                : bi.tk02e.isChecked() ? "5" : bi.tk02f.isChecked() ? "6" : bi.tk02g.isChecked() ? "7" : bi.tk02h.isChecked() ? "8"
                : bi.tk02i.isChecked() ? "9" : bi.tk02j.isChecked() ? "10" : bi.tk02k.isChecked() ? "11" : bi.tk02l.isChecked() ? "12"
                : bi.tk02m.isChecked() ? "13" : bi.tk02n.isChecked() ? "14" : bi.tk0296.isChecked() ? "96" : "0");
        sK.put("tk0296x", bi.tk0296x.getText().toString());
        sK.put("tk03", bi.tk03a.isChecked() ? "1" : bi.tk03b.isChecked() ? "2" : bi.tk03c.isChecked() ? "3" : "0");
        sK.put("tk04", bi.tk04.getText().toString());
        sK.put("tk0498", bi.tk0498.isChecked() ? "98" : "0");
        sK.put("tk05", bi.tk05a.isChecked() ? "1" : bi.tk05b.isChecked() ? "2" : bi.tk05c.isChecked() ? "3" :
                bi.tk05d.isChecked() ? "4" : bi.tk05e.isChecked() ? "5" : "0");
        sK.put("tk06", bi.tk06a.isChecked() ? "1" : bi.tk06b.isChecked() ? "2" : "0");
        sK.put("tk07", bi.tk07a.isChecked() ? "1" : bi.tk07b.isChecked() ? "2" : bi.tk0798.isChecked() ? "98" : "0");*/
        sK.put("tk08", bi.tk08a.isChecked() ? "1" : bi.tk08b.isChecked() ? "2" : bi.tk0898.isChecked() ? "98" : "0");
        sK.put("tk09", bi.tk09a.isChecked() ? "1" : bi.tk09b.isChecked() ? "2" : bi.tk09c.isChecked() ? "3" : bi.tk09d.isChecked() ? "4"
                : bi.tk09e.isChecked() ? "5" : bi.tk09f.isChecked() ? "6" : bi.tk09g.isChecked() ? "7" : bi.tk0996.isChecked() ? "96" : "0");
        sK.put("tk0996x", bi.tk0996x.getText().toString());

        sK.put("tk10", bi.tk10a.isChecked() ? "1" : bi.tk10b.isChecked() ? "2" : bi.tk10c.isChecked() ? "3" : bi.tk10d.isChecked() ? "4"
                : bi.tk10e.isChecked() ? "5" : bi.tk10f.isChecked() ? "6" : bi.tk10g.isChecked() ? "7" : bi.tk1096.isChecked() ? "96" : "0");
        sK.put("tk1096x", bi.tk1096x.getText().toString());

        sK.put("tk11", bi.tk11a.isChecked() ? "1" : bi.tk11b.isChecked() ? "2" : bi.tk11c.isChecked() ? "3" : bi.tk11d.isChecked() ? "4"
                : bi.tk11e.isChecked() ? "5" : bi.tk11f.isChecked() ? "6" : bi.tk11g.isChecked() ? "7" : bi.tk11h.isChecked() ? "8"
                : bi.tk11i.isChecked() ? "9" : bi.tk11j.isChecked() ? "10" : bi.tk11k.isChecked() ? "11" : "0");

        /*sK.put("tk12", bi.tk12a.isChecked() ? "1" : bi.tk12b.isChecked() ? "2" : bi.tk1298.isChecked() ? "98" : "0");
        sK.put("tk13", bi.tk13a.isChecked() ? "1" : bi.tk13b.isChecked() ? "2" : "0");
        sK.put("tk14", bi.tk14a.isChecked() ? "1" : bi.tk14b.isChecked() ? "2" : bi.tk1498.isChecked() ? "98" : "0");*/

        MainApp.fc.setsI(String.valueOf(sK));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();

    }

    public boolean ValidateForm() {

        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!ValidatorClasss.EmptyRadioButton(this, bi.tk01, bi.tk0196, bi.tk0196x, getString(R.string.tk01))) {
            return false;
        }
        /*if (!ValidatorClasss.EmptyRadioButton(this, bi.tk02, bi.tk0296, bi.tk0296x, getString(R.string.tk02))) {
            return false;
        }
        if (!ValidatorClasss.EmptyRadioButton(this, bi.tk03, bi.tk03a, getString(R.string.tk03))) {
            return false;
        }
        if (bi.tk03c.isChecked()) {
            if (!bi.tk0498.isChecked()) {
                if (!ValidatorClasss.EmptyTextBox(this, bi.tk04, getString(R.string.tk04))) {
                    return false;
                }
            }
            if (!ValidatorClasss.EmptyRadioButton(this, bi.tk05, bi.tk05a, getString(R.string.tk05))) {
                return false;
            }
        }

        if (!ValidatorClasss.EmptyRadioButton(this, bi.tk06, bi.tk06a, getString(R.string.tk06))) {
            return false;
        }
        if (!ValidatorClasss.EmptyRadioButton(this, bi.tk07, bi.tk07a, getString(R.string.tk07))) {
            return false;
        }*/
        if (!ValidatorClasss.EmptyRadioButton(this, bi.tk08, bi.tk08a, getString(R.string.tk08))) {
            return false;
        }
        if (bi.tk08a.isChecked()) {

            if (!ValidatorClasss.EmptyRadioButton(this, bi.tk09, bi.tk0996, bi.tk0996x, getString(R.string.tk09))) {
                return false;
            }
        }

        if (!ValidatorClasss.EmptyRadioButton(this, bi.tk10, bi.tk1096, bi.tk1096x, getString(R.string.tk10))) {
            return false;
        }

        return ValidatorClasss.EmptyRadioButton(this, bi.tk11, bi.tk11a, getString(R.string.tk11));
    }


    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSI();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void BtnContinue() {
        if (ValidateForm()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();

                finish();

                Intent secNext = new Intent(this, SectionJActivity.class);
                startActivity(secNext);
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void BtnEnd() {
        MainApp.endActivity(this, this);
    }

    @Override
    public void onBackPressed() {

        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
