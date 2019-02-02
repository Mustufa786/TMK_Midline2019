package edu.aku.ramshasaeed.tmk_midline.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @BindViews({R.id.bcgDate, R.id.opv0Date, R.id.opv1Date, R.id.penta1Date, R.id.pcv1Date, R.id.opv2Date, R.id.penta2Date
            , R.id.pcv2Date, R.id.opv3Date, R.id.penta3Date, R.id.pcv3Date, R.id.ipvDate, R.id.measles1Date, R.id.measles2Date})
    List<DatePickerInputEditText> dates;
    @BindViews({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6, R.id.text7
            , R.id.text8, R.id.text9, R.id.text10, R.id.text11, R.id.text12, R.id.text13, R.id.text14})
    List<TextView> datesText;
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


            if (radioGroup == bi.ipvC) {
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


            if (radioGroup ==  bi.measles1C) {
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
            }

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
            }

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
            }


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


            if (radioGroup == bi.opv0C) {
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
            }
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
            }

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

            if (radioGroup == bi.opv2C) {
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
            }

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
            }
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


            if (radioGroup == bi.pcv2C) {
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
            }

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

            if (radioGroup == bi.opv3C) {
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
            }
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
            }

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

           /* for (byte i = 0; i < MainApp.familyMembersList.size(); i++) {
                if (MainApp.familyMembersList.get(i).getAgeLess5().equals("3")) {
                    MainApp.childsMap.put(MainApp.familyMembersList.get(i).getName(), new FamilyMembersContract(MainApp.familyMembersList.get(i)));
                    MainApp.lstChild.add(MainApp.familyMembersList.get(i).getName());

                }
            }*/


        }
