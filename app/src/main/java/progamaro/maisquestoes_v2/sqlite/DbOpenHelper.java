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
    private static final String DICTIONARY_TABLE_CREATE = "teste";

    DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
