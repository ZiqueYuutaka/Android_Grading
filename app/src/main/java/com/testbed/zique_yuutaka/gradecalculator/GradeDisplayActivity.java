package com.testbed.zique_yuutaka.gradecalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GradeDisplayActivity extends AppCompatActivity {

    private static final String GRADE_VAL = "gradeclaculator.gradeval";
    private String strGrade;
    private TextView tvGrade;

    public static Intent newIntent(Context packageContext, String strGrade){
        Intent intent = new Intent(packageContext, GradeDisplayActivity.class);
        intent.putExtra(GRADE_VAL, strGrade);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_display);

        strGrade = getIntent().getStringExtra(GRADE_VAL);

        tvGrade = (TextView)findViewById(R.id.grade_textview);

        tvGrade.setText(strGrade);
    }
}
