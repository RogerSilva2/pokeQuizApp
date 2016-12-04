package br.edu.fa7.quizapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import br.edu.fa7.quizapp.R;
import br.edu.fa7.quizapp.adapters.QuestionAdapter;
import br.edu.fa7.quizapp.daos.QuestionDAO;
import br.edu.fa7.quizapp.entities.Question;

public class ListQuestionActivity extends AppCompatActivity {

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

            ArrayList<Question> questions = QuestionDAO.getInstance(this).selectAll();

            QuestionAdapter adapter = new QuestionAdapter(this, questions);
            recyclerView.setAdapter(adapter);
        }
    }
}
