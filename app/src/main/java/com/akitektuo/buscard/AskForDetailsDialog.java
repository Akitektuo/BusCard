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

                        if(busID.length() != 3){
                            Toast.makeText(getContext(), "Bus cannot be found", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            String digit1 = busID.substring(0, 1);
                            if (digit1.equals("0")){
                                Toast.makeText(getContext(), "Bus cannot be found", Toast.LENGTH_LONG).show();
                            }
                            else{
                                startActivity(new Intent(getContext(), SuccessActivity.class));
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
