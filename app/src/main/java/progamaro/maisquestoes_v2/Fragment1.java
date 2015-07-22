package progamaro.maisquestoes_v2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by helio on 13/07/15.
 */
public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_frag_1,null);

        TextView tv = (TextView) view.findViewById(R.id.textView1);
        tv.setText("Fragment 01");

        return view;
    }

    public void alteraTextView(String texto){
        TextView tv = (TextView)getView().findViewById(R.id.textView1);
        tv.setText(texto);
    }
}
