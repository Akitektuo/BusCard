package com.akitektuo.buscard;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AskForDetailsDialog extends DialogFragment {

    private EditText editText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =getActivity().getLayoutInflater().inflate(R.layout.dialog_ask_for_details, null);
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity())
                .setTitle("Enter bus details")
                .setView(view)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Your ride has been confirmed. Thank you!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null);

        initViews(view);

        return builder.create();
    }

    private void initViews(View view){
        editText = view.findViewById(R.id.editText);
        editText.setText("");
    }
}
