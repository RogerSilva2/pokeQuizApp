package br.edu.fa7.quizapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import br.edu.fa7.quizapp.R;
import br.edu.fa7.quizapp.entities.Quiz;

public class QuizFragment extends Fragment {

    private static final String ARG_QUIZ = "quiz";

    private Quiz mQuiz;

    private OnFragmentInteractionListener mListener;

    public QuizFragment() {
    }

    public static QuizFragment newInstance(Quiz quiz) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_QUIZ, quiz);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuiz = getArguments().getParcelable(ARG_QUIZ);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        int pos = new Random().nextInt(4) + 1;

        ImageView imageViewQuiz = (ImageView) view.findViewById(R.id.img_quiz);
        imageViewQuiz.setImageResource(mQuiz.getImage());

        Button buttonOne = (Button) view.findViewById(R.id.btn_one);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(((Button) v).getText().toString());
            }
        });
        Button buttonTwo = (Button) view.findViewById(R.id.btn_two);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(((Button) v).getText().toString());
            }
        });
        Button buttonThree = (Button) view.findViewById(R.id.btn_three);
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(((Button) v).getText().toString());
            }
        });
        Button buttonFour = (Button) view.findViewById(R.id.btn_four);
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(((Button) v).getText().toString());
            }
        });

        switch (pos) {
            case 1: {
                buttonOne.setText(mQuiz.getCorrect());
                buttonTwo.setText(mQuiz.getIncorrectThree());
                buttonThree.setText(mQuiz.getIncorrectOne());
                buttonFour.setText(mQuiz.getIncorrectTwo());
            } break;
            case 2: {
                buttonTwo.setText(mQuiz.getCorrect());
                buttonOne.setText(mQuiz.getIncorrectThree());
                buttonThree.setText(mQuiz.getIncorrectTwo());
                buttonFour.setText(mQuiz.getIncorrectOne());
            } break;
            case 3: {
                buttonThree.setText(mQuiz.getCorrect());
                buttonOne.setText(mQuiz.getIncorrectOne());
                buttonTwo.setText(mQuiz.getIncorrectTwo());
                buttonFour.setText(mQuiz.getIncorrectThree());
            } break;
            case 4: {
                buttonFour.setText(mQuiz.getCorrect());
                buttonOne.setText(mQuiz.getIncorrectTwo());
                buttonTwo.setText(mQuiz.getIncorrectOne());
                buttonThree.setText(mQuiz.getIncorrectThree());
            } break;
        }

        return view;
    }

    public void onButtonPressed(String item) {
        if (mListener != null) {
            mListener.onFragmentInteraction(item.equals(mQuiz.getCorrect()));
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
