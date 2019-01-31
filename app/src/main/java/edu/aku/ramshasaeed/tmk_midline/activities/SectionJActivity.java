package edu.aku.ramshasaeed.tmk_midline.activities;

import android.databinding.DataBindingUtil;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.ramshasaeed.tmk_midline.R;
import edu.aku.ramshasaeed.tmk_midline.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline.databinding.ActivitySectionJBinding;
import edu.aku.ramshasaeed.tmk_midline.validation.validatorClass;

public class SectionJActivity extends AppCompatActivity {
    ActivitySectionJBinding bi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this,R.layout.activity_section_j);
        bi.setCallback(this);



        bi.tl01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (bi.tl01a.isChecked()) {
                    bi.fldGrptl02.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrptl02.setVisibility(View.GONE);
                    bi.tl02.clearCheck();
                    bi.tl03.clearCheck();
                    bi.tl04a.setChecked(false);
                    bi.tl04b.setChecked(false);
                    bi.tl04c.setChecked(false);
                    bi.tl04d.setChecked(false);

                }
            }
        });

        bi.tl0196.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bi.tl0196x.setVisibility(View.VISIBLE);
                } else {
                    bi.tl0196x.setVisibility(View.GONE);
                    bi.tl0196x.setText(null);
                }
            }
        });

        bi.tl03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (bi.tl03a.isChecked()) {
                    bi.fldGrptl04.setVisibility(View.VISIBLE);
                    bi.fldGrptl05.setVisibility(View.GONE);
                    bi.tl05.clearCheck();
                    bi.tl06.clearCheck();
                    bi.tl07a.setChecked(false);
                    bi.tl07b.setChecked(false);
                    bi.tl07c.setChecked(false);
                    bi.tl07d.setChecked(false);
                } else {
                    bi.fldGrptl04.setVisibility(View.GONE);
                    bi.tl04a.setChecked(false);
                    bi.tl04b.setChecked(false);
                    bi.tl04c.setChecked(false);
                    bi.tl04d.setChecked(false);
                    bi.fldGrptl05.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.tl05.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (bi.tl05a.isChecked()) {
                    bi.fldGrptl06.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrptl06.setVisibility(View.GONE);
                    bi.tl06.clearCheck();
                    bi.tl07a.setChecked(false);
                    bi.tl07b.setChecked(false);
                    bi.tl07c.setChecked(false);
                    bi.tl07d.setChecked(false);
                }
            }
        });

        bi.tl06.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (bi.tl06a.isChecked()) {
                    bi.fldGrptl07.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrptl07.setVisibility(View.GONE);
                    bi.tl07a.setChecked(false);
                    bi.tl07b.setChecked(false);
                    bi.tl07c.setChecked(false);
                    bi.tl07d.setChecked(false);
                }
            }
        });

        bi.tl0996.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bi.tl0996x.setVisibility(View.VISIBLE);
                } else {
                    bi.tl0996x.setVisibility(View.GONE);
                    bi.tl0996x.setText(null);
                }
            }
        });

        bi.tl1096.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bi.tl1096x.setVisibility(View.VISIBLE);
                } else {
                    bi.tl1096x.setVisibility(View.GONE);
                    bi.tl1096x.setText(null);
                }
            }
        });

        bi.tl1196.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bi.tl1196x.setVisibility(View.VISIBLE);
                } else {
                    bi.tl1196x.setVisibility(View.GONE);
                    bi.tl1196x.setText(null);
                }
            }
        });

        bi.tl10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (bi.tl10a.isChecked()) {
                    bi.fldGrptl10.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrptl10.setVisibility(View.GONE);
                    bi.tl10.clearCheck();
                    bi.tl1096x.setText(null);
                    bi.tl11.clearCheck();
                    bi.tl1196x.setText(null);
                }
            }
        });
    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        JSONObject sL = new JSONObject();

        sL.put("tl01", bi.tl01a.isChecked() ? "1" : bi.tl01b.isChecked() ? "2" : bi.tl01c.isChecked() ? "3"
                : bi.tl0196.isChecked() ? "88" : "0");
        sL.put("tl0196x", bi.tl0196x.getText().toString());

        sL.put("tl02", bi.tl02a.isChecked() ? "1" : bi.tl02b.isChecked() ? "2" : "0");
        sL.put("tl03", bi.tl03a.isChecked() ? "1" : bi.tl03b.isChecked() ? "2" : "0");

        sL.put("tl04a", bi.tl04a.isChecked() ? "1" : "0");
        sL.put("tl04b", bi.tl04b.isChecked() ? "2" : "0");
        sL.put("tl04c", bi.tl04c.isChecked() ? "3" : "0");
        sL.put("tl04d", bi.tl04d.isChecked() ? "4" : "0");

        sL.put("tl05", bi.tl05a.isChecked() ? "1" : bi.tl05b.isChecked() ? "2" : "0");
        sL.put("tl06", bi.tl06a.isChecked() ? "1" : bi.tl06b.isChecked() ? "2" : "0");

        sL.put("tl07a", bi.tl07a.isChecked() ? "1" : "0");
        sL.put("tl07b", bi.tl07b.isChecked() ? "2" : "0");
        sL.put("tl07c", bi.tl07c.isChecked() ? "3" : "0");
        sL.put("tl07d", bi.tl07d.isChecked() ? "4" : "0");

        sL.put("tl08a", bi.tl08a.isChecked() ? "1" : "0");
        sL.put("tl08b", bi.tl08b.isChecked() ? "2" : "0");
        sL.put("tl08c", bi.tl08c.isChecked() ? "3" : "0");
        sL.put("tl08d", bi.tl08d.isChecked() ? "4" : "0");
        sL.put("tl08e", bi.tl08e.isChecked() ? "5" : "0");
        sL.put("tl08f", bi.tl08f.isChecked() ? "6" : "0");
        sL.put("tl08g", bi.tl08g.isChecked() ? "7" : "0");
        sL.put("tl08h", bi.tl08h.isChecked() ? "8" : "0");
        sL.put("tl08i", bi.tl08i.isChecked() ? "9" : "0");

        sL.put("tl09", bi.tl09a.isChecked() ? "1" : bi.tl09b.isChecked() ? "2" : bi.tl09c.isChecked() ? "3"
                : bi.tl09d.isChecked() ? "4" : bi.tl0996.isChecked() ? "96" : "0");
        sL.put("tl0996x", bi.tl0996x.getText().toString());

        sL.put("tl10", bi.tl10a.isChecked() ? "1" : bi.tl10b.isChecked() ? "2" : bi.tl10c.isChecked() ? "3"
                : bi.tl10d.isChecked() ? "4" : bi.tl1096.isChecked() ? "96" : "0");
        sL.put("tl1096x", bi.tl1096x.getText().toString());
        sL.put("tl11", bi.tl11a.isChecked() ? "1" : bi.tl11b.isChecked() ? "2" : bi.tl11c.isChecked() ? "3"
                : bi.tl1196.isChecked() ? "96" : "0");
        sL.put("tl1196x", bi.tl1196x.getText().toString());

        MainApp.fc.setsL(String.valueOf(sL));
    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSL();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public boolean ValidateForm() {

        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyRadioButton(this, bi.tl01, bi.tl0196, bi.tl0196x, getString(R.string.tl01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.tl02, bi.tl02a, getString(R.string.tl02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.tl03, bi.tl03a, getString(R.string.tl03))) {
            return false;
        }
        if (!validatorClass.EmptyCheckBox(this, bi.fldGrptl04, bi.tl04a, getString(R.string.tl04))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.tl05, bi.tl05a, getString(R.string.tl05))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.tl06, bi.tl06a, getString(R.string.tl06))) {
            return false;
        }
        if (!validatorClass.EmptyCheckBox(this, bi.fldGrptl07, bi.tl07a, getString(R.string.tl07))) {
            return false;
        }
        if (!validatorClass.EmptyCheckBox(this, bi.fldGrptl08, bi.tl08a, getString(R.string.tl08))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.tl09, bi.tl0996,bi.tl0996x, getString(R.string.tl09))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.tl10, bi.tl1096,bi.tl1096x, getString(R.string.tl10))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.tl11, bi.tl1196,bi.tl1196x, getString(R.string.tl11))) {
            return false;
        }

        return true;
    }


}