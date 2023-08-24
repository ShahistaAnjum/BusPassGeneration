package com.example.buspass;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class citizenViewPassDashboard extends AppCompatActivity {
   Button ViewMonthly,ViewDaily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_view_pass_dashboard);
        ViewDaily = findViewById(R.id.ViewDaily);
        ViewMonthly = findViewById(R.id.ViewMonthly);
        ViewMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(citizenViewPassDashboard.this,MonthlyPass.class));
            }
        });
        ViewDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(citizenViewPassDashboard.this,DailyPass.class));
            }
        });



    }
}