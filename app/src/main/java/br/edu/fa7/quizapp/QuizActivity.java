package br.edu.fa7.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity implements QuizFragment.OnFragmentInteractionListener {

    private String name = "";
    private int countQuiz = 0;
    private int countCorrect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        name = getIntent().getStringExtra("name");
        onFragmentInteraction(false);
    }

    public void showFirstFragment(@DrawableRes int image, String correct, String incorrectOne,
                                  String incorrectTwo, String incorrectThree) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, QuizFragment.newInstance(image, correct, incorrectOne, incorrectTwo, incorrectThree))
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
        if (isCorrect) countCorrect++;

        if (countQuiz != 0) {
            Toast.makeText(this, (isCorrect) ? R.string.toast_correct : R.string.toast_incorrect, Toast.LENGTH_SHORT).show();
        }

        countQuiz++;
        switch (countQuiz) {
            case 1: {
                showFirstFragment(R.drawable.alakazam, "Alakazam", "Abra", "Kadabra", "Hypno");
            } break;
            case 2: {
                showFirstFragment(R.drawable.beedrill, "Beedrill", "Kakuna", "Weedle", "Butterfree");
            } break;
            case 3: {
                showFirstFragment(R.drawable.blastoise, "Blastoise", "Squirtle", "Nidorina", "Wartortle");
            } break;
            case 4: {
                showFirstFragment(R.drawable.bulbasaur, "Bulbasaur", "Venusaur", "Nidorino", "Ivysaur");
            } break;
            case 5: {
                showFirstFragment(R.drawable.farfetchd, "Farfetch'd", "Fearow", "Spearow", "Pidgeot");
            } break;
            default: {
                countQuiz--;
                showQuizActivity();
            } break;
        }
    }
}
