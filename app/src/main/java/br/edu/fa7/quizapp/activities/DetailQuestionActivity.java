package br.edu.fa7.quizapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.edu.fa7.quizapp.R;
import br.edu.fa7.quizapp.entities.Question;

public class DetailQuestionActivity extends AppCompatActivity {

    private Question mQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_question);

        mQuestion = getIntent().getParcelableExtra("question");

        ImageView imageViewQuestion = (ImageView) findViewById(R.id.img_question);
        Picasso.with(this)
                .load(mQuestion.getImage())
                .placeholder(R.drawable.alakazam)
                .error(R.drawable.beedrill)
                .into(imageViewQuestion);

        TextView textViewQuestion = (TextView) findViewById(R.id.txt_question);
        if (textViewQuestion != null) {
            textViewQuestion.setText(mQuestion.getText());
        }

        TextView textViewAnswer1 = (TextView) findViewById(R.id.txt_answer1);
        if (textViewAnswer1 != null) {
            textViewAnswer1.setText(mQuestion.getAnswers().get(0).getText());
            if (mQuestion.getAnswers().get(0).getCorrect()) {
                textViewAnswer1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textViewAnswer1.setTextColor(getResources().getColor(android.R.color.white));
            }
        }

        TextView textViewAnswer2 = (TextView) findViewById(R.id.txt_answer2);
        if (textViewAnswer2 != null) {
            textViewAnswer2.setText(mQuestion.getAnswers().get(1).getText());
            if (mQuestion.getAnswers().get(1).getCorrect()) {
                textViewAnswer2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textViewAnswer2.setTextColor(getResources().getColor(android.R.color.white));
            }
        }

        TextView textViewAnswer3 = (TextView) findViewById(R.id.txt_answer3);
        if (textViewAnswer3 != null) {
            textViewAnswer3.setText(mQuestion.getAnswers().get(2).getText());
            if (mQuestion.getAnswers().get(2).getCorrect()) {
                textViewAnswer3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textViewAnswer3.setTextColor(getResources().getColor(android.R.color.white));
            }
        }

        TextView textViewAnswer4 = (TextView) findViewById(R.id.txt_answer4);
        if (textViewAnswer4 != null) {
            textViewAnswer4.setText(mQuestion.getAnswers().get(3).getText());
            if (mQuestion.getAnswers().get(3).getCorrect()) {
                textViewAnswer4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textViewAnswer4.setTextColor(getResources().getColor(android.R.color.white));
            }
        }
    }
}
