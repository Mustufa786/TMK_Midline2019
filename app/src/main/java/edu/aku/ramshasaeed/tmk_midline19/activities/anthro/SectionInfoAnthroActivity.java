package edu.aku.ramshasaeed.tmk_midline19.activities.anthro;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.aku.ramshasaeed.tmk_midline19.R;
import edu.aku.ramshasaeed.tmk_midline19.contracts.FamilyMembersContract;
import edu.aku.ramshasaeed.tmk_midline19.contracts.FormsContract;
import edu.aku.ramshasaeed.tmk_midline19.contracts.VillagesContract;
import edu.aku.ramshasaeed.tmk_midline19.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline19.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline19.databinding.ActivitySectionAnthroInfoBinding;
import edu.aku.ramshasaeed.tmk_midline19.validation.ValidatorClasss;

public class SectionInfoAnthroActivity extends Activity {
    private static final String TAG = SectionInfoAnthroActivity.class.getName();
    public static Map<String, FamilyMembersContract> selectedChildrenMap;
    public static ArrayList<String> childrenName;
    private ActivitySectionAnthroInfoBinding bi;
    private Collection<FamilyMembersContract> selectedChildren;
    private DatabaseHelper db;
    private ArrayList<String> labelsSubVillages;
    private Collection<VillagesContract> SubVillagesList;
    private Map<String, String> SubVillagesMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_anthro_info);
        bi.setCallback(this);
        ValidatorClasss.setScrollViewFocus(bi.svseca);
        db = new DatabaseHelper(this);

        this.setTitle("Anthropometric Information");

        MainApp.members_f_m = new ArrayList<>();

        MainApp.familyMembersList = new ArrayList<>();
        MainApp.childUnder2 = new ArrayList<>();
        MainApp.childUnder5 = new ArrayList<>();
        MainApp.serial_no = 0;

        labelsSubVillages = new ArrayList<>();
        SubVillagesMap = new HashMap<>();
        labelsSubVillages.add("Select Sub Village..");

        SubVillagesList = db.getVillage(String.valueOf(MainApp.areaCode));

        if (SubVillagesList.size() != 0) {
            for (VillagesContract vil : SubVillagesList) {
                labelsSubVillages.add(vil.getVillagename());
                SubVillagesMap.put(vil.getVillagename(), vil.getVillagecode());
            }
        }

        bi.spSubVillages.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, labelsSubVillages));

        bi.spSubVillages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (bi.spSubVillages.getSelectedItemPosition() != 0) {
                    MainApp.cluster = SubVillagesMap.get(bi.spSubVillages.getSelectedItem().toString());

                    bi.ta01.setText(MainApp.cluster);
                    bi.ta05h.setText(null);
                } else {

                    bi.ta01.setText(null);

                    bi.fldGrpt03.setVisibility(View.GONE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bi.ta05h.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bi.fldGrpt03.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void onCheckHHClick() {

        if (formValidation()) {

            ArrayList<FormsContract> form = db.getFormsForAnthro(bi.ta01.getText().toString(), bi.ta05h.getText().toString().toUpperCase());

            if (form.size() == 0) {
                Toast.makeText(this, "Not found.", Toast.LENGTH_SHORT).show();
                return;
            }

            selectedChildren = db.getUnder5Children(form.get(form.size() - 1).get_UID(), bi.ta01.getText().toString(), bi.ta05h.getText().toString().toUpperCase());
            selectedChildrenMap = new HashMap<>();
            childrenName = new ArrayList<>(Arrays.asList("...."));

            if (selectedChildren.size() != 0) {

                Toast.makeText(this, "Children Found!!", Toast.LENGTH_SHORT).show();
                bi.fldGrpt03.setVisibility(View.VISIBLE);

                for (FamilyMembersContract fm : selectedChildren) {
                    //Set map
                    selectedChildrenMap.put(fm.getname() + "_(child of: " + fm.getMmname() + ")", fm);
                    childrenName.add(fm.getname() + "_(child of: " + fm.getMmname() + ")");

                }

            } else {
                bi.fldGrpt03.setVisibility(View.GONE);
                Toast.makeText(this, "Not found any child in this HH.", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Not found.", Toast.LENGTH_SHORT).show();
        }


    }

    public void onBtnContinueClick() {
        if (formValidation()) {
            SaveDraft();
            finish();
            startActivity(new Intent(this, SectionAnthroBActivity.class));
        }
    }


    private void SaveDraft() {

        MainApp.cluster = bi.ta01.getText().toString();
        MainApp.hhno = bi.ta05h.getText().toString();

    }

    public boolean formValidation() {

        if (!ValidatorClasss.EmptySpinner(this, bi.spSubVillages, "Sub Village")) {
            return false;
        }

        if (!ValidatorClasss.EmptyTextBox(this, bi.ta01, getString(R.string.ta01))) {
            return false;
        }
        if (!ValidatorClasss.EmptyTextBox(this, bi.ta05h, getString(R.string.ta05h))) {
            return false;
        }
        return ValidatorClasss.PatternTextBox(this, bi.ta05h, getString(R.string.ta05h), "[0-9]{4,4}-[^0-9]{1,1}", "XXXX-X");
    }
}

