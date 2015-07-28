package progamaro.maisquestoes_v2;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.facebook.FacebookSdk;

import java.util.ArrayList;

import progamaro.maisquestoes_v2.sqlite.DbOpenHelper;
import progamaro.maisquestoes_v2.sqlite.DbQuestionHelper;

public class MainActivity extends FragmentActivity {

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        //REALIZAR O TESTE DE INSERÇAO NO SQLITE
        DbQuestionHelper qHelper = new DbQuestionHelper(new DbOpenHelper(this));
        qHelper.DeleteQuestions();
        qHelper.InsertQuestion();

        String[] lista = new String[]{"Hélio Feliciano", "André Miranda", "Grécio Beline", "Xico"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1){
                    /*Fragment1 frag1 = (Fragment1) fm.findFragmentById(R.id.fragment1);
                    frag1.alteraTextView("Fragment1 TextView Alterado");*/
                }
            }
        });


    }

}
