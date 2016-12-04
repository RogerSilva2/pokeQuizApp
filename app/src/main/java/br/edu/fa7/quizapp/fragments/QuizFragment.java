package br.edu.fa7.quizapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.edu.fa7.quizapp.R;
import br.edu.fa7.quizapp.entities.Question;

public class QuizFragment extends Fragment {

    private static final String ARG_QUESTION = "question";

    private Question mQuestion;

    private OnFragmentInteractionListener mListener;

    public QuizFragment() {
    }

    public static QuizFragment newInstance(Question question) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuestion = getArguments().getParcelable(ARG_QUESTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        ImageView imageViewQuiz = (ImageView) view.findViewById(R.id.img_quiz);
        Picasso.with(getActivity())
                .load(mQuestion.getImage())
                .placeholder(R.drawable.alakazam)
                .error(R.drawable.beedrill)
                .into(imageViewQuiz);

        TextView textViewQuiz = (TextView) view.findViewById(R.id.txt_quiz);
        textViewQuiz.setText(mQuestion.getText());

        Button buttonOne = (Button) view.findViewById(R.id.btn_one);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(0);
            }
        });
        buttonOne.setText(mQuestion.getAnswers().get(0).getText());
        Button buttonTwo = (Button) view.findViewById(R.id.btn_two);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(1);
            }
        });
        buttonTwo.setText(mQuestion.getAnswers().get(1).getText());
        Button buttonThree = (Button) view.findViewById(R.id.btn_three);
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(2);
            }
        });
        buttonThree.setText(mQuestion.getAnswers().get(2).getText());
        Button buttonFour = (Button) view.findViewById(R.id.btn_four);
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(3);
            }
        });
        buttonFour.setText(mQuestion.getAnswers().get(3).getText());

        return view;
    }

    public void onButtonPressed(int posistion) {
        if (mListener != null) {
            mListener.onFragmentInteraction(mQuestion.getAnswers().get(posistion).getCorrect());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Boolean isCorrect);
    }
}
