package edu.aku.ramshasaeed.tmk_midline.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

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
        if (check) {
            bi.istatus1.setEnabled(true);
            bi.istatus2.setEnabled(false);
            bi.istatus3.setEnabled(false);
            bi.istatus4.setEnabled(false);
            bi.istatus5.setEnabled(false);
            bi.istatus6.setEnabled(false);
            bi.istatus7.setEnabled(false);
            bi.istatus8.setEnabled(false);
            bi.istatus96.setEnabled(false);
            bi.istatus96x.setEnabled(false);
            bi.istatus96x.setText(null);

        } else {
            //fldGrpmn0823Reason.setVisibility(View.GONE);
            bi.istatus1.setEnabled(false);
        }

        bi.istatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (bi.istatus96.isChecked()) {
                    bi.istatus96x.setVisibility(View.VISIBLE);
                    bi.istatus96x.requestFocus();
                } else {
                    bi.istatus96x.setText(null);
                    bi.istatus96x.setVisibility(View.GONE);
                }
            }
        });

    }

   public void endInterview() {

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            SaveDraft();
            if (UpdateDB()) {

                MainApp.familyMembersList.clear();
                MainApp.memFlag = 0;

                MainApp.TotalMembersCount = 0;
                MainApp.TotalMWRACount = 0;
                MainApp.mwraCount = 1;
                MainApp.TotalChildCount = 0;
                MainApp.imsCount = 0;
                MainApp.totalImsCount = 0;
                MainApp.serial_no = 0;

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

    private void SaveDraft() {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        MainApp.fc.setistatus(bi.istatus1.isChecked() ? "1"
                : bi.istatus2.isChecked() ? "2"
                : bi.istatus3.isChecked() ? "3"
                : bi.istatus4.isChecked() ? "4"
                : bi.istatus5.isChecked() ? "4"
                : bi.istatus6.isChecked() ? "6"
                : bi.istatus7.isChecked() ? "7"
                : bi.istatus8.isChecked() ? "8"
                : bi.istatus96.isChecked() ? "96"
                : "0");

        MainApp.fc.setistatus96x(bi.istatus96x.getText().toString());

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
        if (bi.istatus.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Not Selected): " + getString(R.string.dcstatus), Toast.LENGTH_LONG).show();
            bi.istatus1.setError("Please Select One");    // Set Error on last radio button
            Log.i(TAG, "istatus: This data is Required!");
            return false;
        } else {
            bi.istatus1.setError(null);
        }

        if (bi.istatus96.isChecked()) {

            if (bi.istatus96x.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.other), Toast.LENGTH_SHORT).show();
                bi.istatus96x.setError("This data is Required!");    // Set Error on last radio button
                Log.i(TAG, "istatus96x: This data is Required!");
                return false;
            } else {
                bi.istatus96x.setError(null);
            }

        }

        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


}
