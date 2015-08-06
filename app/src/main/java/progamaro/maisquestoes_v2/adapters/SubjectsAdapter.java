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

    private List<SubjectsDTO> mSubjects;
    private int mRowLayout;
    private Context mContext;

    public SubjectsAdapter(List<SubjectsDTO> subjects, int rowLayout, Context context) {
        mSubjects = subjects;
        mContext = context;
        mRowLayout = rowLayout;
    }

    @Override
    public SubjectsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(mRowLayout, viewGroup, false);
        return new SubjectsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SubjectsViewHolder viewHolder, int i) {
        SubjectsDTO obj = mSubjects.get(i);
        viewHolder.tv_subject.setText(obj.getSubject());
        viewHolder.tv_subject_description.setText(obj.getSubject_descrpiton());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
