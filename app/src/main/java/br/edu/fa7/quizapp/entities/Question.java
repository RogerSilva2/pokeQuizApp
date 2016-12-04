package br.edu.fa7.quizapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Question implements Parcelable {

    private Integer id;

    private String text;

    private String image;

    private List<Answer> answers;

    public Question() {
    }

    private Question(Parcel in) {
        setId(in.readInt());
        setText(in.readString());
        setImage(in.readString());
        setAnswers(new ArrayList<Answer>());
        in.readList(getAnswers(), Answer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Question>
            CREATOR = new Parcelable.Creator<Question>() {

        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getText());
        dest.writeString(getImage());
        dest.writeList(getAnswers());
    }
}
