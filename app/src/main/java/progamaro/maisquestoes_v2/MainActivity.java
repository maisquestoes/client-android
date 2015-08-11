package progamaro.maisquestoes_v2;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
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

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tabs
        mViewPager = (ViewPager)findViewById(R.id.vp_tabs);
        mSlidingTabLayout = (SlidingTabLayout)findViewById(R.id.stl_tabs);


        /*//REALIZAR O TESTE DE INSERÇAO NO SQLITE
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
                    *//*FragFavoritos frag1 = (FragFavoritos) fm.findFragmentById(R.id.fragment1);
                    frag1.alteraTextView("FragFavoritos TextView Alterado");*//*
                }
            }
        });*/


    }

}
