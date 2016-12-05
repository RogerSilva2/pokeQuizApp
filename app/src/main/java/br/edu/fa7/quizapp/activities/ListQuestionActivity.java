package br.edu.fa7.quizapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import br.edu.fa7.quizapp.R;
import br.edu.fa7.quizapp.adapters.QuestionAdapter;
import br.edu.fa7.quizapp.daos.QuestionDAO;
import br.edu.fa7.quizapp.entities.Question;

public class ListQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Question> mQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_question);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcv_question);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            mQuestions = QuestionDAO.getInstance(this).selectAll();

            QuestionAdapter adapter = new QuestionAdapter(this, mQuestions, this);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View v) {
        Integer id = (Integer) v.getTag();
        Intent intent = new Intent(ListQuestionActivity.this, DetailQuestionActivity.class);
        intent.putExtra("question", mQuestions.get(id));
        startActivity(intent);
    }
}
