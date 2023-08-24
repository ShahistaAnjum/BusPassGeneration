package com.example.buspass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Faculty extends AppCompatActivity {

    CardView cardFacultyCurrentPass;
    CardView cardFacultyApplyPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        CardView cardFacultyCurrentPass = findViewById(R.id.cardFacultyCurrentPass);
        CardView cardFacultyApplyPass = findViewById(R.id.cardFacultyApplyPass);

        cardFacultyCurrentPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Faculty.this, FacultyPass.class));

            }

        });
        cardFacultyApplyPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Faculty.this, FacultyForm.class));
            }
        });

    }
}