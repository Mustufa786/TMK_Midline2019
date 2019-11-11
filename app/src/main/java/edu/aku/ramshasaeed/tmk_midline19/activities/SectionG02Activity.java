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
import edu.aku.ramshasaeed.tmk_midline19.databinding.ActivitySectionG02Binding;

public class SectionG02Activity extends AppCompatActivity {
    private static final String TAG = SectionIActivity.class.getSimpleName();
    ActivitySectionG02Binding bi;
    public RadioGroup.OnCheckedChangeListener ipv = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.ipvM01.isChecked() || bi.ipvC01.isChecked()) {
                bi.fldGrpov12.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc10.setLayoutParams(params);
            } else if (bi.ipvC02.isChecked() || bi.ipvM02.isChecked()) {
                bi.fldGrpov12.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc12.setLayoutParams(params);
                bi.ipvPov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener pcv3 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.pcv3M01.isChecked() || bi.pcv3C01.isChecked()) {
                bi.fldGrpov11.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc11.setLayoutParams(params);
            } else if (bi.pcv3C02.isChecked() || bi.pcv3M02.isChecked()) {
                bi.fldGrpov11.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc11.setLayoutParams(params);
                bi.pcv3Pov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener bcg = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.bcgM01.isChecked() || bi.bcgC01.isChecked()) {
                bi.fldGrpov1.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc1.setLayoutParams(params);
                LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1000);
                bi.scbirth.setLayoutParams(param2);
            } else if (bi.bcgC02.isChecked() || bi.bcgM02.isChecked()) {
                bi.fldGrpov1.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc1.setLayoutParams(params);
                bi.scbirth.setLayoutParams(params);
                bi.bcgPov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener opv0 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.opv0M01.isChecked() || bi.opv0C01.isChecked()) {
                bi.fldGrpov2.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc2.setLayoutParams(params);
            } else if (bi.opv0C02.isChecked() || bi.opv0M02.isChecked()) {
                bi.fldGrpov2.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc2.setLayoutParams(params);
                bi.opv0Pov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener opv1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.opv1M01.isChecked() || bi.opv1C01.isChecked()) {
                bi.fldGrpov3.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc3.setLayoutParams(params);
            } else if (bi.opv1C02.isChecked() || bi.opv1M02.isChecked()) {
                bi.fldGrpov3.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc3.setLayoutParams(params);
                bi.opv1Pov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener penta1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.penta1M01.isChecked() || bi.penta1C01.isChecked()) {
                bi.fldGrpov4.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc4.setLayoutParams(params);
            } else if (bi.penta1C02.isChecked() || bi.Penta1M02.isChecked()) {
                bi.fldGrpov4.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc4.setLayoutParams(params);
                bi.penta1Pov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener pcv1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.pcv1M01.isChecked() || bi.pcv1C01.isChecked()) {
                bi.fldGrpov5.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc5.setLayoutParams(params);

            } else if (bi.pcv1C02.isChecked() || bi.pcv1M02.isChecked()) {
                bi.fldGrpov5.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc5.setLayoutParams(params);
                bi.pcv1Pov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener opv2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.opv2M01.isChecked() || bi.opv2C01.isChecked()) {
                bi.fldGrpov6.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc6.setLayoutParams(params);
            } else if (bi.opv2C02.isChecked() || bi.opv2M02.isChecked()) {
                bi.fldGrpov6.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc6.setLayoutParams(params);
                bi.opv2Pov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener penta2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.penta2M01.isChecked() || bi.penta2C01.isChecked()) {
                bi.fldGrpov7.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc7.setLayoutParams(params);
            } else if (bi.penta2C02.isChecked() || bi.penta2M02.isChecked()) {
                bi.fldGrpov7.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc7.setLayoutParams(params);
                bi.penta2Pov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener pcv2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.pcv2M01.isChecked() || bi.pcv2C01.isChecked()) {
                bi.fldGrpov8.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc8.setLayoutParams(params);
            } else if (bi.pcv2C02.isChecked() || bi.pcv2M02.isChecked()) {
                bi.fldGrpov8.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc8.setLayoutParams(params);
                bi.pcv2Pov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener opv3 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.opv3M01.isChecked() || bi.opv3C01.isChecked()) {
                bi.fldGrpov9.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc9.setLayoutParams(params);
            } else if (bi.opv3C02.isChecked() || bi.opv3M02.isChecked()) {
                bi.fldGrpov9.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc9.setLayoutParams(params);
                bi.opv3Pov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener penta3 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            if (bi.penta3M01.isChecked() || bi.penta3C01.isChecked()) {
                bi.fldGrpov10.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc10.setLayoutParams(params);
            } else if (bi.penta3C02.isChecked() || bi.penta3M02.isChecked()) {
                bi.fldGrpov10.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc10.setLayoutParams(params);
                bi.penta3Pov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener Rota1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if (bi.rota1M01.isChecked() || bi.rota1C01.isChecked()) {
                bi.fldGrpprota1.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc15.setLayoutParams(params);
            } else if (bi.rota1C02.isChecked() || bi.rota1M02.isChecked()) {
                bi.fldGrpprota1.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc15.setLayoutParams(params);
                bi.rota1Pov.clearCheck();

            }
        }
    };
    public RadioGroup.OnCheckedChangeListener Rota2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if (bi.rota2M01.isChecked() || bi.rota2C01.isChecked()) {
                bi.fldGrpprota2.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc16.setLayoutParams(params);
            } else if (bi.rota2C02.isChecked() || bi.rota2M02.isChecked()) {
                bi.fldGrpprota2.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc16.setLayoutParams(params);
                bi.rota2Pov.clearCheck();

            }
        }
    };
    @BindViews({R.id.fldGrpBcgM, R.id.fldGrpOpv0M, R.id.fldGrpPenta1M, R.id.fldGrppcv1M, R.id.fldGrpopv1M, R.id.fldGrppenta2M, R.id.fldGrprota1M, R.id.fldGrprota2M,
            R.id.fldGrppcv2M, R.id.fldGrpopv2M, R.id.fldGrpPenta3M, R.id.fldGrppcv3M, R.id.fldGrpopv3M, R.id.fldGrpipvM})
    List<LinearLayout> fldGrpMother;
    @BindViews({R.id.bcgC, R.id.opv0C, R.id.penta1C, R.id.pcv1C, R.id.opv1C, R.id.penta2C, R.id.pcv2C, R.id.opv2C, R.id.penta3C, R.id.rota1C, R.id.rota2C,
            R.id.pcv3C, R.id.opv3C, R.id.ipvC})
    List<RadioGroup> rdoCard;
    @BindViews({R.id.bcgC, R.id.bcgM, R.id.opv0C, R.id.opv0M, R.id.penta1C, R.id.penta1M, R.id.pcv1C, R.id.pcv1M,
            R.id.opv1C, R.id.opv1M, R.id.penta2C, R.id.pcv2C, R.id.pcv2M, R.id.opv2C, R.id.opv2M, R.id.penta3C, R.id.penta3M, R.id.rota1C, R.id.rota1M, R.id.rota2C, R.id.rota2M,
            R.id.pcv3C, R.id.pcv3M, R.id.opv3C, R.id.opv3M, R.id.ipvC, R.id.ipvM})
    List<RadioGroup> rdoAll;
    @BindViews({R.id.bcgPov, R.id.opv0Pov, R.id.penta1Pov, R.id.pcv1Pov, R.id.opv1Pov, R.id.penta2Pov, R.id.pcv2Pov, R.id.opv2Pov, R.id.rota1Pov, R.id.rota2Pov,
            R.id.penta3Pov, R.id.pcv3Pov, R.id.opv3Pov, R.id.ipvPov})
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
    @BindViews({R.id.fldGrpBcgC, R.id.fldGrpopv0C, R.id.fldGrpPenta1C, R.id.fldGrppcv1C, R.id.fldGrpopv1C, R.id.fldGrprota1C, R.id.fldGrprota2C, R.id.fldGrppenta2C,
            R.id.fldGrppcv2C, R.id.fldGrpopv2C, R.id.fldGrpenta3C, R.id.fldGrppcv3C, R.id.fldGrpopv3C, R.id.fldGrpipvC})
    List<LinearLayout> fldGrpCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_g02);
        bi.setCallback(this);
        this.setTitle(getResources().getString(R.string.tgheading));
        ButterKnife.bind(this);

        bi.ti03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (bi.ti03a.isChecked()) {
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
            }
        });

        //================= Individual Skip Patterns of Vaccines=============

        // BCG
        for (RadioGroup rd : grpBcg) {
            rd.setOnCheckedChangeListener(bcg);
        }

        // Opv - 0

        for (RadioGroup rd : grpOpv0) {
            rd.setOnCheckedChangeListener(opv0);
        }

        // OPV1
        for (RadioGroup rd : grpOpv1) {
            rd.setOnCheckedChangeListener(opv1);
        }

        // Penta1
        for (RadioGroup rd : grpPenta1) {
            rd.setOnCheckedChangeListener(penta1);

        }
        // Rota1
        for (RadioGroup rd : grpRota1) {
            rd.setOnCheckedChangeListener(Rota1);

        }
        // Rota2
        for (RadioGroup rd : grpRota2) {
            rd.setOnCheckedChangeListener(Rota2);

        }

        // PCV1
        for (RadioGroup rd : grpPcv1) {
            rd.setOnCheckedChangeListener(pcv1);
        }

        // OPV2
        for (RadioGroup rd : grpOpv2) {
            rd.setOnCheckedChangeListener(opv2);
        }

        // Penta2
        for (RadioGroup rd : grpPenta2) {
            rd.setOnCheckedChangeListener(penta2);

        }

        // PCV2
        for (RadioGroup rd : grpPcv2) {
            rd.setOnCheckedChangeListener(pcv2);
        }


        // OPV3
        for (RadioGroup rd : grpOpv3) {
            rd.setOnCheckedChangeListener(opv3);
        }

        // Penta3
        for (RadioGroup rd : grpPenta3) {
            rd.setOnCheckedChangeListener(penta3);

        }

        // PCV3
        for (RadioGroup rd : grpPcv3) {
            rd.setOnCheckedChangeListener(pcv3);
        }

        // IPV
        for (RadioGroup rd : grpIpv) {
            rd.setOnCheckedChangeListener(ipv);
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

                finish();
                Intent secNext = new Intent(this, SectionG03Activity.class);
                startActivity(secNext);

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

        sI.put("ti03", bi.ti03a.isChecked() ? "1" : bi.ti03b.isChecked() ? "2" : bi.ti03c.isChecked() ? "3" : "0");

        sI.put("bcgM", bi.bcgM01.isChecked() ? "1" : bi.bcgM02.isChecked() ? "2" : "0");
           /* sI.put("bcgdatenr", bi.bcgdatenr.isChecked() ? "1" : "0");
            sI.put("bcgdateM_mon", bi.bcgDateMMon.getText().toString());
            sI.put("bcgdateM_year", bi.bcgDateMYear.getText().toString());*/
        sI.put("bcgC", bi.bcgC01.isChecked() ? "1" : bi.bcgC02.isChecked() ? "2" : "0");
         /*   sI.put("bcgdatenp", bi.bcgdatenp.isChecked() ? "1" : "0");
            sI.put("bcgDate", bi.bcgDate.getText().toString());*/
        sI.put("bcgPov", bi.bcgPova.isChecked() ? "1" : bi.bcgPovb.isChecked() ? "2" : bi.bcgPovc.isChecked() ? "3"
                : bi.bcgPovd.isChecked() ? "98" : "0");
        // BCG At Birth
        sI.put("opv0M", bi.opv0M01.isChecked() ? "1" : bi.opv0M02.isChecked() ? "2" : "0");
          /*  sI.put("opv0datenr", bi.opv0datenr.isChecked() ? "1" : "0");
            sI.put("opv0dateM_mon", bi.opv0DateMMon.getText().toString());
            sI.put("opv0dateM_year", bi.opv0DateMYear.getText().toString());*/
        sI.put("opv0C", bi.opv0C01.isChecked() ? "1" : bi.opv0C02.isChecked() ? "2" : "0");
           /* sI.put("opv0datenp", bi.opv0datenp.isChecked() ? "1" : "0");
            sI.put("opv0Date", bi.opv0Date.getText().toString());*/
        sI.put("opv0Pov", bi.opv0Pova.isChecked() ? "1" : bi.opv0Povb.isChecked() ? "2" : bi.opv0Povc.isChecked() ? "3"
                : bi.opv0Povd.isChecked() ? "98" : "0");
        //Polio At Birth
        sI.put("penta1M", bi.penta1M01.isChecked() ? "1" : bi.Penta1M02.isChecked() ? "2" : "0");
           /* sI.put("penta1datenr", bi.penta1datenr.isChecked() ? "1" : "0");
            sI.put("penta1dateM_mon", bi.penta1DateMMon.getText().toString());
            sI.put("penta1dateM_year", bi.penta1DateMYear.getText().toString());*/
        sI.put("penta1C", bi.penta1C01.isChecked() ? "1" : bi.penta1C02.isChecked() ? "2" : "0");
           /* sI.put("penta1datenp", bi.penta1datenp.isChecked() ? "1" : "0");
            sI.put("penta1Date", bi.penta1Date.getText().toString());*/
        sI.put("penta1Pov", bi.penta1Pova.isChecked() ? "1" : bi.penta1Povb.isChecked() ? "2" : bi.penta1Povc.isChecked() ? "3"
                : bi.penta1Povd.isChecked() ? "98" : "0");
        // Penta 1 at 6 weeks
        sI.put("pcv1M", bi.pcv1M01.isChecked() ? "1" : bi.pcv1M02.isChecked() ? "2" : "0");
          /*  sI.put("pcv1datenr", bi.pcv1datenr.isChecked() ? "1" : "0");
            sI.put("pcv1dateM_mon", bi.pcv1DateMMon.getText().toString());
            sI.put("pcv1dateM_year", bi.pcv1DateMYear.getText().toString());*/

        sI.put("pcv1C", bi.pcv1C01.isChecked() ? "1" : bi.pcv1C02.isChecked() ? "2" : "0");
          /*  sI.put("pcv1datenp", bi.pcv1datenp.isChecked() ? "1" : "0");
            sI.put("pcv1Date", bi.pcv1Date.getText().toString());*/
        sI.put("pcv1Pov", bi.pcv1Pova.isChecked() ? "1" : bi.pcv1Povb.isChecked() ? "2" : bi.pcv1Povc.isChecked() ? "3"
                : bi.pcv1Povd.isChecked() ? "98" : "0");
        //PCV 1 at 6 weeks
        sI.put("opv1M", bi.opv1M01.isChecked() ? "1" : bi.opv1M02.isChecked() ? "2" : "0");
           /* sI.put("opv1datenr", bi.opv1datenr.isChecked() ? "1" : "0");
            sI.put("opv1dateM_mon", bi.opv1DateMMon.getText().toString());
            sI.put("opv1dateM_year", bi.opv1DateMYear.getText().toString());*/
        sI.put("opv1C", bi.opv1C01.isChecked() ? "1" : bi.opv1C02.isChecked() ? "2" : "0");
        /*    sI.put("opv1datenp", bi.opv1datenp.isChecked() ? "1" : "0");
            sI.put("opv1Date", bi.opv1Date.getText().toString());*/
        sI.put("opv1Pov", bi.opv1Pova.isChecked() ? "1" : bi.opv1Povb.isChecked() ? "2" : bi.opv1Povc.isChecked() ? "3"
                : bi.opv1Povd.isChecked() ? "98" : "0");
        // OPV 1 at 6 weeks
        sI.put("penta2M", bi.penta2M01.isChecked() ? "1" : bi.penta2M02.isChecked() ? "2" : "0");
          /*  sI.put("penta2datenr", bi.penta2datenr.isChecked() ? "1" : "0");
            sI.put("penta2dateM_mon", bi.penta2DateMMon.getText().toString());
            sI.put("penta2dateM_year", bi.penta2DateMYear.getText().toString());*/
        sI.put("penta2C", bi.penta2C01.isChecked() ? "1" : bi.penta2C02.isChecked() ? "2" : "0");
          /*  sI.put("penta2datenp", bi.penta2datenp.isChecked() ? "1" : "0");

            sI.put("penta2Date", bi.penta2Date.getText().toString());*/
        sI.put("penta2Pov", bi.penta2Pova.isChecked() ? "1" : bi.penta2Povb.isChecked() ? "2" : bi.penta2Povc.isChecked() ? "3"
                : bi.penta2Povd.isChecked() ? "98" : "0");
        // Penta 2 at 10 weeks
        sI.put("pcv2M", bi.pcv2M01.isChecked() ? "1" : bi.pcv2M02.isChecked() ? "2" : "0");
         /*   sI.put("pcv2datenr", bi.pcv2datenr.isChecked() ? "1" : "0");
            sI.put("pcv2dateM_mon", bi.pcv2DateMMon.getText().toString());
            sI.put("pcv2dateM_year", bi.pcv2DateMYear.getText().toString());*/
        sI.put("pcv2C", bi.pcv2C01.isChecked() ? "1" : bi.pcv2C02.isChecked() ? "2" : "0");
         /*   sI.put("pcv2datenp", bi.pcv2datenp.isChecked() ? "1" : "0");
            sI.put("pcv2Date", bi.pcv2Date.getText().toString());*/
        sI.put("pcv2Pov", bi.pcv2Pova.isChecked() ? "1" : bi.pcv2Povb.isChecked() ? "2" : bi.pcv2Povc.isChecked() ? "3"
                : bi.pcv2Povd.isChecked() ? "98" : "0");

        sI.put("opv2M", bi.opv2M01.isChecked() ? "1" : bi.opv2M02.isChecked() ? "2" : "0");
           /* sI.put("opv2datenr", bi.opv2datenr.isChecked() ? "1" : "0");
            sI.put("opv2dateM_mon", bi.opv2DateMMon.getText().toString());
            sI.put("opv2dateM_year", bi.opv2DateMYear.getText().toString());*/
        sI.put("opv2C", bi.opv2C01.isChecked() ? "1" : bi.opv2C02.isChecked() ? "2" : "0");
           /* sI.put("opv2datenp", bi.opv2datenp.isChecked() ? "1" : "0");

            sI.put("opv2Date", bi.opv2Date.getText().toString());*/
        sI.put("opv2Pov", bi.opv2Pova.isChecked() ? "1" : bi.opv2Povb.isChecked() ? "2" : bi.opv2Povc.isChecked() ? "3"
                : bi.opv2Povd.isChecked() ? "98" : "0");

        // PCV 2 at 6 weeks
        sI.put("penta3M", bi.penta3M01.isChecked() ? "1" : bi.penta3M02.isChecked() ? "2" : "0");
          /*  sI.put("penta3datenr", bi.penta3datenr.isChecked() ? "1" : "0");
            sI.put("penta3dateM_mon", bi.penta3DateMMon.getText().toString());
            sI.put("penta3dateM_year", bi.penta3DateMYear.getText().toString());*/
        sI.put("penta3C", bi.penta3C01.isChecked() ? "1" : bi.penta3C02.isChecked() ? "2" : "0");
          /*  sI.put("penta3datenp", bi.penta3datenp.isChecked() ? "1" : "0");

            sI.put("penta3Date", bi.penta3Date.getText().toString());*/
        sI.put("penta3Pov", bi.penta3Pova.isChecked() ? "1" : bi.penta3Povb.isChecked() ? "2" : bi.penta3Povc.isChecked() ? "3"
                : bi.penta3Povd.isChecked() ? "98" : "0");
        // OPV 2 at 6 weeks
        sI.put("pcv3M", bi.pcv3M01.isChecked() ? "1" : bi.pcv3M02.isChecked() ? "2" : "0");
         /*   sI.put("pcv3datenr", bi.pcv3datenr.isChecked() ? "1" : "0");
            sI.put("pcv3dateM_mon", bi.pcv3DateMMon.getText().toString());
            sI.put("pcv3dateM_year", bi.pcv3DateMYear.getText().toString());
*/
        sI.put("pcv3C", bi.pcv3C01.isChecked() ? "1" : bi.pcv3C02.isChecked() ? "2" : "0");
         /*   sI.put("pcv3datenp", bi.pcv3datenp.isChecked() ? "1" : "0");

            sI.put("pcv3Date", bi.pcv3Date.getText().toString());*/
        sI.put("pcv3Pov", bi.pcv3Pova.isChecked() ? "1" : bi.pcv3Povb.isChecked() ? "2" : bi.pcv3Povc.isChecked() ? "3"
                : bi.pcv3Povd.isChecked() ? "98" : "0");

        // Penta 3 at 14 weeks
        sI.put("opv3M", bi.opv3M01.isChecked() ? "1" : bi.opv3M02.isChecked() ? "2" : "0");
          /*  sI.put("opv3datenr", bi.opv3datenr.isChecked() ? "1" : "0");
            sI.put("opv3dateM_mon", bi.opv3DateMMon.getText().toString());
            sI.put("opv3dateM_year", bi.opv3DateMYear.getText().toString());*/
        sI.put("opv3C", bi.opv3C01.isChecked() ? "1" : bi.opv3C02.isChecked() ? "2" : "0");
         /*   sI.put("opv3datenp", bi.opv3datenp.isChecked() ? "1" : "0");

            sI.put("opv3Date", bi.opv3Date.getText().toString());*/
        sI.put("opv3Pov", bi.opv3Pova.isChecked() ? "1" : bi.opv3Povb.isChecked() ? "2" : bi.opv3Povc.isChecked() ? "3"
                : bi.opv3Povd.isChecked() ? "98" : "0");

        // PCV 3 at 14 weeks
        sI.put("ipvM", bi.ipvM01.isChecked() ? "1" : bi.ipvM02.isChecked() ? "2" : "0");
           /* sI.put("ipvdatenr", bi.ipvdatenr.isChecked() ? "1" : "0");
            sI.put("ipvdateM_mon", bi.ipvDateMMon.getText().toString());
            sI.put("ipvdateM_year", bi.ipvDateMYear.getText().toString());*/
        sI.put("ipvC", bi.ipvC01.isChecked() ? "1" : bi.ipvC02.isChecked() ? "2" : "0");
           /* sI.put("ipvdatenp", bi.ipvdatenp.isChecked() ? "1" : "0");

            sI.put("ipvDate", bi.ipvDate.getText().toString());*/
        sI.put("ipvPov", bi.ipvPova.isChecked() ? "1" : bi.ipvPovb.isChecked() ? "2" : bi.ipvPovc.isChecked() ? "3"
                : bi.ipvPovd.isChecked() ? "98" : "0");
//            rOTA 1
        sI.put("rota1M", bi.rota1M01.isChecked() ? "1" : bi.rota1M02.isChecked() ? "2" : "0");
        sI.put("rota1C", bi.rota1C01.isChecked() ? "1" : bi.rota1C02.isChecked() ? "2" : "0");
        sI.put("rota1Pov", bi.rota1Pova.isChecked() ? "1" : bi.rota1Povb.isChecked() ? "2" : bi.rota1Povc.isChecked() ? "3"
                : bi.rota1Povd.isChecked() ? "98" : "0");
//            rOTA 2
        sI.put("rota2M", bi.rota2M01.isChecked() ? "1" : bi.rota2M02.isChecked() ? "2" : "0");
        sI.put("rota2C", bi.rota2C01.isChecked() ? "1" : bi.rota2C02.isChecked() ? "2" : "0");
        sI.put("rota2Pov", bi.rota2Pova.isChecked() ? "1" : bi.rota2Povb.isChecked() ? "2" : bi.rota2Povc.isChecked() ? "3"
                : bi.rota2Povd.isChecked() ? "98" : "0");

        MainApp.ims.setsI(String.valueOf(JsonUtils.mergeJSONObjects(new JSONObject(MainApp.ims.getsI()), sI)));

    }

    public boolean ValidateForm() {

        if (bi.ti03.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.ti03), Toast.LENGTH_SHORT).show();
            bi.ti03c.setError("This data is Required!");    // Set Error on last radio button
            bi.ti03c.setFocusable(true);
            bi.ti03c.setFocusableInTouchMode(true);
            bi.ti03c.requestFocus();
            Log.i(TAG, "ti03: This data is Required!");
            return false;
        } else {
            bi.ti03c.setError(null);
        }

        //============ BCG / Mother ==========
        if (bi.bcgM.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.bcg), Toast.LENGTH_SHORT).show();
            bi.bcgM02.setError("This data is Required!");
            Log.i(TAG, "bcgM: This data is Required!");
            bi.bcgM02.setFocusable(true);
            bi.bcgM02.setFocusableInTouchMode(true);
            bi.bcgM02.requestFocus();
            return false;
        } else {
            bi.bcgM02.setError(null);
        }

            /*    if (bi.bcgM01.isChecked() && !bi.bcgdatenr.isChecked()) {
                    if (bi.bcgDateMYear.getText().toString().isEmpty() && bi.bcgDateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.bcg) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.bcgDateMYear.setError("This data is Required!");
                        bi.bcgDateMMon.setError("This data is Required!");
                        Log.i(TAG, "bcgMDate: This data is Required!");
                        bi.bcgDateMYear.requestFocus();
                        return false;
                    } else {
                        bi.bcgDateMYear.setError(null);
                        bi.bcgDateMMon.setError(null);
                    }
                }
*/
        if (bi.ti03a.isChecked()) {
            //============ BCG / Card Present ==========
            if (bi.bcgC.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.bcg), Toast.LENGTH_SHORT).show();
                bi.bcgC02.setError("This data is Required!");
                Log.i(TAG, "bcgC: This data is Required!");
                bi.bcgC02.setFocusable(true);
                bi.bcgC02.setFocusableInTouchMode(true);
                bi.bcgC02.requestFocus();

                return false;
            } else {
                bi.bcgC02.setError(null);
            }


                  /*  if (bi.bcgC01.isChecked() && !bi.bcgdatenp.isChecked()) {
                        if (bi.bcgDate.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.bcg) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.bcgDate.setError("This data is Required!");
                            Log.i(TAG, "bcgDate: This data is Required!");
                            bi.bcgDate.requestFocus();
                            return false;
                        } else {
                            bi.bcgDate.setError(null);
                        }
                    }*/
        }
        if (bi.bcgC01.isChecked() || bi.bcgM01.isChecked()) {

            if (bi.bcgPov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.bcg) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.bcgPova.setError("This data is Required!");
                Log.i(TAG, "bcgPov: This data is Required!");
                bi.bcgPova.setFocusable(true);
                bi.bcgPova.setFocusableInTouchMode(true);
                bi.bcgPova.requestFocus();
                return false;
            } else {
                bi.bcgPova.setError(null);
            }
        }


        //============ Polio 0 / Mother ==========
        if (bi.opv0M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv0), Toast.LENGTH_SHORT).show();
            bi.opv0M02.setError("This data is Required!");
            Log.i(TAG, "opv0M: This data is Required!");
            bi.opv0M02.setFocusable(true);
            bi.opv0M02.setFocusableInTouchMode(true);
            bi.opv0M02.requestFocus();
            return false;
        } else {
            bi.opv0M02.setError(null);
        }

             /*   if (bi.opv0M01.isChecked() && !bi.opv0datenr.isChecked()) {
                    if (bi.opv0DateMYear.getText().toString().isEmpty() && bi.opv0DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv0) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.opv0DateMYear.setError("This data is Required!");
                        bi.opv0DateMMon.setError("This data is Required!");
                        Log.i(TAG, "opv0MDate: This data is Required!");
                        bi.opv0DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.opv0DateMYear.setError(null);
                        bi.opv0DateMMon.setError(null);
                    }
                }*/


        if (bi.ti03a.isChecked()) {
            //============ Polio 0 / Card Present ==========
            if (bi.opv0C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv0), Toast.LENGTH_SHORT).show();
                bi.opv0C02.setError("This data is Required!");
                Log.i(TAG, "opv0C: This data is Required!");
                bi.opv0C02.setFocusable(true);
                bi.opv0C02.setFocusableInTouchMode(true);
                bi.opv0C02.requestFocus();
                return false;
            } else {
                bi.opv0C02.setError(null);
            }
