package edu.aku.ramshasaeed.tmk_midline.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
import edu.aku.ramshasaeed.tmk_midline.databinding.ActivitySectionBBinding;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;

public class SectionBActivity extends AppCompatActivity {

    private static final String TAG = SectionBActivity.class.getName();
    ActivitySectionBBinding bi;
  /*  @BindView(R.id.app_header)
    TextView appHeader;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.TotalMem)
    TextView totalMem;
    @BindView(R.id.Totalmwra)
    TextView totalmwra;
    @BindView(R.id.TotalChild)
    TextView totalChild;
    @BindView(R.id.tb02)
    EditText tb02;
    @BindView(R.id.tb03)
    RadioGroup tb03;
    @BindView(R.id.tb03a)
    RadioButton tb03a;
    @BindView(R.id.tb03b)
    RadioButton tb03b;
    @BindView(R.id.tb03c)
    RadioButton tb03c;
    @BindView(R.id.tb03d)
    RadioButton tb03d;
    @BindView(R.id.tb03e)
    RadioButton tb03e;
    @BindView(R.id.tb03f)
    RadioButton tb03f;
    @BindView(R.id.tb03g)
    RadioButton tb03g;
    @BindView(R.id.tb03h)
    RadioButton tb03h;
    @BindView(R.id.tb03i)
    RadioButton tb03i;
    @BindView(R.id.tb03j)
    RadioButton tb03j;
    @BindView(R.id.tb03k)
    RadioButton tb03k;
    @BindView(R.id.tb03l)
    RadioButton tb03l;
    @BindView(R.id.tb03m)
    RadioButton tb03m;
    @BindView(R.id.tb03n)
    RadioButton tb03n;
    @BindView(R.id.tb0388)
    RadioButton tb0388;
    @BindView(R.id.tb0388x)
    EditText tb0388x;
    @BindView(R.id.tb04)
    RadioGroup tb04;
    @BindView(R.id.tb04a)
    RadioButton tb04a;
    @BindView(R.id.tb04b)
    RadioButton tb04b;
    @BindView(R.id.tb05)
    EditText tb05;
    @BindView(R.id.tb06)
    EditText tb06;
    @BindView(R.id.tbdob)
    RadioGroup tbdob;
    @BindView(R.id.tbdob01)
    RadioButton tbdob01;
    @BindView(R.id.tbAge02)
    RadioButton tbAge02;
    @BindView(R.id.fldGrptb07)
    LinearLayout fldGrptb07;
    @BindView(R.id.tb07)
    DatePickerInputEditText tb07;
    @BindView(R.id.fldGrptb08)
    LinearLayout fldGrptb08;
    @BindView(R.id.tb08y)
    EditText tb08y;
    @BindView(R.id.tb08m)
    EditText tb08m;
    @BindView(R.id.tb09)
    EditText tb09;
    @BindView(R.id.tb10)
    RadioGroup tb10;
    @BindView(R.id.tb10a)
    RadioButton tb10a;
    @BindView(R.id.tb10b)
    RadioButton tb10b;
    @BindView(R.id.tb10c)
    RadioButton tb10c;
    @BindView(R.id.tb10d)
    RadioButton tb10d;
    @BindView(R.id.tb10e)
    RadioButton tb10e;
    @BindView(R.id.tb10f)
    RadioButton tb10f;
    @BindView(R.id.tb10g)
    RadioButton tb10g;
    @BindView(R.id.tb10h)
    RadioButton tb10h;
    @BindView(R.id.tb10ia)
    RadioButton tb10ia;
    @BindView(R.id.tb10ib)
    RadioButton tb10ib;
    @BindView(R.id.tb10j)
    RadioButton tb10j;
    @BindView(R.id.tb10k)
    RadioButton tb10k;
    @BindView(R.id.tb10l)
    RadioButton tb10l;
    @BindView(R.id.tb10999)
    RadioButton tb10999;
    @BindView(R.id.tb11)
    RadioGroup tb11;
    @BindView(R.id.tb11a)
    RadioButton tb11a;
    @BindView(R.id.tb11b)
    RadioButton tb11b;
    /*    @BindView(R.id.tb11c)
        RadioButton tb11c;
    @BindView(R.id.tb11d)
    RadioButton tb11d;
    @BindView(R.id.tb11e)
    RadioButton tb11e;

    @BindView(R.id.btn_ContNextSec)
    Button btn_ContNextSec;

    @BindView(R.id.fldGrptb11)
    LinearLayout fldGrptb11;

    @BindView(R.id.tb12)
    RadioGroup tb12;
    @BindView(R.id.tb12a)
    RadioButton tb12a;
    @BindView(R.id.tb12b)
    RadioButton tb12b;
    @BindView(R.id.fldGrpOcc)
    LinearLayout fldGrpOcc;
    @BindView(R.id.fldGrpMarital)
    LinearLayout fldGrpMarital;
    @BindView(R.id.txtRsn)
    TextView txtRsn;
*/

