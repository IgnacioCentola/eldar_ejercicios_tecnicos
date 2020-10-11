package com.nacho.eldar_ejercicios_tecnicos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrCodeActivity extends AppCompatActivity {

    private TextView contentTextView;
    private Button scanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        contentTextView = findViewById(R.id.qrCodeContentTeztView);
        scanButton = findViewById(R.id.scanQrButton);

        scanButton.setOnClickListener(view -> {
            new IntentIntegrator(QrCodeActivity.this).initiateScan();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() != null) {
                contentTextView.setText(String.format("%s", intentResult.getContents()));
            } else {
                contentTextView.setText(String.format("%s", "Error. Please try again "));
            }
        }
    }
}