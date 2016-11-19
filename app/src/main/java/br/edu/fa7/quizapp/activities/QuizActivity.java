package br.edu.fa7.quizapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.fa7.quizapp.R;
import br.edu.fa7.quizapp.entities.Quiz;
import br.edu.fa7.quizapp.fragments.QuizFragment;

public class QuizActivity extends AppCompatActivity implements QuizFragment.OnFragmentInteractionListener {

    private String name = "";
    private int countQuiz = 0;
    private int countCorrect = 0;
    private ArrayList<Quiz> quizList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        name = getIntent().getStringExtra("name");
        quizList = getIntent().getParcelableArrayListExtra("quiz");
        onFragmentInteraction(false);
    }

    public void showFirstFragment(Quiz quiz) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, QuizFragment.newInstance(quiz))
                .commit();
    }

    private void showQuizActivity() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("countQuiz", countQuiz);
        intent.putExtra("countCorrect", countCorrect);
        onBackPressed();
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Boolean isCorrect) {
        if (isCorrect) {
            countCorrect++;
        }
        if (countQuiz != 0) {
            Toast.makeText(this, (isCorrect) ? R.string.toast_correct : R.string.toast_incorrect, Toast.LENGTH_SHORT).show();
        }

        if (countQuiz < quizList.size()) {
            showFirstFragment(quizList.get(countQuiz));
            countQuiz++;
        } else {
            showQuizActivity();
        }
    }
}