    DatabaseHelper db;
    long ageInyears = 0;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_b);
        bi.setCallback(this);
        this.setTitle(getResources().getString(R.string.td08eading));

//        ButterKnife.bind(this);

//        Counter for serial no
        MainApp.counter++;

        bi.tb07.setManager(getSupportFragmentManager());

        String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis());

        bi.tb07.setMaxDate(dateToday);

        db = new DatabaseHelper(this);

//        Check for next sec button

        if (MainApp.TotalMembersCount == 0) {
            bi.btnContNextSec.setVisibility(View.GONE);
        } else {
            bi.btnContNextSec.setVisibility(View.VISIBLE);
        }

//        set head values
      /*  bi.totalMem.setText(String.valueOf(MainApp.TotalMembersCount));
        bi.totalmwra.setText(String.valueOf(MainApp.TotalMWRACount));
        bi.totalChild.setText(String.valueOf(MainApp.TotalChildCount));
*/
//        Check HH

     /*   if (MainApp.isHead) {
            bi.tb03a.setEnabled(false);
        }*/

//        Skip Patterns

        bi.tb04.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (bi.tb04b.isChecked()) {

                    bi.tb10a.setEnabled(true);

                  /*  if (bi.tb11b.isChecked()) {
                        bi.fldGrptb11.setVisibility(View.VISIBLE);
                    } else {
                        bi.tb12.clearCheck();
                        bi.fldGrptb11.setVisibility(View.GONE);
                    }*/

                } else {

//                    bi.tb12.clearCheck();
//                    bi.fldGrptb11.setVisibility(View.GONE);

                    bi.tb10a.setEnabled(false);
                    bi.tb10a.setChecked(false);
                }
            }
        });

//        DOB skip checker
        bi.tbdob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (bi.tbdob01.isChecked()) {
                    bi.fldGrptb07.setVisibility(View.VISIBLE);
                    bi.fldGrptb08.setVisibility(View.GONE);
                    bi.tb08y.setText(null);
                    bi.tb08m.setText(null);

                    //ageInyears = (ageInYears(tb07.getText().toString()));
                } else {
                    bi.tb07.setText(null);
                    bi.fldGrptb08.setVisibility(View.VISIBLE);
                    bi.fldGrptb07.setVisibility(View.GONE);
                    //ageInyears = Long.valueOf(tb08y.getText().toString());
                }
            }
        });

