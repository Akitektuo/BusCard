package com.akitektuo.buscard;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class AskForDetailsDialog extends DialogFragment {

    private EditText editText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =getActivity().getLayoutInflater().inflate(R.layout.dialog_ask_for_details, null);
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String busID = editText.getText().toString();

                        if (busID.length() != 3){
                            Toast.makeText(getContext(), "The bus cod must have 3 digits", Toast.LENGTH_LONG).show();
                        } else {
                            String digit1 = busID.substring(0, 1);
                            if (digit1.equals("0")){
                                startActivity(new Intent(getContext(), ErrorActivity.class));
                            } else{
                                App.Companion.getDatabase().createRequest(new Function0<Unit>() {
                                    @Override
                                    public Unit invoke() {
                                        startActivity(new Intent(getContext(), SuccessActivity.class));
                                        return null;
                                    }
                                });
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", null);

        initViews(view);

        return builder.create();
    }

    private void initViews(View view){
        editText = view.findViewById(R.id.editText);
    }
}
