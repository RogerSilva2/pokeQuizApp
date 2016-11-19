package br.edu.fa7.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView textViewResult = (TextView) findViewById(R.id.txt_result);
        if (textViewResult != null)
            textViewResult.setText(getString(R.string.text_result, getIntent().getStringExtra("name"),
                    getIntent().getIntExtra("countCorrect", 0), getIntent().getIntExtra("countQuiz", 0)));
    }
}
