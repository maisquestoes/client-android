package progamaro.maisquestoes_v2.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andremiranda on 25/07/15.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "maisQuestoes";
    private static final int DATABASE_VERSION = 2;

    private static final StringBuilder ANSWER_TABLE_CREATE = new StringBuilder()
            .append("CREATE TABLE ANSWER ")
            .append("(IdAnswer integer, Description text, IsCorrect numeric, ImagePath text);");

    private static final StringBuilder QUESTION_TABLE_CREATE = new StringBuilder()
            .append("CREATE TABLE QUESTION ")
            .append("(Query text, Text text, Subject text);");

    private static final StringBuilder USER_TABLE_CREATE = new StringBuilder()
            .append("CREATE TABLE USER ")
            .append("(IdUser integer, Latitude text, Longitude text);");

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static StringBuilder getQuestionTableCreate() {
        return QUESTION_TABLE_CREATE;
    }

    public static StringBuilder getAnswerTableCreate() {
        return ANSWER_TABLE_CREATE;
    }

    public static StringBuilder getUserTableCreate() {
        return USER_TABLE_CREATE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ANSWER_TABLE_CREATE.toString());
        db.execSQL(QUESTION_TABLE_CREATE.toString());
        db.execSQL(USER_TABLE_CREATE.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS QUESTION");
        db.execSQL("DROP TABLE IF EXISTS ANSWER");
        db.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(db);
    }
}
