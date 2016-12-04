package br.edu.fa7.quizapp.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.edu.fa7.quizapp.entities.Answer;
import br.edu.fa7.quizapp.entities.Question;
import br.edu.fa7.quizapp.util.PersistenceHelper;

public class QuestionDAO {

    public static final String TABLE = "question";
    public static final String ID = "_id";
    public static final String TEXT = "text";
    public static final String IMAGE = "image";

    public static final String SCRIPT_CREATE_TABLE_QUESTION = "CREATE TABLE " + TABLE + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TEXT + " TEXT," + IMAGE + " TEXT" + ");";

    public static final String SCRIPT_DROP_TABLE_QUESTION = "DROP TABLE IF EXISTS " + TABLE;

    private SQLiteDatabase mDataBase = null;

    private static QuestionDAO instance;
    private static Context mContext;

    public static QuestionDAO getInstance(Context context) {
        if (instance == null) {
            instance = new QuestionDAO(context);
            mContext = context;
        }
        return instance;
    }

    private QuestionDAO(Context context) {
        PersistenceHelper persistenceHelper = PersistenceHelper
                .getInstance(context);
        mDataBase = persistenceHelper.getWritableDatabase();
    }

    private ArrayList<Question> selectAll() {
        ArrayList<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE + ";";

        Cursor cursor = mDataBase.rawQuery(query, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int indexID = cursor.getColumnIndex(ID);
                    int indexText = cursor.getColumnIndex(TEXT);
                    int indexImage = cursor.getColumnIndex(IMAGE);

                    do {
                        Question question = new Question();
                        question.setId(cursor.getInt(indexID));
                        question.setText(cursor.getString(indexText));
                        question.setImage(cursor.getString(indexImage));
                        question.setAnswers(AnswerDAO.getInstance(mContext).selectAll(question.getId()));

                        questions.add(question);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }
        return questions;
    }

    public void insert(Question question) {
        ContentValues values = generateContentValuesQuestion(question);
        mDataBase.insert(TABLE, null, values);
        for (Answer answer : question.getAnswers()) {
            AnswerDAO.getInstance(mContext).insert(answer, question.getId());
        }
    }

    public void update(Question question) {
        ContentValues values = generateContentValuesQuestion(question);
        String[] id = {String.valueOf(question.getId())};
        mDataBase.update(TABLE, values, ID + " = ?", id);
        for (Answer answer : question.getAnswers()) {
            AnswerDAO.getInstance(mContext).update(answer, question.getId());
        }
    }

    public void delete(Question question) {
        for (Answer answer : question.getAnswers()) {
            AnswerDAO.getInstance(mContext).delete(answer);
        }
        String[] id = {String.valueOf(question.getId())};
        mDataBase.delete(TABLE, ID + " = ?", id);
    }

    @SuppressWarnings("unused")
    public void closeConnection() {
        if (mDataBase != null && mDataBase.isOpen())
            mDataBase.close();
    }

    private ContentValues generateContentValuesQuestion(Question question) {
        ContentValues values = new ContentValues();
        values.put(TEXT, question.getText());
        values.put(IMAGE, question.getImage());
        return values;
    }

}
