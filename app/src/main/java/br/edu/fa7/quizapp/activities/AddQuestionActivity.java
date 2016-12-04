package br.edu.fa7.quizapp.activities;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import br.edu.fa7.quizapp.R;
import br.edu.fa7.quizapp.daos.QuestionDAO;
import br.edu.fa7.quizapp.entities.Answer;
import br.edu.fa7.quizapp.entities.Question;

public class AddQuestionActivity extends AppCompatActivity {

    private TextInputLayout mInputLayoutQuestion;
    private EditText mEditTextQuestion;
    private EditText mEditTextImage;
    private TextInputLayout mInputLayoutAnswer1;
    private EditText mEditTextAnswer1;
    private TextInputLayout mInputLayoutAnswer2;
    private EditText mEditTextAnswer2;
    private TextInputLayout mInputLayoutAnswer3;
    private EditText mEditTextAnswer3;
    private TextInputLayout mInputLayoutAnswer4;
    private EditText mEditTextAnswer4;
    private RadioGroup mRadioGroupAnswer;
    private RadioButton mRadioButtonAnswer1;
    private Button mButtonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        mInputLayoutQuestion = (TextInputLayout) findViewById(R.id.til_question);
        mEditTextQuestion = (EditText) findViewById(R.id.edt_question);
        mEditTextImage = (EditText) findViewById(R.id.edt_image);
        mInputLayoutAnswer1 = (TextInputLayout) findViewById(R.id.til_answer1);
        mEditTextAnswer1 = (EditText) findViewById(R.id.edt_answer1);
        mInputLayoutAnswer2 = (TextInputLayout) findViewById(R.id.til_answer2);
        mEditTextAnswer2 = (EditText) findViewById(R.id.edt_answer2);
        mInputLayoutAnswer3 = (TextInputLayout) findViewById(R.id.til_answer3);
        mEditTextAnswer3 = (EditText) findViewById(R.id.edt_answer3);
        mInputLayoutAnswer4 = (TextInputLayout) findViewById(R.id.til_answer4);
        mEditTextAnswer4 = (EditText) findViewById(R.id.edt_answer4);
        mRadioGroupAnswer = (RadioGroup) findViewById(R.id.rdg_answer);
        mRadioButtonAnswer1 = (RadioButton) findViewById(R.id.rdb_answer1);

        mButtonSave = (Button) findViewById(R.id.btn_save);
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    saveQuestion();
                }
            }
        });
    }

    private void saveQuestion() {
        Question question = new Question();
        question.setText(mEditTextQuestion.getText().toString());
        question.setImage(mEditTextImage.getText().toString());

        List<Answer> answers = new ArrayList<>();
        Answer answer1 = new Answer();
        answer1.setText(mEditTextAnswer1.getText().toString());
        answer1.setCorrect(false);
        Answer answer2 = new Answer();
        answer2.setText(mEditTextAnswer2.getText().toString());
        answer2.setCorrect(false);
        Answer answer3 = new Answer();
        answer3.setText(mEditTextAnswer3.getText().toString());
        answer3.setCorrect(false);
        Answer answer4 = new Answer();
        answer4.setText(mEditTextAnswer4.getText().toString());
        answer4.setCorrect(false);

        switch (mRadioGroupAnswer.getCheckedRadioButtonId()) {
            case R.id.rdb_answer1: {
                answer1.setCorrect(true);
            } break;
            case R.id.rdb_answer2: {
                answer2.setCorrect(true);
            } break;
            case R.id.rdb_answer3: {
                answer3.setCorrect(true);
            } break;
            case R.id.rdb_answer4: {
                answer4.setCorrect(true);
            } break;
        }

        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        question.setAnswers(answers);

        QuestionDAO.getInstance(this).insert(question);
        Snackbar.make(mButtonSave, R.string.snackbar_question_save, Snackbar.LENGTH_LONG).show();
        cleanFields();
    }

    private boolean validateFields() {
        boolean isValid = true;
        if (mEditTextQuestion.getText().toString().trim().isEmpty()) {
            mEditTextQuestion.setText("");
            mInputLayoutQuestion.setError(getResources().getString(R.string.error_question_required));
            isValid = false;
        } else {
            mInputLayoutQuestion.setError("");
            mInputLayoutQuestion.setErrorEnabled(false);
        }
        if (mEditTextAnswer1.getText().toString().trim().isEmpty()) {
            mEditTextAnswer1.setText("");
            mInputLayoutAnswer1.setError(getResources().getString(R.string.error_answer_required));
            isValid = false;
        } else {
            mInputLayoutAnswer1.setError("");
            mInputLayoutAnswer1.setErrorEnabled(false);
        }
        if (mEditTextAnswer2.getText().toString().trim().isEmpty()) {
            mEditTextAnswer2.setText("");
            mInputLayoutAnswer2.setError(getResources().getString(R.string.error_answer_required));
            isValid = false;
        } else {
            mInputLayoutAnswer2.setError("");
            mInputLayoutAnswer2.setErrorEnabled(false);
        }
        if (mEditTextAnswer3.getText().toString().trim().isEmpty()) {
            mEditTextAnswer3.setText("");
            mInputLayoutAnswer3.setError(getResources().getString(R.string.error_answer_required));
            isValid = false;
        } else {
            mInputLayoutAnswer3.setError("");
            mInputLayoutAnswer3.setErrorEnabled(false);
        }
        if (mEditTextAnswer4.getText().toString().trim().isEmpty()) {
            mEditTextAnswer4.setText("");
            mInputLayoutAnswer4.setError(getResources().getString(R.string.error_answer_required));
            isValid = false;
        } else {
            mInputLayoutAnswer4.setError("");
            mInputLayoutAnswer4.setErrorEnabled(false);
        }
        return isValid;
    }

    private void cleanFields() {
        mEditTextQuestion.setText("");
        mEditTextImage.setText("");
        mEditTextAnswer1.setText("");
        mEditTextAnswer2.setText("");
        mEditTextAnswer3.setText("");
        mEditTextAnswer4.setText("");
        mRadioButtonAnswer1.setChecked(true);
    }
}
