package progamaro.maisquestoes_v2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import progamaro.maisquestoes_v2.dto.SubjectsDTO;

/**
 * Created by andremiranda on 05/08/15.
 */
public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsViewHolder> {

    private List<SubjectsDTO> _subjects;
    private int _rowLayout;
    private Context _context;

    public SubjectsAdapter(List<SubjectsDTO> subjects, int rowLayout) {
        _subjects = subjects;
        _rowLayout = rowLayout;
    }

    @Override
    public SubjectsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(_rowLayout, viewGroup, false);
        return new SubjectsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SubjectsViewHolder viewHolder, int i) {
        SubjectsDTO obj = _subjects.get(i);
        viewHolder.tv_subject.setText(obj.get_id());
        viewHolder.tv_subject_description.setText(obj.getSubject());
    }

    @Override
    public int getItemCount() {
        return _subjects.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
