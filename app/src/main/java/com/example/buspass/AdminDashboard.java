package com.example.buspass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdminDashboard extends AppCompatActivity {

    CardView cardUpdateDatabase;
    CardView cardAdminLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        cardUpdateDatabase = findViewById(R.id.cardUpdateDatabase);
        cardAdminLogout = findViewById(R.id.cardAdminLogout);

        cardUpdateDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, UpdateDatabase.class));
            }
        });

        cardAdminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, LoginActivity.class));
            }
        });

    }
}