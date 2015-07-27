package progamaro.maisquestoes_v2.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import progamaro.maisquestoes_v2.dto.QuestionDTO;

/**
 * Created by andremiranda on 25/07/15.
 */
public class DbQuestionHelper {

    private SQLiteDatabase database;

    public DbQuestionHelper(DbOpenHelper helper) {
        this.database = helper.getWritableDatabase();
    }

    public void InsertQuestion(QuestionDTO question){
        ContentValues values = new ContentValues(3);
        values.put("Query", "query");
        values.put("Text", "teste");
        values.put("Subject", "subject");
        this.database.insert("QUESTION", String.valueOf(new String[]{"Query", "Text", "Subject"}), values );
    }

}
