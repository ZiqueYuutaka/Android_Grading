package com.testbed.zique_yuutaka.gradecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InvalidObjectException;
import java.text.Format;

import validation.Validator;

public class MainActivity extends AppCompatActivity {

    private Button btnClear;
    private Button btnSubmit;

    private EditText projOne;
    private EditText projTwo;
    private EditText projThree;
    private EditText midProj;
    private EditText finalProj;
    private EditText attnd;

    private final String DEBUG = "DebugTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get EditText objects
        getTextFields();

        //Wire buttons
        wireButtons();
    }

    private void wireButtons(){
        btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Log.d(DEBUG, "Clear button pressed");
                clearEntries();
            }
        });
        btnSubmit = (Button) findViewById(R.id.btnSumbit);
        btnSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Log.d(DEBUG, "Submit button pressed");
                if(validEntries()){
                    Log.d(DEBUG, "Launching calculator");

                    //Create float array of scores
                    Float[] scores = scoreArray();

                    //Calculate grades from the text field values
                    String strGrade = Calculator.getLetterGrade(scores);

                    Intent gradeDisplayIntent = GradeDisplayActivity.newIntent(MainActivity.this, strGrade);

                    startActivity(gradeDisplayIntent);

                    clearEntries();
                }
                else{
                    Log.d(DEBUG, "Incorrect Entries");
                }
            }
        });
    }

    private void getTextFields(){
        projOne = (EditText) findViewById(R.id.etProjectOne);
        projTwo = (EditText) findViewById(R.id.etProjectTwo);
        projThree = (EditText) findViewById(R.id.etProjectThree);
        midProj = (EditText) findViewById(R.id.etMidterm);
        finalProj = (EditText) findViewById(R.id.etFinal);
        attnd = (EditText) findViewById(R.id.etAttend);
    }

    private boolean validEntries(){
        String pOneStr = projOne.getText().toString();
        String pTwoStr = projTwo.getText().toString();
        String pThreeStr = projThree.getText().toString();
        String pMidStr = midProj.getText().toString();
        String pFinalStr = finalProj.getText().toString();
        String attndStr = attnd.getText().toString();

        final int MIN = 0;
        final int MAX_PROJECTS = 100;
        final int MAX_ATTEND = 10;

        return ( !Validator.isEmpty(getBaseContext(), pOneStr) &&
                  Validator.isFloat(getBaseContext(), pOneStr) &&
                  Validator.inRange(getBaseContext(), pOneStr, MIN, MAX_PROJECTS) ) &&

                ( !Validator.isEmpty(getBaseContext(), pTwoStr) &&
                  Validator.isFloat(getBaseContext(), pTwoStr) &&
                  Validator.inRange(getBaseContext(), pTwoStr, MIN, MAX_PROJECTS)  ) &&

                ( !Validator.isEmpty(getBaseContext(), pThreeStr) &&
                   Validator.isFloat(getBaseContext(), pThreeStr) &&
                   Validator.inRange(getBaseContext(), pThreeStr, MIN, MAX_PROJECTS)  ) &&

                ( !Validator.isEmpty(getBaseContext(), pMidStr) &&
                   Validator.isFloat(getBaseContext(), pMidStr) &&
                   Validator.inRange(getBaseContext(), pMidStr, MIN, MAX_PROJECTS)  ) &&

                ( !Validator.isEmpty(getBaseContext(), pFinalStr) &&
                   Validator.isFloat(getBaseContext(), pFinalStr) &&
                   Validator.inRange(getBaseContext(), pFinalStr, MIN, MAX_PROJECTS)  ) &&

                ( !Validator.isEmpty(getBaseContext(), attndStr) &&
                   Validator.isFloat(getBaseContext(), attndStr) &&
                   Validator.inRange(getBaseContext(), attndStr, MIN, MAX_ATTEND)  );
    }

    private void clearEntries(){
        projOne.setText("");
        projTwo.setText("");
        projThree.setText("");
        midProj.setText("");
        finalProj.setText("");
        attnd.setText("");
    }

    private Float[] scoreArray(){
        Float[] temp = new Float[6];

        temp[0] = Float.parseFloat(projOne.getText().toString());
        temp[1] = Float.parseFloat(projTwo.getText().toString());
        temp[2] = Float.parseFloat(projThree.getText().toString());
        temp[3] = Float.parseFloat(midProj.getText().toString());
        temp[4] = Float.parseFloat(finalProj.getText().toString());
        temp[5] = Float.parseFloat(attnd.getText().toString());

        return temp;
    }
}
