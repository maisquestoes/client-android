package progamaro.maisquestoes_v2.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import progamaro.maisquestoes_v2.domain.Question;
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

    public List<String> GetQuestions(){
        Cursor result = database.query("QUESTION", new String[]{"Query", "Text", "Subject"},null,null,null,null,null);
        List<String> lista = new ArrayList<String>();

        while(result.moveToNext()){
            lista.add(result.getString(result.getColumnIndex("Query")));
        }

        return lista;
    }

    public void DeleteQuestions(){
        database.delete("QUESTION",null,null);
    }

    public void InsertQuestion(){
        Question q1 = new Question();
        q1.setQuery("Query 1");
        q1.setText("Text1");
        q1.setSubjects(new String[]{"subject1", "subject2"});

        Question q2 = new Question();
        q2.setQuery("Query 2");
        q2.setText("Text2");
        q2.setSubjects(new String[]{"subject3", "subject4"});

        ContentValues values = new ContentValues(3);
        values.put("Query", q1.getQuery());
        values.put("Text", q1.getText());
        values.put("Subject", q1.getSubjects().toString());

        this.database.insert("QUESTION", String.valueOf(new String[]{"Query", "Text", "Subject"}), values);

        ContentValues values2 = new ContentValues(3);
        values2.put("Query", q2.getQuery());
        values2.put("Text", q2.getText());
        values2.put("Subject", q2.getSubjects().toString());

        this.database.insert("QUESTION", String.valueOf(new String[]{"Query", "Text", "Subject"}), values2);
    }

}
