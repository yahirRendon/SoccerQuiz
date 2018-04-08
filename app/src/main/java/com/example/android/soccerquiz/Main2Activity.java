package com.example.android.soccerquiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    boolean question1Result;
    boolean question2Result;
    boolean question3Result;
    boolean question4Result;
    boolean question5Result;
    boolean question6Result;
    TextView resultSummary;
    String summaryMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        resultSummary = (TextView)findViewById(R.id.summary_results);
        question1Result = getIntent().getBooleanExtra("question1Result", false);
        question2Result = getIntent().getBooleanExtra("question2Result", false);
        question3Result = getIntent().getBooleanExtra("question3Result", false);
        question4Result = getIntent().getBooleanExtra("question4Result", false);
        question5Result = getIntent().getBooleanExtra("question5Result", false);
        question6Result = getIntent().getBooleanExtra("question6Result", false);
        createAnswerSummary();
    }

    /**
     * Creates summary of quiz results
     *
     * @return void
     */
    private void createAnswerSummary() {
        summaryMessage = getString(R.string.results);
        if(question1Result) {
            summaryMessage += getString(R.string.q_1) + " " + getString(R.string.correct);
            summaryMessage += getString(R.string.q_1_correct_message);
        } else {
            summaryMessage += getString(R.string.q_1) + " " + getString(R.string.incorrect);
            summaryMessage += getString(R.string.q_1_incorrect_message);
        }

        if(question2Result) {
            summaryMessage += getString(R.string.q_2) + " " +getString(R.string.correct);
            summaryMessage += getString(R.string.q_2_correct_message);
        } else {
            summaryMessage += getString(R.string.q_2) + " " + getString(R.string.incorrect);
            summaryMessage += getString(R.string.q_2_incorrect_message);
        }

        if(question3Result) {
            summaryMessage += getString(R.string.q_3) + " " + getString(R.string.correct);
            summaryMessage += getString(R.string.q_3_correct_message);
        } else {
            summaryMessage += getString(R.string.q_3) + " " + getString(R.string.incorrect);
            summaryMessage += getString(R.string.q_3_incorrect_message);
        }

        if(question4Result) {
            summaryMessage += getString(R.string.q_4) + " " + getString(R.string.correct);
            summaryMessage += getString(R.string.q_4_correct_message);
        } else {
            summaryMessage += getString(R.string.q_4) + " " + getString(R.string.incorrect);
            summaryMessage += getString(R.string.q_4_incorrect_message);
        }

        if(question5Result) {
            summaryMessage += getString(R.string.q_5) + " " + getString(R.string.correct);
            summaryMessage += getString(R.string.q_5_correct_message);
        } else {
            summaryMessage += getString(R.string.q_5) + " " + getString(R.string.incorrect);
            summaryMessage += getString(R.string.q_5_incorrect_message);
        }

        if(question6Result) {
            summaryMessage += getString(R.string.q_6) + " " + getString(R.string.correct);
            summaryMessage += getString(R.string.q_6_correct_message);
        } else {
            summaryMessage += getString(R.string.q_6) + " " + getString(R.string.incorrect);
            summaryMessage += getString(R.string.q_6_incorrect_message);
        }

        resultSummary.setText(summaryMessage);
    }

    public void shareQuizResults(View view) {
        //Send order summary to email app
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "My Soccer Quiz Results");
        intent.putExtra(Intent.EXTRA_TEXT, summaryMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
