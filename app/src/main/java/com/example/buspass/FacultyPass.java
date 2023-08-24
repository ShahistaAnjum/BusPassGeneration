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

public class FacultyPass extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference reference;
    EditText facultyPassAadharNo;
    Button facultyPassSearchButton;
    TextView facultyPassAadhar,facultyPassName,facultyPassFrom,facultyPassTo,facultyPassValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_pass);
        facultyPassAadharNo = (EditText)findViewById(R.id.facultyPassAadharNo);// Search Aadhar
        facultyPassSearchButton = (Button)findViewById(R.id.facultyPassSearchButton);
        facultyPassAadhar= (TextView) findViewById(R.id.facultyPassAadhar);//Display Aadhar
        facultyPassName = (TextView) findViewById(R.id.facultyPassName);
        facultyPassFrom = (TextView) findViewById(R.id.facultyPassFrom);
        facultyPassTo = (TextView) findViewById(R.id.facultyPassTo);
        facultyPassValid = (TextView) findViewById(R.id.facultyPassValid);
        facultyPassSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AadharNumber = facultyPassAadharNo.getText().toString();
                if (!AadharNumber.isEmpty()) {
                    readData(AadharNumber);
                }//closing if

                else {
                    Toast.makeText(FacultyPass.this, "Please enter the Aadhar Number", Toast.LENGTH_LONG).show();

                }//closing else
            }
        });
    }
    private void readData(String aadharNumber) {
        reference = db.getInstance().getReference("Faculty");
        reference.child(aadharNumber).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    if(task.getResult().exists()){
                        Toast.makeText(FacultyPass.this, "Successfully Read", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String FullName = String.valueOf(dataSnapshot.child("fullName").getValue());
                        String AadharNo = String.valueOf(dataSnapshot.child("aadharNumber").getValue());
                        String To = String.valueOf(dataSnapshot.child("to").getValue());
                        String From = String.valueOf(dataSnapshot.child("from").getValue());
                        facultyPassAadhar.setText(AadharNo);
                        facultyPassName.setText(FullName);
                        facultyPassFrom.setText(From);
                        facultyPassTo.setText(To);
                        facultyPassValid.setText("2023");

                    }//closing of if(task exists)
                    else{
                        Toast.makeText(FacultyPass.this, "Aadhar number does not exists", Toast.LENGTH_SHORT).show();
                    }//closing of else(task exists)

                }//closing of if(task)
                else {
                    Toast.makeText(FacultyPass.this, "Failed to read", Toast.LENGTH_SHORT).show();

                }//closing of else(task)
            }
        });

    }
}