package com.almasapp.hw7.almasapp7;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDemo extends Fragment {
    private static final String TAG = "FragmentDemo";

    private TextView dialogResultText;

    public FragmentDemo() {
        // Required empty public constructor
    }

    private static final int REQUEST = 0;
    public void showDialogGetResult() {
        FragmentMyDialog dialog = FragmentMyDialog.newInstance();
        dialog.setTargetFragment(FragmentDemo.this, REQUEST);
        dialog.show(getFragmentManager(), "Dialog: get result");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");

        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST) {
            // get data via data.getSerializableExtra
            String name = data.getStringExtra(FragmentMyDialog.ARGS_NAME);
            Float rating = data.getFloatExtra(FragmentMyDialog.ARGS_RATING, 0);
            boolean willBuy = data.getBooleanExtra(FragmentMyDialog.ARGS_WILL_BUY, false);

            String newText = "Form results: ";
            newText += name + " rated " + rating.intValue()
                    + " out of 5 starts and will";
            if (!willBuy)
                newText += " not";
            newText += " buy a movie ticket.";
            dialogResultText.setText(newText);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demo, container, false);

        dialogResultText = (TextView) view.findViewById(R.id.textViewDemoResult);

        Button dialogButton = (Button) view.findViewById(R.id.buttonDialogDemo);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogGetResult();
            }
        });

        return view;
    }
}