//        Textwatcher
        bi.tb07.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //if (checkChildLessThenFive(1)) {

                ageInyears = ageInYears(bi.tb07.getText().toString());
                bi.txtRsn.setVisibility(View.VISIBLE);
                if (ageInyears > 0 && ageInyears < 2) {
                    bi.fldGrptd13.setVisibility(View.GONE);
                    bi.fldGrpGender.setVisibility(View.VISIBLE);
                    bi.txtRsn.setText(ageInyears + " year");
                } else {
                    bi.txtRsn.setText(ageInyears + " years");
                }
                if (ageInyears < 5) {
                    bi.fldGrptd13.setVisibility(View.GONE);
                    bi.fldGrpGender.setVisibility(View.VISIBLE);
                    bi.tb09.setText("NA");
                    bi.tb09.setEnabled(false);
                    bi.fldGrpOcc.setVisibility(View.GONE);
//                    bi.fldGrpMarital.setVisibility(View.GONE);
                    bi.tb10.clearCheck();
//                    bi.tb11.clearCheck();
                } else if (ageInyears > 5 && ageInyears < 14) {
                    bi.fldGrptd13.setVisibility(View.VISIBLE);
                    bi.fldGrpGender.setVisibility(View.GONE);
                    bi.fldGrpOcc.setVisibility(View.VISIBLE);
                   /* bi.fldGrpMarital.setVisibility(View.GONE);
                    bi.tb11.clearCheck();*/

                } else if (ageInyears > 14) {
                    bi.fldGrptd13.setVisibility(View.VISIBLE);
                    bi.fldGrpGender.setVisibility(View.GONE);

//                    fldGrpMarital.setVisibility(View.VISIBLE);
                    bi.fldGrpOcc.setVisibility(View.VISIBLE);
                    bi.tb09.setText(null);
                    bi.tb09.setEnabled(true);

                    if (bi.tb04b.isChecked()) {
                        bi.tb10a.setEnabled(true);
                    } else {
                        bi.tb10a.setEnabled(false);
                        bi.tb10a.setChecked(false);
                    }
                    bi.tb10b.setEnabled(true);
                    bi.tb10c.setEnabled(true);
                    bi.tb10d.setEnabled(true);
                    bi.tb10e.setEnabled(true);
                    bi.tb10f.setEnabled(true);
                    bi.tb10g.setEnabled(true);
                    bi.tb10h.setEnabled(true);
                    bi.tb10ia.setEnabled(true);
//                    bi.tb10ib.setEnabled(true);
                    bi.tb10j.setEnabled(true);
                    bi.tb10k.setEnabled(true);
                    bi.tb10l.setEnabled(true);

                 /*   if (!bi.tb10a.isChecked() || !bi.tb03b.isChecked()) {
                        bi.tb11a.setEnabled(true);
                    } else {
                        bi.tb11a.setEnabled(false);
                        bi.tb11a.setChecked(false);
                    }
                    bi.tb11b.setEnabled(true);
                    bi.tb11d.setEnabled(true);
                    bi.tb11e.setEnabled(true);*/
                }
                //}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bi.tb08y.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (!bi.tb08y.getText().toString().isEmpty()) {

                    ageInyears = Long.valueOf(bi.tb08y.getText().toString());
                    bi.txtRsn.setVisibility(View.VISIBLE);
                    //if (checkChildLessThenFive(2)) {
                    if (ageInyears > 0 && ageInyears < 2) {
                        bi.fldGrptd13.setVisibility(View.GONE);
                        bi.txtRsn.setText(ageInyears + " year");
                        bi.fldGrpGender.setVisibility(View.VISIBLE);
                    } else {
                        bi.txtRsn.setText(ageInyears + " years");

                    }
                    if (ageInyears < 5) {
                        bi.fldGrptd13.setVisibility(View.GONE);

                        bi.tb09.setText("NA");
                        bi.tb09.setEnabled(false);
                        bi.fldGrpOcc.setVisibility(View.GONE);
//                        fldGrpMarital.setVisibility(View.GONE);
                        bi.fldGrpGender.setVisibility(View.VISIBLE);

                        bi.tb10.clearCheck();
//                        bi.tb11.clearCheck();

                    } else if (ageInyears > 5 && ageInyears < 14) {
                        bi.fldGrptd13.setVisibility(View.VISIBLE);

                        bi.fldGrpGender.setVisibility(View.GONE);
                        bi.fldGrpOcc.setVisibility(View.VISIBLE);
//                        fldGrpMarital.setVisibility(View.GONE);
                        bi.tb09.setText(null);
                        bi.tb09.setEnabled(true);
//                        bi.tb11.clearCheck();
                    } else if (ageInyears > 14) {
                        bi.fldGrptd13.setVisibility(View.VISIBLE);
                        bi.fldGrpGender.setVisibility(View.GONE);

                        bi.fldGrpOcc.setVisibility(View.VISIBLE);
//                        fldGrpMarital.setVisibility(View.VISIBLE);
                        bi.tb09.setText(null);
                        bi.tb09.setEnabled(true);

                        if (bi.tb04b.isChecked()) {
                            bi.tb10a.setEnabled(true);
                        } else {
                            bi.tb10a.setEnabled(false);
                            bi.tb10a.setChecked(false);
                        }
                        bi.tb10b.setEnabled(true);
                        bi.tb10c.setEnabled(true);
                        bi.tb10d.setEnabled(true);
                        bi.tb10e.setEnabled(true);
                        bi.tb10f.setEnabled(true);
                        bi.tb10g.setEnabled(true);
                        bi.tb10h.setEnabled(true);
                        bi.tb10ia.setEnabled(true);
//                        bi.tb10ib.setEnabled(true);
                        bi.tb10j.setEnabled(true);
                        bi.tb10k.setEnabled(true);
                        bi.tb10l.setEnabled(true);

                    /*    if (!bi.tb10a.isChecked() || !bi.tb03b.isChecked()) {
                            bi.tb11a.setEnabled(true);
                        } else {
                            bi.tb11a.setEnabled(false);
                            bi.tb11a.setChecked(false);
                        }
                        bi.tb11b.setEnabled(true);
                        bi.tb11d.setEnabled(true);
                        bi.tb11e.setEnabled(true);
                        */
                    }
                    //}

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        /*bi.tb0388.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (bi.tb0388.isChecked()) {
                    bi.tb0388x.setVisibility(View.VISIBLE);
                    bi.tb0388x.requestFocus();
                } else {
                    bi.tb0388x.setText(null);
                    bi.tb0388x.setVisibility(View.GONE);
                }
            }
        });*/


        bi.tb10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (bi.tb10a.isChecked()) {
//                    bi.tb11a.setEnabled(false);
                } else {
//                    bi.tb11a.setEnabled(true);
                }
            }
        });


     /*   bi.tb11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (bi.tb11b.isChecked()) {

                    if (bi.tb04b.isChecked()) {
                        //        IS RSVP
                        if (MainApp.isRsvp) {
                            fldGrptb11.setVisibility(View.GONE);
                            bi.tb12.clearCheck();
                        } else {
                            fldGrptb11.setVisibility(View.VISIBLE);
                        }
                    } else {
                        bi.tb12.clearCheck();
                        fldGrptb11.setVisibility(View.GONE);
                    }

                } else {
                    bi.tb12.clearCheck();
                    fldGrptb11.setVisibility(View.GONE);
                }
            }
        });*/

    }

