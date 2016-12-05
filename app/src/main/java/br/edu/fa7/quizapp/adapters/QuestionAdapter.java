package br.edu.fa7.quizapp.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.fa7.quizapp.R;
import br.edu.fa7.quizapp.entities.Question;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Question> mQuestions = new ArrayList<>();
    private View.OnClickListener mListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewQuestion;
        public CardView mCardViewQuestion;
        public ViewHolder(View view) {
            super(view);
            mTextViewQuestion = (TextView) view.findViewById(R.id.txt_question);
            mCardViewQuestion = (CardView) view.findViewById(R.id.cdv_question);
        }
    }

    public QuestionAdapter(Context context, ArrayList<Question> questions, View.OnClickListener listener) {
        mContext = context;
        mQuestions = questions;
        mListener = listener;
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
        holder.mTextViewQuestion.setText(question.getText());
        holder.mCardViewQuestion.setTag(position);
        holder.mCardViewQuestion.setOnClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }
}
