package com.akitektuo.buscard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private TextView txtCheck, txtScanQr, txtBusCode;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        txtScanQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ScanActivity.class));
            }
        });
        txtCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CheckActivity.class));
            }
        });
        txtBusCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AskForDetailsDialog dialog = new AskForDetailsDialog();
                dialog.show(getSupportFragmentManager(), "ask for details");
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private void initViews(){
        txtScanQr = findViewById(R.id.TxtScanQR);
        txtBusCode = findViewById(R.id.txtBusCode);
        cardView = findViewById(R.id.cardView);
        txtCheck = findViewById(R.id.txtCheck);
    }
}
