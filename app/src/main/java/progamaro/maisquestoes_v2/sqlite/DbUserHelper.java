package progamaro.maisquestoes_v2.sqlite;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by andremiranda on 29/07/15.
 */
public class DbUserHelper {
    private SQLiteDatabase database;

    public DbUserHelper(DbOpenHelper helper) {
        this.database = helper.getWritableDatabase();
    }
}
