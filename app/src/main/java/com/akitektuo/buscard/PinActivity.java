package com.akitektuo.buscard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PinActivity extends AppCompatActivity {

    private EditText editTxt1, editTxt2, editTxt3, editTxt4;
    private int digit1, digit2, digit3, digit4;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        mContext = this;
        initViews();
        editTxt1.requestFocus();
        editTxt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length() == 1) {
                    digit1 = Integer.parseInt(editable.toString());
                    editTxt2.requestFocus();
                }
            }
        });
        editTxt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length() == 1){
                digit2 = Integer.parseInt(editable.toString());
                editTxt3.requestFocus();
            }

            }
        });
        editTxt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length() == 1){
                    digit3 = Integer.parseInt(editable.toString());
                    editTxt4.requestFocus();
                }
            }
        });

        editTxt4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length() == 1){
                    digit4 = Integer.parseInt(editable.toString());
                    if(digit1 == 0 && digit2 == 0 && digit3 == 0 && digit4 == 0){
                        Intent intent = new Intent(mContext, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(mContext, "ID is not correct, try again", Toast.LENGTH_SHORT).show();
                        editTxt1.setText("");
                        editTxt2.setText("");
                        editTxt3.setText("");
                        editTxt4.setText("");
                        editTxt1.requestFocus();
                    }
                }
            }
        });

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
    private void initViews(){
        editTxt1 = findViewById(R.id.Digit1);
        editTxt2 = findViewById(R.id.Digit2);
        editTxt3 = findViewById(R.id.Digit3);
        editTxt4 = findViewById(R.id.Digit4);
    }
}
