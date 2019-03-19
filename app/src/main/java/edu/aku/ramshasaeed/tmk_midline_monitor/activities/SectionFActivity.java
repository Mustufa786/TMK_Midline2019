package edu.aku.ramshasaeed.tmk_midline_monitor.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.aku.ramshasaeed.tmk_midline_monitor.R;
import edu.aku.ramshasaeed.tmk_midline_monitor.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline_monitor.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline_monitor.databinding.ActivitySectionFBinding;
import edu.aku.ramshasaeed.tmk_midline_monitor.validation.ClearClass;
import edu.aku.ramshasaeed.tmk_midline_monitor.validation.ValidatorClasss;

import static android.view.View.VISIBLE;

public class SectionFActivity extends AppCompatActivity {
    private static final String TAG = SectionFActivity.class.getName();
    ActivitySectionFBinding bi;
    Map<String, String> childsMap;
    ArrayList<String> lstChild;
    String MotherName, MotherID;

    public void clearFldgrpthb15a() {
        bi.thb15.clearCheck();
        bi.thb19.clearCheck();
        bi.thb20a.setChecked(false);
        bi.thb20b.setChecked(false);
        bi.thb20c.setChecked(false);
        bi.thb20d.setChecked(false);
        bi.thb20e.setChecked(false);
        bi.thb20f.setChecked(false);
        bi.thb20g.setChecked(false);
        bi.thb21.clearCheck();
        bi.thb26.clearCheck();

//        bi.thb31a.setChecked(false);
//        bi.thb31b.setChecked(false);
//        bi.thb31c.setChecked(false);
//        bi.thb31d.setChecked(false);
//        bi.thb31e.setChecked(false);
//        bi.thb31f.setChecked(false);
//        bi.thb31g.setChecked(false);

//        bi.thb32.clearCheck();
//
//        bi.thb32hr.setText(null);
//        bi.thb32d.setText(null);


//        bi.thb33.clearCheck();

//        bi.thb36.setText(null);
//
//        bi.thb24.clearCheck();
//
//        bi.thb25a.setChecked(false);
//        bi.thb25b.setChecked(false);
//        bi.thb25c.setChecked(false);
//        bi.thb25d.setChecked(false);
//        bi.thb25e.setChecked(false);
//        bi.thb25f.setChecked(false);
//        bi.thb25g.setChecked(false);
    }

