package com.nacho.eldar_ejercicios_tecnicos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.location_item: {
                Intent i = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(i);
                break;
            }
            case R.id.qr_reader_item: {
                Intent i = new Intent(MainActivity.this, QrCodeActivity.class);
                startActivity(i);
                break;
            }
            case R.id.tateti_item: {
                Intent i = new Intent(MainActivity.this, TatetiActivity.class);
                startActivity(i);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}