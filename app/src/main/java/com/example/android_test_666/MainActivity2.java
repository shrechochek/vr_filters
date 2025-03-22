package com.example.android_test_666;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
    }

    public void onClickNormal(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickDaltonic(View view) {
        Intent intent = new Intent(this, Daltonic.class);
        startActivity(intent);
    }
    public void onClickVMirror(View view) {
        Intent intent = new Intent(this, Vflip.class);
        startActivity(intent);
    }
    public void onClickHMirror(View view) {
        Intent intent = new Intent(this, Hflip.class);
        startActivity(intent);
    }
    public void onClickInvert(View view) {
        Intent intent = new Intent(this, Invert.class);
        startActivity(intent);
    }
    public void onClickWORed(View view) {
        Intent intent = new Intent(this, WithoutRed.class);
        startActivity(intent);
    }
    public void onClickWOgreen(View view) {
        Intent intent = new Intent(this, WithoutGreen.class);
        startActivity(intent);
    }
    public void onClickWOblue(View view) {
        Intent intent = new Intent(this, WithoutBlue.class);
        startActivity(intent);
    }
    public void onClickNuar(View view) {
        Intent intent = new Intent(this, Nuar.class);
        startActivity(intent);
    }
    public void onClickNegativchik(View view) {
        Intent intent = new Intent(this, Negativchik.class);
        startActivity(intent);
    }
    public void onClickPink(View view) {
        Intent intent = new Intent(this, Pink.class);
        startActivity(intent);
    }
    public void onClickDuo(View view) {
        Intent intent = new Intent(this, Duo.class);
        startActivity(intent);
    }
    public void onClickAlcohol(View view) {
        Intent intent = new Intent(this, Alcohol.class);
        startActivity(intent);
    }
    public void AntiGreen(View view) {
        Intent intent = new Intent(this, AntiGreen.class);
        startActivity(intent);
    }
    public void AntiRed(View view) {
        Intent intent = new Intent(this, AntiRed.class);
        startActivity(intent);
    }
    public void AntiBlue(View view) {
        Intent intent = new Intent(this, AntiBlue.class);
        startActivity(intent);
    }
}