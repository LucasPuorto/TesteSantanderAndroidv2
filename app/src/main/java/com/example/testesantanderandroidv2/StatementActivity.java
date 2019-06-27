package com.example.testesantanderandroidv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StatementActivity extends AppCompatActivity {

    private Button btLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);

        btLogout = findViewById(R.id.activity_statement_bt_logout);
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StatementActivity.this, "Click", Toast.LENGTH_LONG).show();
            }
        });
    }
}
