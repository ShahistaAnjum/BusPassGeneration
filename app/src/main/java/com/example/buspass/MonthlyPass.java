package com.example.buspass;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MonthlyPass extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference reference;
    TextView mothlyPassAadharNo,mothlyPassName,monthlyPassMonth,monthlyPassYear;
    EditText monthlyPassAadhar;
    Button monthlyPassSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_pass);
        monthlyPassAadhar = (EditText)findViewById(R.id.monthlyPassAadhar);// search Aadhar
        monthlyPassSearchButton = (Button)findViewById(R.id.monthlyPassSearchButton);
        mothlyPassAadharNo= (TextView) findViewById(R.id. mothlyPassAadharNo);// display Aadhar
        mothlyPassName = (TextView) findViewById(R.id.mothlyPassName);
        monthlyPassMonth = (TextView) findViewById(R.id.monthlyPassMonth);
        monthlyPassYear = (TextView) findViewById(R.id.monthlyPassYear);
        monthlyPassSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AadharNumber = monthlyPassAadhar.getText().toString();
                if (!AadharNumber.isEmpty()) {
                    readData(AadharNumber);
                }//closing if

                else {
                    Toast.makeText(MonthlyPass.this, "Please enter the Aadhar Number", Toast.LENGTH_LONG).show();

                }//closing else
            }
        });

    }
    private void readData(String aadharNumber) {
        reference = db.getInstance().getReference("Citizen");
        reference.child("Monthly").child(aadharNumber).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    if(task.getResult().exists()){
                        Toast.makeText(MonthlyPass.this, "Successfully Read", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String FullName = String.valueOf(dataSnapshot.child("fullName").getValue());
                        String AadharNo = String.valueOf(dataSnapshot.child("aadharNumber").getValue());
                        String Month = String.valueOf(dataSnapshot.child("month").getValue());

                        mothlyPassAadharNo.setText(AadharNo);
                        mothlyPassName.setText(FullName);
                        monthlyPassMonth.setText(Month);
                        monthlyPassYear.setText("2023");

                    }//closing of if(task exists)
                    else{
                        Toast.makeText(MonthlyPass.this, "Aadhar number does not exists", Toast.LENGTH_SHORT).show();
                    }//closing of else(task exists)

                }//closing of if(task)
                else {
                    Toast.makeText(MonthlyPass.this, "Failed to read", Toast.LENGTH_SHORT).show();

                }//closing of else(task)
            }
        });

    }
}