    public void clearFldgrpthb15() {
        bi.thb15.clearCheck();
        bi.thb19.clearCheck();
        bi.thb20a.setChecked(false);
        bi.thb20b.setChecked(false);
        bi.thb20c.setChecked(false);
        bi.thb20d.setChecked(false);
        bi.thb20e.setChecked(false);
        bi.thb20f.setChecked(false);
        bi.thb20g.setChecked(false);
        bi.thb21.clearCheck();
        bi.thb26.clearCheck();

        bi.thb31.clearCheck();
//        bi.thb31c.setChecked(false);
//        bi.thb31d.setChecked(false);
//        bi.thb31e.setChecked(false);
//        bi.thb31f.setChecked(false);
//        bi.thb31g.setChecked(false);

        bi.thb32.clearCheck();
//
//        bi.thb32hr.setText(null);
//        bi.thb32d.setText(null);


        bi.thb33.clearCheck();
        bi.thb36a.setChecked(false);
        bi.thb36b.setChecked(false);
        bi.thb36c.setChecked(false);
        bi.thb36d.setChecked(false);
        bi.thb36e.setChecked(false);
        bi.thb36f.setChecked(false);
        bi.thb36g.setChecked(false);
        bi.thb36h.setChecked(false);
        bi.thb36i.setChecked(false);
        bi.thb36j.setChecked(false);
        bi.thb36k.setChecked(false);
        bi.thb3696.setChecked(false);

//        bi.thb36.setText(null);
//
//        bi.thb24.clearCheck();
//
//        bi.thb25a.setChecked(false);
//        bi.thb25b.setChecked(false);
//        bi.thb25c.setChecked(false);
//        bi.thb25d.setChecked(false);
//        bi.thb25e.setChecked(false);
//        bi.thb25f.setChecked(false);
//        bi.thb25g.setChecked(false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_f);
        bi.setCallback(this);

        this.setTitle(getResources().getString(R.string.tfheading));
        childsMap = new HashMap<>();
        lstChild = new ArrayList<>();

        childsMap.put("....", "");
        lstChild.add("....");

        for (byte i = 0; i < MainApp.familyMembersList.size(); i++) {
            int Age = Integer.parseInt(MainApp.familyMembersList.get(i).getage());
            if (Age < 5) {
                childsMap.put(MainApp.familyMembersList.get(i).getname(), MainApp.familyMembersList.get(i).getserialNo());
                lstChild.add(MainApp.familyMembersList.get(i).getname());
            }
        }

        bi.thb05.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lstChild));
        bi.thb05.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    MotherID = MainApp.familyMembersList.get(Integer.valueOf(childsMap.get(bi.thb05.getSelectedItem().toString())) - 1).getmotherId();
                    for (int j = 0; j < MainApp.familyMembersList.size(); j++) {
                        if (MainApp.familyMembersList.get(j).getserialNo().equals(MotherID)) {
                            MotherName = MainApp.familyMembersList.get(j).getname();
                        }
                    }
                    bi.motherName.setText(MotherName+"_"+MotherID);
                } else {
                    bi.motherName.setText(null);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        bi.thb01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (bi.thb01a.isChecked()) {
                    bi.fldGrpthb02.setVisibility(VISIBLE);
                    bi.thb02.requestFocus();
                } else {

                    bi.thb02.setText(null);
                    bi.fldGrpthb02.setVisibility(View.GONE);
                }
            }
        });


        bi.thb03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (bi.thb03a.isChecked()) {
                    bi.fldGrpthb04.setVisibility(VISIBLE);

                } else {

                    /*bi.thb04.setText(null);
                     *//*bi.thb05.getChildAt(0).toString();*//*
                    bi.thb06.setText(null);

                    bi.thb07.clearCheck();

                    bi.thb08a.setChecked(false);
                    bi.thb08b.setChecked(false);
                    bi.thb08c.setChecked(false);
                    bi.thb08d.setChecked(false);
                    bi.thb08e.setChecked(false);
                    bi.thb08f.setChecked(false);
                    bi.thb08g.setChecked(false);
                    bi.thb08h.setChecked(false);
                    bi.thb0896.setChecked(false);


                    bi.thb0896x.setText(null);

                    bi.thb09.setText(null);
                    bi.thb10.clearCheck();
                    bi.thb11.clearCheck();

                    bi.thb12a.setChecked(false);
                    bi.thb12b.setChecked(false);
                    bi.thb12c.setChecked(false);
                    bi.thb12d.setChecked(false);
                    bi.thb12e.setChecked(false);
                    bi.thb12f.setChecked(false);
                    bi.thb12g.setChecked(false);
                    bi.thb12h.setChecked(false);

                    bi.thb13.clearCheck();
                    bi.thb14.clearCheck();
                    bi.thb15.clearCheck();
                    bi.thb19.clearCheck();

                    bi.thb20a.setChecked(false);
                    bi.thb20b.setChecked(false);
                    bi.thb20c.setChecked(false);
                    bi.thb20d.setChecked(false);
                    bi.thb20e.setChecked(false);
                    bi.thb20f.setChecked(false);
                    bi.thb20g.setChecked(false);

                    bi.thb21.clearCheck();

                    bi.thb21hr.setText(null);
                    bi.thb21d.setText(null);

                    bi.thb32.clearCheck();

                    bi.thb33.clearCheck();

                    bi.thb36a.setChecked(false);
                    bi.thb36b.setChecked(false);
                    bi.thb36c.setChecked(false);
                    bi.thb36d.setChecked(false);
                    bi.thb36e.setChecked(false);
                    bi.thb36f.setChecked(false);
                    bi.thb36g.setChecked(false);
                    bi.thb36h.setChecked(false);
                    bi.thb36i.setChecked(false);
                    bi.thb36j.setChecked(false);
                    bi.thb36k.setChecked(false);
                    bi.thb3696.setChecked(false);*/
                    bi.fldGrpthb04.setVisibility(View.GONE);
                    ClearClass.ClearAllFields(bi.fldGrpthb04, null);
                }
            }
        });


        bi.thb07.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (bi.thb07a.isChecked()) {

                    bi.thb08a.setChecked(false);
                    bi.thb08b.setChecked(false);
                    bi.thb08c.setChecked(false);
                    bi.thb08d.setChecked(false);
                    bi.thb08e.setChecked(false);
                    bi.thb08f.setChecked(false);
                    bi.thb08g.setChecked(false);
                    bi.thb08h.setChecked(false);
                    bi.thb0896.setChecked(false);

                    bi.thb0896x.setText(null);

                    bi.fldGrpth08.setVisibility(View.GONE);
                    bi.fldGrpth08a.setVisibility(VISIBLE);

                } else {

                    bi.thb09.setText(null);
                    bi.thb10.clearCheck();
                    bi.thb11.clearCheck();

                    bi.thb12a.setChecked(false);
                    bi.thb12b.setChecked(false);
                    bi.thb12c.setChecked(false);
                    bi.thb12d.setChecked(false);
                    bi.thb12e.setChecked(false);
                    bi.thb12f.setChecked(false);
                    bi.thb12g.setChecked(false);
                    bi.thb12h.setChecked(false);

                    bi.thb13.clearCheck();
                    bi.thb14.clearCheck();
                    bi.thb15.clearCheck();

                    bi.thb19.clearCheck();

                    bi.thb20a.setChecked(false);
                    bi.thb20b.setChecked(false);
                    bi.thb20c.setChecked(false);
                    bi.thb20d.setChecked(false);
                    bi.thb20e.setChecked(false);
                    bi.thb20f.setChecked(false);
                    bi.thb20g.setChecked(false);

                    bi.thb21.clearCheck();
                    bi.thb21hr.setText(null);
                    bi.thb21d.setText(null);

                    bi.thb32.clearCheck();

                    bi.thb33.clearCheck();

                    bi.thb36a.setChecked(false);
                    bi.thb36b.setChecked(false);
                    bi.thb36c.setChecked(false);
                    bi.thb36d.setChecked(false);
                    bi.thb36e.setChecked(false);
                    bi.thb36f.setChecked(false);
                    bi.thb36g.setChecked(false);
                    bi.thb36h.setChecked(false);
                    bi.thb36i.setChecked(false);
                    bi.thb36j.setChecked(false);
                    bi.thb36k.setChecked(false);
                    bi.thb3696.setChecked(false);

                    bi.fldGrpth08.setVisibility(VISIBLE);
                    bi.fldGrpth08a.setVisibility(View.GONE);
                }
            }
        });


        bi.thb0896.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (bi.thb0896.isChecked()) {
                    bi.thb0896x.setVisibility(VISIBLE);
                    bi.thb0896x.requestFocus();
                } else {
                    bi.thb0896x.setText(null);
                    bi.thb0896x.setVisibility(View.GONE);
                }
            }
        });


        bi.thb10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.thb10a && bi.thb14b.isChecked()) {

                    bi.fldGrpth15.setVisibility(VISIBLE);

                    bi.fldGrpth15a.setVisibility(View.GONE);
                    clearFldgrpthb15a();

                } else if (bi.thb14b.isChecked()) {
                    clearFldgrpthb15a();

                    bi.fldGrpth15.setVisibility(VISIBLE);
                    bi.fldGrpth15a.setVisibility(View.GONE);
                } else {
                    bi.fldGrpth15.setVisibility(VISIBLE);
                }
            }
        });

        bi.thb14.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.thb14b && bi.thb10a.isChecked()) {

                    bi.fldGrpth15.setVisibility(VISIBLE);

                    bi.fldGrpth15a.setVisibility(View.GONE);
                    clearFldgrpthb15a();
//                    fldGrpth26.setVisibility(View.VISIBLE);

                } else if (i == R.id.thb14b) {
                    clearFldgrpthb15a();
                    bi.fldGrpth15.setVisibility(VISIBLE);
                    bi.fldGrpth15a.setVisibility(View.GONE);
                } else {
                    bi.fldGrpth15.setVisibility(VISIBLE);
                    bi.fldGrpth15a.setVisibility(VISIBLE);
                }
            }
        });


        bi.thb21.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (bi.thb21a.isChecked()) {

                    bi.thb21d.setText(null);
                    bi.thb21d.setVisibility(View.GONE);

                    /*bi.thb32.clearCheck();
                    bi.thb__.setText(null);
                    bi.thb33.clearCheck();

                    bi.thb36a.setChecked(false);
                    bi.thb36b.setChecked(false);
                    bi.thb36c.setChecked(false);
                    bi.thb36d.setChecked(false);
                    bi.thb36e.setChecked(false);
                    bi.thb36f.setChecked(false);
                    bi.thb36g.setChecked(false);

                    bi.thb26.clearCheck();*/


                    bi.fldGrpth22.setVisibility(VISIBLE);
                    bi.fldGrptbh23.setVisibility(VISIBLE);
//                    fldGrpth26.setVisibility(View.VISIBLE);
                    bi.fldGrptbh21.setVisibility(VISIBLE);

                    bi.thb21hr.setVisibility(VISIBLE);
                    bi.thb21hr.requestFocus();

                } else if (bi.thb21b.isChecked()) {
                    bi.thb21hr.setText(null);
                    bi.thb21hr.setVisibility(View.GONE);

                    /*bi.thb32.clearCheck();
                    bi.thb__.setText(null);
                    bi.thb33.clearCheck();

                    bi.thb36a.setChecked(false);
                    bi.thb36b.setChecked(false);
                    bi.thb36c.setChecked(false);
                    bi.thb36d.setChecked(false);
                    bi.thb36e.setChecked(false);
                    bi.thb36f.setChecked(false);
                    bi.thb36g.setChecked(false);

                    bi.thb26.clearCheck();*/

                    bi.fldGrpth22.setVisibility(VISIBLE);
                    bi.fldGrptbh23.setVisibility(VISIBLE);
                    bi.fldGrptbh21.setVisibility(View.VISIBLE);

//                    fldGrpth26.setVisibility(View.VISIBLE);

                    bi.thb21d.setVisibility(VISIBLE);
                    bi.thb21d.requestFocus();

                } else if (bi.thb21c.isChecked()) {


//                    bi.thb32.clearCheck();

//                    bi.thb33.clearCheck();

                   /* bi.thb36a.setChecked(false);
                    bi.thb36b.setChecked(false);
                    bi.thb36c.setChecked(false);
                    bi.thb36d.setChecked(false);
                    bi.thb36e.setChecked(false);
                    bi.thb36f.setChecked(false);
                    bi.thb36g.setChecked(false);
                    bi.thb36h.setChecked(false);
                    bi.thb36i.setChecked(false);
                    bi.thb36j.setChecked(false);
                    bi.thb36k.setChecked(false);
                    bi.thb3696.setChecked(false);*/
//                    bi.thb26.clearCheck();


                    bi.thb21hr.setText(null);
                    bi.thb21hr.setVisibility(View.GONE);

                    bi.thb21d.setText(null);
                    bi.thb21d.setVisibility(View.GONE);

                    bi.fldGrpth22.setVisibility(View.VISIBLE);
                    bi.fldGrptbh23.setVisibility(View.VISIBLE);
                    bi.fldGrptbh21.setVisibility(View.GONE);
                    bi.thb26.clearCheck();

//                    fldGrpth26.setVisibility(View.GONE);
                }
            }
        });

