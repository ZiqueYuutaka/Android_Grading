package com.testbed.zique_yuutaka.gradecalculator;

import android.util.Log;

/**
 * Created by Zique Yuutaka on 2/12/2017.
 */

public abstract class Calculator {

    public static float getTotal(Float[] scores){
        float temp = 0.00f;

        //Calculate Projects
        temp += (scores[0] * .15f);
        temp += (scores[1] * .15f);
        temp += (scores[2] * .15f);
        temp += (scores[3] * .20f);
        temp += (scores[4] * .25f);
        temp += scores[5];
        Log.d("Debug getTotal", "temp before round: " + temp);
        temp = Math.round(temp);
        Log.d("Debug getTotal", "temp after round: " + temp);

        return temp;
    }

    public static String getLetterGrade(Float[] scores){
        Float temp = getTotal(scores);

        if(temp >= 94 && temp <= 100 ){
            return "A";
        } else if( temp >= 90 && temp <= 93 ){
            return "A-";
        }else if( temp >= 87 && temp <= 89 ){
            return "B+";
        }else if( temp >= 83 && temp <= 86 ){
            return "B";
        }else if( temp >= 80 && temp <= 82 ){
            return "B-";
        }else if( temp >= 77 && temp <= 79 ){
            return "C+";
        }else if( temp >= 70 && temp <= 76 ){
            return "C";
        }else if( temp >= 60 && temp <= 69 ){
            return "D";
        }else{
            return "F";
        }
    }
}
