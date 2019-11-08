package edu.aku.ramshasaeed.tmk_midline19.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import edu.aku.ramshasaeed.tmk_midline19.R;
import edu.aku.ramshasaeed.tmk_midline19.contracts.FamilyMembersContract;
import edu.aku.ramshasaeed.tmk_midline19.contracts.SectionIIMContract;
import edu.aku.ramshasaeed.tmk_midline19.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline19.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline19.databinding.ActivitySectionGBinding;
import edu.aku.ramshasaeed.tmk_midline19.validation.ValidatorClasss;

public class SectionGActivity extends AppCompatActivity {
    ActivitySectionGBinding bi;
    private static final String TAG = SectionIActivity.class.getSimpleName();

    @BindViews({R.id.fldGrpBcgM, R.id.fldGrpOpv0M, R.id.fldGrpPenta1M, R.id.fldGrppcv1M, R.id.fldGrpopv1M, R.id.fldGrppenta2M, R.id.fldGrprota1M, R.id.fldGrprota2M,
            R.id.fldGrppcv2M, R.id.fldGrpopv2M, R.id.fldGrpPenta3M, R.id.fldGrppcv3M, R.id.fldGrpopv3M, R.id.fldGrpipvM,
            R.id.fldGrpMeasles1M, R.id.fldGrpMeasles2M})
    List<LinearLayout> fldGrpMother;
    @BindViews({R.id.bcgC, R.id.opv0C, R.id.penta1C, R.id.pcv1C, R.id.opv1C, R.id.penta2C, R.id.pcv2C, R.id.opv2C, R.id.penta3C, R.id.rota1C, R.id.rota2C,
            R.id.pcv3C, R.id.opv3C, R.id.ipvC, R.id.measles1C, R.id.measles2C})
    List<RadioGroup> rdoCard;
    @BindViews({R.id.bcgC, R.id.bcgM, R.id.opv0C, R.id.opv0M, R.id.penta1C, R.id.penta1M, R.id.pcv1C, R.id.pcv1M,
            R.id.opv1C, R.id.opv1M, R.id.penta2C, R.id.pcv2C, R.id.pcv2M, R.id.opv2C, R.id.opv2M, R.id.penta3C, R.id.penta3M, R.id.rota1C, R.id.rota1M, R.id.rota2C, R.id.rota2M,
            R.id.pcv3C, R.id.pcv3M, R.id.opv3C, R.id.opv3M, R.id.ipvC, R.id.ipvM, R.id.measles1C, R.id.measles1M,
            R.id.measles2C, R.id.measles2M})
    List<RadioGroup> rdoAll;
    @BindViews({R.id.bcgPov, R.id.opv0Pov, R.id.penta1Pov, R.id.pcv1Pov, R.id.opv1Pov, R.id.penta2Pov, R.id.pcv2Pov, R.id.opv2Pov, R.id.rota1Pov, R.id.rota2Pov,
            R.id.penta3Pov, R.id.pcv3Pov, R.id.opv3Pov, R.id.ipvPov, R.id.measles1Pov, R.id.measles2Pov})
    List<RadioGroup> placeofVacc;
    @BindViews({R.id.bcgC, R.id.bcgM})
    List<RadioGroup> grpBcg;
    @BindViews({R.id.opv0C, R.id.opv0M})
    List<RadioGroup> grpOpv0;
    @BindViews({R.id.rota1C, R.id.rota1M})
    List<RadioGroup> grpRota1;
    @BindViews({R.id.rota2C, R.id.rota2M})
    List<RadioGroup> grpRota2;
    @BindViews({R.id.penta1C, R.id.penta1M})
    List<RadioGroup> grpPenta1;
    @BindViews({R.id.pcv1C, R.id.pcv1M})
    List<RadioGroup> grpPcv1;
    @BindViews({R.id.opv1C, R.id.opv1M})
    List<RadioGroup> grpOpv1;
    @BindViews({R.id.penta2C, R.id.penta2M})
    List<RadioGroup> grpPenta2;
    @BindViews({R.id.pcv2C, R.id.pcv2M})
    List<RadioGroup> grpPcv2;
    @BindViews({R.id.opv2C, R.id.opv2M})
    List<RadioGroup> grpOpv2;
    @BindViews({R.id.penta3C, R.id.penta3M})
    List<RadioGroup> grpPenta3;
    @BindViews({R.id.pcv3C, R.id.pcv3M})
    List<RadioGroup> grpPcv3;
    @BindViews({R.id.opv3C, R.id.opv3M})
    List<RadioGroup> grpOpv3;
    @BindViews({R.id.ipvC, R.id.ipvM})
    List<RadioGroup> grpIpv;
    @BindViews({R.id.measles1C, R.id.measles1M})
    List<RadioGroup> grpMeasles1;
    @BindViews({R.id.measles2C, R.id.measles2M})
    List<RadioGroup> grpMeasles2;