/*
        bi.thb32.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (bi.thb32b.isChecked()) {
                    bi.thb33.clearCheck();

                    bi.thb36a.setChecked(false);
                    bi.thb36b.setChecked(false);
                    bi.thb36c.setChecked(false);
                    bi.thb36d.setChecked(false);
                    bi.thb36e.setChecked(false);
                    bi.thb36f.setChecked(false);
                    bi.thb36g.setChecked(false);
                    bi.thb36h.setChecked(false);
                    bi.thb36i.setChecked(false);
                    bi.thb36j.setChecked(false);
                    bi.thb36k.setChecked(false);
                    bi.thb3696.setChecked(false);

                    bi.fldGrpth23.setVisibility(View.GONE);
                    bi.fldGrptbh23.setVisibility(View.GONE);

                } else {
                    bi.fldGrpth23.setVisibility(VISIBLE);
                    bi.fldGrptbh23.setVisibility(VISIBLE);
                }
            }
        });

        bi.thb33.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (bi.thb33a.isChecked()) {

                    bi.thb36a.setChecked(false);
                    bi.thb36b.setChecked(false);
                    bi.thb36c.setChecked(false);
                    bi.thb36d.setChecked(false);
                    bi.thb36e.setChecked(false);
                    bi.thb36f.setChecked(false);
                    bi.thb36g.setChecked(false);
                    bi.thb36h.setChecked(false);
                    bi.thb36i.setChecked(false);
                    bi.thb36j.setChecked(false);
                    bi.thb36k.setChecked(false);
                    bi.thb3696.setChecked(false);

                    bi.fldGrptbh23.setVisibility(View.GONE);

                } else {
                    bi.fldGrptbh23.setVisibility(VISIBLE);
                }
            }
        });
        */


    }


    public void BtnContinue() {

        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (UpdateDB()) {
                Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();

                finish();

                if (MainApp.totalImsCount > 0) {
                    Intent secNext = new Intent(this, SectionGActivity.class);
                    startActivity(secNext);
                } else {
                    startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
                }
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void BtnEnd() {
        Toast.makeText(this, "Starting Form Ending Section", Toast.LENGTH_SHORT).show();

        MainApp.endActivity(this, this);
    }


    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSF();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    private boolean formValidation() {


      /*  //        00
        if (bi.thb00.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.tiRespondentMother), Toast.LENGTH_SHORT).show();
            bi.thb00a.setError("This data is Required!");    // Set Error on last radio button
            Log.i(TAG, "thb00: This data is Required!");
            bi.thb00a.setFocusable(true);
            bi.thb00a.setFocusableInTouchMode(true);
            bi.thb00a.requestFocus();
            return false;
        } else {
            bi.thb00a.setError(null);
        }

*/
        //        01
        if (bi.thb01.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb01), Toast.LENGTH_SHORT).show();
            bi.thb01a.setError("This data is Required!");    // Set Error on last radio button
            Log.i(TAG, "thb01: This data is Required!");
            bi.thb01a.setFocusable(true);
            bi.thb01a.setFocusableInTouchMode(true);
            bi.thb01a.requestFocus();
            return false;
        } else {
            bi.thb01a.setError(null);
        }


        if (bi.thb01a.isChecked()) {

            //        02
            if (bi.thb02.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb02), Toast.LENGTH_SHORT).show();
                bi.thb02.setError("This data is Required!");    // Set Error on last radio button
                Log.i(TAG, "thb02: This data is Required!");
                bi.thb02.requestFocus();
                return false;
            } else {
                bi.thb02.setError(null);
            }

            if (!bi.thb02.getText().toString().isEmpty()) {

                if (Integer.parseInt(bi.thb02.getText().toString()) < 0 || Integer.valueOf(bi.thb02.getText().toString()) > MainApp.TotalChildCount) {
                    Toast.makeText(this, "How many children had fever during last two weeks in the household", Toast.LENGTH_SHORT).show();
                    bi.thb02.setError("Data Range is " + MainApp.TotalChildCount);
                    Log.i(TAG, "thb02: This data is Required!");
                    bi.thb02.requestFocus();
                    return false;
                } else {
                    bi.thb02.setError(null);
                }
            }

        }


        //        03
        if (bi.thb03.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb03), Toast.LENGTH_SHORT).show();
            bi.thb03a.setError("This data is Required!");    // Set Error on last radio button
            Log.i(TAG, "thb03: This data is Required!");
            bi.thb03a.setFocusable(true);
            bi.thb03a.setFocusableInTouchMode(true);
            bi.thb03a.requestFocus();
            return false;
        } else {
            bi.thb03a.setError(null);
        }


        if (bi.thb03a.isChecked()) {

            //        04
            if (bi.thb04.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb04), Toast.LENGTH_SHORT).show();
                bi.thb04.setError("This data is Required!");    // Set Error on last radio button
                Log.i(TAG, "thb04: This data is Required!");
                bi.thb04.requestFocus();
                return false;
            } else {
                bi.thb04.setError(null);
            }


            if (!bi.thb04.getText().toString().isEmpty()) {

                if (Integer.parseInt(bi.thb04.getText().toString()) < 0 || Integer.valueOf(bi.thb04.getText().toString()) > MainApp.TotalChildCount) {
                    Toast.makeText(this, "Number of childrens must be greater than 0", Toast.LENGTH_SHORT).show();
                    bi.thb04.setError("Data Range is " + MainApp.TotalChildCount);
                    Log.i(TAG, "thb04: This data is Required!");
                    bi.thb04.requestFocus();
                    return false;
                } else {
                    bi.thb04.setError(null);
                }

            }


            //        05
          /*  if (bi.thb05.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb05), Toast.LENGTH_SHORT).show();
                bi.thb05.setError("This data is Required!");    // Set Error on last radio button
                Log.i(TAG, "thb05: This data is Required!");
                return false;
            } else {
                bi.thb05.setError(null);
            }*/
            if (!ValidatorClasss.EmptySpinner(this, bi.thb05, getString(R.string.thb05))) {
                return false;
            }
          /*  if (bi.thb05.getSelectedItem() == "....") {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.thb05), Toast.LENGTH_SHORT).show();
                ((TextView) bi.thb05.getSelectedView()).setText("This Data is Required");
                ((TextView) bi.thb05.getSelectedView()).setTextColor(Color.RED);
                bi.thb05.requestFocus();
                Log.i(TAG, "thb05: This Data is Required!");
                return false;
            } else {
                ((TextView) bi.thb05.getSelectedView()).setError(null);
            }*/


            //        06
            if (bi.thb06.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb06), Toast.LENGTH_SHORT).show();
                bi.thb06.setError("This data is Required!");    // Set Error on last radio button
                Log.i(TAG, "thb06: This data is Required!");
                bi.thb06.requestFocus();
                return false;
            } else {
                bi.thb06.setError(null);
            }


            if (bi.thb06.getText().toString().isEmpty()) {

                if (Integer.parseInt(bi.thb06.getText().toString()) < 0) {
                    Toast.makeText(this, "Number of days must be greater than 0", Toast.LENGTH_SHORT).show();
                    bi.thb06.setError("Must be greater than 0");
                    Log.i(TAG, "thb06: This data is Required!");
                    bi.thb06.requestFocus();
                    return false;
                } else {
                    bi.thb06.setError(null);
                }

            }


            //        07
            if (bi.thb07.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb07), Toast.LENGTH_SHORT).show();
                bi.thb07a.setError("This data is Required!");    // Set Error on last radio button
                Log.i(TAG, "thb07: This data is Required!");
                bi.thb07a.setFocusable(true);
                bi.thb07a.setFocusableInTouchMode(true);
                bi.thb07a.requestFocus();
                return false;
            } else {
                bi.thb07a.setError(null);
            }

            if (bi.thb07b.isChecked() || bi.thb0798.isChecked()) {

                //        08
                if (!bi.thb08a.isChecked()
                        && !bi.thb08b.isChecked()
                        && !bi.thb08c.isChecked()
                        && !bi.thb08d.isChecked()
                        && !bi.thb08e.isChecked()
                        && !bi.thb08f.isChecked()
                        && !bi.thb08g.isChecked()
                        && !bi.thb08h.isChecked()
                        && !bi.thb0896.isChecked()) {
                    Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb08), Toast.LENGTH_LONG).show();
                    bi.thb08a.setError("This data is Required!");
                    Log.i(TAG, "thb08a: This data is Required!");
                    bi.thb08a.setFocusable(true);
                    bi.thb08a.setFocusableInTouchMode(true);
                    bi.thb08a.requestFocus();
                    return false;
                } else {
                    bi.thb08a.setError(null);
                }


                if (bi.thb0896.isChecked()) {
                    //        0888
                    if (bi.thb0896x.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.other), Toast.LENGTH_SHORT).show();
                        bi.thb0896x.setError("This data is Required!");    // Set Error on last radio button
                        Log.i(TAG, "thb0896x: This data is Required!");
                        bi.thb0896x.requestFocus();
                        return false;
                    } else {
                        bi.thb0896x.setError(null);
                    }
                }


            } else {


                //        09
                if (bi.thb09.getText().toString().isEmpty()) {
                    Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb09), Toast.LENGTH_SHORT).show();
                    bi.thb09.setError("This data is Required!");    // Set Error on last radio button
                    Log.i(TAG, "thb09: This data is Required!");
                    bi.thb09.requestFocus();
                    return false;
                } else {
                    bi.thb09.setError(null);
                }


                //        10
                if (bi.thb10.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb10), Toast.LENGTH_SHORT).show();
                    bi.thb10a.setError("This data is Required!");    // Set Error on last radio button
                    Log.i(TAG, "thb10: This data is Required!");
                    bi.thb10a.setFocusable(true);
                    bi.thb10a.setFocusableInTouchMode(true);
                    bi.thb10a.requestFocus();
                    return false;
                } else {
                    bi.thb10a.setError(null);
                }


                //        11
                if (bi.thb11.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb11), Toast.LENGTH_SHORT).show();
                    bi.thb11a.setError("This data is Required!");    // Set Error on last radio button
                    Log.i(TAG, "thb11: This data is Required!");
                    bi.thb11a.setFocusable(true);
                    bi.thb11a.setFocusableInTouchMode(true);
                    bi.thb11a.requestFocus();
                    return false;
                } else {
                    bi.thb11a.setError(null);
                }


                //        12
                if (!bi.thb12a.isChecked()
                        && !bi.thb12b.isChecked()
                        && !bi.thb12c.isChecked()
                        && !bi.thb12d.isChecked()
                        && !bi.thb12e.isChecked()
                        && !bi.thb12f.isChecked()
                        && !bi.thb12g.isChecked()
                        && !bi.thb12h.isChecked()) {
                    Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb12), Toast.LENGTH_LONG).show();
                    bi.thb12a.setError("This data is Required!");
                    Log.i(TAG, "thb12a: This data is Required!");
                    bi.thb12a.setFocusable(true);
                    bi.thb12a.setFocusableInTouchMode(true);
                    bi.thb12a.requestFocus();
                    return false;
                } else {
                    bi.thb12a.setError(null);
                }


                //        13
                if (bi.thb13.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb13), Toast.LENGTH_SHORT).show();
                    bi.thb13a.setError("This data is Required!");    // Set Error on last radio button
                    Log.i(TAG, "thb13: This data is Required!");
                    bi.thb13a.setFocusable(true);
                    bi.thb13a.setFocusableInTouchMode(true);
                    bi.thb13a.requestFocus();
                    return false;
                } else {
                    bi.thb13a.setError(null);
                }


                //        14
                if (bi.thb14.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb14), Toast.LENGTH_SHORT).show();
                    bi.thb14a.setError("This data is Required!");    // Set Error on last radio button
                    bi.thb14a.setFocusable(true);
                    bi.thb14a.setFocusableInTouchMode(true);
                    bi.thb14a.requestFocus();
                    Log.i(TAG, "thb14: This data is Required!");
                    return false;
                } else {
                    bi.thb14a.setError(null);
                }


           /*     if (bi.thb14a.isChecked()) {

                    //        15
                    if (bi.thb15.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb15), Toast.LENGTH_SHORT).show();
                        bi.thb15a.setError("This data is Required!");    // Set Error on last radio button
                        Log.i(TAG, "thb15: This data is Required!");
                        bi.thb15a.setFocusable(true);
                        bi.thb15a.setFocusableInTouchMode(true);
                        bi.thb15a.requestFocus();
                        return false;
                    } else {
                        bi.thb15a.setError(null);
                    }


                    //        19
                    if (bi.thb19.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb19), Toast.LENGTH_SHORT).show();
                        bi.thb19a.setError("This data is Required!");    // Set Error on last radio button
                        Log.i(TAG, "thb19: This data is Required!");
                        bi.thb19a.setFocusable(true);
                        bi.thb19a.setFocusableInTouchMode(true);
                        bi.thb19a.requestFocus();
                        return false;
                    } else {
                        bi.thb19a.setError(null);
                    }


                    //        20
                    if (!bi.thb20a.isChecked()
                            && !bi.thb20b.isChecked()
                            && !bi.thb20c.isChecked()
                            && !bi.thb20d.isChecked()
                            && !bi.thb20e.isChecked()
                            && !bi.thb20f.isChecked()
                            && !bi.thb20g.isChecked()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb20), Toast.LENGTH_LONG).show();
                        bi.thb20a.setError("This data is Required!");
                        Log.i(TAG, "thb20a: This data is Required!");
                        bi.thb20a.setFocusable(true);
                        bi.thb20a.setFocusableInTouchMode(true);
                        bi.thb20a.requestFocus();
                        return false;
                    } else {
                        bi.thb20a.setError(null);
                    }


                    //        21
                    if (bi.thb21.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb21), Toast.LENGTH_SHORT).show();
                        bi.thb21a.setError("This data is Required!");    // Set Error on last radio button
                        Log.i(TAG, "thb21: This data is Required!");
                        bi.thb21a.setFocusable(true);
                        bi.thb21a.setFocusableInTouchMode(true);
                        bi.thb21a.requestFocus();
                        return false;
                    } else {
                        bi.thb21a.setError(null);
                    }


                    if (bi.thb21a.isChecked()) {
                        //        21hr
                        if (bi.thb21hr.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb21), Toast.LENGTH_SHORT).show();
                            bi.thb21hr.setError("This data is Required!");    // Set Error on last radio button
                            Log.i(TAG, "thb21hr: This data is Required!");
                            bi.thb21hr.requestFocus();
                            return false;
                        } else {
                            bi.thb21hr.setError(null);
                        }


                        if (!bi.thb21hr.getText().toString().isEmpty()) {
                            if (Integer.parseInt(bi.thb21hr.getText().toString()) < 0 && Integer.parseInt(bi.thb21hr.getText().toString()) > 23) {
                                Toast.makeText(this, "Hours must be 0 - 23", Toast.LENGTH_SHORT).show();
                                bi.thb21hr.setError("Hours must be 0 - 23");
                                Log.i(TAG, "thb21hr: This data is Required!");
                                bi.thb21hr.requestFocus();
                                return false;
                            } else {
                                bi.thb21hr.setError(null);
                            }
                        }

                    }


                    if (bi.thb21b.isChecked()) {
                        //        21d
                        if (bi.thb21d.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb21), Toast.LENGTH_SHORT).show();
                            bi.thb21d.setError("This data is Required!");    // Set Error on last radio button
                            Log.i(TAG, "thb21d: This data is Required!");
                            bi.thb21d.requestFocus();
                            return false;
                        } else {
                            bi.thb21d.setError(null);
                        }
                    }


//                    if (!bi.thb21c.isChecked()) {

                    //        22
                    if (bi.thb32.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb32), Toast.LENGTH_SHORT).show();
                        bi.thb32a.setError("This data is Required!");    // Set Error on last radio button
                        Log.i(TAG, "thb32: This data is Required!");
                        bi.thb32a.setFocusable(true);
                        bi.thb32a.setFocusableInTouchMode(true);
                        bi.thb32a.requestFocus();
                        return false;
                    } else {
                        bi.thb32a.setError(null);
                    }


//                        if (bi.thb32a.isChecked()) {


                    //        24
                    if (bi.thb33.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb33), Toast.LENGTH_SHORT).show();
                        bi.thb33a.setError("This data is Required!");    // Set Error on last radio button
                        Log.i(TAG, "thb33: This data is Required!");
                        bi.thb33a.setFocusable(true);
                        bi.thb33a.setFocusableInTouchMode(true);
                        bi.thb33a.requestFocus();
                        return false;
                    } else {
                        bi.thb33a.setError(null);
                    }


//                            if (bi.thb33b.isChecked()) {

                    //        25
                    if (!bi.thb36a.isChecked()
                            && !bi.thb36b.isChecked()
                            && !bi.thb36c.isChecked()
                            && !bi.thb36d.isChecked()
                            && !bi.thb36e.isChecked()
                            && !bi.thb36f.isChecked()
                            && !bi.thb36g.isChecked()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.thb36), Toast.LENGTH_LONG).show();
                        bi.thb36a.setError("This data is Required!");
                        bi.thb36a.setFocusable(true);
                        bi.thb36a.setFocusableInTouchMode(true);
                        bi.thb36a.requestFocus();
                        Log.i(TAG, "thb36a: This data is Required!");
                        return false;
                    } else {
                        bi.thb36a.setError(null);
                    }

//                            }


//                        }


//                    }
                }*/


