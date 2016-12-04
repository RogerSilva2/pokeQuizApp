package br.edu.fa7.quizapp.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.edu.fa7.quizapp.daos.AnswerDAO;
import br.edu.fa7.quizapp.daos.QuestionDAO;

public class PersistenceHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "quiz.db3";
    public static final int VERSION = 1;

    private static PersistenceHelper instance;
    @SuppressWarnings("unused")
    private Context context;

    private PersistenceHelper(Context context) {
        super(context, DATABASE, null, VERSION);
        this.context = context;
    }

    public static PersistenceHelper getInstance(Context context) {
        if (instance == null)
            instance = new PersistenceHelper(context);

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QuestionDAO.SCRIPT_CREATE_TABLE_QUESTION);
        db.execSQL(AnswerDAO.SCRIPT_CREATE_TABLE_ANSWER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(AnswerDAO.SCRIPT_DROP_TABLE_ANSWER);
        db.execSQL(QuestionDAO.SCRIPT_DROP_TABLE_QUESTION);
        onCreate(db);
    }
}
