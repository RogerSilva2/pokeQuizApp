package br.edu.fa7.quizapp.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.fa7.quizapp.R;
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

    public ArrayList<Question> selectAll(int limit) {
        ArrayList<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE + " ORDER BY RANDOM() LIMIT " + limit + ";";

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
        Integer questionId = ((Long) mDataBase.insert(TABLE, null, values)).intValue();
        for (Answer answer : question.getAnswers()) {
            AnswerDAO.getInstance(mContext).insert(answer, questionId);
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

    public void insertDefault() {
        //Alakazam
        Question question = new Question();
        question.setText(mContext.getString(R.string.text_quiz));
        question.setImage(String.valueOf(R.drawable.alakazam));
        List<Answer> answers = new ArrayList<>();
        Answer answer1 = new Answer();
        answer1.setText(mContext.getString(R.string.pokemon_065));
        answer1.setCorrect(true);
        answers.add(answer1);
        Answer answer2 = new Answer();
        answer2.setText(mContext.getString(R.string.pokemon_063));
        answer2.setCorrect(false);
        answers.add(answer2);
        Answer answer3 = new Answer();
        answer3.setText(mContext.getString(R.string.pokemon_064));
        answer3.setCorrect(false);
        answers.add(answer3);
        Answer answer4 = new Answer();
        answer4.setText(mContext.getString(R.string.pokemon_097));
        answer4.setCorrect(false);
        answers.add(answer4);
        question.setAnswers(answers);
        insert(question);

        //Beedrill
        question = new Question();
        question.setText(mContext.getString(R.string.text_quiz));
        question.setImage(String.valueOf(R.drawable.beedrill));
        answers = new ArrayList<>();
        answer1 = new Answer();
        answer1.setText(mContext.getString(R.string.pokemon_015));
        answer1.setCorrect(true);
        answers.add(answer1);
        answer2 = new Answer();
        answer2.setText(mContext.getString(R.string.pokemon_014));
        answer2.setCorrect(false);
        answers.add(answer2);
        answer3 = new Answer();
        answer3.setText(mContext.getString(R.string.pokemon_013));
        answer3.setCorrect(false);
        answers.add(answer3);
        answer4 = new Answer();
        answer4.setText(mContext.getString(R.string.pokemon_012));
        answer4.setCorrect(false);
        answers.add(answer4);
        question.setAnswers(answers);
        insert(question);

        //Blastoise
        question = new Question();
        question.setText(mContext.getString(R.string.text_quiz));
        question.setImage(String.valueOf(R.drawable.blastoise));
        answers = new ArrayList<>();
        answer1 = new Answer();
        answer1.setText(mContext.getString(R.string.pokemon_009));
        answer1.setCorrect(true);
        answers.add(answer1);
        answer2 = new Answer();
        answer2.setText(mContext.getString(R.string.pokemon_007));
        answer2.setCorrect(false);
        answers.add(answer2);
        answer3 = new Answer();
        answer3.setText(mContext.getString(R.string.pokemon_030));
        answer3.setCorrect(false);
        answers.add(answer3);
        answer4 = new Answer();
        answer4.setText(mContext.getString(R.string.pokemon_007));
        answer4.setCorrect(false);
        answers.add(answer4);
        question.setAnswers(answers);
        insert(question);

//        new Quiz(R.drawable.alakazam, "Alakazam", "Abra", "Kadabra", "Hypno"),
//                new Quiz(R.drawable.beedrill, "Beedrill", "Kakuna", "Weedle", "Butterfree"),
//                new Quiz(R.drawable.blastoise, "Blastoise", "Squirtle", "Nidorina", "Wartortle"),
//                new Quiz(R.drawable.bulbasaur, "Bulbasaur", "Venusaur", "Nidorino", "Ivysaur"),
//                new Quiz(R.drawable.chansey, "Chansey", "Clefairy", "Clefable", "Jigglypuff"),
//                new Quiz(R.drawable.charmeleon, "Charmeleon", "Charizard", "Charmander", "Magmar"),
//                new Quiz(R.drawable.dragonite, "Dragonite", "Dragonair", "Dratini", "Charizard"),
//                new Quiz(R.drawable.dugtrio, "Dugtrio", "Diglett", "Doduo", "Dodrio"),
//                new Quiz(R.drawable.ekans, "Ekans", "Arbok", "Dratini", "Dragonair"),
//                new Quiz(R.drawable.exeggutor, "Exeggutor", "Exeggcute", "Bellsprout", "Oddish"),
//                new Quiz(R.drawable.farfetchd, "Farfetch'd", "Fearow", "Spearow", "Pidgeot"),
//                new Quiz(R.drawable.geodude, "Geodude", "Graveler", "Golem", "Onix"),
//                new Quiz(R.drawable.golbat, "Golbat", "Zubat", "Spearow", "Aerodactyl"),
//                new Quiz(R.drawable.growlithe, "Growlithe", "Arcanine", "Vulpix", "Presian"),
//                new Quiz(R.drawable.gyarados, "Gyarados", "Magikarp", "Lapras", "Dragonair"),
//                new Quiz(R.drawable.haunter, "Haunter", "Gastly", "Gengar", "Cloyster"),
//                new Quiz(R.drawable.hitmonlee, "Hitmonlee", "Hitmonchan", "Marowak", "Primeape"),
//                new Quiz(R.drawable.jigglypuff, "Jigglypuff", "Wigglytuff", "Clefairy", "Clefable"),
//                new Quiz(R.drawable.jolteon, "Jolteon", "Flareon", "Vaporeon", "Eevee"),
//                new Quiz(R.drawable.koffing, "Koffing", "Magneton", "Weezing", "Gastly"),
//                new Quiz(R.drawable.lickitung, "Lickitung", "Chansey", "Rhydon", "Kangaskhan"),
//                new Quiz(R.drawable.machop, "Machop", "Machoke", "Machamp", "Graveler"),
//                new Quiz(R.drawable.magmar, "Magmar", "Kangaskhan", "Pinsir", "Electabuzz"),
//                new Quiz(R.drawable.nidoranfe, "Nidoran ♀", "Nidoran ♂", "Nidorina", "Nidorino"),
//                new Quiz(R.drawable.ninetales, "Ninetales", "Vulpix", "Arcanine", "Persian"),
//                new Quiz(R.drawable.pikachu, "Pikachu", "Raichu", "Raticate", "Ratata"),
//                new Quiz(R.drawable.poliwhirl, "Poliwhirl", "Poliwag", "Poliwrath", "Machoke"),
//                new Quiz(R.drawable.porygon, "Porygon", "Ditto", "Mr. Mime", "Jynx"),
//                new Quiz(R.drawable.primeape, "Primeape", "Graveler", "Machoke", "Mankey"),
//                new Quiz(R.drawable.raticate, "Raticate", "Ratata", "Pikachu", "Raichu"),
//                new Quiz(R.drawable.scyther, "Scyther", "Pinsir", "Beedrill", "Butterfree"),
//                new Quiz(R.drawable.snorlax, "Snorlax", "Golem", "Magmar", "Eectabuzz"),
//                new Quiz(R.drawable.victreebel, "Victreebel", "Bellsprout", "Weepinbell", "Vileplume"),
//                new Quiz(R.drawable.vileplume, "Vileplume", "Weepinbell", "Oddish", "Gloom"),
//                new Quiz(R.drawable.zapdos, "Zapdos", "Articuno", "Moltres", "Fearow")
    }
}