//        bi.tiname.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, MainApp.lstChild));

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
                    for (DatePickerInputEditText de : dates) {
                        de.setVisibility(View.VISIBLE);
                        if (MainApp.ageRdo == 1 && (!bi.tiname.getSelectedItem().equals("...."))) {
                            de.setMinDate(MainApp.convertDateFormat(MainApp.childsMap.get(bi.tiname.getSelectedItem()).getDob()));
                        } else if (MainApp.ageRdo == 2 && (!bi.tiname.getSelectedItem().equals("...."))) {
                            de.setMinDate(maxDate2Years);
                        }
                    }
                    for (LinearLayout le : fldGrpCard) {
                        le.setVisibility(View.VISIBLE);
                    }
                    for (TextView te : datesText) {
                        te.setVisibility(View.VISIBLE);
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

                        for (DatePickerInputEditText de : dates) {
                            de.setVisibility(View.GONE);
                            de.setText(null);
                        }
                        for (TextView te : datesText) {
                            te.setVisibility(View.GONE);
                        }
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
                    for (DatePickerInputEditText de : dates) {
                        de.setText(null);
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

        bi.bcgdatenr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        });
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
                        Intent secNext = new Intent(this, SectionIActivity.class);
                        //tiname.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, MainApp.lstChild));
                        startActivity(secNext);


                    } else {
                        MainApp.imsCount = 0;

                        Intent secNext = new Intent(this, SectionJActivity.class);
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
                        (MainApp.fc.getDeviceID() + MainApp.ims.get_ID()));
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

            MainApp.ims.set_UUID(MainApp.fc.getUID());
            MainApp.ims.setFormDate(MainApp.fc.getFormDate());
            MainApp.ims.setDeviceId(MainApp.fc.getDeviceID());
            MainApp.ims.setUser(MainApp.fc.getUser());
            MainApp.ims.setDevicetagID(sharedPref.getString("tagName", null));

            JSONObject sI = new JSONObject();

            sI.put("ta01", MainApp.cluster);
            sI.put("ta05h", MainApp.hhno);
            sI.put("ta05u", MainApp.billno);

            sI.put("tiImsSerial", MainApp.childsMap.get(bi.tiname.getSelectedItem().toString()).getSerialNo());

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
            sI.put("bcgdatenr", bi.bcgdatenr.isChecked() ? "1" : "0");
            sI.put("bcgdateM_mon", bi.bcgDateMMon.getText().toString());
            sI.put("bcgdateM_year", bi.bcgDateMYear.getText().toString());
            sI.put("bcgC", bi.bcgC01.isChecked() ? "1" : bi.bcgC02.isChecked() ? "2" : "0");
            sI.put("bcgdatenp", bi.bcgdatenp.isChecked() ? "1" : "0");
            sI.put("bcgDate", bi.bcgDate.getText().toString());
            sI.put("bcgPov", bi.bcgPova.isChecked() ? "1" : bi.bcgPovb.isChecked() ? "2" : bi.bcgPovc.isChecked() ? "3"
                    : bi.bcgPovd.isChecked() ? "4" : bi.bcgPove.isChecked() ? "5" : bi.bcgPovf.isChecked() ? "6" : bi.bcgPovg.isChecked() ? "7"
                    : bi.bcgPovh.isChecked() ? "8" : "0");
            // BCG At Birth
            sI.put("opv0M", bi.opv0M01.isChecked() ? "1" : bi.opv0M02.isChecked() ? "2" : "0");
            sI.put("opv0datenr", bi.opv0datenr.isChecked() ? "1" : "0");
            sI.put("opv0dateM_mon", bi.opv0DateMMon.getText().toString());
            sI.put("opv0dateM_year", bi.opv0DateMYear.getText().toString());
            sI.put("opv0C", bi.opv0C01.isChecked() ? "1" : bi.opv0C02.isChecked() ? "2" : "0");
            sI.put("opv0datenp", bi.opv0datenp.isChecked() ? "1" : "0");
            sI.put("opv0Date", bi.opv0Date.getText().toString());
            sI.put("opv0Pov", bi.opv0Pova.isChecked() ? "1" : bi.opv0Povb.isChecked() ? "2" : bi.opv0Povc.isChecked() ? "3"
                    : bi.opv0Povd.isChecked() ? "4" : bi.opv0Pove.isChecked() ? "5" : bi.opv0Povf.isChecked() ? "6" : bi.opv0Povg.isChecked() ? "7"
                    : bi.opv0Povh.isChecked() ? "8" : "0");
            //Polio At Birth
            sI.put("penta1M", bi.penta1M01.isChecked() ? "1" : bi.Penta1M02.isChecked() ? "2" : "0");
            sI.put("penta1datenr", bi.penta1datenr.isChecked() ? "1" : "0");
            sI.put("penta1dateM_mon", bi.penta1DateMMon.getText().toString());
            sI.put("penta1dateM_year", bi.penta1DateMYear.getText().toString());
            sI.put("penta1C", bi.penta1C01.isChecked() ? "1" : bi.penta1C02.isChecked() ? "2" : "0");
            sI.put("penta1datenp", bi.penta1datenp.isChecked() ? "1" : "0");
            sI.put("penta1Date", bi.penta1Date.getText().toString());
            sI.put("penta1Pov", bi.penta1Pova.isChecked() ? "1" : bi.penta1Povb.isChecked() ? "2" : bi.penta1Povc.isChecked() ? "3"
                    : bi.penta1Povd.isChecked() ? "4" : bi.penta1Pove.isChecked() ? "5" : bi.penta1Povf.isChecked() ? "6" : bi.penta1Povg.isChecked() ? "7"
                    : bi.penta1Povh.isChecked() ? "8" : "0");
            // Penta 1 at 6 weeks
            sI.put("pcv1M", bi.pcv1M01.isChecked() ? "1" : bi.pcv1M02.isChecked() ? "2" : "0");
            sI.put("pcv1datenr", bi.pcv1datenr.isChecked() ? "1" : "0");
            sI.put("pcv1dateM_mon", bi.pcv1DateMMon.getText().toString());
            sI.put("pcv1dateM_year", bi.pcv1DateMYear.getText().toString());

            sI.put("pcv1C", bi.pcv1C01.isChecked() ? "1" : bi.pcv1C02.isChecked() ? "2" : "0");
            sI.put("pcv1datenp", bi.pcv1datenp.isChecked() ? "1" : "0");
            sI.put("pcv1Date", bi.pcv1Date.getText().toString());
            sI.put("pcv1Pov", bi.pcv1Pova.isChecked() ? "1" : bi.pcv1Povb.isChecked() ? "2" : bi.pcv1Povc.isChecked() ? "3"
                    : bi.pcv1Povd.isChecked() ? "4" : bi.pcv1Pove.isChecked() ? "5" : bi.pcv1Povf.isChecked() ? "6" : bi.pcv1Povg.isChecked() ? "7"
                    : bi.pcv1Povh.isChecked() ? "8" : "0");
            //PCV 1 at 6 weeks
            sI.put("opv1M", bi.opv1M01.isChecked() ? "1" : bi.opv1M02.isChecked() ? "2" : "0");
            sI.put("opv1datenr", bi.opv1datenr.isChecked() ? "1" : "0");
            sI.put("opv1dateM_mon", bi.opv1DateMMon.getText().toString());
            sI.put("opv1dateM_year", bi.opv1DateMYear.getText().toString());
            sI.put("opv1C", bi.opv1C01.isChecked() ? "1" : bi.opv1C02.isChecked() ? "2" : "0");
            sI.put("opv1datenp", bi.opv1datenp.isChecked() ? "1" : "0");
            sI.put("opv1Date", bi.opv1Date.getText().toString());
            sI.put("opv1Pov", bi.opv1Pova.isChecked() ? "1" : bi.opv1Povb.isChecked() ? "2" : bi.opv1Povc.isChecked() ? "3"
                    : bi.opv1Povd.isChecked() ? "4" : bi.opv1Pove.isChecked() ? "5" : bi.opv1Povf.isChecked() ? "6" : bi.opv1Povg.isChecked() ? "7"
                    : bi.opv1Povh.isChecked() ? "8" : "0");
            // OPV 1 at 6 weeks
            sI.put("penta2M", bi.penta2M01.isChecked() ? "1" : bi.penta2M02.isChecked() ? "2" : "0");
            sI.put("penta2datenr", bi.penta2datenr.isChecked() ? "1" : "0");
            sI.put("penta2dateM_mon", bi.penta2DateMMon.getText().toString());
            sI.put("penta2dateM_year", bi.penta2DateMYear.getText().toString());
            sI.put("penta2C", bi.penta2C01.isChecked() ? "1" : bi.penta2C02.isChecked() ? "2" : "0");
            sI.put("penta2datenp", bi.penta2datenp.isChecked() ? "1" : "0");

            sI.put("penta2Date", bi.penta2Date.getText().toString());
            sI.put("penta2Pov", bi.penta2Pova.isChecked() ? "1" : bi.penta2Povb.isChecked() ? "2" : bi.penta2Povc.isChecked() ? "3"
                    : bi.penta2Povd.isChecked() ? "4" : bi.penta2Pove.isChecked() ? "5" : bi.penta2Povf.isChecked() ? "6" : bi.penta2Povg.isChecked() ? "7"
                    : bi.penta2Povh.isChecked() ? "8" : "0");
            // Penta 2 at 10 weeks
            sI.put("pcv2M", bi.pcv2M01.isChecked() ? "1" : bi.pcv2M02.isChecked() ? "2" : "0");
            sI.put("pcv2datenr", bi.pcv2datenr.isChecked() ? "1" : "0");
            sI.put("pcv2dateM_mon", bi.pcv2DateMMon.getText().toString());
            sI.put("pcv2dateM_year", bi.pcv2DateMYear.getText().toString());
            sI.put("pcv2C", bi.pcv2C01.isChecked() ? "1" : bi.pcv2C02.isChecked() ? "2" : "0");
            sI.put("pcv2datenp", bi.pcv2datenp.isChecked() ? "1" : "0");
            sI.put("pcv2Date", bi.pcv2Date.getText().toString());
            sI.put("pcv2Pov", bi.pcv2Pova.isChecked() ? "1" : bi.pcv2Povb.isChecked() ? "2" : bi.pcv2Povc.isChecked() ? "3"
                    : bi.pcv2Povd.isChecked() ? "4" : bi.pcv2Pove.isChecked() ? "5" : bi.pcv2Povf.isChecked() ? "6" : bi.pcv2Povg.isChecked() ? "7"
                    : bi.pcv2Povh.isChecked() ? "8" : "0");

            sI.put("opv2M", bi.opv2M01.isChecked() ? "1" : bi.opv2M02.isChecked() ? "2" : "0");
            sI.put("opv2datenr", bi.opv2datenr.isChecked() ? "1" : "0");
            sI.put("opv2dateM_mon", bi.opv2DateMMon.getText().toString());
            sI.put("opv2dateM_year", bi.opv2DateMYear.getText().toString());
            sI.put("opv2C", bi.opv2C01.isChecked() ? "1" : bi.opv2C02.isChecked() ? "2" : "0");
            sI.put("opv2datenp", bi.opv2datenp.isChecked() ? "1" : "0");

            sI.put("opv2Date", bi.opv2Date.getText().toString());
            sI.put("opv2Pov", bi.opv2Pova.isChecked() ? "1" : bi.opv2Povb.isChecked() ? "2" : bi.opv2Povc.isChecked() ? "3"
                    : bi.opv2Povd.isChecked() ? "4" : bi.opv2Pove.isChecked() ? "5" : bi.opv2Povf.isChecked() ? "6" : bi.opv2Povg.isChecked() ? "7"
                    : bi.opv2Povh.isChecked() ? "8" : "0");

            // PCV 2 at 6 weeks
            sI.put("penta3M", bi.penta3M01.isChecked() ? "1" : bi.penta3M02.isChecked() ? "2" : "0");
            sI.put("penta3datenr", bi.penta3datenr.isChecked() ? "1" : "0");
            sI.put("penta3dateM_mon", bi.penta3DateMMon.getText().toString());
            sI.put("penta3dateM_year", bi.penta3DateMYear.getText().toString());
            sI.put("penta3C", bi.penta3C01.isChecked() ? "1" : bi.penta3C02.isChecked() ? "2" : "0");
            sI.put("penta3datenp", bi.penta3datenp.isChecked() ? "1" : "0");

            sI.put("penta3Date", bi.penta3Date.getText().toString());
            sI.put("penta3Pov", bi.penta3Pova.isChecked() ? "1" : bi.penta3Povb.isChecked() ? "2" : bi.penta3Povc.isChecked() ? "3"
                    : bi.penta3Povd.isChecked() ? "4" : bi.penta3Pove.isChecked() ? "5" : bi.penta3Povf.isChecked() ? "6" : bi.penta3Povg.isChecked() ? "7"
                    : bi.penta3Povh.isChecked() ? "8" : "0");
            // OPV 2 at 6 weeks
            sI.put("pcv3M", bi.pcv3M01.isChecked() ? "1" : bi.pcv3M02.isChecked() ? "2" : "0");
            sI.put("pcv3datenr", bi.pcv3datenr.isChecked() ? "1" : "0");
            sI.put("pcv3dateM_mon", bi.pcv3DateMMon.getText().toString());
            sI.put("pcv3dateM_year", bi.pcv3DateMYear.getText().toString());

            sI.put("pcv3C", bi.pcv3C01.isChecked() ? "1" : bi.pcv3C02.isChecked() ? "2" : "0");
            sI.put("pcv3datenp", bi.pcv3datenp.isChecked() ? "1" : "0");

            sI.put("pcv3Date", bi.pcv3Date.getText().toString());
            sI.put("pcv3Pov", bi.pcv3Pova.isChecked() ? "1" : bi.pcv3Povb.isChecked() ? "2" : bi.pcv3Povc.isChecked() ? "3"
                    : bi.pcv3Povd.isChecked() ? "4" : bi.pcv3Pove.isChecked() ? "5" : bi.pcv3Povf.isChecked() ? "6" : bi.pcv3Povg.isChecked() ? "7"
                    : bi.pcv3Povh.isChecked() ? "8" : "0");

            // Penta 3 at 14 weeks
            sI.put("opv3M", bi.opv3M01.isChecked() ? "1" : bi.opv3M02.isChecked() ? "2" : "0");
            sI.put("opv3datenr", bi.opv3datenr.isChecked() ? "1" : "0");
            sI.put("opv3dateM_mon", bi.opv3DateMMon.getText().toString());
            sI.put("opv3dateM_year", bi.opv3DateMYear.getText().toString());
            sI.put("opv3C", bi.opv3C01.isChecked() ? "1" : bi.opv3C02.isChecked() ? "2" : "0");
            sI.put("opv3datenp", bi.opv3datenp.isChecked() ? "1" : "0");

            sI.put("opv3Date", bi.opv3Date.getText().toString());
            sI.put("opv3Pov", bi.opv3Pova.isChecked() ? "1" : bi.opv3Povb.isChecked() ? "2" : bi.opv3Povc.isChecked() ? "3"
                    : bi.opv3Povd.isChecked() ? "4" : bi.opv3Pove.isChecked() ? "5" : bi.opv3Povf.isChecked() ? "6" : bi.opv3Povg.isChecked() ? "7"
                    : bi.opv3Povh.isChecked() ? "8" : "0");

            // PCV 3 at 14 weeks
            sI.put("ipvM", bi.ipvM01.isChecked() ? "1" : bi.ipvM02.isChecked() ? "2" : "0");
            sI.put("ipvdatenr", bi.ipvdatenr.isChecked() ? "1" : "0");
            sI.put("ipvdateM_mon", bi.ipvDateMMon.getText().toString());
            sI.put("ipvdateM_year", bi.ipvDateMYear.getText().toString());
            sI.put("ipvC", bi.ipvC01.isChecked() ? "1" : bi.ipvC02.isChecked() ? "2" : "0");
            sI.put("ipvdatenp", bi.ipvdatenp.isChecked() ? "1" : "0");

            sI.put("ipvDate", bi.ipvDate.getText().toString());
            sI.put("ipvPov", bi.ipvPova.isChecked() ? "1" : bi.ipvPovb.isChecked() ? "2" : bi.ipvPovc.isChecked() ? "3"
                    : bi.ipvPovd.isChecked() ? "4" : bi.ipvPove.isChecked() ? "5" : bi.ipvPovf.isChecked() ? "6" : bi.ipvPovg.isChecked() ? "7"
                    : bi.ipvPovh.isChecked() ? "8" : "0");
            // OPV 3 at 14 weeks
            sI.put("measles1M", bi.measles1M01.isChecked() ? "1" : bi.measles1M02.isChecked() ? "2" : "0");
            sI.put("measles1datenr", bi.measles1datenr.isChecked() ? "1" : "0");
            sI.put("measles1dateM_mon",bi.measles1DateMMon.getText().toString());
            sI.put("measles1dateM_year", bi.measles1DateMYear.getText().toString());
            sI.put("measles1C", bi.measles1C01.isChecked() ? "1" : bi.measles1C02.isChecked() ? "2" : "0");
            sI.put("measles1datenp", bi.measles1datenp.isChecked() ? "1" : "0");

            sI.put("measles1Date", bi.measles1Date.getText().toString());
            sI.put("measles1Pov", bi.measles1Pova.isChecked() ? "1" : bi.measles1Povb.isChecked() ? "2" : bi.measles1Povc.isChecked() ? "3"
                    : bi.measles1Povd.isChecked() ? "4" : bi.measles1Pove.isChecked() ? "5" : bi.measles1Povf.isChecked() ? "6" : bi.measles1Povg.isChecked() ? "7"
                    : bi.measles1Povh.isChecked() ? "8" : "0");
            // IPV at 14 weeks
            sI.put("measles2M", bi.measles2M01.isChecked() ? "1" : bi.measles2M02.isChecked() ? "2" : "0");
            sI.put("measles2datenr", bi.measles2datenr.isChecked() ? "1" : "0");
            sI.put("measles2dateM_mon", bi.measles2DateMMon.getText().toString());
            sI.put("measles2dateM_year", bi.measles2DateMYear.getText().toString());
            sI.put("measles2C", bi.measles2C01.isChecked() ? "1" : bi.measles2C02.isChecked() ? "2" : "0");
            sI.put("measles2datenp", bi.measles2datenp.isChecked() ? "1" : "0");

            sI.put("measles2Date", bi.measles2Date.getText().toString());
            sI.put("measles2Pov", bi.measles2Pova.isChecked() ? "1" : bi.measles2Povb.isChecked() ? "2" : bi.measles2Povc.isChecked() ? "3"
                    : bi.measles2Povd.isChecked() ? "4" : bi.measles2Pove.isChecked() ? "5" : bi.measles2Povf.isChecked() ? "6"
                    : bi.measles2Povg.isChecked() ? "7" : bi.measles2Povh.isChecked() ? "8" : "0");
            sI.put("appver", MainApp.versionName + "." + MainApp.versionCode);


            MainApp.ims.setsI(String.valueOf(sI));

            Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();

        }

        public boolean ValidateForm() {


        return true;
        }
    public void BtnEnd() {
        MainApp.endActivity(this, this);
    }



}