//    @OnClick(R.id.btn_End)
   public void onBtnEnd() {
        //TODO implement
        MainApp.endActivity(this, this);
    }


    private boolean UpdateCount() {

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateCount();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }


    }

//    @OnClick(R.id.btn_ContNextSec)
    public void onBtnContinueClick() {
        //TODO implement
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                SectionBActivity.this);
        alertDialogBuilder
                .setMessage("Are you sure to move next section??")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                Toast.makeText(SectionBActivity.this, "Processing This Section", Toast.LENGTH_SHORT).show();
                                if (formValidation()) {
                                    try {
                                        SaveDraft();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    if (UpdateDB() && UpdateCount()) {

                                        Toast.makeText(SectionBActivity.this, "Starting Next Section", Toast.LENGTH_SHORT).show();

                                        finish();
                                        startActivity(new Intent(getApplicationContext(), SectionIActivity.class));
                                    }
                                } else {
                                    Toast.makeText(SectionBActivity.this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void onBtnAddMore() {
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

            }
            startActivity(new Intent(this, SectionBActivity.class));
        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }
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
        /*if (!bi.tb11a.isChecked() && bi.tb04b.isChecked()
                && ageInyears >= 14 && ageInyears < 50) {
            MainApp.TotalMWRACount++;
        }*/
        if (bi.tb04b.isChecked()
                && ageInyears >= 14 && ageInyears < 50) {
            MainApp.TotalMWRACount++;
        }
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

        sB.put("ta01", MainApp.cluster);
        sB.put("ta05h", MainApp.hhno);
        sB.put("ta05u", MainApp.billno);

        sB.put("tb01", MainApp.counter);
        sB.put("tb02", bi.tb02.getText().toString());
     /*   sB.put("tb03", bi.tb03a.isChecked() ? "1" : bi.tb03b.isChecked() ? "2" : bi.tb03c.isChecked() ? "3"
                : bi.tb03d.isChecked() ? "4" : bi.tb03e.isChecked() ? "5" : bi.tb03f.isChecked() ? "6"
                : bi.tb03g.isChecked() ? "7" : bi.tb03h.isChecked() ? "8" : bi.tb03i.isChecked() ? "9" : bi.tb03j.isChecked() ? "10"
                : bi.tb03k.isChecked() ? "11" : bi.tb03l.isChecked() ? "12" : bi.tb03m.isChecked() ? "13"
                : bi.tb03n.isChecked() ? "14" : bi.tb0388.isChecked() ? "88" : "0");

        sB.put("tb0388x", bi.tb0388x.getText().toString());

        if (!MainApp.isHead) {
            MainApp.isHead = bi.tb03a.isChecked();
        }
        */

        sB.put("tb04", bi.tb04a.isChecked() ? "1" : bi.tb04b.isChecked() ? "2" : "0");
       /* sB.put("tb05", bi.tb05.getText().toString());
        sB.put("tb06", bi.tb06.getText().toString());*/

        if (bi.tbdob01.isChecked()) {
            sB.put("tb07", bi.tb07.getText().toString());

        } else {
            sB.put("tb08y", bi.tb08y.getText().toString());
            sB.put("tb08m", bi.tb08m.getText().toString());
        }

        sB.put("tb09", bi.tb09.getText().toString().equals("NA") ? "999" : bi.tb09.getText().toString());
        sB.put("tb10", bi.tb10a.isChecked() ? "1" : bi.tb10b.isChecked() ? "2" : bi.tb10c.isChecked() ? "3"
                : bi.tb10d.isChecked() ? "4" : bi.tb10e.isChecked() ? "5" : bi.tb10f.isChecked() ? "6"
                : bi.tb10g.isChecked() ? "7" : bi.tb10h.isChecked() ? "8" : bi.tb10ia.isChecked() ? "9a"  : bi.tb10j.isChecked() ? "10"
                : bi.tb10k.isChecked() ? "11" : bi.tb10l.isChecked() ? "12" : bi.tb10999.isChecked() ? "999"
                : "0");
     /*   sB.put("tb11", bi.tb11a.isChecked() ? "1" : bi.tb11b.isChecked() ? "2"
                : bi.tb11d.isChecked() ? "3" : bi.tb11e.isChecked() ? "4" : "0");

        sB.put("tb12", bi.tb12a.isChecked() ? "1" : bi.tb12b.isChecked() ? "2" : "0");*/
        sB.put("appver", MainApp.versionName + "." + MainApp.versionCode);

       /* if (bi.tb12a.isChecked()) {
            MainApp.isRsvp = true;
        }
*/
        MainApp.ageRdo = bi.tbdob.indexOfChild(findViewById(bi.tbdob.getCheckedRadioButtonId())) + 1;
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

            MainApp.familyMembersList.add(new FamilyMembersContract(bi.tb02.getText().toString(),
                    ageInyears < 2 ? "3" : ageInyears < 5 ? "1" :
                            (bi.tb04b.isChecked()
                                    && (ageInyears > 15 && ageInyears < 49) ? "2" : "0")
                    , String.valueOf(MainApp.counter),
                    bi.tb07.getText().toString().isEmpty() ?
                            bi.tb08m.getText().toString() + "-" + bi.tb08y.getText().toString() :
                            bi.tb07.getText().toString()));
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean formValidation() {
        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

//        01
        if (bi.tb02.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.tb02), Toast.LENGTH_SHORT).show();
            bi.tb02.setError("This data is Required! ");    // Set Error on last radio button
            bi.tb02.requestFocus();
            Log.i(TAG, "tb02: This data is Required!");
            return false;
        } else {
            bi.tb02.setError(null);
        }

//        02
       /* if (bi.tb03.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string..tb03), Toast.LENGTH_SHORT).show();
            bi.tb0388.setError("This data is Required!");    // Set Error on last radio button
            bi.tb03a.setFocusable(true);
            bi.tb03a.setFocusableInTouchMode(true);
            bi.tb03a.requestFocus();
            Log.i(TAG, "bi.tb03: This data is Required!");
            return false;
        } else {
            bi.tb0388.setError(null);
        }

        if (bi.tb0388.isChecked() && bi.tb0388x.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.other), Toast.LENGTH_SHORT).show();
            bi.tb0388x.setError("This data is Required! ");    // Set Error on last radio button
            bi.tb0388x.requestFocus();
            Log.i(TAG, "bi.tb0388x: This data is Required!");
            return false;
        } else {
            bi.tb0388x.setError(null);
        }*/

//        04

//        05
       /* if (bi.tb05.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string..tb05), Toast.LENGTH_SHORT).show();
            bi.tb05.setError("This data is Required! ");    // Set Error on last radio button
            bi.tb05.requestFocus();
            Log.i(TAG, "bi.tb05: This data is Required!");
            return false;
        } else {
            bi.tb05.setError(null);
        }*/

//        06
       /* if (bi.tb06.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string..tb06), Toast.LENGTH_SHORT).show();
            bi.tb06.setError("This data is Required! ");    // Set Error on last radio button
            bi.tb06.requestFocus();
            Log.i(TAG, "bi.tb06: This data is Required!");
            return false;
        } else {
            bi.tb06.setError(null);
        }*/

//        07 & 08
        if (bi.tbdob.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.tbAge), Toast.LENGTH_SHORT).show();
            bi.tbdob01.setError("This data is Required!");    // Set Error on last radio button
            bi.tbdob01.setFocusable(true);
            bi.tbdob01.setFocusableInTouchMode(true);
            bi.tbdob01.requestFocus();
            Log.i(TAG, "bi.tbdob: This data is Required!");
            return false;
        } else {
            bi.tbdob01.setError(null);
        }

        if (bi.tbdob01.isChecked() && bi.tb07.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.tb07), Toast.LENGTH_SHORT).show();
            bi.tb07.setError("This data is Required! ");    // Set Error on last radio button
            bi.tb07.requestFocus();
            Log.i(TAG, "bi.tb07: This data is Required!");
            return false;
        } else {
            bi.tb07.setError(null);
        }

        if (bi.tbAge02.isChecked()) {
            if (bi.tb08y.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.year), Toast.LENGTH_SHORT).show();
                bi.tb08y.setError("This data is Required! ");    // Set Error on last radio button
                bi.tb08y.requestFocus();
                Log.i(TAG, "bi.tb08y: This data is Required!");
                return false;
            } else {
                bi.tb08y.setError(null);
            }

            if (bi.tb08m.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.month), Toast.LENGTH_SHORT).show();
                bi.tb08m.setError("This data is Required! ");    // Set Error on last radio button
                bi.tb08m.requestFocus();
                Log.i(TAG, "bi.tb08m: This data is Required!");
                return false;
            } else {
                bi.tb08m.setError(null);
            }

            if (Integer.parseInt(bi.tb08y.getText().toString()) < 0) {
                Toast.makeText(this, "ERROR(invalid): " + getString(R.string.year), Toast.LENGTH_SHORT).show();
                bi.tb08y.setError("Greater then 0! ");    // Set Error on last radio button
                bi.tb08y.requestFocus();
                Log.i(TAG, "bi.tb08y: Greater then 0!");
                return false;
            } else {
                bi.tb08y.setError(null);
            }

            if (Integer.parseInt(bi.tb08m.getText().toString()) < 0 || Integer.parseInt(bi.tb08m.getText().toString()) > 11) {
                Toast.makeText(this, "ERROR(invalid): " + getString(R.string.month), Toast.LENGTH_SHORT).show();
                bi.tb08m.setError("Range from 0 - 11! ");    // Set Error on last radio button
                bi.tb08m.requestFocus();
                Log.i(TAG, "bi.tb08m: Range from 0 - 11!");
                return false;
            } else {
                bi.tb08m.setError(null);
            }

            if (Integer.parseInt(bi.tb08y.getText().toString()) == 0 && Integer.parseInt(bi.tb08m.getText().toString()) == 0) {
                Toast.makeText(this, "ERROR(invalid): " + getString(R.string.year), Toast.LENGTH_SHORT).show();
                bi.tb08y.setError("Greater then 0! ");    // Set Error on last radio button
                bi.tb08y.requestFocus();
                Log.i(TAG, "bi.tb08y: Greater then 0!");
                return false;
            } else {
                bi.tb08y.setError(null);
            }
        }

