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

public class StudentPass extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference reference;
    EditText studentPassAadharNo;
    TextView studentPassTo,studentPassFrom,studentPassName,studentPassAadhar,studentPassValid;
    Button studentPassSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_pass);
        studentPassAadharNo = (EditText)findViewById(R.id.studentPassAadharNo);
        studentPassSearchButton = (Button)findViewById(R.id.studentPassSearchButton);
        studentPassTo= (TextView) findViewById(R.id.studentPassTo);
        studentPassFrom = (TextView) findViewById(R.id.studentPassFrom);
        studentPassName = (TextView) findViewById(R.id.studentPassName);
        studentPassAadhar = (TextView) findViewById(R.id.studentPassAadhar);
        studentPassValid = (TextView) findViewById(R.id.studentPassValid);
        studentPassSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AadharNumber = studentPassAadharNo.getText().toString();
                if (!AadharNumber.isEmpty()) {
                    readData(AadharNumber);
                }//closing if

                else {
                    Toast.makeText(StudentPass.this, "Please enter the Aadhar Number", Toast.LENGTH_LONG).show();

                }//closing else
            }
        });


    }

    private void readData(String aadharNumber) {
        reference = db.getInstance().getReference("Student");
        reference.child(aadharNumber).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    if(task.getResult().exists()){
                        Toast.makeText(StudentPass.this, "Successfully Read", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String FullName = String.valueOf(dataSnapshot.child("fullName").getValue());
                        String AadharNo = String.valueOf(dataSnapshot.child("aadharNumber").getValue());
                        String To = String.valueOf(dataSnapshot.child("to").getValue());
                        String From = String.valueOf(dataSnapshot.child("from").getValue());
                        studentPassAadhar.setText(AadharNo);
                        studentPassName.setText(FullName);
                        studentPassFrom.setText(From);
                        studentPassTo.setText(To);
                        studentPassValid.setText("2023");

                    }//closing of if(task exists)
                    else{
                        Toast.makeText(StudentPass.this, "Aadhar number does not exists", Toast.LENGTH_SHORT).show();
                    }//closing of else(task exists)

                }//closing of if(task)
                else {
                    Toast.makeText(StudentPass.this, "Failed to read", Toast.LENGTH_SHORT).show();

                }//closing of else(task)
            }
        });

    }
}