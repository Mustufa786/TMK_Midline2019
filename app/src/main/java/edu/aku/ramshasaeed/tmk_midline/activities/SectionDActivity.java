package edu.aku.ramshasaeed.tmk_midline.activities;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.aku.ramshasaeed.tmk_midline.R;
import edu.aku.ramshasaeed.tmk_midline.contracts.FamilyMembersContract;
import edu.aku.ramshasaeed.tmk_midline.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline.databinding.ActivitySectionDBinding;
import edu.aku.ramshasaeed.tmk_midline.validation.ValidatorClasss;

public class SectionDActivity extends AppCompatActivity {
ActivitySectionDBinding bi;
    DatabaseHelper db;
    long ageInyears = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this,R.layout.activity_section_d);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
        this.setTitle(getResources().getString(R.string.tdheading));
        ValidatorClasss.setScrollViewFocus(bi.svsecd);

//        Counter for serial no
        MainApp.counter++;

        bi.td16.setManager(getSupportFragmentManager());

        String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis());

        bi.td16.setMaxDate(dateToday);

        db = new DatabaseHelper(this);



        if (MainApp.TotalMembersCount == 0) {
            bi.btnContNextSec.setVisibility(View.GONE);
        } else {
            bi.btnContNextSec.setVisibility(View.VISIBLE);
        }

//        set head values
    /*    totalMem.setText(String.valueOf(MainApp.TotalMembersCount));*/
    /*    totalmwra.setText(String.valueOf(MainApp.TotalMWRACount));*/
    /*    totalChild.setText(String.valueOf(MainApp.TotalChildCount));*/
        bi.td08.setText(String.valueOf(MainApp.TotalMWRACount));
        bi.td01.setText(String.valueOf(MainApp.TotalMembersCount));



//        DOB skip checker
        bi.tbdob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (bi.tbdob01.isChecked()) {
                    bi.fldGrptd16.setVisibility(View.VISIBLE);
                    bi.fldGrptd17.setVisibility(View.GONE);
                    bi.td17y.setText(null);
                    bi.td17m.setText(null);
                    //ageInyears = (ageInYears(tb07.getText().toString()));
                } else {
                    bi.td16.setText(null);
                    bi.fldGrptd17.setVisibility(View.VISIBLE);
                    bi.fldGrptd16.setVisibility(View.GONE);
                    //ageInyears = Long.valueOf(tb08y.getText().toString());
                }
            }
        });


//        Textwatcher
        bi.td16.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //if (checkChildLessThenFive(1)) {

                ageInyears = ageInYears(bi.td16.getText().toString());
                bi.txtRsn.setVisibility(View.VISIBLE);
                if (ageInyears > 0 && ageInyears < 2) {
                    bi.txtRsn.setText(ageInyears + " year");
                } else {
                    bi.txtRsn.setText(ageInyears + " years");
                }
                if (ageInyears < 5) {
                    bi.td11.setText("NA");
                    bi.td11.setEnabled(false);
                    bi.fldGrpOcc.setVisibility(View.GONE);
                    bi.td12.clearCheck();

                } else if (ageInyears > 5 && ageInyears < 14) {

                    bi.fldGrpOcc.setVisibility(View.VISIBLE);



                } else if (ageInyears > 14) {

                    bi.fldGrpOcc.setVisibility(View.VISIBLE);
                    bi.td11.setText(null);
                    bi.td11.setEnabled(true);

                    if (bi.td15b.isChecked()) {
                        bi.td12a.setEnabled(true);
                    } else {
                        bi.td12a.setEnabled(false);
                        bi.td12a.setChecked(false);
                    }
                    bi.td12b.setEnabled(true);
                    bi.td12c.setEnabled(true);
                    bi.td12d.setEnabled(true);
                    bi.td12e.setEnabled(true);
                    bi.td12f.setEnabled(true);
                    bi.td12g.setEnabled(true);
                    bi.td12h.setEnabled(true);
                    bi.td12i.setEnabled(true);
                    bi.td12j.setEnabled(true);
                    bi.td12j.setEnabled(true);
                    bi.td12k.setEnabled(true);

                  /*  if (! bi.td12a.isChecked() || !tb03b.isChecked()) {

                    } else {

                    }*/

                }
                //}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bi.td17y.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

