package com.akitektuo.buscard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class PinActivity extends AppCompatActivity {

    private EditText editTxt1, editTxt2, editTxt3, editTxt4;
    private ImageView imageFingerprint;
    private int digit1, digit2, digit3, digit4;
    private Context mContext;

    private TextView textFingerprint;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        mContext = this;
        initViews();

        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        checkFingerprint();

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
                if (editable.toString().length() == 1) {
                    digit4 = Integer.parseInt(editable.toString());
                    if (digit1 == 0 && digit2 == 0 && digit3 == 0 && digit4 == 0) {

                        App.Companion.getDatabase().createRequest(new Function0<Unit>() {
                            @Override
                            public Unit invoke() {
                                Intent intent = new Intent(mContext, MainActivity.class);
                                startActivity(intent);
                                finish();
                                return null;
                            }
                        });
                    } else{
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

        imageFingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions((Activity) mContext, new String[]{ Manifest.permission.USE_FINGERPRINT }, 1);
            }
        });

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
    private void initViews(){
        editTxt1 = findViewById(R.id.Digit1);
        editTxt2 = findViewById(R.id.Digit2);
        editTxt3 = findViewById(R.id.Digit3);
        editTxt4 = findViewById(R.id.Digit4);

        imageFingerprint = findViewById(R.id.imageFingerprint);
        textFingerprint = findViewById(R.id.textFingerError);
    }

    private void checkFingerprint() {
        if (!fingerprintManager.isHardwareDetected()) {
            imageFingerprint.setVisibility(View.INVISIBLE);
            textFingerprint.setVisibility(View.INVISIBLE);
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            textFingerprint.setText("Tap the image in order to grant permission to use the fingerprint");
            return;
        }

        if (!keyguardManager.isKeyguardSecure()) {
            textFingerprint.setText("Add lock yo your phone to use the fingerprint");
            return;
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            textFingerprint.setText("You must register a fingerprint");
            return;
        }

        FingerPrintHandler fingerPrintHandler = new FingerPrintHandler(this);
        fingerPrintHandler.startAuth(fingerprintManager, null);
        textFingerprint.setText("Fingerprint ready to use");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        if (requestCode == 1) {
            checkFingerprint();
        }
    }

}
