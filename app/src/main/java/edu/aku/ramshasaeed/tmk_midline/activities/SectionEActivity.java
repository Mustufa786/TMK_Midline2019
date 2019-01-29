package edu.aku.ramshasaeed.tmk_midline.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.ramshasaeed.tmk_midline.R;
import edu.aku.ramshasaeed.tmk_midline.databinding.ActivitySectionEBinding;

public class SectionEActivity extends AppCompatActivity {

    ActivitySectionEBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_e);
        bi.setCallback(this);


    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
                if (UpdateDB()) {
//                    startActivity(new Intent(getApplicationContext(), Form02HHPart_2_HI_SE.class));
                } else {
                    Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean UpdateDB() {

        return true;
    }

    private void SaveDraft() throws JSONException {

        JSONObject sE = new JSONObject();

        sE.put("te01", bi.te01a.isChecked() ? "1"
                : bi.te01b.isChecked() ? "2"
                : bi.te0198.isChecked() ? "98" : "0");

        sE.put("te02", bi.te02.getText().toString());
//        sE.put("te03", bi.te03.getText().toString());
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


    }

    private boolean formValidation() {

        return true;
    }

    public void BtnEnd() {
//        MainApp.endActivity(this, this, EndingActivity.class, false, Form01Enrolment.fc_4_5);
    }

    @Override
    public void onBackPressed() {

        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
