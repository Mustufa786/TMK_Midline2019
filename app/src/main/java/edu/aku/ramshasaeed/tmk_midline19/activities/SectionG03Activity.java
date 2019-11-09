package edu.aku.ramshasaeed.tmk_midline19.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import edu.aku.ramshasaeed.tmk_midline19.R;
import edu.aku.ramshasaeed.tmk_midline19.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline19.core.JsonUtils;
import edu.aku.ramshasaeed.tmk_midline19.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline19.databinding.ActivitySectionG03Binding;

public class SectionG03Activity extends AppCompatActivity {
    private static final String TAG = SectionIActivity.class.getSimpleName();
    ActivitySectionG03Binding bi;
    public RadioGroup.OnCheckedChangeListener measles1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.measles1M01.isChecked() || bi.measles1C01.isChecked()) {
                bi.fldGrpov13.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc13.setLayoutParams(params);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1000);
                bi.sc9M.setLayoutParams(params2);
            } else if (bi.measles1C02.isChecked() || bi.measles1M02.isChecked()) {
                bi.fldGrpov13.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc13.setLayoutParams(params);
                bi.sc9M.setLayoutParams(params);
                bi.measles1Pov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener measles2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.measles2M01.isChecked() || bi.measles2C01.isChecked()) {
                bi.fldGrpov14.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc14.setLayoutParams(params);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1000);
                bi.sc15M.setLayoutParams(params2);
            } else if (bi.measles2C02.isChecked() || bi.measles2M02.isChecked()) {
                bi.fldGrpov14.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc14.setLayoutParams(params);
                bi.sc15M.setLayoutParams(params);
                bi.measles2Pov.clearCheck();

            }
        }
    };
    @BindViews({R.id.fldGrpMeasles1M, R.id.fldGrpMeasles2M})
    List<LinearLayout> fldGrpMother;
    @BindViews({R.id.measles1C, R.id.measles2C})
    List<RadioGroup> rdoCard;
    @BindViews({R.id.measles1C, R.id.measles1M})
    List<RadioGroup> grpMeasles1;
    @BindViews({R.id.measles2C, R.id.measles2M})
    List<RadioGroup> grpMeasles2;
    boolean ti03;
    @BindViews({R.id.fldGrpmeasles1C, R.id.fldGrpmeasles2C})
    List<LinearLayout> fldGrpCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_g03);
        bi.setCallback(this);
        this.setTitle(getResources().getString(R.string.tgheading));
        ButterKnife.bind(this);

        ti03 = getIntent().getBooleanExtra("ti03", false);

        if (ti03) {
            for (LinearLayout le : fldGrpCard) {
                le.setVisibility(View.VISIBLE);
            }
            for (LinearLayout le : fldGrpMother) {
                le.setVisibility(View.VISIBLE);
            }
        } else {

            for (LinearLayout le : fldGrpCard) {
                le.setVisibility(View.GONE);
                for (RadioGroup re : rdoCard) {
                    re.clearCheck();
                }
            }
            for (LinearLayout le : fldGrpMother) {
                le.setVisibility(View.VISIBLE);
            }

        }


        // Measles 1

        for (RadioGroup rd : grpMeasles1) {
            rd.setOnCheckedChangeListener(measles1);
        }

        // Measles 2
        for (RadioGroup rd : grpMeasles2) {
            rd.setOnCheckedChangeListener(measles2);
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }

    public void onBtnContinueClick() {

        if (ValidateForm()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {

                //finish();

                if (MainApp.imsCount < MainApp.totalImsCount) {

                    finish();

                    MainApp.imsCount++;

                    MainApp.lstChild.remove(MainApp.positionIm);
                    MainApp.childsMap.remove(MainApp.positionIm);
                    MainApp.flag = false;
                    Intent secNext = new Intent(this, SectionGActivity.class);
                    startActivity(secNext);


                } else {
                    MainApp.imsCount = 1;

                    Intent secNext = new Intent(this, SectionHActivity.class);
                    startActivity(secNext);
                }

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateChildG2();

        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void SaveDraft() throws JSONException {

        JSONObject sI = new JSONObject();

        // OPV 3 at 14 weeks
        sI.put("measles1M", bi.measles1M01.isChecked() ? "1" : bi.measles1M02.isChecked() ? "2" : "0");
           /* sI.put("measles1datenr", bi.measles1datenr.isChecked() ? "1" : "0");
            sI.put("measles1dateM_mon",bi.measles1DateMMon.getText().toString());
            sI.put("measles1dateM_year", bi.measles1DateMYear.getText().toString());*/
        sI.put("measles1C", bi.measles1C01.isChecked() ? "1" : bi.measles1C02.isChecked() ? "2" : "0");
          /*  sI.put("measles1datenp", bi.measles1datenp.isChecked() ? "1" : "0");

            sI.put("measles1Date", bi.measles1Date.getText().toString());*/
        sI.put("measles1Pov", bi.measles1Pova.isChecked() ? "1" : bi.measles1Povb.isChecked() ? "2" : bi.measles1Povc.isChecked() ? "3"
                : bi.measles1Povd.isChecked() ? "98" : "0");
        // IPV at 14 weeks
        sI.put("measles2M", bi.measles2M01.isChecked() ? "1" : bi.measles2M02.isChecked() ? "2" : "0");
          /*  sI.put("measles2datenr", bi.measles2datenr.isChecked() ? "1" : "0");
            sI.put("measles2dateM_mon", bi.measles2DateMMon.getText().toString());
            sI.put("measles2dateM_year", bi.measles2DateMYear.getText().toString());*/
        sI.put("measles2C", bi.measles2C01.isChecked() ? "1" : bi.measles2C02.isChecked() ? "2" : "0");
          /*  sI.put("measles2datenp", bi.measles2datenp.isChecked() ? "1" : "0");

            sI.put("measles2Date", bi.measles2Date.getText().toString());*/
        sI.put("measles2Pov", bi.measles2Pova.isChecked() ? "1" : bi.measles2Povb.isChecked() ? "2" : bi.measles2Povc.isChecked() ? "3"
                : bi.measles2Povd.isChecked() ? "98" : "0");

        MainApp.ims.setsI(String.valueOf(JsonUtils.mergeJSONObjects(new JSONObject(MainApp.ims.getsI()), sI)));

    }

    public boolean ValidateForm() {

        //============ measles1 / Mother ==========
        if (bi.measles1M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.measles1), Toast.LENGTH_SHORT).show();
            bi.measles1M02.setError("This data is Required!");
            Log.i(TAG, "measles1M: This data is Required!");
            bi.measles1M02.setFocusable(true);
            bi.measles1M02.setFocusableInTouchMode(true);
            bi.measles1M02.requestFocus();
            return false;
        } else {
            bi.measles1M02.setError(null);
        }

               /* if (bi.measles1M01.isChecked() && !bi.measles1datenr.isChecked()) {
                    if (bi.measles1DateMYear.getText().toString().isEmpty() && bi.measles1DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.measles1) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.measles1DateMYear.setError("This data is Required!");
                        bi.measles1DateMMon.setError("This data is Required!");
                        Log.i(TAG, "measles1MDate: This data is Required!");
                        bi.measles1DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.measles1DateMYear.setError(null);
                        bi.measles1DateMMon.setError(null);
                    }
                }*/

        if (ti03) {
            //============ measles1 / Card Present ==========
            if (bi.measles1C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.measles1), Toast.LENGTH_SHORT).show();
                bi.measles1C02.setError("This data is Required!");
                Log.i(TAG, "measles1C: This data is Required!");
                bi.measles1C02.setFocusable(true);
                bi.measles1C02.setFocusableInTouchMode(true);
                bi.measles1C02.requestFocus();
                return false;
            } else {
                bi.measles1C02.setError(null);
            }

                /*    if (bi.measles1C01.isChecked() && !bi.measles1datenp.isChecked()) {
                        if (bi.measles1Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.measles1) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.measles1Date.setError("This data is Required!");
                            Log.i(TAG, "measles1Date: This data is Required!");
                            bi.measles1Date.requestFocus();
                            return false;
                        } else {
                            bi.measles1Date.setError(null);
                        }
                    }*/
        }

        if (bi.measles1C01.isChecked() || bi.measles1M01.isChecked()) {
            if (bi.measles1Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.measles1) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.measles1Pova.setError("This data is Required!");
                Log.i(TAG, "measles1Pov: This data is Required!");
                bi.measles1Pov.setFocusable(true);
                bi.measles1Pov.setFocusableInTouchMode(true);
                bi.measles1Pov.requestFocus();
                return false;
            } else {
                bi.measles1Pova.setError(null);
            }
        }

        //============ measles2 / Mother ==========
        if (bi.measles2M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.measles2), Toast.LENGTH_SHORT).show();
            bi.measles2M02.setError("This data is Required!");
            Log.i(TAG, "measles2M: This data is Required!");
            bi.measles2M02.setFocusable(true);
            bi.measles2M02.setFocusableInTouchMode(true);
            bi.measles2M02.requestFocus();
            return false;
        } else {
            bi.measles2M02.setError(null);
        }

               /* if (bi.measles2M01.isChecked() && !bi.measles2datenr.isChecked()) {
                    if (bi.measles2DateMYear.getText().toString().isEmpty() && bi.measles1DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.measles2) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.measles2DateMYear.setError("This data is Required!");
                        bi.measles2DateMMon.setError("This data is Required!");
                        Log.i(TAG, "measles2MDate: This data is Required!");
                        bi.measles2DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.measles2DateMYear.setError(null);
                        bi.measles2DateMMon.setError(null);
                    }
                }*/

        if (ti03) {

            //============ measles2 / Card Present ==========
            if (bi.measles2C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.measles2), Toast.LENGTH_SHORT).show();
                bi.measles2C02.setError("This data is Required!");
                Log.i(TAG, "measles2C: This data is Required!");
                bi.measles2C02.setFocusable(true);
                bi.measles2C02.setFocusableInTouchMode(true);
                bi.measles2C02.requestFocus();
                return false;
            } else {
                bi.measles2C02.setError(null);
            }

                   /* if (bi.measles2C01.isChecked() && !bi.measles2datenp.isChecked()) {

                        if (bi.measles2Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.measles2) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.measles2Date.setError("This data is Required!");
                            Log.i(TAG, "measles2Date: This data is Required!");
                            bi.measles2Date.requestFocus();
                            return false;
                        } else {
                            bi.measles2Date.setError(null);
                        }
                    }*/
        }

        if (bi.measles2C01.isChecked() || bi.measles2M01.isChecked()) {
            if (bi.measles2Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.measles2) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.measles2Pova.setError("This data is Required!");
                Log.i(TAG, "measles2Pov: This data is Required!");
                bi.measles2Pova.setFocusable(true);
                bi.measles2Pova.setFocusableInTouchMode(true);
                bi.measles2Pova.requestFocus();
                return false;
            } else {
                bi.measles2Pova.setError(null);
            }
        }
