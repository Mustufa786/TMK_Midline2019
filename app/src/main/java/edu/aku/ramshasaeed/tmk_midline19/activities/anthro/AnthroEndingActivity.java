package edu.aku.ramshasaeed.tmk_midline19.activities.anthro;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import edu.aku.ramshasaeed.tmk_midline19.R;
import edu.aku.ramshasaeed.tmk_midline19.activities.MainActivity;
import edu.aku.ramshasaeed.tmk_midline19.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline19.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline19.databinding.ActivityAnthroEndingBinding;

import static edu.aku.ramshasaeed.tmk_midline19.activities.anthro.SectionInfoAnthroActivity.selectedChildrenMap;

public class AnthroEndingActivity extends Activity {

    private static final String TAG = AnthroEndingActivity.class.getSimpleName();
    ActivityAnthroEndingBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_anthro_ending);
        bi.setCallback(this);


        Boolean check = getIntent().getExtras().getBoolean("complete");
        if (check) {
            bi.istatus1.setEnabled(true);
            bi.istatus2.setEnabled(false);
            bi.istatus3.setEnabled(false);
            bi.istatus4.setEnabled(false);
        } else {
            bi.istatus1.setEnabled(false);
        }

    }

    public void endInterview() {
        if (formValidation()) {
            SaveDraft();
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, selectedChildrenMap.size() == 0 ? MainActivity.class : SectionAnthroBActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SaveDraft() {
        MainApp.ac.setIstatus(bi.istatus1.isChecked() ? "1"
                : bi.istatus2.isChecked() ? "2"
                : bi.istatus3.isChecked() ? "3"
                : bi.istatus4.isChecked() ? "4"
                : "0");
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateAnthroEnding();

        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private boolean formValidation() {
        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();
        if (bi.istatus.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Not Selected): " + getString(R.string.dcstatus), Toast.LENGTH_LONG).show();
            bi.istatus1.setError("Please Select One");    // Set Error on last radio button
            Log.i(TAG, "istatus: This data is Required!");
            return false;
        } else {
            bi.istatus1.setError(null);
        }

        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


}
