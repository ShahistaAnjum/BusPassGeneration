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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DailyPass extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference reference;
    EditText dailyPassAadhar;
    Button dailyPassSearchButton;
    TextView dailyPassAadharNo,dailyPassName,dailyPassDate,dailyPassYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_pass);
        dailyPassAadhar = (EditText)findViewById(R.id.dailyPassAadhar);// search Aadhar
        dailyPassSearchButton = (Button)findViewById(R.id.dailyPassSearchButton);
        dailyPassAadharNo= (TextView) findViewById(R.id. dailyPassAadharNo);// display Aadhar
        dailyPassName = (TextView) findViewById(R.id.dailyPassName);
        dailyPassDate = (TextView) findViewById(R.id.dailyPassDate);
        dailyPassYear = (TextView) findViewById(R.id.dailyPassYear);
        dailyPassSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AadharNumber = dailyPassAadhar.getText().toString();
                if (!AadharNumber.isEmpty()) {
                    readData(AadharNumber);
                }//closing if

                else {
                    Toast.makeText(DailyPass.this, "Please enter the Aadhar Number", Toast.LENGTH_LONG).show();

                }//closing else
            }
        });

    }
    private void readData(String aadharNumber) {
        reference = db.getInstance().getReference("Citizen");
        reference.child("Daily").child(aadharNumber).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    if(task.getResult().exists()){
                        Toast.makeText(DailyPass.this, "Successfully Read", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String FullName = String.valueOf(dataSnapshot.child("fullName").getValue());
                        String AadharNo = String.valueOf(dataSnapshot.child("aadharNumber").getValue());
                       // Date date = Calendar.getInstance().getTime();
                       // SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
                       // String Date = dateFormat.format(date);
                        String Date = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).format(new Date());

                        dailyPassAadharNo.setText(AadharNo);
                        dailyPassName.setText(FullName);
                        dailyPassDate.setText(Date);
                        dailyPassYear.setText("2023");

                    }//closing of if(task exists)
                    else{
                        Toast.makeText(DailyPass.this, "Aadhar number does not exists", Toast.LENGTH_SHORT).show();
                    }//closing of else(task exists)

                }//closing of if(task)
                else {
                    Toast.makeText(DailyPass.this, "Failed to read", Toast.LENGTH_SHORT).show();

                }//closing of else(task)
            }
        });

    }
}