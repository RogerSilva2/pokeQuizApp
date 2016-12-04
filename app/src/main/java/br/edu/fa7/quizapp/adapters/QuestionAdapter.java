package br.edu.fa7.quizapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import br.edu.fa7.quizapp.R;
import br.edu.fa7.quizapp.entities.Question;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Question> mQuestions = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mText;
        public ViewHolder(View view) {
            super(view);
            mText = (TextView) view.findViewById(R.id.txt_question);
        }
    }

    public QuestionAdapter(Context context, ArrayList<Question> questions) {
        mContext = context;
        mQuestions = questions;
    }

    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Question question = mQuestions.get(position);
        holder.mText.setText(question.getText());
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }
}
