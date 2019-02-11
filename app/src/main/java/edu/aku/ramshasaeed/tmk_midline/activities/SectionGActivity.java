package edu.aku.ramshasaeed.tmk_midline.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import edu.aku.ramshasaeed.tmk_midline.R;
import edu.aku.ramshasaeed.tmk_midline.contracts.FamilyMembersContract;
import edu.aku.ramshasaeed.tmk_midline.contracts.SectionIIMContract;
import edu.aku.ramshasaeed.tmk_midline.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline.databinding.ActivitySectionGBinding;
import edu.aku.ramshasaeed.tmk_midline.validation.validatorClass;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;

public class SectionGActivity extends AppCompatActivity {
    ActivitySectionGBinding bi;
    private static final String TAG = SectionIActivity.class.getSimpleName();

   /* @BindViews({R.id.bcgDate, R.id.opv0Date, R.id.opv1Date, R.id.penta1Date, R.id.pcv1Date, R.id.opv2Date, R.id.penta2Date
            , R.id.pcv2Date, R.id.opv3Date, R.id.penta3Date, R.id.pcv3Date, R.id.ipvDate, R.id.measles1Date, R.id.measles2Date})
    List<DatePickerInputEditText> dates;
    @BindViews({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6, R.id.text7
            , R.id.text8, R.id.text9, R.id.text10, R.id.text11, R.id.text12, R.id.text13, R.id.text14})
    List<TextView> datesText;*/
    @BindViews({R.id.fldGrpBcgC, R.id.fldGrpopv0C, R.id.fldGrpPenta1C, R.id.fldGrppcv1C, R.id.fldGrpopv1C, R.id.fldGrppenta2C,
            R.id.fldGrppcv2C, R.id.fldGrpopv2C, R.id.fldGrpenta3C, R.id.fldGrppcv3C, R.id.fldGrpopv3C, R.id.fldGrpipvC,
            R.id.fldGrpmeasles1C, R.id.fldGrpmeasles2C})
    List<LinearLayout> fldGrpCard;
    @BindViews({R.id.fldGrpBcgM, R.id.fldGrpOpv0M, R.id.fldGrpPenta1M, R.id.fldGrppcv1M, R.id.fldGrpopv1M, R.id.fldGrppenta2M,
            R.id.fldGrppcv2M, R.id.fldGrpopv2M, R.id.fldGrpPenta3M, R.id.fldGrppcv3M, R.id.fldGrpopv3M, R.id.fldGrpipvM,
            R.id.fldGrpMeasles1M, R.id.fldGrpMeasles2M})
    List<LinearLayout> fldGrpMother;
    @BindViews({R.id.bcgC, R.id.opv0C, R.id.penta1C, R.id.pcv1C, R.id.opv1C, R.id.penta2C, R.id.pcv2C, R.id.opv2C, R.id.penta3C,
            R.id.pcv3C, R.id.opv3C, R.id.ipvC, R.id.measles1C, R.id.measles2C})
    List<RadioGroup> rdoCard;
    @BindViews({R.id.bcgC, R.id.bcgM, R.id.opv0C, R.id.opv0M, R.id.penta1C, R.id.penta1M, R.id.pcv1C, R.id.pcv1M,
            R.id.opv1C, R.id.opv1M, R.id.penta2C, R.id.pcv2C, R.id.pcv2M, R.id.opv2C, R.id.opv2M, R.id.penta3C, R.id.penta3M,
            R.id.pcv3C, R.id.pcv3M, R.id.opv3C, R.id.opv3M, R.id.ipvC, R.id.ipvM, R.id.measles1C, R.id.measles1M,
            R.id.measles2C, R.id.measles2M})
    List<RadioGroup> rdoAll;
    @BindViews({R.id.bcgPov, R.id.opv0Pov, R.id.penta1Pov, R.id.pcv1Pov, R.id.opv1Pov, R.id.penta2Pov, R.id.pcv2Pov, R.id.opv2Pov,
            R.id.penta3Pov, R.id.pcv3Pov, R.id.opv3Pov, R.id.ipvPov, R.id.measles1Pov, R.id.measles2Pov})
    List<RadioGroup> placeofVacc;
    @BindViews({R.id.bcgC, R.id.bcgM})
    List<RadioGroup> grpBcg;
    @BindViews({R.id.opv0C, R.id.opv0M})
    List<RadioGroup> grpOpv0;
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
    String maxDate2Years;


