package edu.aku.ramshasaeed.tmk_midline.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;

import edu.aku.ramshasaeed.tmk_midline.R;
import edu.aku.ramshasaeed.tmk_midline.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline.databinding.ActivityEndingBinding;

public class EndingActivity extends Activity {

    private static final String TAG = EndingActivity.class.getSimpleName();
    ActivityEndingBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this,R.layout.activity_ending);
        bi.setCallback(this);


        Boolean check = getIntent().getExtras().getBoolean("complete");
/*
        if (check) {
            istatus1.setEnabled(true);
            istatus2.setEnabled(false);
            istatus3.setEnabled(false);
            istatus4.setEnabled(false);
            istatus5.setEnabled(false);
            istatus6.setEnabled(false);
            istatus7.setEnabled(false);
            istatus8.setEnabled(false);
            istatus888x.setEnabled(false);
            istatus888x.setText(null);

        } else {
            //fldGrpmn0823Reason.setVisibility(View.GONE);
            istatus1.setEnabled(false);
        }

        istatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (istatus8.isChecked()) {
                    istatus888x.setVisibility(View.VISIBLE);
                    istatus888x.requestFocus();
                } else {
                    istatus888x.setText(null);
                    istatus888x.setVisibility(View.GONE);
                }
            }
        });
        */

    }

   public void endInterview() {

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {

                MainApp.familyMembersList.clear();
                MainApp.memFlag = 0;

                MainApp.TotalMembersCount = 0;
                MainApp.TotalMWRACount = 0;
                MainApp.mwraCount = 1;
                MainApp.TotalChildCount = 0;
                MainApp.imsCount = 1;
                MainApp.totalImsCount = 0;

                MainApp.CounterDeceasedMother = 0;
                MainApp.CounterDeceasedChild = 0;

                MainApp.lstChild.clear();
                MainApp.childsMap.clear();

                MainApp.counter = 0;

//    Total No of Alive members got from Section B

/*                MainApp.currentStatusCount = 0;
                MainApp.currentd05ceasedCheck = 0;
                MainApp.currentMotherCheck = 0;*/

                MainApp.selectedPos = -1;

                MainApp.randID = 1;

                MainApp.isRsvp = false;
                MainApp.isHead = false;

                MainApp.flag = true;

                finish();

                Intent endSec = new Intent(this, MainActivity.class);
                endSec.putExtra("complete", false);
                startActivity(endSec);
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

       /* MainApp.fc.setIstatus(istatus1.isChecked() ? "1"
                : istatus2.isChecked() ? "2"
                : istatus3.isChecked() ? "3"
                : istatus4.isChecked() ? "4"
                : istatus5.isChecked() ? "4"
                : istatus6.isChecked() ? "6"
                : istatus7.isChecked() ? "7"
                : istatus8.isChecked() ? "8"
                : "0");

        MainApp.fc.setIstatus88x(istatus888x.getText().toString());
*/

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateEnding();
        /*if (MainApp.memFlag != 0) {
            db.updateFamilyMember();
        }
        if (MainApp.currentd05ceasedCheck != 0) {
            db.updateDeceasedMother();
        }
        if (MainApp.currentMotherCheck != 0) {
            db.updateMother();
        }
        if (MainApp.totalChild != 0) {
            db.updateIM();
        }
*/
        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private boolean formValidation() {
        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();
/*
        if (istatus.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Not Selected): " + getString(R.string.dcstatus), Toast.LENGTH_LONG).show();
            istatus1.setError("Please Select One");    // Set Error on last radio button
            Log.i(TAG, "istatus: This data is Required!");
            return false;
        } else {
            istatus1.setError(null);
        }

        if (istatus8.isChecked()) {

            if (istatus888x.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.other), Toast.LENGTH_SHORT).show();
                istatus888x.setError("This data is Required!");    // Set Error on last radio button
                Log.i(TAG, "istatus888x: This data is Required!");
                return false;
            } else {
                istatus888x.setError(null);
            }

        }

*/
        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


}