//           nEW VALIDATION ACCORDING TO THE FORM
                if (bi.thb14a.isChecked()) {
                    if (!ValidatorClasss.EmptyRadioButton(this, bi.thb15, bi.thb15a, getString(R.string.thb15))) {
                        return false;
                    }
                    if (!ValidatorClasss.EmptyRadioButton(this, bi.thb19, bi.thb19a, getString(R.string.thb19))) {
                        return false;
                    }
                    if (!ValidatorClasss.EmptyCheckBox(this, bi.thb20, bi.thb20a, getString(R.string.thb20))) {
                        return false;
                    }
                    if (!ValidatorClasss.EmptyRadioButton(this, bi.thb21, bi.thb21a, bi.thb21hr, getString(R.string.thb21) + " hours")) {
                        return false;
                    }
                    if (!ValidatorClasss.EmptyRadioButton(this, bi.thb21, bi.thb21b, bi.thb21d, getString(R.string.thb21) + " Days")) {
                        return false;
                    }
                    if (!bi.thb21c.isChecked()) {

                        if (!ValidatorClasss.EmptyRadioButton(this, bi.thb26, bi.thb26a, getString(R.string.thb26))) {
                            return false;
                        }
                    }
                }
                if (!ValidatorClasss.EmptyRadioButton(this, bi.thb31, bi.thb31a, getString(R.string.thb31))) {
                    return false;
                }
                if (!ValidatorClasss.EmptyRadioButton(this, bi.thb32, bi.thb32a, getString(R.string.thb32))) {
                    return false;
                }
                if (!ValidatorClasss.EmptyRadioButton(this, bi.thb33, bi.thb33a, getString(R.string.thb33))) {
                    return false;
                }
                return ValidatorClasss.EmptyCheckBox(this, bi.thb36, bi.thb3696, bi.thb3696x, getString(R.string.thb36));


            }
        }

        return true;

    }


    private void SaveDraft() throws JSONException {

        Toast.makeText(this, "Saving Draft for This Section", Toast.LENGTH_SHORT).show();

        JSONObject sHB = new JSONObject();

//        sHB.put("thb00", bi.thb00a.isChecked() ? "1" : bi.thb00b.isChecked() ? "2" : "0");
        sHB.put("thb01", bi.thb01a.isChecked() ? "1" : bi.thb01b.isChecked() ? "2" : bi.thb0198.isChecked() ? "98" : "0");
        sHB.put("thb02", bi.thb02.getText().toString());

        sHB.put("thb03", bi.thb03a.isChecked() ? "1" : bi.thb03b.isChecked() ? "2" : bi.thb0398.isChecked() ? "98" : "0");
        sHB.put("thb04", bi.thb04.getText().toString());
        if (bi.thb03a.isChecked()) {
            sHB.put("thb05", bi.thb05.getSelectedItem().toString());
            sHB.put("thb05Serial", childsMap.get(bi.thb05.getSelectedItem().toString()));
            sHB.put("thb05mname", bi.motherName.getText().toString());
        }
        sHB.put("thb06", bi.thb06.getText().toString());
        sHB.put("thb07", bi.thb07a.isChecked() ? "1" : bi.thb07b.isChecked() ? "2" : bi.thb0798.isChecked() ? "98" : "0");


        sHB.put("thb08a", bi.thb08a.isChecked() ? "1" : "0");
        sHB.put("thb08b", bi.thb08b.isChecked() ? "2" : "0");
        sHB.put("thb08c", bi.thb08c.isChecked() ? "3" : "0");
        sHB.put("thb08d", bi.thb08d.isChecked() ? "4" : "0");
        sHB.put("thb08e", bi.thb08e.isChecked() ? "5" : "0");
        sHB.put("thb08f", bi.thb08f.isChecked() ? "6" : "0");
        sHB.put("thb08g", bi.thb08g.isChecked() ? "7" : "0");
        sHB.put("thb08h", bi.thb08h.isChecked() ? "8" : "0");
        sHB.put("thb0896", bi.thb0896.isChecked() ? "96" : "0");

        sHB.put("thb0896x", bi.thb0896x.getText().toString());

        sHB.put("thb09", bi.thb09.getText().toString());

        sHB.put("thb10", bi.thb10a.isChecked() ? "1" : bi.thb10b.isChecked() ? "2"
                : bi.thb10c.isChecked() ? "3"
                : bi.thb10d.isChecked() ? "4"
                : "0");


        sHB.put("thb11", bi.thb11a.isChecked() ? "1" : bi.thb11b.isChecked() ? "2"
                : bi.thb11c.isChecked() ? "3"
                : bi.thb11d.isChecked() ? "4"
                : bi.thb11e.isChecked() ? "5"
                : bi.thb11f.isChecked() ? "6"
                : bi.thb11g.isChecked() ? "7"
                : bi.thb11h.isChecked() ? "8"
                : bi.thb11i.isChecked() ? "9"
                : bi.thb11j.isChecked() ? "10"
                : bi.thb11k.isChecked() ? "11"
                : "0");


        sHB.put("thb12a", bi.thb12a.isChecked() ? "1" : "0");
        sHB.put("thb12b", bi.thb12b.isChecked() ? "2" : "0");
        sHB.put("thb12c", bi.thb12c.isChecked() ? "3" : "0");
        sHB.put("thb12d", bi.thb12d.isChecked() ? "4" : "0");
        sHB.put("thb12e", bi.thb12e.isChecked() ? "5" : "0");
        sHB.put("thb12f", bi.thb12f.isChecked() ? "6" : "0");
        sHB.put("thb12g", bi.thb12g.isChecked() ? "7" : "0");
        sHB.put("thb12h", bi.thb12h.isChecked() ? "8" : "0");

        sHB.put("thb13", bi.thb13a.isChecked() ? "1" : bi.thb13b.isChecked() ? "2" : "0");
        sHB.put("thb14", bi.thb14a.isChecked() ? "1" : bi.thb14b.isChecked() ? "2" : "0");
        sHB.put("thb15", bi.thb15a.isChecked() ? "1" : bi.thb15b.isChecked() ? "2" : bi.thb15c.isChecked() ? "3" : "0");


        sHB.put("thb19", bi.thb19a.isChecked() ? "1" : bi.thb19b.isChecked() ? "2"
                : bi.thb19c.isChecked() ? "3"
                : bi.thb19d.isChecked() ? "4"
                : bi.thb19e.isChecked() ? "5"
                : "0");


        sHB.put("thb20a", bi.thb20a.isChecked() ? "1" : "0");
        sHB.put("thb20b", bi.thb20b.isChecked() ? "2" : "0");
        sHB.put("thb20c", bi.thb20c.isChecked() ? "3" : "0");
        sHB.put("thb20d", bi.thb20d.isChecked() ? "4" : "0");
        sHB.put("thb20e", bi.thb20e.isChecked() ? "5" : "0");
        sHB.put("thb20f", bi.thb20f.isChecked() ? "6" : "0");
        sHB.put("thb20g", bi.thb20g.isChecked() ? "7" : "0");

        sHB.put("thb21", bi.thb21a.isChecked() ? "1" : bi.thb21b.isChecked() ? "2" : bi.thb21c.isChecked() ? "3" : "0");

        sHB.put("thb21hr", bi.thb21hr.getText().toString());
        sHB.put("thb21d", bi.thb21d.getText().toString());

        sHB.put("thb26", bi.thb26a.isChecked() ? "1" : bi.thb26b.isChecked() ? "2" : bi.thb26c.isChecked() ? "3" : "0");
        sHB.put("thb31", bi.thb31a.isChecked() ? "1" : bi.thb31b.isChecked() ? "2" : bi.thb3198.isChecked() ? "98" : "0");

        sHB.put("thb32", bi.thb32a.isChecked() ? "1" : bi.thb32b.isChecked() ? "2" : bi.thb3298.isChecked() ? "98" : "0");

        sHB.put("thb33", bi.thb33a.isChecked() ? "1" : bi.thb33b.isChecked() ? "2" : bi.thb3398.isChecked() ? "98" : "0");


        sHB.put("thb36a", bi.thb36a.isChecked() ? "1" : "0");
        sHB.put("thb36b", bi.thb36b.isChecked() ? "2" : "0");
        sHB.put("thb36c", bi.thb36c.isChecked() ? "3" : "0");
        sHB.put("thb36d", bi.thb36d.isChecked() ? "4" : "0");
        sHB.put("thb36e", bi.thb36e.isChecked() ? "5" : "0");
        sHB.put("thb36f", bi.thb36f.isChecked() ? "6" : "0");
        sHB.put("thb36g", bi.thb36g.isChecked() ? "7" : "0");
        sHB.put("thb36h", bi.thb36h.isChecked() ? "8" : "0");
        sHB.put("thb36i", bi.thb36i.isChecked() ? "9" : "0");
        sHB.put("thb36j", bi.thb36j.isChecked() ? "10" : "0");
        sHB.put("thb36k", bi.thb36k.isChecked() ? "11" : "0");
        sHB.put("thb3696", bi.thb3696.isChecked() ? "96" : "0");
        sHB.put("thb3696x", bi.thb3696x.getText().toString());


//        sHB.put("appver", MainApp.versionName + "." + MainApp.versionCode);

        MainApp.fc.setsF(String.valueOf(sHB));
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }

}

