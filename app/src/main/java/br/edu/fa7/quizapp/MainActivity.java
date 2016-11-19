package br.edu.fa7.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStart = (Button) findViewById(R.id.btn_start);
        if (buttonStart != null) {
            buttonStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showQuizActivity();
                }
            });
        }
    }

    private void showQuizActivity() {
        EditText editTextName = (EditText) findViewById(R.id.edt_name);

        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        if (editTextName != null) {
            intent.putExtra("name", editTextName.getText().toString());
        }
        startActivity(intent);
    }
}
