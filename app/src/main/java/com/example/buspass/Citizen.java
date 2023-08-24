package com.example.buspass;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Citizen extends AppCompatActivity {


    CardView CitizenApplyPass;
    CardView CitizenCurrentPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen);

        CitizenApplyPass = findViewById(R.id.CitizenApplyPass);
        CitizenCurrentPass = findViewById(R.id.CitizenCurrentPass);


        CitizenApplyPass.setOnClickListener(v -> startActivity(new Intent(Citizen.this,CitizenApplyPassDashboard.class)));
        CitizenCurrentPass.setOnClickListener(v -> {
            try{
                startActivity(new Intent(Citizen.this, citizenViewPassDashboard.class));
            }catch (Exception e){
               e.printStackTrace();
            }

        });


    }
}