    @BindViews({R.id.fldGrpBcgC, R.id.fldGrpopv0C, R.id.fldGrpPenta1C, R.id.fldGrppcv1C, R.id.fldGrpopv1C, R.id.fldGrprota1C, R.id.fldGrprota2C, R.id.fldGrppenta2C,
            R.id.fldGrppcv2C, R.id.fldGrpopv2C, R.id.fldGrpenta3C, R.id.fldGrppcv3C, R.id.fldGrpopv3C, R.id.fldGrpipvC,
            R.id.fldGrpmeasles1C, R.id.fldGrpmeasles2C})
    List<LinearLayout> fldGrpCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_g);
        bi.setCallback(this);
        this.setTitle(getResources().getString(R.string.tgheading));
        ButterKnife.bind(this);

        if (MainApp.flag) {

            MainApp.childsMap.put("....", null);
            MainApp.lstChild.add("....");

            for (byte i = 0; i < MainApp.childUnder2.size(); i++) {
                MainApp.childsMap.put(MainApp.childUnder2.get(i).getname(), new FamilyMembersContract(MainApp.childUnder2.get(i)));
                MainApp.lstChild.add(MainApp.childUnder2.get(i).getname());
            }


        }
        bi.tiname.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, MainApp.lstChild));

        bi.tiname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MainApp.positionIm = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bi.ti01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (bi.ti01a.isChecked()) {
                    bi.fldGrpti02.setVisibility(View.GONE);
                    bi.ti02a.setChecked(false);
                    bi.ti02b.setChecked(false);
                    bi.ti02c.setChecked(false);
                    bi.ti02d.setChecked(false);
                    bi.ti0296.setChecked(false);
                    bi.ti0296x.setText(null);
                } else {
                    for (RadioGroup re : rdoAll) {
                        re.clearCheck();
                    }

                    for (RadioGroup re : placeofVacc) {
                        re.clearCheck();
                    }
                    bi.fldGrpti02.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.ti0296.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bi.ti0296x.setVisibility(View.VISIBLE);
                    bi.ti0296x.requestFocus();
                } else {
                    bi.ti0296x.setVisibility(View.GONE);
                    bi.ti0296x.setText(null);
                }
            }
        });
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

                if (bi.ti01a.isChecked()) {
                    Intent secNext = new Intent(this, SectionG02Activity.class);
                    startActivity(secNext);
                } else {

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

                }

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        Long updcount = db.addChild(MainApp.ims);
        MainApp.ims.set_ID(String.valueOf(updcount));

        if (updcount != -1) {

            MainApp.ims.setUID(
                    (MainApp.fc.getdeviceid() + MainApp.ims.get_ID()));
            db.updateChildID();

            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void SaveDraft() throws JSONException {

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

        MainApp.ims = new SectionIIMContract();

        MainApp.ims.set_UUID(MainApp.fc.get_UID());
        MainApp.ims.setFormDate(MainApp.fc.getformDate());
        MainApp.ims.setDeviceId(MainApp.fc.getdeviceid());
        MainApp.ims.setUser(MainApp.fc.getuser());
        MainApp.ims.setDevicetagID(sharedPref.getString("tagName", null));

        JSONObject sI = new JSONObject();

        sI.put("ta01", MainApp.cluster);
        sI.put("ta05h", MainApp.hhno);

        sI.put("tiImsSerial", MainApp.childsMap.get(bi.tiname.getSelectedItem().toString()).getserialNo());

        sI.put("tiname", bi.tiname.getSelectedItem().toString());

        sI.put("ti01", bi.ti01a.isChecked() ? "1" : bi.ti01b.isChecked() ? "2" : bi.ti0198.isChecked() ? "98" : "0");
        sI.put("ti02a", bi.ti02a.isChecked() ? "1" : "0");
        sI.put("ti02b", bi.ti02b.isChecked() ? "2" : "0");
        sI.put("ti02c", bi.ti02c.isChecked() ? "3" : "0");
        sI.put("ti02d", bi.ti02d.isChecked() ? "4" : "0");
        sI.put("ti02e", bi.ti02e.isChecked() ? "5" : "0");
        sI.put("ti0296", bi.ti0296.isChecked() ? "96" : "0");
        sI.put("ti0296x", bi.ti0296x.getText().toString());

        MainApp.ims.setsI(String.valueOf(sI));

    }

    public boolean ValidateForm() {

        if (!ValidatorClasss.EmptySpinner(this, bi.tiname, getString(R.string.name))) {
            return false;
        }

        if (!bi.ti01a.isChecked()) {
            if (!(bi.ti02a.isChecked() || bi.ti02b.isChecked() || bi.ti02c.isChecked() || bi.ti02d.isChecked()
            )) {
                Toast.makeText(this, "ERROR(empty)" + getString(R.string.ti02), Toast.LENGTH_SHORT).show();
                bi.ti02a.setError("This data is Required!");
                bi.ti02a.requestFocus();
                Log.i(TAG, "ti02: This data is Required!");
                return false;
            } else {
                bi.ti02a.setError(null);
            }
        }

        return true;
    }

    public void BtnEnd() {
        MainApp.endActivity(this, this);
    }


}