//        09
        if (bi.tb09.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.tb09), Toast.LENGTH_SHORT).show();
            bi.tb09.setError("This data is Required! ");    // Set Error on last radio button
            bi.tb09.requestFocus();
            Log.i(TAG, "bi.tb09: This data is Required!");
            return false;
        } else {
            bi.tb09.setError(null);
        }

        if (!bi.tb09.getText().toString().equals("NA") && !bi.tb08y.getText().toString().isEmpty()) {

            if (Integer.parseInt(bi.tb08y.getText().toString())
                    <= Integer.parseInt(bi.tb09.getText().toString()) &&
                    !bi.tb09.getText().toString().equals("888") && !bi.tb09.getText().toString().equals("999") && !bi.tb09.getText().toString().equals("777")) {
                Toast.makeText(this, "Age and years of education cannot be same or Years of education cannot be greater than age ", Toast.LENGTH_SHORT).show();
                bi.tb09.setError("Age and years of education cannot be same or Years of education cannot be greater than age!");    // Set Error on last radio button
                bi.tb09.requestFocus();
                Log.i(TAG, "bi.tb09: Age and years of education cannot be same or Years of education cannot be greater than age!");
                return false;
            } else {
                bi.tb09.setError(null);
            }

        }


        if (!bi.tb09.getText().toString().equals("NA")) {

            if (Integer.parseInt(bi.tb09.getText().toString()) < 0 && (!bi.tb09.getText().toString().equals("888") && !bi.tb09.getText().toString().equals("999") && !bi.tb09.getText().toString().equals("777"))
                    || Integer.parseInt(bi.tb09.getText().toString()) > 20 && (!bi.tb09.getText().toString().equals("888") && !bi.tb09.getText().toString().equals("999") && !bi.tb09.getText().toString().equals("777"))) {
                Toast.makeText(this, "Years of education cannot be less than 0 and cannot be greater than 20 ", Toast.LENGTH_SHORT).show();
                bi.tb09.setError("Years of education cannot be less than 0 and cannot be greater than 20!");    // Set Error on last radio button
                bi.tb09.requestFocus();
                Log.i(TAG, "bi.tb09: Years of education cannot be less than 0 and cannot be greater than 20!");
                return false;
            } else {
                bi.tb09.setError(null);
            }

        }

        if (ageInyears > 5) {



//        10
            if (bi.tb10.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.tb10), Toast.LENGTH_SHORT).show();
                bi.tb10999.setError("This data is Required!");    // Set Error on last radio button
                bi.tb10a.setFocusable(true);
                bi.tb10a.setFocusableInTouchMode(true);
                bi.tb10a.requestFocus();
                Log.i(TAG, "bi.tb10: This data is Required!");
                return false;
            } else {
                bi.tb10999.setError(null);
            }
        }

        if (ageInyears > 14) {
//        11
          /*  if (bi.tb11.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string..tb11), Toast.LENGTH_SHORT).show();
                bi.tb11e.setError("This data is Required!");    // Set Error on last radio button
                bi.tb11a.setFocusableInTouchMode(true);
                bi.tb11a.setFocusable(true);
                bi.tb11a.requestFocus();
                Log.i(TAG, "bi.tb11: This data is Required!");
                return false;
            } else {
                bi.tb11e.setError(null);
            }*/
        }
        if(ageInyears <= 5){
            if (bi.tb04.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.tb04), Toast.LENGTH_SHORT).show();
                bi.tb04b.setError("This data is Required!");    // Set Error on last radio button
                bi.tb04a.setFocusableInTouchMode(true);
                bi.tb04a.setFocusable(true);
                bi.tb04a.requestFocus();
                Log.i(TAG, "bi.tb04: This data is Required!");
                return false;
            } else {
                bi.tb04b.setError(null);
            }
        }


     /*   if (bi.tb04b.isChecked() && bi.tb11b.isChecked() && !MainApp.isRsvp) {
            if (bi.tb12.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.tiRespondentMother), Toast.LENGTH_SHORT).show();
                bi.tb12a.setError("This data is Required!");    // Set Error on last radio button
                bi.tb12a.setFocusable(true);
                bi.tb12a.setFocusableInTouchMode(true);
                bi.tb12a.requestFocus();
                Log.i(TAG, "bi.tb12a: Th  is data is Required!");
                return false;
            } else {
                bi.tb12a.setError(null);
            }
        }*/


        return true;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }

}
