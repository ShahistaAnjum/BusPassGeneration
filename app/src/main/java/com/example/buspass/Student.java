package com.example.buspass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Student extends AppCompatActivity {

    CardView cardCurrentPass;
    CardView cardApplyPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        CardView cardCurrentPass = findViewById(R.id.cardCurrentPass);
        CardView cardApplyPass = findViewById(R.id.cardApplyPass);

        cardCurrentPass .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student.this, StudentPass.class));

            }

        });
        cardApplyPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student.this, StudentForm.class));
            }
        });
    }
}