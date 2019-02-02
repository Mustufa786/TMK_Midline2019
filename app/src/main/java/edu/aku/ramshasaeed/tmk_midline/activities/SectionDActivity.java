package edu.aku.ramshasaeed.tmk_midline.activities;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.ramshasaeed.tmk_midline.R;
import edu.aku.ramshasaeed.tmk_midline.contracts.FamilyMembersContract;
import edu.aku.ramshasaeed.tmk_midline.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline.databinding.ActivitySectionDBinding;
import edu.aku.ramshasaeed.tmk_midline.databinding.ActivitySectionJBinding;
import edu.aku.ramshasaeed.tmk_midline.validation.validatorClass;

public class SectionDActivity extends AppCompatActivity {
ActivitySectionDBinding bi;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this,R.layout.activity_section_d);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
        this.setTitle(getResources().getString(R.string.tdheading));
        validatorClass.setScrollViewFocus(bi.svsecd);

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

       /* // children
        if (ageInyears < 5) {
            // u2
            if (ageInyears < 2) {
                MainApp.totalImsCount++;
            }
            // u5
            MainApp.TotalChildCount++;
        }
        // MWRA
        if (!tb11a.isChecked() && tb04b.isChecked()
                && ageInyears >= 14 && ageInyears < 50) {
            MainApp.TotalMWRACount++;
        }*/
        // TOTAL MEMBERS
        MainApp.TotalMembersCount++;

        JSONObject count = new JSONObject();
        count.put("tb13", MainApp.TotalMembersCount);
        count.put("tb14", MainApp.TotalMWRACount);
        count.put("tb15", MainApp.TotalChildCount);
        count.put("tb16", MainApp.totalImsCount);

        MainApp.fc.setsB(String.valueOf(count));

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

        MainApp.fmc = new FamilyMembersContract();


        MainApp.fmc.setFormDate(MainApp.fc.getFormDate());
        MainApp.fmc.setDeviceId(MainApp.fc.getDeviceID());
        MainApp.fmc.setUser(MainApp.fc.getUser());
        MainApp.fmc.set_UUID(MainApp.fc.getUID());
        MainApp.fmc.setDevicetagID(sharedPref.getString("tagName", null));

        JSONObject sB = new JSONObject();

        MainApp.fmc.setsB(String.valueOf(sB));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {

        Long updcount = db.addFamilyMembers(MainApp.fmc);
        MainApp.fmc.set_ID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.fmc.set_UID(
                    (MainApp.fc.getDeviceID() + MainApp.fmc.get_ID()));
            db.updateFamilyMemberID();

           /* MainApp.familyMembersList.add(new FamilyMembersContract(tb02.getText().toString(),
                    ageInyears < 2 ? "3" : ageInyears < 5 ? "1" :
                            (tb11b.isChecked() && tb04b.isChecked()
                                    && (ageInyears > 15 && ageInyears < 49) ? "2" : "0")
                    , String.valueOf(MainApp.counter),
                    tb07.getText().toString().isEmpty() ?
                            tb08m.getText().toString() + "-" + tb08y.getText().toString() :
                            tb07.getText().toString()));*/
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean formValidation() {

        return true;
    }
}