/*
                if (bi.ti04.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "ERROR(empty): " + getString(R.string.ti04), Toast.LENGTH_SHORT).show();
                    bi.ti04888.setError("This data is Required!");
                    Log.i(TAG, "ti04: This data is Required!");
                    bi.ti04a.setFocusable(true);
                    bi.ti04a.setFocusableInTouchMode(true);
                    bi.ti04a.requestFocus();
                    return false;
                } else {
                    bi.ti04888.setError(null);
                }

                if (bi.ti04a.isChecked()) {

                    if (!bi.ti05888.isChecked()) {
                        if (bi.ti05.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.ti05), Toast.LENGTH_SHORT).show();
                            bi.ti05.setError("This data is Required!");
                            bi.ti05.requestFocus();
                            Log.i(TAG, "ti05: This data is Required!");

                            return false;
                        } else {
                            bi.ti05.setError(null);
                        }

                        if (Integer.parseInt(bi.ti05.getText().toString()) < 1) {
                            Toast.makeText(this, "ERROR(invalid): " + getString(R.string.ti05), Toast.LENGTH_SHORT).show();
                            bi.ti05.setError("Zero not allowed");
                            bi.ti05.requestFocus();
                            Log.i(TAG, "ti05: Zero not allowed");
                            return false;
                        } else {
                            bi.ti05.setError(null);
                        }

                    }
                }*/

        return true;
    }

    public void BtnEnd() {
        MainApp.endActivity(this, this);
    }


}
