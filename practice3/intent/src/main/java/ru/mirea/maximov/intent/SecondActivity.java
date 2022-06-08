package ru.mirea.maximov.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView outText = (TextView) findViewById(R.id.outTextView);

        Intent intent = getIntent();
        outText.setText(intent.getStringExtra("dateStr"));
    }
}