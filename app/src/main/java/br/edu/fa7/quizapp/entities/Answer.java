package br.edu.fa7.quizapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {

    private Integer id;

    private String text;

    private Boolean correct;

    public Answer() {
    }

    private Answer(Parcel in) {
        setId(in.readInt());
        setText(in.readString());
        setCorrect((Boolean) in.readValue(null));
    }

    public static final Parcelable.Creator<Answer>
            CREATOR = new Parcelable.Creator<Answer>() {

        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        public Answer[] newArray(int size) {
            return new Answer[size];
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

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getText());
        dest.writeValue(getCorrect());
    }
}