    public RadioGroup.OnCheckedChangeListener ipv = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {


          /*  if (radioGroup == bi.ipvC) {
                if (bi.ipvC01.isChecked()) {
                    bi.fldGrpIpvCCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpIpvCCheck.setVisibility(View.GONE);
                    bi.ipvDate.setText(null);
                    bi.ipvdatenp.setChecked(false);
                }
            }

            if (radioGroup == bi.ipvM) {

                if (bi.ipvM01.isChecked()) {
                    bi.fldGrpIpvMCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpIpvMCheck.setVisibility(View.GONE);
                    bi.ipvDateMYear.setText(null);
                    bi.ipvDateMMon.setText(null);
                    bi.ipvdatenr.setChecked(false);
                }
            }
*/

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
    public RadioGroup.OnCheckedChangeListener measles1 = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {


         /*   if (radioGroup ==  bi.measles1C) {
                if ( bi.measles1C01.isChecked()) {
                    bi.fldGrpMeasles1CCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpMeasles1CCheck.setVisibility(View.GONE);
                    bi.measles1Date.setText(null);
                    bi.measles1datenp.setChecked(false);
                }
            }

            if (radioGroup ==  bi.measles1M) {

                if ( bi.measles1M01.isChecked()) {
                    bi.fldGrpmeasles1MCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpmeasles1MCheck.setVisibility(View.GONE);
                    bi.measles1DateMYear.setText(null);
                    bi.measles1DateMMon.setText(null);
                    bi.measles1datenr.setChecked(false);
                }
            }*/

            if ( bi.measles1M01.isChecked() ||  bi.measles1C01.isChecked()) {
                bi.fldGrpov13.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 900);
                bi.sc13.setLayoutParams(params);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1000);
                bi.sc9M.setLayoutParams(params2);
            } else if ( bi.measles1C02.isChecked() ||  bi.measles1M02.isChecked()) {
                bi.fldGrpov13.setVisibility(View.GONE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bi.sc13.setLayoutParams(params);
                bi.sc9M.setLayoutParams(params);
                bi.measles1Pov.clearCheck();

            }
        }
    };

    public RadioGroup.OnCheckedChangeListener measles2 = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

/*
            if (radioGroup == bi.measles2C) {
                if (bi.measles2C01.isChecked()) {
                    bi.fldGrpMeasles2CCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpMeasles2CCheck.setVisibility(View.GONE);
                    bi.measles2Date.setText(null);
                    bi.measles2datenp.setChecked(false);
                }
            }

            if (radioGroup == bi.measles2M) {

                if (bi.measles2M01.isChecked()) {
                    bi.fldGrpmeasles2MCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpmeasles2MCheck.setVisibility(View.GONE);
                    bi.measles2DateMYear.setText(null);
                    bi.measles2DateMMon.setText(null);
                    bi.measles2datenr.setChecked(false);
                }
            }*/

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
    public RadioGroup.OnCheckedChangeListener pcv3 = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

/*
            if (radioGroup == bi.pcv3C) {
                if (bi.pcv3C01.isChecked()) {
                    bi.fldGrpPcv3CCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpPcv3CCheck.setVisibility(View.GONE);
                    bi.pcv3Date.setText(null);
                    bi.pcv3datenp.setChecked(false);
                }
            }

            if (radioGroup == bi.pcv3M) {

                if (bi.pcv3M01.isChecked()) {
                    bi.fldGrpPcv3MCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpPcv3MCheck.setVisibility(View.GONE);
                    bi.pcv3DateMYear.setText(null);
                    bi.pcv3DateMMon.setText(null);
                    bi.pcv3datenr.setChecked(false);
                }
            }*/


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
    public RadioGroup.OnCheckedChangeListener bcg = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

