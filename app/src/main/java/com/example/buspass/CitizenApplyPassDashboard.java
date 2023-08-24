package com.example.buspass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CitizenApplyPassDashboard extends AppCompatActivity {

    Button ApplyDaily;
    Button ApplyMonthly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_apply_pass_dashboard);

        ApplyDaily = findViewById(R.id.ApplyDaily);
        ApplyMonthly = findViewById(R.id.ApplyMonthly);

        ApplyDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CitizenApplyPassDashboard.this, DayForm.class));
            }
        });
        ApplyMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CitizenApplyPassDashboard.this,MontlyForm.class));
            }
        });
    }
}