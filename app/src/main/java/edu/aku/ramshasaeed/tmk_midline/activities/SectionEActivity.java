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
import edu.aku.ramshasaeed.tmk_midline.validation.ValidatorClass;

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
        sE.put("te03", bi.te03.getSelectedItem().toString());
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


    }

    private boolean formValidation() {

        if(!ValidatorClass.EmptyCheckingContainer(this,bi.fldGrpSecE)){
            return false;
        }
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
