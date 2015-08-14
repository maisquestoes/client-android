package progamaro.maisquestoes_v2;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import progamaro.maisquestoes_v2.adapters.SubjectsAdapter;
import progamaro.maisquestoes_v2.dto.SubjectsDTO;

/**
 * Created by andremiranda on 05/08/15.
 */
public class SubjectsFrag extends Fragment {

    private RecyclerView _recyclerView;
    private RecyclerView.LayoutManager _layoutManager;
    private SubjectsAdapter _adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subjects,null);

        _recyclerView = (RecyclerView) view.findViewById(R.id.rv_subjects);
        _recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        _adapter = new SubjectsAdapter(getSubjects(), R.layout.card_subjects);
        _recyclerView.setAdapter(_adapter);

        return view;

    }

    public List<SubjectsDTO> getSubjects(){
        List<SubjectsDTO> list = new ArrayList<SubjectsDTO>();
        /*list.add(new SubjectsDTO("teste1", "teste_desc1"));
        list.add(new SubjectsDTO("teste2", "teste_desc2"));
        list.add(new SubjectsDTO("teste3", "teste_desc3"));
        list.add(new SubjectsDTO("teste4", "teste_desc4"));*/

        return list;
    }
}
