package br.edu.fa7.quizapp.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

public class Quiz implements Parcelable {

    private @DrawableRes int image;

    private String correct;

    private String incorrectOne;

    private String incorrectTwo;

    private String incorrectThree;

    public Quiz(@DrawableRes int image, String correct, String incorrectOne,
                String incorrectTwo, String incorrectThree) {
        setImage(image);
        setCorrect(correct);
        setIncorrectOne(incorrectOne);
        setIncorrectTwo(incorrectTwo);
        setIncorrectThree(incorrectThree);
    }

    private Quiz(Parcel in) {
        this(in.readInt(), in.readString(), in.readString(), in.readString(), in.readString());
    }

    public static final Parcelable.Creator<Quiz>
            CREATOR = new Parcelable.Creator<Quiz>() {

        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getIncorrectOne() {
        return incorrectOne;
    }

    public void setIncorrectOne(String incorrectOne) {
        this.incorrectOne = incorrectOne;
    }

    public String getIncorrectTwo() {
        return incorrectTwo;
    }

    public void setIncorrectTwo(String incorrectTwo) {
        this.incorrectTwo = incorrectTwo;
    }

    public String getIncorrectThree() {
        return incorrectThree;
    }

    public void setIncorrectThree(String incorrectThree) {
        this.incorrectThree = incorrectThree;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "image=" + getImage() +
                ", correct='" + getCorrect() + '\'' +
                ", incorrectOne='" + getIncorrectOne() + '\'' +
                ", incorrectTwo='" + getIncorrectTwo() + '\'' +
                ", incorrectThree='" + getIncorrectThree() + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getImage());
        dest.writeString(getCorrect());
        dest.writeString(getIncorrectOne());
        dest.writeString(getIncorrectTwo());
        dest.writeString(getIncorrectThree());
    }
}