/*

            if (radioGroup == bi.bcgC) {
                if (bi.bcgC01.isChecked()) {
                    bi.fldGrpBcgCCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpBcgCCheck.setVisibility(View.GONE);
                    bi.bcgDate.setText(null);
                    bi.bcgdatenp.setChecked(false);
                }
            }

            if (radioGroup == bi.bcgM) {

                if (bi.bcgM01.isChecked()) {
                    bi.fldGrpBcgMCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpBcgMCheck.setVisibility(View.GONE);
                    bi.bcgDateMYear.setText(null);
                    bi.bcgDateMMon.setText(null);
                    bi.bcgdatenr.setChecked(false);
                }
            }
*/


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
    public RadioGroup.OnCheckedChangeListener opv0 = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {


          /*  if (radioGroup == bi.opv0C) {
                if (bi.opv0C01.isChecked()) {
                    bi.fldGrpOpv0CCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpOpv0CCheck.setVisibility(View.GONE);
                    bi.opv0Date.setText(null);
                    bi.opv0datenp.setChecked(false);
                }
            }

            if (radioGroup == bi.opv0M) {
                if (bi.opv0M01.isChecked()) {
                    bi.fldGrpOpv0MCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpOpv0MCheck.setVisibility(View.GONE);
                    bi.opv0DateMYear.setText(null);
                    bi.opv0DateMMon.setText(null);
                    bi.opv0datenr.setChecked(false);
                }
            }*/
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
    public RadioGroup.OnCheckedChangeListener opv1 = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

/*
            if (radioGroup == bi.opv1C) {
                if (bi.opv1C01.isChecked()) {
                    bi.fldGrpOpv1CCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpOpv1CCheck.setVisibility(View.GONE);
                    bi.opv1Date.setText(null);
                    bi.opv1datenp.setChecked(false);
                }
            }

            if (radioGroup == bi.opv1M) {

                if (bi.opv1M01.isChecked()) {
                    bi.fldGrpOpv1MCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpOpv1MCheck.setVisibility(View.GONE);
                    bi.opv1DateMYear.setText(null);
                    bi.opv1DateMMon.setText(null);
                    bi.opv1datenr.setChecked(false);
                }
            }*/

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
    public RadioGroup.OnCheckedChangeListener penta1 = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
/*

            if (radioGroup == bi.penta1C) {
                if (bi.penta1C01.isChecked()) {
                    bi.fldGrppenta1CCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrppenta1CCheck.setVisibility(View.GONE);
                    bi.penta1Date.setText(null);
                    bi.penta1datenp.setChecked(false);
                }
            }


            if (radioGroup == bi.penta1M) {
                if (bi.penta1M01.isChecked()) {
                    bi.fldGrpPenta1MCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpPenta1MCheck.setVisibility(View.GONE);
                    bi.penta1DateMYear.setText(null);
                    bi.penta1DateMMon.setText(null);
                    bi.penta1datenr.setChecked(false);
                }
            }
*/

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
    public RadioGroup.OnCheckedChangeListener pcv1 = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

/*

            if (radioGroup == bi.pcv1C) {
                if (bi.pcv1C01.isChecked()) {
                    bi.fldGrpPcv1CCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpPcv1CCheck.setVisibility(View.GONE);
                    bi.pcv1Date.setText(null);
                    bi.pcv1datenp.setChecked(false);
                }
            }

            if (radioGroup == bi.pcv1M) {
                if (bi.pcv1M01.isChecked()) {
                    bi.fldGrpPcv1MCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpPcv1MCheck.setVisibility(View.GONE);
                    bi.pcv1DateMYear.setText(null);
                    bi.pcv1DateMMon.setText(null);
                    bi.pcv1datenr.setChecked(false);
                }
            }
*/


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
    public RadioGroup.OnCheckedChangeListener opv2 = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

         /*   if (radioGroup == bi.opv2C) {
                if (bi.opv2C01.isChecked()) {
                    bi.fldGrpOpv2CCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpOpv2CCheck.setVisibility(View.GONE);
                    bi.opv2Date.setText(null);
                    bi.opv2datenp.setChecked(false);
                }
            }

            if (radioGroup == bi.opv2M) {
                if (bi.opv2M01.isChecked()) {
                    bi.fldGrpOpv2MCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpOpv2MCheck.setVisibility(View.GONE);
                    bi.opv2DateMYear.setText(null);
                    bi.opv2DateMMon.setText(null);
                    bi.opv2datenr.setChecked(false);
                }
            }*/

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
    public RadioGroup.OnCheckedChangeListener penta2 = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

/*
            if (radioGroup == bi.penta2C) {
                if (bi.penta2C01.isChecked()) {
                    bi.fldGrpPenta2CCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpPenta2CCheck.setVisibility(View.GONE);
                    bi.penta2Date.setText(null);
                    bi.penta2datenp.setChecked(false);
                }
            }


            if (radioGroup == bi.penta2M) {
                if (bi.penta2M01.isChecked()) {
                    bi.fldGrppenta2MCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrppenta2MCheck.setVisibility(View.GONE);
                    bi.penta2DateMYear.setText(null);
                    bi.penta2DateMMon.setText(null);
                    bi.penta2datenr.setChecked(false);
                }
            }*/
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
    public RadioGroup.OnCheckedChangeListener pcv2 = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {


          /*  if (radioGroup == bi.pcv2C) {
                if (bi.pcv2C01.isChecked()) {
                    bi.fldGrpPcv2CCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpPcv2CCheck.setVisibility(View.GONE);
                    bi.pcv2Date.setText(null);
                    bi.pcv2datenp.setChecked(false);
                }
            }

            if (radioGroup == bi.pcv2M) {

                if (bi.pcv2M01.isChecked()) {
                    bi.fldGrpPcv2MCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpPcv2MCheck.setVisibility(View.GONE);
                    bi.pcv2DateMYear.setText(null);
                    bi.pcv2DateMMon.setText(null);
                    bi.pcv2datenr.setChecked(false);
                }
            }*/

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


    public RadioGroup.OnCheckedChangeListener opv3 = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

          /*  if (radioGroup == bi.opv3C) {
                if (bi.opv3C01.isChecked()) {
                    bi.fldGrpOpv3CCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpOpv3CCheck.setVisibility(View.GONE);
                    bi.opv3Date.setText(null);
                    bi.opv3datenp.setChecked(false);
                }
            }

            if (radioGroup == bi.opv3M) {

                if (bi.opv3M01.isChecked()) {
                    bi.fldGrpOpv3MCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpOpv3MCheck.setVisibility(View.GONE);
                    bi.opv3DateMYear.setText(null);
                    bi.opv3DateMMon.setText(null);
                    bi.opv3datenr.setChecked(false);
                }
            }*/
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


    public RadioGroup.OnCheckedChangeListener penta3 = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

/*
            if (radioGroup == bi.penta3C) {
                if (bi.penta3C01.isChecked()) {
                    bi.fldGrppenta3CCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrppenta3CCheck.setVisibility(View.GONE);
                    bi.penta3Date.setText(null);
                    bi.penta3datenp.setChecked(false);
                }
            }

            if (radioGroup == bi.penta3M) {

                if (bi.penta3M01.isChecked()) {
                    bi.fldGrpPenta3MCheck.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpPenta3MCheck.setVisibility(View.GONE);
                    bi.penta3DateMYear.setText(null);
                    bi.penta3DateMMon.setText(null);
                    bi.penta3datenr.setChecked(false);
                }
            }*/

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this,R.layout.activity_section_g);
        bi.setCallback(this);
        this.setTitle(getResources().getString(R.string.tgheading));
        ButterKnife.bind(this);

        if (MainApp.flag) {


            MainApp.childsMap.put("....", null);
            MainApp.lstChild.add("....");

            for (byte i = 0; i < MainApp.familyMembersList.size(); i++) {
                if (Integer.parseInt(MainApp.familyMembersList.get(i).getage()) < 3) {
                    MainApp.childsMap.put(MainApp.familyMembersList.get(i).getname(), new FamilyMembersContract(MainApp.familyMembersList.get(i)));
                    MainApp.lstChild.add(MainApp.familyMembersList.get(i).getname());

                }
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


        String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis());
/*
        for (DatePickerInputEditText de : dates) {
            de.setManager(getSupportFragmentManager());
            de.setMaxDate(dateToday);


        }
*/

        bi.ti03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (bi.ti03a.isChecked()) {
                 /*   for (DatePickerInputEditText de : dates) {
                        de.setVisibility(View.VISIBLE);
                       *//* if (MainApp.ageRdo == 1 && (!bi.tiname.getSelectedItem().equals("...."))) {
                            de.setMinDate(MainApp.convertd01teFormat(MainApp.childsMap.get(bi.tiname.getSelectedItem()).getDob()));
                        } else if (MainApp.ageRdo == 2 && (!bi.tiname.getSelectedItem().equals("...."))) {
                            de.setMinDate(maxDate2Years);
                        }*//*
                    }*/
                    for (LinearLayout le : fldGrpCard) {
                        le.setVisibility(View.VISIBLE);
                    }
                  /*  for (TextView te : datesText) {
                        te.setVisibility(View.VISIBLE);
                    }*/
                    for (LinearLayout le : fldGrpMother) {
                        le.setVisibility(View.VISIBLE);
                    }
                } else {

                    for (LinearLayout le : fldGrpCard) {
                        le.setVisibility(View.GONE);
                        for (RadioGroup re : rdoCard) {
                            re.clearCheck();
                        }

                     /*   for (DatePickerInputEditText de : dates) {
                            de.setVisibility(View.GONE);
                            de.setText(null);
                        }
                        for (TextView te : datesText) {
                            te.setVisibility(View.GONE);
                        }*/
                    }
                    for (LinearLayout le : fldGrpMother) {
                        le.setVisibility(View.VISIBLE);
                    }

                }
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
                    bi.fldGrpti03.setVisibility(View.VISIBLE);
                } else {

                    bi.fldGrpti03.setVisibility(View.GONE);
                    for (RadioGroup re : rdoAll) {
                        re.clearCheck();
                    }
                  /*  for (DatePickerInputEditText de : dates) {
                        de.setText(null);
                    }*/

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


        // Measles 1

        for (RadioGroup rd : grpMeasles1) {
            rd.setOnCheckedChangeListener(measles1);
        }

        // Measles 2
        for (RadioGroup rd : grpMeasles2) {
            rd.setOnCheckedChangeListener(measles2);
        }

     /*   bi.bcgdatenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpbcgMDate.setVisibility(View.GONE);
                    bi.bcgDateMMon.setText(null);
                    bi.bcgDateMYear.setText(null);
                } else {
                    bi.fldGrpbcgMDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.bcgdatenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpBcgCDate.setVisibility(View.GONE);
                    bi.bcgDate.setText(null);
                } else {
                    bi.fldGrpBcgCDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.opv0datenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpopv0MDate.setVisibility(View.GONE);
                    bi.opv0DateMMon.setText(null);
                    bi.opv0DateMYear.setText(null);
                } else {
                    bi.fldGrpopv0MDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.opv0datenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpOpv0CDate.setVisibility(View.GONE);
                    bi.opv0Date.setText(null);
                } else {
                    bi.fldGrpOpv0CDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.opv1datenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpopv1MDate.setVisibility(View.GONE);
                    bi.opv1DateMMon.setText(null);
                    bi.opv1DateMYear.setText(null);
                } else {
                    bi.fldGrpopv1MDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.opv1datenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpOpv1CDate.setVisibility(View.GONE);
                    bi.opv1Date.setText(null);
                } else {
                    bi.fldGrpOpv1CDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.penta1datenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrppenta1MDate.setVisibility(View.GONE);
                    bi.penta1DateMMon.setText(null);
                    bi.penta1DateMYear.setText(null);
                } else {
                    bi.fldGrppenta1MDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.penta1datenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpPenta1CDate.setVisibility(View.GONE);
                    bi.penta1Date.setText(null);
                } else {
                    bi.fldGrpPenta1CDate.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.pcv1datenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrppcv1MDate.setVisibility(View.GONE);
                    bi.pcv1DateMMon.setText(null);
                    bi.pcv1DateMYear.setText(null);
                } else {
                    bi.fldGrppcv1MDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.pcv1datenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpPcv1CDate.setVisibility(View.GONE);
                    bi.pcv1Date.setText(null);
                } else {
                    bi.fldGrpPcv1CDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.opv2datenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpopv2MDate.setVisibility(View.GONE);
                    bi.opv2DateMMon.setText(null);
                    bi.opv2DateMYear.setText(null);
                } else {
                    bi.fldGrpopv2MDate.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.opv2datenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpOpv2CDate.setVisibility(View.GONE);
                    bi.opv2Date.setText(null);
                } else {
                    bi.fldGrpOpv2CDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.penta2datenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrppenta2MDate.setVisibility(View.GONE);
                    bi.penta2DateMMon.setText(null);
                    bi.penta2DateMYear.setText(null);
                } else {
                    bi.fldGrppenta2MDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.penta2datenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpPenta2CDate.setVisibility(View.GONE);
                    bi.penta2Date.setText(null);
                } else {
                    bi.fldGrpPenta2CDate.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.pcv2datenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrppcv2MDate.setVisibility(View.GONE);
                    bi.pcv2DateMMon.setText(null);
                    bi.pcv2DateMYear.setText(null);
                } else {
                    bi.fldGrppcv2MDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.pcv2datenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpPcv2CDate.setVisibility(View.GONE);
                    bi.pcv2Date.setText(null);
                } else {
                    bi.fldGrpPcv2CDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.opv3datenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpopv3MDate.setVisibility(View.GONE);
                    bi.opv3DateMMon.setText(null);
                    bi.opv3DateMYear.setText(null);
                } else {
                    bi.fldGrpopv3MDate.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.opv3datenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpOpv3CDate.setVisibility(View.GONE);
                    bi.opv3Date.setText(null);
                } else {
                    bi.fldGrpOpv3CDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.penta3datenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrppenta3MDate.setVisibility(View.GONE);
                    bi.penta3DateMMon.setText(null);
                    bi.penta3DateMYear.setText(null);
                } else {
                    bi.fldGrppenta3MDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.penta3datenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpPenta3CDate.setVisibility(View.GONE);
                    bi.penta3Date.setText(null);
                } else {
                    bi.fldGrpPenta3CDate.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.pcv3datenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrppcv3MDate.setVisibility(View.GONE);
                    bi.pcv3DateMMon.setText(null);
                    bi.pcv3DateMYear.setText(null);
                } else {
                    bi.fldGrppcv3MDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.pcv3datenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpPcv3CDate.setVisibility(View.GONE);
                    bi.pcv3Date.setText(null);
                } else {
                    bi.fldGrpPcv3CDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.ipvdatenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpipvMDate.setVisibility(View.GONE);
                    bi.ipvDateMMon.setText(null);
                    bi.ipvDateMYear.setText(null);
                } else {
                    bi.fldGrpipvMDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.ipvdatenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpIpvCDate.setVisibility(View.GONE);
                    bi.ipvDate.setText(null);
                } else {
                    bi.fldGrpIpvCDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.measles1datenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpmeasles1MDate.setVisibility(View.GONE);
                    bi.measles1DateMMon.setText(null);
                    bi.measles1DateMYear.setText(null);
                } else {
                    bi.fldGrpmeasles1MDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.measles1datenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpMeasles1CDate.setVisibility(View.GONE);
                    bi.measles1Date.setText(null);
                } else {
                    bi.fldGrpMeasles1CDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.measles2datenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpmeasles2MDate.setVisibility(View.GONE);
                    bi.measles2DateMMon.setText(null);
                    bi.measles2DateMYear.setText(null);
                } else {
                    bi.fldGrpmeasles2MDate.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.measles2datenp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bi.fldGrpMeasles2CDate.setVisibility(View.GONE);
                    bi.measles2Date.setText(null);
                } else {
                    bi.fldGrpMeasles2CDate.setVisibility(View.VISIBLE);
                }
            }
        });*/
    }


        public void onBtnContinueClick() {

            if (ValidateForm()) {
                try {
                    SaveDraft();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (UpdateDB()) {
                    Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();

                    //finish();

                    if (MainApp.imsCount < MainApp.totalImsCount) {
                        finish();

                        MainApp.imsCount++;

                        MainApp.lstChild.remove(MainApp.positionIm);
                        MainApp.childsMap.remove(MainApp.positionIm);
                        MainApp.flag = false;
                        Intent secNext = new Intent(this, SectionGActivity.class);
                        //tiname.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, MainApp.lstChild));
                        startActivity(secNext);


                    } else {
                        MainApp.imsCount = 0;

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

            Long updcount = db.addChild(MainApp.ims);
            MainApp.ims.set_ID(String.valueOf(updcount));

            if (updcount != -1) {
                Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

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
            Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

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
            sI.put("ta05u", MainApp.billno);

            sI.put("tiImsSerial", MainApp.childsMap.get(bi.tiname.getSelectedItem().toString()).getserialNo());

            sI.put("tiname", bi.tiname.getSelectedItem().toString());

            sI.put("ti01", bi.ti01a.isChecked() ? "1" : bi.ti01b.isChecked() ? "2" : bi.ti0198.isChecked() ? "98" : "0");
            sI.put("ti02a", bi.ti02a.isChecked() ? "1" : "0");
            sI.put("ti02b", bi.ti02b.isChecked() ? "2" : "0");
            sI.put("ti02c", bi.ti02c.isChecked() ? "3" : "0");
            sI.put("ti02d", bi.ti02d.isChecked() ? "4" : "0");
            sI.put("ti0296", bi.ti0296.isChecked() ? "96" : "0");
            sI.put("ti0296x", bi.ti0296x.getText().toString());
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
                    : bi.penta1Povd.isChecked() ? "98": "0");
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
                    : bi.penta2Povd.isChecked() ? "98": "0");
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
//            sI.put("appver", MainApp.versionName + "." + MainApp.versionCode);


            MainApp.ims.setsI(String.valueOf(sI));

            Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();

        }

        public boolean ValidateForm() {

            if (!validatorClass.EmptySpinner(this, bi.tiname, getString(R.string.name))) {
                return false;
            }
         /*   if (bi.tiname.getSelectedItem() == "....") {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.name), Toast.LENGTH_SHORT).show();
                ((TextView) bi.tiname.getSelectedView()).setText("This Data is Required");
                ((TextView) bi.tiname.getSelectedView()).setTextColor(Color.RED);
                bi.tiname.requestFocus();
                Log.i(TAG, "tiname: This Data is Required!");
                return false;
            } else {
                ((TextView) bi.tiname.getSelectedView()).setError(null);
            }*/

/*
            if (bi.ti00.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.tiRespondentMother), Toast.LENGTH_SHORT).show();
                bi.ti00a.setError("This data is Required!");    // Set Error on last radio button
                bi.ti00a.setFocusable(true);
                bi.ti00a.setFocusableInTouchMode(true);
                bi.ti00a.requestFocus();
                Log.i(TAG, "ti00: This data is Required!");
                return false;
            } else {
                bi.ti00a.setError(null);
            }

            if (bi.ti01.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.ti01), Toast.LENGTH_SHORT).show();
                bi.ti01888.setError("This data is Required!");    // Set Error on last radio button
                bi.ti01888.setFocusable(true);
                bi.ti01888.setFocusableInTouchMode(true);
                bi.ti01888.requestFocus();
                Log.i(TAG, "ti01: This data is Required!");
                return false;
            } else {
                bi.ti01888.setError(null);
            }*/

            if (bi.ti01a.isChecked()) {
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

                if (bi.ti03a.isChecked()) {
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

                if (bi.ti03a.isChecked()) {

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


            } else {
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
/*
                if (bi.ti0288.isChecked() && bi.ti0288x.getText().toString().isEmpty()) {
                    Toast.makeText(this, "ERROR(empty)" + getString(R.string.ti02) + " - " + getString(R.string.other), Toast.LENGTH_SHORT).show();
                    bi.ti0288x.setError("This data is Required!");
                    bi.ti0288x.requestFocus();
                    Log.i(TAG, "ti0288x: This data is Required!");
                    return false;
                } else {
                    bi.ti0288x.setError(null);
                }*/
            }

            return true;
        }

    public void BtnEnd() {
        MainApp.endActivity(this, this);
    }



}