/*
                if (!bi.td17y.getText().toString().isEmpty()) {

                    ageInyears = Long.valueOf(bi.td17y.getText().toString());
                    txtRsn.setVisibility(View.VISIBLE);
                    //if (checkChildLessThenFive(2)) {
                    if (ageInyears > 0 && ageInyears < 2) {
                        txtRsn.setText(ageInyears + " year");
                    } else {
                        txtRsn.setText(ageInyears + " years");
                    }
                    if (ageInyears < 5) {

                        tb09.setText("NA");
                        tb09.setEnabled(false);
                        fldGrpOcc.setVisibility(View.GONE);
                        tb10.clearCheck();
                        tb11.clearCheck();

                    } else if (ageInyears > 5 && ageInyears < 14) {
                        fldGrpOcc.setVisibility(View.VISIBLE);
                        fldGrpMarital.setVisibility(View.GONE);
                        tb09.setText(null);
                        tb09.setEnabled(true);
                        tb11.clearCheck();
                    } else if (ageInyears > 14) {
                        fldGrpOcc.setVisibility(View.VISIBLE);
                        fldGrpMarital.setVisibility(View.VISIBLE);
                        tb09.setText(null);
                        tb09.setEnabled(true);

                        if (tb04b.isChecked()) {
                            tb10a.setEnabled(true);
                        } else {
                            tb10a.setEnabled(false);
                            tb10a.setChecked(false);
                        }
                        tb10b.setEnabled(true);
                        tb10c.setEnabled(true);
                        tb10d.setEnabled(true);
                        tb10e.setEnabled(true);
                        tb10f.setEnabled(true);
                        tb10g.setEnabled(true);
                        tb10h.setEnabled(true);
                        tb10ia.setEnabled(true);
                        tb10ib.setEnabled(true);
                        tb10j.setEnabled(true);
                        tb10k.setEnabled(true);
                        tb10l.setEnabled(true);

                        if (!tb10a.isChecked() || !tb03b.isChecked()) {
                            tb11a.setEnabled(true);
                        } else {
                            tb11a.setEnabled(false);
                            tb11a.setChecked(false);
                        }
                        tb11b.setEnabled(true);
                        tb11d.setEnabled(true);
                        tb11e.setEnabled(true);
                    }
                    //}

                }*/
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });







    }
    public static long ageInYears(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = getCalendarDate(dateStr);
        Date dob = cal.getTime();
        Date today = new Date();

        Long diff = today.getTime() - dob.getTime();

        //double ageindays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        long ageInYears = (diff / (24 * 60 * 60 * 1000)) / 365;


        return ageInYears;

    }
    public static Calendar getCalendarDate(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(value);
            calendar.setTime(date);
            return calendar;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }


    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        // children
        if (ageInyears < 5) {
            // u2
            if (ageInyears < 2) {
                MainApp.totalImsCount++;
            }
            // u5
            MainApp.TotalChildCount++;
        }
        // MWRA
        /*if (!tb11a.isChecked() && tb04b.isChecked()*/
        /*        && ageInyears >= 14 && ageInyears < 50) {*/
        /*    MainApp.TotalMWRACount++;*/
        /*}*/
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


        MainApp.fmc.setformDate(MainApp.fc.getformDate());
        MainApp.fmc.setdeviceId(MainApp.fc.getdeviceid());
        MainApp.fmc.setuser(MainApp.fc.getuser());
        MainApp.fmc.set_UUID(MainApp.fc.get_UID());
        MainApp.fmc.setdevicetagID(sharedPref.getString("tagName", null));

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
                    (MainApp.fc.getdeviceid() + MainApp.fmc.get_ID()));
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
