package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import progamaro.maisquestoes_v2.adapters.SubjectsViewAdapter;
import progamaro.maisquestoes_v2.dto.SubjectsDTO;

/**
 * Created by andremiranda on 06/08/15.
 */
public class PreConfiguration extends AppCompatActivity {

    private GridView _gridview;
    private Toolbar _toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_configuration);

        _toolbar = (Toolbar) findViewById(R.id.inc_pre_configuration_toolbar);

        setSupportActionBar(_toolbar);
        _toolbar.setNavigationIcon(R.mipmap.ic_search2);

//        _toolbar.setTitle("Pré Configuração");
//        _toolbar.setSubtitle("Configure aqui seu material de estudo...");
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setHomeButtonEnabled(true);

        _gridview = (GridView) findViewById(R.id.gv_cards);
        _gridview.setAdapter(new SubjectsViewAdapter(getSubjects(), this));
    }

    public List<SubjectsDTO> getSubjects(){
        List<SubjectsDTO> list = new ArrayList<SubjectsDTO>();
        list.add(new SubjectsDTO("teste1", "teste_desc1"));
        list.add(new SubjectsDTO("teste2", "teste_desc2"));
        list.add(new SubjectsDTO("teste3", "teste_desc3"));
        list.add(new SubjectsDTO("teste4", "teste_desc4"));

        return list;
    }
}
