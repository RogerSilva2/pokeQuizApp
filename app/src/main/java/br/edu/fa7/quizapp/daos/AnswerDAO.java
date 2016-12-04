package br.edu.fa7.quizapp.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.edu.fa7.quizapp.entities.Answer;
import br.edu.fa7.quizapp.entities.Question;
import br.edu.fa7.quizapp.util.PersistenceHelper;

public class AnswerDAO {

    public static final String TABLE = "answer";
    public static final String ID = "_id";
    public static final String TEXT = "text";
    public static final String CORRECT = "correct";
    public static final String QUESTION_ID = "question_id";

    public static final String SCRIPT_CREATE_TABLE_ANSWER = "CREATE TABLE " + TABLE + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TEXT + " TEXT," + CORRECT + " BOOL,"
            + QUESTION_ID + " INTEGER," + " CONSTRAINT FK_question FOREIGN KEY(" + QUESTION_ID
            + ") REFERENCES " + QuestionDAO.TABLE + "(" + QuestionDAO.ID + "));";

    public static final String SCRIPT_DROP_TABLE_ANSWER = "DROP TABLE IF EXISTS " + TABLE;

    private SQLiteDatabase mDataBase = null;

    private static AnswerDAO instance;

    public static AnswerDAO getInstance(Context context) {
        if (instance == null)
            instance = new AnswerDAO(context);
        return instance;
    }

    private AnswerDAO(Context context) {
        PersistenceHelper persistenceHelper = PersistenceHelper
                .getInstance(context);
        mDataBase = persistenceHelper.getWritableDatabase();
    }

    public ArrayList<Answer> selectAll(Integer questionId) {
        ArrayList<Answer> answers = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE + " WHERE " + QUESTION_ID + " = " + questionId + " ORDER BY RANDOM();";

        Cursor cursor = mDataBase.rawQuery(query, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int indexID = cursor.getColumnIndex(ID);
                    int indexText = cursor.getColumnIndex(TEXT);
                    int indexCorrect = cursor.getColumnIndex(CORRECT);

                    do {
                        Answer answer = new Answer();
                        answer.setId(cursor.getInt(indexID));
                        answer.setText(cursor.getString(indexText));
                        answer.setCorrect((cursor.getInt(indexCorrect) == 1));

                        answers.add(answer);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }
        return answers;
    }

    public void insert(Answer answer, Integer questionId) {
        ContentValues values = generateContentValuesAnswer(answer, questionId);
        mDataBase.insert(TABLE, null, values);
    }

    public void update(Answer answer, Integer questionId) {
        ContentValues values = generateContentValuesAnswer(answer, questionId);
        String[] id = { String.valueOf(answer.getId()) };
        mDataBase.update(TABLE, values, ID + " = ?", id);
    }

    public void delete(Answer answer) {
        String[] id = { String.valueOf(answer.getId()) };
        mDataBase.delete(TABLE, ID + " = ?", id);
    }

    @SuppressWarnings("unused")
    public void closeConnection() {
        if (mDataBase != null && mDataBase.isOpen())
            mDataBase.close();
    }

    private ContentValues generateContentValuesAnswer(Answer answer, Integer questionId) {
        ContentValues values = new ContentValues();
        values.put(TEXT, answer.getText());
        values.put(CORRECT, answer.getCorrect());
        values.put(QUESTION_ID, questionId);
        return values;
    }
}
