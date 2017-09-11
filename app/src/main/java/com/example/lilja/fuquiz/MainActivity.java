package com.example.lilja.fuquiz;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitAnswers(View view) {
        // first question
        CheckBox finnish = (CheckBox) findViewById(R.id.first_question_Finnish);
        CheckBox french = (CheckBox) findViewById(R.id.first_question_French);
        CheckBox hungarian = (CheckBox) findViewById(R.id.first_question_Hungarian);
        CheckBox russian = (CheckBox) findViewById(R.id.first_question_Russian);

        // second question
        RadioButton hungary = (RadioButton) findViewById(R.id.second_question_Hungary);

        // third question
        EditText capital = (EditText) findViewById(R.id.third_question_capital);
        String capitalOfHungary = capital.getText().toString();

        // forth question
        CheckBox komi = (CheckBox) findViewById(R.id.forth_question_Komi);
        CheckBox sockish = (CheckBox) findViewById(R.id.forth_question_Sockish);
        CheckBox udmurt = (CheckBox) findViewById(R.id.forth_question_Udmurt);
        CheckBox rogalik = (CheckBox) findViewById(R.id.forth_question_Rogalik);

        // fifth question
        RadioButton city = (RadioButton) findViewById(R.id.fifth_question_city);

        // sixth
        RadioButton fiSweNoRu = (RadioButton) findViewById(R.id.sixth_question_FiSweNoRu);

        // seventh
        RadioButton bear = (RadioButton) findViewById(R.id.seventh_question_bear);

        // the correct answers
        final RadioButton[] correctAnswers = {city, hungary, fiSweNoRu, bear};
        final CheckBox[] correctAnswersBoxes = {finnish, hungarian, komi, udmurt};

        score += firstQuestion(finnish, french, hungarian, russian);
        if (hungary.isChecked()) {
            score++;
        }
        if (capitalOfHungary.equalsIgnoreCase("Budapest")) {
            score++;
        }
        score += forthQuestion(komi, sockish, udmurt, rogalik);
        if (city.isChecked()) {
            score++;
        }
        if (fiSweNoRu.isChecked()) {
            score++;
        }
        if (bear.isChecked()) {
            score++;
        }
        // callin the toast creator
        Toast.makeText(this, createGameSummary(score), Toast.LENGTH_LONG).show();

        //setting the corrent answers being blue
        setColor(correctAnswers, correctAnswersBoxes);
        score=0;
        Button submit = (Button) findViewById(R.id.button_submit);
        submit.setEnabled(false);

        //creating the reset button that will make the score 0 and correct answers black
        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAnswers(score, correctAnswers, correctAnswersBoxes);
            }
        });
    }

    /**
     * puts the correct answers in blue     *
     *
     * @param correctAnswers
     * @param correctAnswersBoxes
     */

    public void setColor(RadioButton[] correctAnswers, CheckBox[] correctAnswersBoxes) {
        for (int i = 0; i < correctAnswers.length; i++) {
            correctAnswers[i].setTextColor(Color.BLUE);
        }
        for (int j = 0; j < correctAnswersBoxes.length; j++) {
            correctAnswersBoxes[j].setTextColor(Color.BLUE);
        }
    }

    /**
     * forms the correct message according to the result     *
     *
     * @param score of the game
     * @return message that the user will see
     */

    private String createGameSummary(int score) {
        String message = "Your result is " + score;
        if (score > -1 && score < 3) {
            message += ". This is really bad";
        } else if (score > 2 && score < 6) {
            message += ". You know something, but should learn more";
        } else if (score > 5) {
            message += ". You are a born fenno-ugrist!!!";
        }
        return message;
    }

    /**
     * Resets the score and makes the correct answers black again
     */

    public void resetAnswers(int score, RadioButton[] correctAnswers, CheckBox[] correctAnswersBoxes) {
        score = 0;
        for (int i = 0; i < correctAnswers.length; i++) {
            correctAnswers[i].setTextColor(Color.BLACK);
        }
        for (int j = 0; j < correctAnswersBoxes.length; j++) {
            correctAnswersBoxes[j].setTextColor(Color.BLACK);
        }
        setContentView(R.layout.activity_main);
    }

    /**
     * check whether the given answers are correct and return the score for this part     *
     *
     * @param finnish
     * @param french
     * @param hungarian
     * @param russian
     * @return score
     */
    private int firstQuestion(CheckBox finnish, CheckBox french, CheckBox hungarian, CheckBox russian) {
       if(finnish.isChecked() && !french.isChecked() && hungarian.isChecked() && !russian.isChecked()){
            score = 2;
        }
        return score;
    }

    /**
     * count the result of the 4th question     *
     *
     * @param komi
     * @param sockish
     * @param udmurt
     * @param rogalik
     * @return score
     */

    private int forthQuestion(CheckBox komi, CheckBox sockish, CheckBox udmurt, CheckBox rogalik) {
        if(komi.isChecked() && !sockish.isChecked() && udmurt.isChecked() && !rogalik.isChecked()){
        score = 2;
    }
        return score;
    }
}
