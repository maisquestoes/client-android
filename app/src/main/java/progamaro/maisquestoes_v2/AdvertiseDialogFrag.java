package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by helio on 05/11/15.
 */
public class AdvertiseDialogFrag extends DialogFragment {

    public interface AdvertiseDialogFragListener {

        public void onDialogPositiveClick(DialogFragment dialog);

        public void onDialogNegativeClick(DialogFragment dialog);

    }

    AdvertiseDialogFragListener _listener;

    static AdvertiseDialogFrag newInstance(String titulo) {
        AdvertiseDialogFrag dialog = new AdvertiseDialogFrag();
        Bundle args = new Bundle();
        args.putString("title", titulo);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            _listener = (AdvertiseDialogFragListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " deve implementar AdvertiseDialogFragListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /*String title = getArguments().getString("title");
        Dialog myDialog = new AlertDialog.Builder(getActivity())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        _listener.onDialogPositiveClick(AdvertiseDialogFrag.this);
                    }
                })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                _listener.onDialogNegativeClick(AdvertiseDialogFrag.this);
                            }
                        }).create();*/
        Dialog _dialog = new Dialog(getActivity());
        _dialog.setContentView(R.layout.question_advertise);

        return _dialog;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
