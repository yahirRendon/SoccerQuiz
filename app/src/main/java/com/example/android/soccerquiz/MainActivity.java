package com.example.android.soccerquiz;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ColorStateList btnGreenBackground; //Adjust question 4 True/False Button
    ColorStateList btnPrimaryBackground; //Adjust question 4 True/False Button
    int correctAnswers = 0; //Track number of correct answers
    boolean question1Result; //Determine if question 1 is answered correctly
    boolean question2Result; //Determine if question 2 is answered correctly
    boolean question3Result; //Determine if question 3 is answered correctly
    boolean question4Result; //Determine if question 4 is answered correctly
    boolean question5Result; //Determine if question 5 is answered correctly
    boolean question6Result; //Determine if question 6 is answered correctly
    boolean userBooleanAnswer; //Determine users boolean answer to question 4
    TextView resultSummary; //Display summary of quiz result
    RadioGroup question1Group; //Question 1 RadioGroup
    int question1ID; //RadioGroup ID
    RadioButton question1Ans; //RadioButton answer to question 1
    RadioGroup question2Group; //Question 2 RadioGroup
    int question2ID; //RadioGroup ID
    RadioButton question2Ans; //RadioButton answer to question 2
    CheckBox question3Ans1; //Question 3 CheckBox answer 1
    CheckBox question3Ans2; //Question 3 CheckBox answer 2
    CheckBox question3Ans3; //Question 3 CheckBox answer 3
    CheckBox question3Ans4; //Question 3 CheckBox answer 4
    Button question4TrueButton; //Question 4 true button
    Button question4FalseButton; //Question 4 false button
    EditText question5Ans; //Question 5 EditText view
    String question5AnsText; //Question 5 String text
    RadioGroup question6Group; //Question 6 RadioGroup
    int question6ID; //RadioGroup ID
    RadioButton question6Ans; //RadioButton answer to question 6
    Button submitResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Hide Keyboard unless EditText is active
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize variables and view elements
        initializeViews();
    }

    /**
     * Initialize Views
     *
     * @return void
     */
    public void initializeViews() {
        submitResults = (Button)findViewById(R.id.submit_answers);
        submitResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAnswers();
                Intent toMain2 = new Intent(getApplicationContext(), Main2Activity.class);
                toMain2.putExtra("question1Result", question1Result);
                toMain2.putExtra("question2Result", question2Result);
                toMain2.putExtra("question3Result", question3Result);
                toMain2.putExtra("question4Result", question4Result);
                toMain2.putExtra("question5Result", question5Result);
                toMain2.putExtra("question6Result", question6Result);
                startActivity(toMain2);
            }
        });
        //Initialize button background colors
        btnGreenBackground = AppCompatResources.getColorStateList(getApplicationContext(), R.color.btnAccentBackgroundTint);
        btnPrimaryBackground = AppCompatResources.getColorStateList(getApplicationContext(), R.color.btnPrimaryBackgroundTint);
        //Initialize Quiz Summary
        resultSummary = (TextView) findViewById(R.id.summary_results);
        //Initialize Question 1 elements
        question1Group = (RadioGroup) findViewById(R.id.radioGroup_1);
        question1Ans = (RadioButton) findViewById(R.id.q1_a3);

        //Initialize Question 2 elements
        question2Group = (RadioGroup) findViewById(R.id.radioGroup_2);
        question2Ans = (RadioButton) findViewById(R.id.q2_a2);

        //Initialize Question 3 elements
        question3Ans1 = (CheckBox) findViewById(R.id.q3_a1);
        question3Ans2 = (CheckBox) findViewById(R.id.q3_a2);
        question3Ans3 = (CheckBox) findViewById(R.id.q3_a3);
        question3Ans4 = (CheckBox) findViewById(R.id.q3_a4);

        //Initialize Question 4 elements
        question4TrueButton = (Button) findViewById(R.id.q4_aTrue);
        question4FalseButton = (Button) findViewById(R.id.q4_aFalse);

        //Initialize Question 5 elements
        question5Ans = (EditText) findViewById(R.id.q5_aText);
        question5AnsText = getString(R.string.question_5_Answer);

        //Initialize Question 6 elements
        question6Group = (RadioGroup) findViewById(R.id.radioGroup_6);
        question6Ans = (RadioButton) findViewById(R.id.q6_a2);

    }

    /**
     * Runs when submit answers button is clicked
     *
     * @return void
     */
    public void submitAnswers() {
        //Reset result booleans to false to allow users to update answer and resubmit
        question1Result = false;
        question2Result = false;
        question3Result = false;
        question4Result = false;
        question5Result = false;
        question6Result = false;

        //Question 1
        question1ID = question1Group.getCheckedRadioButtonId();
        if(question1ID == question1Ans.getId()) {
            question1Result = true;
            correctAnswers += 1;
        }

        //Question 2
        question2ID = question2Group.getCheckedRadioButtonId();
        if(question2ID == question2Ans.getId()) {
            question2Result = true;
            correctAnswers += 1;
        }

        //Question 3
        boolean question3Ans1Selected = question3Ans1.isChecked();
        boolean question3Ans2Selected = question3Ans2.isChecked();
        boolean question3Ans3Selected = question3Ans3.isChecked();
        boolean question3Ans4Selected = question3Ans4.isChecked();
        if (question3Ans3Selected && question3Ans4Selected && !question3Ans1Selected && !question3Ans2Selected) {
            question3Result = true;
            correctAnswers = correctAnswers + 1;
        }

        //Question 4
        if(userBooleanAnswer) {
            question4Result = true;
            correctAnswers += 1;
        }

        //Question 5
        String userAnswerEntered = question5Ans.getText().toString().toUpperCase().trim();
        String key = "PENALTY";
        if(userAnswerEntered.equals(key)) {
            question5Result = true;
            correctAnswers += 1;
        }

        //Question 6
        question6ID = question6Group.getCheckedRadioButtonId();
        if(question6ID == question6Ans.getId()) {
            question6Result = true;
            correctAnswers += 1;
        }

        //Toast with message and score
        if(correctAnswers >= 5) {
            Toast.makeText(getBaseContext(), "Great Job! Your score is: " + correctAnswers + " out of 6.",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "Try Again. Your score is: " + correctAnswers + " out of 6.",
                    Toast.LENGTH_LONG).show();
        }

        //Reset correct to 0 allowing for user to change answers and resubmit
        correctAnswers = 0;

    }

    /**
     * Runs when true button is clicked
     *
     * @return void
     */
    public void question4True(View view) {
        userBooleanAnswer = false;
        ViewCompat.setBackgroundTintList(question4TrueButton, btnGreenBackground);
        ViewCompat.setBackgroundTintList(question4FalseButton, btnPrimaryBackground);
    }

    /**
     * Runs when false button is clicked
     *
     * @return void
     */
    public void question4False(View view) {
        userBooleanAnswer = true;
        ViewCompat.setBackgroundTintList(question4TrueButton, btnPrimaryBackground);
        ViewCompat.setBackgroundTintList(question4FalseButton, btnGreenBackground);
    }

    /**
     * Runs when reset quiz button is clicked
     *
     * @return void
     */
    public void resetQuiz(View view) {
        //Clear all RadioGroup, CheckBox, Button, and EditText
        question1Group.clearCheck();
        question2Group.clearCheck();
        question3Ans1.setChecked(false);
        question3Ans2.setChecked(false);
        question3Ans3.setChecked(false);
        question3Ans4.setChecked(false);
        ViewCompat.setBackgroundTintList(question4TrueButton, btnPrimaryBackground);
        ViewCompat.setBackgroundTintList(question4FalseButton, btnPrimaryBackground);
        userBooleanAnswer = false;
        question5Ans.setText("");
        question6Group.clearCheck();
        resultSummary.setText(R.string.summary_results_text);
    }

}
