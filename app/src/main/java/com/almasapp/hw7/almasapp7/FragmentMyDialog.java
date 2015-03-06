package com.almasapp.hw7.almasapp7;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class FragmentMyDialog extends DialogFragment {
    private static final String TAG = "FragmentMyDialog";

    public static final String ARGS_NAME = "name";
    public static final String ARGS_RATING = "rating";
    public static final String ARGS_WILL_BUY = "buy";

    public FragmentMyDialog() {
        // Required empty public constructor
    }

    public void showDialog() {
        FragmentMyDialog dialog = FragmentMyDialog.newInstance();
        dialog.show(getFragmentManager(), "Dialog");
    }

    public static FragmentMyDialog newInstance() {
        return new FragmentMyDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog, null);

        final EditText name = (EditText) view.findViewById(R.id.editTextDialogName);
        final RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBarDialog);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBoxDialog);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view)
                .setTitle("Title")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (getTargetFragment() != null) {
                                    if (name.getText().toString().length() != 0) {
                                        Intent i = new Intent();

                                        i.putExtra(ARGS_NAME, name.getText().toString());
                                        i.putExtra(ARGS_RATING, ratingBar.getRating());
                                        i.putExtra(ARGS_WILL_BUY, checkBox.isChecked());

                                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                                    }
                                    else
                                        Toast.makeText(getActivity(), "You must provide a name!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (getTargetFragment() != null) {
                                    Intent i = new Intent();
                                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, i);
                                }
                            }
                        });
        return alertDialogBuilder.create();
    }
}
