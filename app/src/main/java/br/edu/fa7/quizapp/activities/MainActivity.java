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

import java.util.List;

import br.edu.fa7.quizapp.R;
import br.edu.fa7.quizapp.daos.QuestionDAO;
import br.edu.fa7.quizapp.entities.Question;

public class MainActivity extends AppCompatActivity {

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
        intent.putParcelableArrayListExtra("questions",
                QuestionDAO.getInstance(getApplicationContext()).selectAll(countQuestion()));
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

    private void showAddQuestionActivity() {
        Intent intent = new Intent(MainActivity.this, AddQuestionActivity.class);
        startActivity(intent);
    }
}
