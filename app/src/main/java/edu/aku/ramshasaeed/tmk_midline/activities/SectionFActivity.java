package edu.aku.ramshasaeed.tmk_midline.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.aku.ramshasaeed.tmk_midline.R;

public class SectionFActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_f);
        this.setTitle(getResources().getString(R.string.tfheading));

    }
}
