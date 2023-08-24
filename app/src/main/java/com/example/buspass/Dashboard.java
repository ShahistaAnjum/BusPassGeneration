package com.example.buspass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Dashboard extends AppCompatActivity {

    CardView cardStudent;
    CardView cardFaculty;
    CardView cardLogout;
    CardView cardCitizen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

         CardView cardLogout = findViewById(R.id.cardLogout);
        CardView cardStudent = findViewById(R.id.cardStudent);
        CardView cardFaculty = findViewById(R.id.cardFaculty);
        CardView cardCitizen = findViewById(R.id.cardCitizen);

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,LoginActivity.class));

            }
        });
        cardStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,Student.class));

            }
        });
        cardFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,Faculty.class));

            }
        });
        cardCitizen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,Citizen.class));

            }
        });

    }
}