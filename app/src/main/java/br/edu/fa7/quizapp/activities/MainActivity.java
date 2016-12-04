package br.edu.fa7.quizapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import br.edu.fa7.quizapp.R;
import br.edu.fa7.quizapp.entities.Quiz;

public class MainActivity extends AppCompatActivity {

    List<Quiz> quizList = Arrays.asList(
            new Quiz(R.drawable.alakazam, "Alakazam", "Abra", "Kadabra", "Hypno"),
            new Quiz(R.drawable.beedrill, "Beedrill", "Kakuna", "Weedle", "Butterfree"),
            new Quiz(R.drawable.blastoise, "Blastoise", "Squirtle", "Nidorina", "Wartortle"),
            new Quiz(R.drawable.bulbasaur, "Bulbasaur", "Venusaur", "Nidorino", "Ivysaur"),
            new Quiz(R.drawable.chansey, "Chansey", "Clefairy", "Clefable", "Jigglypuff"),
            new Quiz(R.drawable.charmeleon, "Charmeleon", "Charizard", "Charmander", "Magmar"),
            new Quiz(R.drawable.dragonite, "Dragonite", "Dragonair", "Dratini", "Charizard"),
            new Quiz(R.drawable.dugtrio, "Dugtrio", "Diglett", "Doduo", "Dodrio"),
            new Quiz(R.drawable.ekans, "Ekans", "Arbok", "Dratini", "Dragonair"),
            new Quiz(R.drawable.exeggutor, "Exeggutor", "Exeggcute", "Bellsprout", "Oddish"),
            new Quiz(R.drawable.farfetchd, "Farfetch'd", "Fearow", "Spearow", "Pidgeot"),
            new Quiz(R.drawable.geodude, "Geodude", "Graveler", "Golem", "Onix"),
            new Quiz(R.drawable.golbat, "Golbat", "Zubat", "Spearow", "Aerodactyl"),
            new Quiz(R.drawable.growlithe, "Growlithe", "Arcanine", "Vulpix", "Presian"),
            new Quiz(R.drawable.gyarados, "Gyarados", "Magikarp", "Lapras", "Dragonair"),
            new Quiz(R.drawable.haunter, "Haunter", "Gastly", "Gengar", "Cloyster"),
            new Quiz(R.drawable.hitmonlee, "Hitmonlee", "Hitmonchan", "Marowak", "Primeape"),
            new Quiz(R.drawable.jigglypuff, "Jigglypuff", "Wigglytuff", "Clefairy", "Clefable"),
            new Quiz(R.drawable.jolteon, "Jolteon", "Flareon", "Vaporeon", "Eevee"),
            new Quiz(R.drawable.koffing, "Koffing", "Magneton", "Weezing", "Gastly"),
            new Quiz(R.drawable.lickitung, "Lickitung", "Chansey", "Rhydon", "Kangaskhan"),
            new Quiz(R.drawable.machop, "Machop", "Machoke", "Machamp", "Graveler"),
            new Quiz(R.drawable.magmar, "Magmar", "Kangaskhan", "Pinsir", "Electabuzz"),
            new Quiz(R.drawable.nidoranfe, "Nidoran ♀", "Nidoran ♂", "Nidorina", "Nidorino"),
            new Quiz(R.drawable.ninetales, "Ninetales", "Vulpix", "Arcanine", "Persian"),
            new Quiz(R.drawable.pikachu, "Pikachu", "Raichu", "Raticate", "Ratata"),
            new Quiz(R.drawable.poliwhirl, "Poliwhirl", "Poliwag", "Poliwrath", "Machoke"),
            new Quiz(R.drawable.porygon, "Porygon", "Ditto", "Mr. Mime", "Jynx"),
            new Quiz(R.drawable.primeape, "Primeape", "Graveler", "Machoke", "Mankey"),
            new Quiz(R.drawable.raticate, "Raticate", "Ratata", "Pikachu", "Raichu"),
            new Quiz(R.drawable.scyther, "Scyther", "Pinsir", "Beedrill", "Butterfree"),
            new Quiz(R.drawable.snorlax, "Snorlax", "Golem", "Magmar", "Eectabuzz"),
            new Quiz(R.drawable.victreebel, "Victreebel", "Bellsprout", "Weepinbell", "Vileplume"),
            new Quiz(R.drawable.vileplume, "Vileplume", "Weepinbell", "Oddish", "Gloom"),
            new Quiz(R.drawable.zapdos, "Zapdos", "Articuno", "Moltres", "Fearow"));

    private static final int LEVEL_EASY = 5;
    private static final int LEVEL_MEDIUM = 10;
    private static final int LEVEL_HARD = 15;

    private TextInputLayout mInputLayoutName;
    private EditText mEditTextName;
    private Spinner mSpinnerLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputLayoutName = (TextInputLayout) findViewById(R.id.til_name);
        mEditTextName = (EditText) findViewById(R.id.edt_name);

        mSpinnerLevel = (Spinner) findViewById(R.id.spr_level);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.levels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerLevel.setAdapter(adapter);

        Button buttonStart = (Button) findViewById(R.id.btn_start);
        if (buttonStart != null) {
            buttonStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (validateFields()) {
                        showQuizActivity();
                    }
                }
            });
        }

        FloatingActionButton buttonAdd = (FloatingActionButton) findViewById(R.id.fab_add);
        if (buttonAdd != null) {
            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAddQuestionActivity();
                }
            });
        }
    }

    private void showQuizActivity() {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        EditText editTextName = (EditText) findViewById(R.id.edt_name);
        if (editTextName != null) {
            intent.putExtra("name", editTextName.getText().toString());
        }


        intent.putParcelableArrayListExtra("quiz", shortQuiz(countQuestion()));
        startActivity(intent);
    }

    private boolean validateFields() {
        boolean isValid = true;
        if (mEditTextName.getText().toString().trim().isEmpty()) {
            mEditTextName.setText("");
            mInputLayoutName.setError(getResources().getString(R.string.error_name_required));
            isValid = false;
        } else {
            mInputLayoutName.setError("");
            mInputLayoutName.setErrorEnabled(false);
        }
        return isValid;
    }

    private int countQuestion() {
        int countQuestion = LEVEL_EASY;
        switch (mSpinnerLevel.getSelectedItemPosition()) {
            case 0: {
                countQuestion = LEVEL_EASY;
            } break;
            case 1: {
                countQuestion = LEVEL_MEDIUM;
            } break;
            case 2: {
                countQuestion = LEVEL_HARD;
            } break;
        }
        return countQuestion;
    }

    private ArrayList<Quiz> shortQuiz(int size) {
        ArrayList<Quiz> quizListArray = new ArrayList<>();
        List<Integer> exclude = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int sort = sortNumber(0, quizList.size(), exclude);
            if (quizList.size() > sort) {
                quizListArray.add(quizList.get(sort));
                exclude.add(sort);
            }
        }
        return quizListArray;
    }

    private int sortNumber(int init, int end, List<Integer> exclude) {
        int random = new Random().nextInt(end) + init;
        if (exclude.contains(random)) {
            return sortNumber(init, end, exclude);
        }
        return random;
    }

    private void showAddQuestionActivity() {
        Intent intent = new Intent(MainActivity.this, AddQuestionActivity.class);
        startActivity(intent);
    }
}
