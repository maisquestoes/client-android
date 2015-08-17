package progamaro.maisquestoes_v2.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.Response;

import java.util.ArrayList;
import java.util.List;

import progamaro.maisquestoes_v2.domain.Question;
import progamaro.maisquestoes_v2.dto.QuestionDTO;
import progamaro.maisquestoes_v2.dto.SubjectsDTO;

/**
 * Created by andremiranda on 25/07/15.
 */
public class DbSubjectsFavoritesHelper {

    private SQLiteDatabase database;

    public DbSubjectsFavoritesHelper(DbOpenHelper helper) {
        this.database = helper.getWritableDatabase();
    }

    public long Insert(SubjectsDTO pSubjectsDTO){
        ContentValues values = new ContentValues(3);
        values.put("_id", pSubjectsDTO.get_id());
        values.put("subject", pSubjectsDTO.getSubject());
        values.put("ischecked", 0);
        return this.database.insert("SUBJECTS_FAVORITES", String.valueOf(new String[]{"_id", "subject, ischecked"}), values );
    }

    public long Update(SubjectsDTO pSubjectsDTO){
        ContentValues values = new ContentValues(3);
        values.put("_id", pSubjectsDTO.get_id());
        values.put("subject", pSubjectsDTO.getSubject());
        values.put("ischecked", pSubjectsDTO.isChecked());
        return this.database.update("SUBJECTS_FAVORITES", values, "_id = '" + pSubjectsDTO.get_id() + "'", null );
    }

    public boolean Delete(SubjectsDTO pSubjectDTO) {
        return this.database.delete("SUBJECTS_FAVORITES", "_id ='" + pSubjectDTO.get_id() + "'", null) > 0;
    }

    public List<SubjectsDTO> GetSubjects(){
        SubjectsDTO subjectsDTO = null;
        Cursor result = database.query("SUBJECTS_FAVORITES", new String[]{"_id", "subject", "ischecked"},null,null,null,null,null);
        List<SubjectsDTO> lista = new ArrayList<SubjectsDTO>();

        while(result.moveToNext()){
            subjectsDTO = new SubjectsDTO();
            subjectsDTO.set_id(result.getString(result.getColumnIndex("_id")));
            subjectsDTO.setSubject(result.getString(result.getColumnIndex("subject")));
            subjectsDTO.setChecked( (result.getInt(result.getColumnIndex("ischecked")) == 1 ? true : false) );
            lista.add(subjectsDTO);
        }

        if (result != null)
            result.close();

        return lista;
    }



    public void DeleteAll(){
        database.delete("SUBJECTS_FAVORITES",null,null);
    }

    /*public void InsertQuestion(){
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
    }*/

}
