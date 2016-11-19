package br.edu.fa7.quizapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class QuizFragment extends Fragment {

    private static final String ARG_IMAGE = "image";
    private static final String ARG_CORRECT = "correct";
    private static final String ARG_INCORRECT_ONE = "incorrectOne";
    private static final String ARG_INCORRECT_TWO = "incorrectTwo";
    private static final String ARG_INCORRECT_THREE = "incorrectThree";

    private @DrawableRes int mImage;
    private String mCorrect;
    private String mIncorrectOne;
    private String mIncorrectTwo;
    private String mIncorrectThree;

    private OnFragmentInteractionListener mListener;

    public QuizFragment() {
    }

    public static QuizFragment newInstance(@DrawableRes int image, String correct, String incorrectOne,
                                           String incorrectTwo, String incorrectThree) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE, image);
        args.putString(ARG_CORRECT, correct);
        args.putString(ARG_INCORRECT_ONE, incorrectOne);
        args.putString(ARG_INCORRECT_TWO, incorrectTwo);
        args.putString(ARG_INCORRECT_THREE, incorrectThree);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImage = getArguments().getInt(ARG_IMAGE);
            mCorrect = getArguments().getString(ARG_CORRECT);
            mIncorrectOne = getArguments().getString(ARG_INCORRECT_ONE);
            mIncorrectTwo = getArguments().getString(ARG_INCORRECT_TWO);
            mIncorrectThree = getArguments().getString(ARG_INCORRECT_THREE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        int pos = new Random().nextInt(4) + 1;

        ImageView imageViewQuiz = (ImageView) view.findViewById(R.id.img_quiz);
        imageViewQuiz.setImageResource(mImage);

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
                buttonOne.setText(mCorrect);
                buttonTwo.setText(mIncorrectThree);
                buttonThree.setText(mIncorrectOne);
                buttonFour.setText(mIncorrectTwo);
            } break;
            case 2: {
                buttonTwo.setText(mCorrect);
                buttonOne.setText(mIncorrectThree);
                buttonThree.setText(mIncorrectTwo);
                buttonFour.setText(mIncorrectOne);
            } break;
            case 3: {
                buttonThree.setText(mCorrect);
                buttonOne.setText(mIncorrectOne);
                buttonTwo.setText(mIncorrectTwo);
                buttonFour.setText(mIncorrectThree);
            } break;
            case 4: {
                buttonFour.setText(mCorrect);
                buttonOne.setText(mIncorrectTwo);
                buttonTwo.setText(mIncorrectOne);
                buttonThree.setText(mIncorrectThree);
            } break;
        }

        return view;
    }

    public void onButtonPressed(String item) {
        if (mListener != null) {
            mListener.onFragmentInteraction(item.equals(mCorrect));
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