/*
                    if (bi.opv0C01.isChecked() && !bi.opv0datenp.isChecked()) {
                        if (bi.opv0Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv0) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.opv0Date.setError("This data is Required!");
                            Log.i(TAG, "opv0Date: This data is Required!");
                            bi.opv0Date.requestFocus();
                            return false;
                        } else {
                            bi.opv0Date.setError(null);
                        }
                    }*/
        }

        if (bi.opv0C01.isChecked() || bi.opv0M01.isChecked()) {
            if (bi.opv0Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv0) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.opv0Pova.setError("This data is Required!");
                Log.i(TAG, "opv0Pov: This data is Required!");
                bi.opv0Pova.setFocusable(true);
                bi.opv0Pova.setFocusableInTouchMode(true);
                bi.opv0Pova.requestFocus();
                return false;
            } else {
                bi.opv0Pova.setError(null);
            }
        }


        //============ OPV 1 / Mother ==========
        if (bi.opv1M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv1), Toast.LENGTH_SHORT).show();
            bi.opv1M02.setError("This data is Required!");
            Log.i(TAG, "opv1M: This data is Required!");
            bi.opv1M02.setFocusable(true);
            bi.opv1M02.setFocusableInTouchMode(true);
            bi.opv1M02.requestFocus();
            return false;
        } else {
            bi.opv1M02.setError(null);
        }

           /*     if (bi.opv1M01.isChecked() && !bi.opv1datenr.isChecked()) {
                    if (bi.opv1DateMYear.getText().toString().isEmpty() && bi.opv1DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv1) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.opv1DateMYear.setError("This data is Required!");
                        bi.opv1DateMMon.setError("This data is Required!");
                        Log.i(TAG, "opv1MDate: This data is Required!");
                        bi.opv1DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.opv1DateMYear.setError(null);
                        bi.opv1DateMMon.setError(null);
                    }
                }
*/
        if (bi.ti03a.isChecked()) {
            //============ OPV 1 / Card Present ==========
            if (bi.opv1C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv1), Toast.LENGTH_SHORT).show();
                bi.opv1C02.setError("This data is Required!");
                Log.i(TAG, "opv1C: This data is Required!");
                bi.opv1C02.setFocusable(true);
                bi.opv1C02.setFocusableInTouchMode(true);
                bi.opv1C02.requestFocus();
                return false;
            } else {
                bi.opv1C02.setError(null);
            }

             /*       if (bi.opv1C01.isChecked() && !bi.opv1datenp.isChecked()) {
                        if (bi.opv1Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv1) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.opv1Date.setError("This data is Required!");
                            Log.i(TAG, "opv1Date: This data is Required!");
                            bi.opv1Date.requestFocus();
                            return false;
                        } else {
                            bi.opv1Date.setError(null);
                        }
                    }*/
        }


        if (bi.opv1C01.isChecked() || bi.opv1M01.isChecked()) {
            if (bi.opv1Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv1) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.opv1Pova.setError("This data is Required!");
                Log.i(TAG, "opv1Pov: This data is Required!");
                bi.opv1Pova.setFocusable(true);
                bi.opv1Pova.setFocusableInTouchMode(true);
                bi.opv1Pova.requestFocus();
                return false;
            } else {
                bi.opv1Pova.setError(null);
            }
        }


        //============ ROTA 1 / Mother ==========
        if (bi.rota1M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.rota1), Toast.LENGTH_SHORT).show();
            bi.rota1M02.setError("This data is Required!");
            Log.i(TAG, "rota1M: This data is Required!");
            bi.rota1M02.setFocusable(true);
            bi.rota1M02.setFocusableInTouchMode(true);
            bi.rota1M02.requestFocus();
            return false;
        } else {
            bi.rota1M02.setError(null);
        }

           /*     if (bi.opv1M01.isChecked() && !bi.opv1datenr.isChecked()) {
                    if (bi.opv1DateMYear.getText().toString().isEmpty() && bi.opv1DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv1) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.opv1DateMYear.setError("This data is Required!");
                        bi.opv1DateMMon.setError("This data is Required!");
                        Log.i(TAG, "opv1MDate: This data is Required!");
                        bi.opv1DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.opv1DateMYear.setError(null);
                        bi.opv1DateMMon.setError(null);
                    }
                }
*/
        if (bi.ti03a.isChecked()) {
            //============ OPV 1 / Card Present ==========
            if (bi.rota1C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.rota1), Toast.LENGTH_SHORT).show();
                bi.rota1C02.setError("This data is Required!");
                Log.i(TAG, "rota1C: This data is Required!");
                bi.rota1C02.setFocusable(true);
                bi.rota1C02.setFocusableInTouchMode(true);
                bi.rota1C02.requestFocus();
                return false;
            } else {
                bi.rota1C02.setError(null);
            }

             /*       if (bi.opv1C01.isChecked() && !bi.opv1datenp.isChecked()) {
                        if (bi.opv1Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv1) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.opv1Date.setError("This data is Required!");
                            Log.i(TAG, "opv1Date: This data is Required!");
                            bi.opv1Date.requestFocus();
                            return false;
                        } else {
                            bi.opv1Date.setError(null);
                        }
                    }*/
        }


        if (bi.rota1C01.isChecked() || bi.rota1M01.isChecked()) {
            if (bi.rota1Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.rota1) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.rota1Pova.setError("This data is Required!");
                Log.i(TAG, "rota1Pov: This data is Required!");
                bi.rota1Pova.setFocusable(true);
                bi.rota1Pova.setFocusableInTouchMode(true);
                bi.rota1Pova.requestFocus();
                return false;
            } else {
                bi.rota1Pova.setError(null);
            }
        }

        //============ Penta 1 / Mother ==========
        if (bi.penta1M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta1), Toast.LENGTH_SHORT).show();
            bi.Penta1M02.setError("This data is Required!");
            Log.i(TAG, "penta1M: This data is Required!");
            bi.Penta1M02.setFocusable(true);
            bi.Penta1M02.setFocusableInTouchMode(true);
            bi.Penta1M02.requestFocus();
            return false;
        } else {
            bi.Penta1M02.setError(null);
        }

             /*   if (bi.penta1M01.isChecked() && !bi.penta1datenr.isChecked()) {
                    if (bi.penta1DateMYear.getText().toString().isEmpty() && bi.penta1DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta1) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.penta1DateMYear.setError("This data is Required!");
                        bi.penta1DateMMon.setError("This data is Required!");
                        Log.i(TAG, "penta1MDate: This data is Required!");
                        bi.penta1DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.penta1DateMYear.setError(null);
                        bi.penta1DateMMon.setError(null);
                    }
                }*/


        if (bi.ti03a.isChecked()) {
            //============ Penta 1 / Card Present ==========
            if (bi.penta1C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta1), Toast.LENGTH_SHORT).show();
                bi.penta1C02.setError("This data is Required!");
                Log.i(TAG, "penta1C: This data is Required!");
                bi.penta1C02.setFocusable(true);
                bi.penta1C02.setFocusableInTouchMode(true);
                bi.penta1C02.requestFocus();
                return false;
            } else {
                bi.penta1C02.setError(null);
            }

                  /*  if (bi.penta1C01.isChecked() && !bi.penta1datenp.isChecked()) {
                        if (bi.penta1Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta1) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.penta1Date.setError("This data is Required!");
                            Log.i(TAG, "bi.penta1Date: This data is Required!");
                            bi.penta1Date.requestFocus();
                            return false;
                        } else {
                            bi.penta1Date.setError(null);
                        }
                    }*/
        }

        if (bi.penta1C01.isChecked() || bi.penta1M01.isChecked()) {
            if (bi.penta1Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta1) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.penta1Pova.setError("This data is Required!");
                Log.i(TAG, "penta1Pov: This data is Required!");
                bi.penta1Pova.setFocusable(true);
                bi.penta1Pova.setFocusableInTouchMode(true);
                bi.penta1Pova.requestFocus();
                return false;
            } else {
                bi.penta1Pova.setError(null);
            }
        }


        //============ PCV 1 / Mother ==========
        if (bi.pcv1M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv1), Toast.LENGTH_SHORT).show();
            bi.pcv1M02.setError("This data is Required!");
            Log.i(TAG, "pcv1M: This data is Required!");
            bi.pcv1M02.setFocusable(true);
            bi.pcv1M02.setFocusableInTouchMode(true);
            bi.pcv1M02.requestFocus();
            return false;
        } else {
            bi.pcv1M02.setError(null);
        }

               /* if (bi.pcv1M01.isChecked() && !bi.pcv1datenr.isChecked()) {
                    if (bi.pcv1DateMYear.getText().toString().isEmpty() && bi.pcv1DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv1) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.pcv1DateMYear.setError("This data is Required!");
                        bi.pcv1DateMMon.setError("This data is Required!");
                        Log.i(TAG, "pcv1MDate: This data is Required!");
                        bi.pcv1DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.pcv1DateMYear.setError(null);
                        bi.pcv1DateMMon.setError(null);
                    }
                }*/

        if (bi.ti03a.isChecked()) {

            //============ PCV 1 / Card Present ==========
            if (bi.pcv1C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv1), Toast.LENGTH_SHORT).show();
                bi.pcv1C02.setError("This data is Required!");
                Log.i(TAG, "pcv1C: This data is Required!");
                bi.pcv1C02.setFocusable(true);
                bi.pcv1C02.setFocusableInTouchMode(true);
                bi.pcv1C02.requestFocus();
                return false;
            } else {
                bi.pcv1C02.setError(null);
            }

                   /* if (bi.pcv1C01.isChecked() && !bi.pcv1datenp.isChecked()) {
                        if (bi.pcv1Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv1) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.pcv1Date.setError("This data is Required!");
                            Log.i(TAG, "pcv1Date: This data is Required!");
                            bi.pcv1Date.requestFocus();
                            return false;
                        } else {
                            bi.pcv1Date.setError(null);
                        }
                    }*/
        }

        if (bi.pcv1C01.isChecked() || bi.pcv1M01.isChecked()) {
            if (bi.pcv1Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv1) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.pcv1Pova.setError("This data is Required!");
                Log.i(TAG, "pcv1Pov: This data is Required!");
                bi.pcv1Pova.setFocusable(true);
                bi.pcv1Pova.setFocusableInTouchMode(true);
                bi.pcv1Pova.requestFocus();
                return false;
            } else {
                bi.pcv1Pova.setError(null);
            }
        }

        //============ OPV 2 / Mother ==========
        if (bi.opv2M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv2), Toast.LENGTH_SHORT).show();
            bi.opv2M02.setError("This data is Required!");
            Log.i(TAG, "opv2M: This data is Required!");
            bi.opv2M02.setFocusable(true);
            bi.opv2M02.setFocusableInTouchMode(true);
            bi.opv2M02.requestFocus();
            return false;
        } else {
            bi.opv2M02.setError(null);
        }

                /*if (bi.opv2M01.isChecked() && !bi.opv2datenr.isChecked()) {
                    if (bi.opv2DateMYear.getText().toString().isEmpty() && bi.opv2DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv2) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.opv2DateMYear.setError("This data is Required!");
                        bi.opv2DateMMon.setError("This data is Required!");
                        Log.i(TAG, "opv2MDate: This data is Required!");
                        bi.opv2DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.opv2DateMYear.setError(null);
                        bi.opv2DateMMon.setError(null);
                    }
                }*/

        if (bi.ti03a.isChecked()) {
            //============ OPV 2 / Card Present ==========
            if (bi.opv2C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv2), Toast.LENGTH_SHORT).show();
                bi.opv2C02.setError("This data is Required!");
                Log.i(TAG, "opv2C: This data is Required!");
                bi.opv2C02.setFocusable(true);
                bi.opv2C02.setFocusableInTouchMode(true);
                bi.opv2C02.requestFocus();
                return false;
            } else {
                bi.opv2C02.setError(null);
            }

                   /* if (bi.opv2C01.isChecked() && !bi.opv2datenp.isChecked()) {
                        if (bi.opv2Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv2) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.opv2Date.setError("This data is Required!");
                            Log.i(TAG, "opv2Date: This data is Required!");
                            bi.opv2Date.requestFocus();
                            return false;
                        } else {
                            bi.opv2Date.setError(null);
                        }
                    }*/
        }


        if (bi.opv2C01.isChecked() || bi.opv2M01.isChecked()) {
            if (bi.opv2Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv2) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.opv2Pova.setError("This data is Required!");
                Log.i(TAG, "opv2Pov: This data is Required!");
                bi.opv2Pova.setFocusable(true);
                bi.opv2Pova.setFocusableInTouchMode(true);
                bi.opv2Pova.requestFocus();
                return false;
            } else {
                bi.opv2Pova.setError(null);
            }
        }
        //============ ROTA 2 / Mother ==========
        if (bi.rota2M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.rota2), Toast.LENGTH_SHORT).show();
            bi.rota2M02.setError("This data is Required!");
            Log.i(TAG, "rota2M: This data is Required!");
            bi.rota2M02.setFocusable(true);
            bi.rota2M02.setFocusableInTouchMode(true);
            bi.rota2M02.requestFocus();
            return false;
        } else {
            bi.rota2M02.setError(null);
        }

                /*if (bi.opv2M01.isChecked() && !bi.opv2datenr.isChecked()) {
                    if (bi.opv2DateMYear.getText().toString().isEmpty() && bi.opv2DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv2) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.opv2DateMYear.setError("This data is Required!");
                        bi.opv2DateMMon.setError("This data is Required!");
                        Log.i(TAG, "opv2MDate: This data is Required!");
                        bi.opv2DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.opv2DateMYear.setError(null);
                        bi.opv2DateMMon.setError(null);
                    }
                }*/

        if (bi.ti03a.isChecked()) {
            //============ OPV 2 / Card Present ==========
            if (bi.rota2C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.rota2), Toast.LENGTH_SHORT).show();
                bi.rota2C02.setError("This data is Required!");
                Log.i(TAG, "rota2C: This data is Required!");
                bi.rota2C02.setFocusable(true);
                bi.rota2C02.setFocusableInTouchMode(true);
                bi.rota2C02.requestFocus();
                return false;
            } else {
                bi.rota2C02.setError(null);
            }

                   /* if (bi.opv2C01.isChecked() && !bi.opv2datenp.isChecked()) {
                        if (bi.opv2Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv2) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.opv2Date.setError("This data is Required!");
                            Log.i(TAG, "opv2Date: This data is Required!");
                            bi.opv2Date.requestFocus();
                            return false;
                        } else {
                            bi.opv2Date.setError(null);
                        }
                    }*/
        }


        if (bi.rota2C01.isChecked() || bi.rota2M01.isChecked()) {
            if (bi.rota2Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.rota2) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.rota2Pova.setError("This data is Required!");
                Log.i(TAG, "rota2Pov: This data is Required!");
                bi.rota2Pova.setFocusable(true);
                bi.rota2Pova.setFocusableInTouchMode(true);
                bi.rota2Pova.requestFocus();
                return false;
            } else {
                bi.rota2Pova.setError(null);
            }
        }

        //============ Penta 2 / Mother ==========
        if (bi.penta2M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta2), Toast.LENGTH_SHORT).show();
            bi.penta2M02.setError("This data is Required!");
            Log.i(TAG, "penta2M: This data is Required!");
            bi.penta2M02.setFocusable(true);
            bi.penta2M02.setFocusableInTouchMode(true);
            bi.penta2M02.requestFocus();
            return false;
        } else {
            bi.penta2M02.setError(null);
        }

            /*    if (bi.penta2M01.isChecked() && !bi.penta2datenr.isChecked()) {
                    if (bi.penta2DateMYear.getText().toString().isEmpty() && bi.penta2DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta2) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.penta2DateMYear.setError("This data is Required!");
                        bi.penta2DateMMon.setError("This data is Required!");
                        Log.i(TAG, "penta2MDate: This data is Required!");
                        bi.penta2DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.penta2DateMYear.setError(null);
                        bi.penta2DateMMon.setError(null);
                    }
                }
*/
        if (bi.ti03a.isChecked()) {
            //============ Penta 2 / Card Present ==========
            if (bi.penta2C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta2), Toast.LENGTH_SHORT).show();
                bi.penta2C02.setError("This data is Required!");
                Log.i(TAG, "penta2C: This data is Required!");
                bi.penta2C02.setFocusable(true);
                bi.penta2C02.setFocusableInTouchMode(true);
                bi.penta2C02.requestFocus();
                return false;
            } else {
                bi.penta2C02.setError(null);
            }


                  /*  if (bi.penta2C01.isChecked() && !bi.penta2datenp.isChecked()) {
                        if (bi.penta2Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta2) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.penta2Date.setError("This data is Required!");
                            Log.i(TAG, "penta2Date: This data is Required!");
                            bi.penta2Date.requestFocus();
                            return false;
                        } else {
                            bi.penta2Date.setError(null);
                        }
                    }*/
        }


        if (bi.penta2C01.isChecked() || bi.penta2M01.isChecked()) {
            if (bi.penta2Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta2) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.penta2Pova.setError("This data is Required!");
                Log.i(TAG, "penta2Pov: This data is Required!");
                bi.penta2Pova.setFocusable(true);
                bi.penta2Pova.setFocusableInTouchMode(true);
                bi.penta2Pova.requestFocus();
                return false;
            } else {
                bi.penta2Pova.setError(null);
            }
        }

        //============ PCV 2 / Mother ==========
        if (bi.pcv2M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv2), Toast.LENGTH_SHORT).show();
            bi.pcv2M02.setError("This data is Required!");
            Log.i(TAG, "pcv2M: This data is Required!");
            bi.pcv2M02.setFocusable(true);
            bi.pcv2M02.setFocusableInTouchMode(true);
            bi.pcv2M02.requestFocus();
            return false;
        } else {
            bi.pcv2M02.setError(null);
        }

             /*   if (bi.pcv2M01.isChecked() && !bi.pcv2datenr.isChecked()) {
                    if (bi.pcv2DateMYear.getText().toString().isEmpty() && bi.pcv2DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv2) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.pcv2DateMYear.setError("This data is Required!");
                        bi.pcv2DateMMon.setError("This data is Required!");
                        Log.i(TAG, "pcv2MDate: This data is Required!");
                        bi.pcv2DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.pcv2DateMYear.setError(null);
                        bi.pcv2DateMMon.setError(null);
                    }
                }*/

        if (bi.ti03a.isChecked()) {
            //============ PCV 2 / Card Present ==========
            if (bi.pcv2C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv2), Toast.LENGTH_SHORT).show();
                bi.pcv2C02.setError("This data is Required!");
                Log.i(TAG, "bi.pcv2C: This data is Required!");
                bi.pcv2C02.setFocusable(true);
                bi.pcv2C02.setFocusableInTouchMode(true);
                bi.pcv2C02.requestFocus();
                return false;
            } else {
                bi.pcv2C02.setError(null);
            }

                   /* if (bi.pcv2C01.isChecked() && !bi.pcv2datenp.isChecked()) {
                        if (bi.pcv2Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv2) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.pcv2Date.setError("This data is Required!");
                            Log.i(TAG, "bi.pcv2Date: This data is Required!");
                            bi.pcv2Date.requestFocus();
                            return false;
                        } else {
                            bi.pcv2Date.setError(null);
                        }
                    }*/
        }

        if (bi.pcv2C01.isChecked() || bi.pcv2M01.isChecked()) {
            if (bi.pcv2Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv2) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.pcv2Pova.setError("This data is Required!");
                Log.i(TAG, "pcv2Pov: This data is Required!");
                bi.pcv2Pova.setFocusable(true);
                bi.pcv2Pova.setFocusableInTouchMode(true);
                bi.pcv2Pova.requestFocus();
                return false;
            } else {
                bi.pcv2Pova.setError(null);
            }
        }

        //============ OPV 3 / Mother ==========
        if (bi.opv3M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv3), Toast.LENGTH_SHORT).show();
            bi.opv3M02.setError("This data is Required!");
            Log.i(TAG, "opv3M: This data is Required!");
            bi.opv3M02.setFocusable(true);
            bi.opv3M02.setFocusableInTouchMode(true);
            bi.opv3M02.requestFocus();
            return false;
        } else {
            bi.opv3M02.setError(null);
        }

            /*    if (bi.opv3M01.isChecked() && !bi.opv3datenr.isChecked()) {
                    if (bi.opv3DateMYear.getText().toString().isEmpty() && bi.opv3DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv3) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.opv3DateMYear.setError("This data is Required!");
                        bi.opv3DateMMon.setError("This data is Required!");
                        Log.i(TAG, "opv3MDate: This data is Required!");
                        bi.opv3DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.opv3DateMYear.setError(null);
                        bi.opv3DateMMon.setError(null);
                    }
                }*/

        if (bi.ti03a.isChecked()) {
            //============ OPV 3 / Card Present ==========
            if (bi.opv3C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv3), Toast.LENGTH_SHORT).show();
                bi.opv3C02.setError("This data is Required!");
                Log.i(TAG, "opv3C: This data is Required!");
                bi.opv3C02.setFocusable(true);
                bi.opv3C02.setFocusableInTouchMode(true);
                bi.opv3C02.requestFocus();
                return false;
            } else {
                bi.opv3C02.setError(null);
            }

                /*    if (bi.opv3C01.isChecked() && !bi.opv3datenp.isChecked()) {
                        if (bi.opv3Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv3) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.opv3Date.setError("This data is Required!");
                            Log.i(TAG, "opv3Date: This data is Required!");
                            bi.opv3Date.requestFocus();
                            return false;
                        } else {
                            bi.opv3Date.setError(null);
                        }
                    }*/
        }


        if (bi.opv3C01.isChecked() || bi.opv3M01.isChecked()) {
            if (bi.opv3Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.opv3) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.opv3Pova.setError("This data is Required!");
                Log.i(TAG, "opv3Pov: This data is Required!");
                bi.opv3Pova.setFocusable(true);
                bi.opv3Pova.setFocusableInTouchMode(true);
                bi.opv3Pova.requestFocus();
                return false;
            } else {
                bi.opv3Pova.setError(null);
            }
        }

        //============ Penta 3 / Mother ==========
        if (bi.penta3M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta3), Toast.LENGTH_SHORT).show();
            bi.penta3M02.setError("This data is Required!");
            Log.i(TAG, "penta3M: This data is Required!");
            bi.penta3M02.setFocusable(true);
            bi.penta3M02.setFocusableInTouchMode(true);
            bi.penta3M02.requestFocus();
            return false;
        } else {
            bi.penta3M02.setError(null);
        }

           /*     if (bi.penta3M01.isChecked() && !bi.penta3datenr.isChecked()) {
                    if (bi.penta3DateMYear.getText().toString().isEmpty() && bi.penta3DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta3) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.penta3DateMYear.setError("This data is Required!");
                        bi.penta3DateMMon.setError("This data is Required!");
                        Log.i(TAG, "penta3MDate: This data is Required!");
                        bi.penta2DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.penta3DateMYear.setError(null);
                        bi.penta3DateMMon.setError(null);
                    }
                }*/

        if (bi.ti03a.isChecked()) {
            //============ Penta 3 / Card Present ==========
            if (bi.penta3C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta3), Toast.LENGTH_SHORT).show();
                bi.penta3C02.setError("This data is Required!");
                Log.i(TAG, "penta3C: This data is Required!");
                bi.penta3C02.setFocusable(true);
                bi.penta3C02.setFocusableInTouchMode(true);
                bi.penta3C02.requestFocus();
                return false;
            } else {
                bi.penta3C02.setError(null);
            }

                 /*   if (bi.penta3C01.isChecked() && !bi.penta3datenp.isChecked()) {
                        if (bi.penta3Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta3) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.penta3Date.setError("This data is Required!");
                            Log.i(TAG, "penta3Date: This data is Required!");
                            bi.penta3Date.requestFocus();
                            return false;
                        } else {
                            bi.penta3Date.setError(null);
                        }
                    }*/
        }

        if (bi.penta3C01.isChecked() || bi.penta3M01.isChecked()) {
            if (bi.penta3Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.penta3) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.penta3Pova.setError("This data is Required!");
                Log.i(TAG, "penta3Pov: This data is Required!");
                bi.penta3Pova.setFocusable(true);
                bi.penta3Pova.setFocusableInTouchMode(true);
                bi.penta3Pova.requestFocus();
                return false;
            } else {
                bi.penta3Pova.setError(null);
            }
        }

        //============ PCV / Mother ==========
        if (bi.pcv3M.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv3), Toast.LENGTH_SHORT).show();
            bi.pcv3M02.setError("This data is Required!");
            Log.i(TAG, "pcv3M: This data is Required!");
            bi.pcv3M02.setFocusable(true);
            bi.pcv3M02.setFocusableInTouchMode(true);
            bi.pcv3M02.requestFocus();
            return false;
        } else {
            bi.pcv3M02.setError(null);
        }

             /*   if (bi.pcv3M01.isChecked() && !bi.pcv3datenr.isChecked()) {
                    if (bi.pcv3DateMYear.getText().toString().isEmpty() && bi.pcv3DateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv3) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.pcv3DateMYear.setError("This data is Required!");
                        bi.pcv3DateMMon.setError("This data is Required!");
                        Log.i(TAG, "pcv3MDate: This data is Required!");
                        bi.pcv3DateMYear.requestFocus();
                        return false;
                    } else {
                        bi.pcv3DateMYear.setError(null);
                        bi.pcv3DateMMon.setError(null);
                    }
                }*/

        if (bi.ti03a.isChecked()) {
            //============ PCV 3 / Card Present ==========
            if (bi.pcv3C.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv3), Toast.LENGTH_SHORT).show();
                bi.pcv3C02.setError("This data is Required!");
                Log.i(TAG, "pcv3C: This data is Required!");
                bi.pcv3C02.setFocusable(true);
                bi.pcv3C02.setFocusableInTouchMode(true);
                bi.pcv3C02.requestFocus();
                return false;
            } else {
                bi.pcv3C02.setError(null);
            }

                   /* if (bi.pcv3C01.isChecked() && !bi.pcv3datenp.isChecked()) {
                        if (bi.pcv3Date.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv3) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.pcv3Date.setError("This data is Required!");
                            Log.i(TAG, "pcv3Date: This data is Required!");
                            bi.pcv3Date.requestFocus();
                            return false;
                        } else {
                            bi.pcv3Date.setError(null);
                        }
                    }*/
        }

        if (bi.pcv3C01.isChecked() || bi.pcv3M01.isChecked()) {
            if (bi.pcv3Pov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.pcv3) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.pcv3Pova.setError("This data is Required!");
                Log.i(TAG, "pcv3Pov: This data is Required!");
                bi.pcv3Pova.setFocusable(true);
                bi.pcv3Pova.setFocusableInTouchMode(true);
                bi.pcv3Pova.requestFocus();
                return false;
            } else {
                bi.pcv3Pova.setError(null);
            }
        }


        //============ IPV / Mother ==========
        if (bi.ipvM.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(empty): " + getString(R.string.ipv), Toast.LENGTH_SHORT).show();
            bi.ipvM02.setError("This data is Required!");
            Log.i(TAG, "ipvM: This data is Required!");
            bi.ipvM02.setFocusable(true);
            bi.ipvM02.setFocusableInTouchMode(true);
            bi.ipvM02.requestFocus();
            return false;
        } else {
            bi.ipvM02.setError(null);
        }

             /*   if (bi.ipvM01.isChecked() && !bi.ipvdatenr.isChecked()) {
                    if (bi.ipvDateMYear.getText().toString().isEmpty() && bi.ipvDateMMon.getText().toString().isEmpty()) {
                        Toast.makeText(this, "ERROR(empty): " + getString(R.string.ipv) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                        bi.ipvDateMYear.setError("This data is Required!");
                        bi.ipvDateMMon.setError("This data is Required!");
                        Log.i(TAG, "ipvMDate: This data is Required!");
                        bi.ipvDateMYear.requestFocus();
                        return false;
                    } else {
                        bi.ipvDateMYear.setError(null);
                        bi.ipvDateMMon.setError(null);
                    }
                }*/


        if (bi.ti03a.isChecked()) {
            //============ IPV / Card Present ==========
            if (bi.ipvC.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.ipv), Toast.LENGTH_SHORT).show();
                bi.ipvC02.setError("This data is Required!");
                Log.i(TAG, "ipvC: This data is Required!");
                bi.ipvC02.setFocusable(true);
                bi.ipvC02.setFocusableInTouchMode(true);
                bi.ipvC02.requestFocus();
                return false;
            } else {
                bi.ipvC02.setError(null);
            }


                   /* if (bi.ipvC01.isChecked() && !bi.ipvdatenp.isChecked()) {
                        if (bi.ipvDate.getText().toString().isEmpty()) {
                            Toast.makeText(this, "ERROR(empty): " + getString(R.string.ipv) + " - " + getString(R.string.date), Toast.LENGTH_SHORT).show();
                            bi.ipvDate.setError("This data is Required!");
                            Log.i(TAG, "ipvDate: This data is Required!");
                            bi.ipvDate.requestFocus();
                            return false;
                        } else {
                            bi.ipvDate.setError(null);
                        }
                    }*/
        }

        if (bi.ipvC01.isChecked() || bi.ipvM01.isChecked()) {
            if (bi.ipvPov.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.ipv) + " - " + getString(R.string.place), Toast.LENGTH_SHORT).show();
                bi.ipvPova.setError("This data is Required!");
                Log.i(TAG, "ipvPov: This data is Required!");
                bi.ipvPova.setFocusable(true);
                bi.ipvPova.setFocusableInTouchMode(true);
                bi.ipvPova.requestFocus();
                return false;
            } else {
                bi.ipvPova.setError(null);
            }
        }

        return true;
    }

    public void BtnEnd() {
        MainApp.endActivity(this, this);
    }


}
