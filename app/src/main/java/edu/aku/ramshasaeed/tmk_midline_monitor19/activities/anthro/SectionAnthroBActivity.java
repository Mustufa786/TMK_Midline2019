package edu.aku.ramshasaeed.tmk_midline_monitor19.activities.anthro;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.aku.ramshasaeed.tmk_midline_monitor19.R;
import edu.aku.ramshasaeed.tmk_midline_monitor19.activities.EndingActivity;
import edu.aku.ramshasaeed.tmk_midline_monitor19.contracts.FamilyMembersContract;
import edu.aku.ramshasaeed.tmk_midline_monitor19.core.DatabaseHelper;
import edu.aku.ramshasaeed.tmk_midline_monitor19.core.MainApp;
import edu.aku.ramshasaeed.tmk_midline_monitor19.databinding.ActivitySectionAnthroBBinding;
import edu.aku.ramshasaeed.tmk_midline_monitor19.validation.ClearClass;
import edu.aku.ramshasaeed.tmk_midline_monitor19.validation.ValidatorClass02;

public class SectionAnthroBActivity extends AppCompatActivity {
    public static Map<String, FamilyMembersContract> selectedChildrenMap;
    public static ArrayList<String> childrenName = null;
    ActivitySectionAnthroBBinding bi;
    DatabaseHelper db;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_anthro_b);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
        this.setTitle(getString(R.string.tsh));

        //Setting Data
        Collection<FamilyMembersContract> members = db.getUnder5Children(MainApp.cluster, MainApp.hhno);
        if (members.size() == 0) {
            finish();
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
        }

        selectedChildrenMap = new HashMap<>();
        childrenName = new ArrayList<>(Arrays.asList("...."));

        for (FamilyMembersContract fm : members) {
            //Set map
            selectedChildrenMap.put(fm.getname() + "_(child of: " + fm.getname() + ")", fm);
            childrenName.add(fm.getname() + "_(child of: " + fm.getMmname() + ")");

        }

        //Set Child Name
        bi.tsa00.setAdapter(new ArrayAdapter<>(this, android.R.layout.select_dialog_item, childrenName));

        bi.tsa00.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        RadioGroup[] rdb = {bi.tsb03, bi.tsb07, bi.tsb11};
        for (RadioGroup radio : rdb) {
            for (int i = 0; i < radio.getChildCount(); i++) {
                if (radio.getChildAt(i) instanceof RadioButton)
                    radio.getChildAt(i).setEnabled(false);
            }
        }

        setListeners();

    }

    private void setListeners() {

        TextWatcher tsb01_02 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (bi.tsb01.getText().toString().isEmpty() || bi.tsb02.getText().toString().isEmpty())
                    return;
                float values = Math.abs(Float.valueOf(bi.tsb01.getText().toString()) - Float.valueOf(bi.tsb02.getText().toString()));
                bi.tsb03.clearCheck();
                bi.tsb03.check(Double.valueOf(String.format("%.01f", values)) > 0.6 ? bi.tsb03a.getId() : bi.tsb03b.getId());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        bi.tsb01.addTextChangedListener(tsb01_02);
        bi.tsb02.addTextChangedListener(tsb01_02);

        TextWatcher tsb05_06 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (bi.tsb05.getText().toString().isEmpty() || bi.tsb06.getText().toString().isEmpty())
                    return;
                float values = Math.abs(Float.valueOf(bi.tsb05.getText().toString()) - Float.valueOf(bi.tsb06.getText().toString()));
                bi.tsb07.clearCheck();
                bi.tsb07.check(Double.valueOf(String.format("%.01f", values)) > 0.1 ? bi.tsb07a.getId() : bi.tsb07b.getId());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        bi.tsb05.addTextChangedListener(tsb05_06);
        bi.tsb06.addTextChangedListener(tsb05_06);

        TextWatcher tsb09_10 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (bi.tsb09.getText().toString().isEmpty() || bi.tsb10.getText().toString().isEmpty())
                    return;
                float values = Math.abs(Float.valueOf(bi.tsb09.getText().toString()) - Float.valueOf(bi.tsb10.getText().toString()));
                bi.tsb11.clearCheck();
                bi.tsb11.check(Double.valueOf(String.format("%.01f", values)) > 1.3 ? bi.tsb11a.getId() : bi.tsb11b.getId());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        bi.tsb09.addTextChangedListener(tsb09_10);
        bi.tsb10.addTextChangedListener(tsb09_10);

        bi.tsb13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.tsb13a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpSecE02, null);
            }
        });

        bi.tsfa04.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.tsfa04b.getId())
                    ClearClass.ClearAllFields(bi.fldGrpSecE01b, null);
            }
        });

        bi.tsfa02.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.tsfa02b.getId())
                    ClearClass.ClearAllFields(bi.fldgrpte04, null);
            }
        });

    }

    private void SaveDraft() throws JSONException {

        JSONObject sb = new JSONObject();

        sb.put("tsfa01", bi.tsfa01.getText().toString());
        sb.put("tsfa02", bi.tsfa02a.isChecked() ? "1"
                : bi.tsfa02b.isChecked() ? "2"
                : bi.tsfa02c.isChecked() ? "3" : "0");
        sb.put("tsfa03", bi.tsfa03.getText().toString());
        sb.put("tsfa0398", bi.tsfa0398.isChecked() ? "98" : "0");
        sb.put("tsfa04", bi.tsfa04a.isChecked() ? "1" : bi.tsfa04b.isChecked() ? "2" : "0");

        FamilyMembersContract fm = selectedChildrenMap.get(bi.tsa00.getSelectedItem().toString());

        sb.put("anth_fm_luid", fm.get_UID());
        sb.put("anth_fm_serial", fm.getserialNo());
        sb.put("mothername", fm.getMmname());
        sb.put("mother_id", fm.getmotherId());

        sb.put("tsa01", bi.tsa01a.isChecked() ? "1"
                : bi.tsa01b.isChecked() ? "2"
                : bi.tsa01c.isChecked() ? "3" : "0");
        sb.put("tsa02", bi.tsa02a.isChecked() ? "1"
                : bi.tsa02b.isChecked() ? "2"
                : bi.tsa02c.isChecked() ? "3" : "0");
        sb.put("tsb01", bi.tsb01.getText().toString());
        sb.put("tsb02", bi.tsb02.getText().toString());
        sb.put("tsb03", bi.tsb03a.isChecked() ? "1"
                : bi.tsb03b.isChecked() ? "2" : "0");
        sb.put("tsb04", bi.tsb04.getText().toString());
        sb.put("tsb05", bi.tsb05.getText().toString());
        sb.put("tsb06", bi.tsb06.getText().toString());
        sb.put("tsb07", bi.tsb07a.isChecked() ? "1"
                : bi.tsb07b.isChecked() ? "2" : "0");
        sb.put("tsb08", bi.tsb08.getText().toString());
        sb.put("tsb09", bi.tsb09.getText().toString());
        sb.put("tsb10", bi.tsb10.getText().toString());
        sb.put("tsb11", bi.tsb11a.isChecked() ? "1"
                : bi.tsb11b.isChecked() ? "2" : "0");
        sb.put("tsb12", bi.tsb12.getText().toString());
        sb.put("tsb13", bi.tsb13a.isChecked() ? "1"
                : bi.tsb13b.isChecked() ? "2"
                : bi.tsb13c.isChecked() ? "3" : "0");

        MainApp.fc.setsL(String.valueOf(sb));

    }

    public void BtnContinue() {

        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void BtnEnd() {
        MainApp.endActivity(this, this);
    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSL();

        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean formValidation() {
        if (!ValidatorClass02.EmptyCheckingContainer(this, bi.fldGrpSecE01))
            return false;
        return ValidatorClass02.EmptyCheckingContainer(this, bi.fldGrpSecE);
    }
}