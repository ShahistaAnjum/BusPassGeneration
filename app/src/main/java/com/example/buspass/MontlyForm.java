package com.example.buspass;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MontlyForm extends AppCompatActivity {
    private static String value=null;

    EditText MonthlyAadharNumber;
    EditText MonthlyFullName;
    EditText MonthlyPhoneNumber;
    EditText MonthlyDob;
    EditText MonthlyAge;
    Spinner spinnerMonths;
    Button MonthlySubmit;

    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_montly_form);

        MonthlySubmit = findViewById(R.id.MonthlySubmit);
        MonthlyAadharNumber = findViewById(R.id.MonthlyAadharNumber);
        MonthlyFullName = findViewById(R.id.MonthlyFullName);
        MonthlyPhoneNumber = findViewById(R.id.MonthlyPhoneNumber);
        MonthlyDob = findViewById(R.id.MonthlyDob);
        MonthlyAge = findViewById(R.id.MonthlyAge);
        spinnerMonths = findViewById(R.id.spinnerMonths);
        MonthlySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AadharNumber =  MonthlyAadharNumber.getText().toString();
                if (TextUtils.isEmpty(AadharNumber)) {
                    MonthlyAadharNumber.setError("The characters should be 12");
                    MonthlyAadharNumber.requestFocus();
                } else if (AadharNumber.length() != 12) {
                    MonthlyAadharNumber.setError("The characters should be 12");
                    MonthlyAadharNumber.requestFocus();
                }
                db = FirebaseDatabase.getInstance();
                reference = db.getReference();
                reference.child("Aadhar").child(AadharNumber).child("phoneNumber").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else{
                            Object dataSnapshotValue = task.getResult().getValue();
                            if(dataSnapshotValue != null){
                                value = dataSnapshotValue.toString(); // Assign the value as a string
                                Log.d("firebase", value);


                            }//close of if
                            else{
                                Toast.makeText(MontlyForm.this,"enter correct Aadhar Number",Toast.LENGTH_SHORT).show();
                                Log.d("firebase", "Value is null");

                            }//close of inner else

                        }//close of outer else
                    } // close of onComplete
                });// close of onComplteListner
                performComparision(value);

            }
        });
    }
    private void performComparision(String value){

        String PhoneNumber = MonthlyPhoneNumber.getText().toString();
        if (TextUtils.isEmpty(PhoneNumber)) {
            MonthlyPhoneNumber.setError("Please the phone");
            MonthlyPhoneNumber.requestFocus();
        } else if (PhoneNumber.length() != 10) {
            MonthlyPhoneNumber.setError("The characters should be 10");
            MonthlyPhoneNumber.requestFocus();
        }
        if (value != null){
            if (value.equals(PhoneNumber)){
                Log.d("firebase","equal");
                insertData();

            }//close of inner if
            else{
                Log.d("firebase","not equal");
                Toast.makeText(MontlyForm.this,"Enter correct Data",Toast.LENGTH_SHORT).show();

            }//close of inner else

        }//close of outer if

    }// close of performComparision function
    private void insertData(){
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Citizen");
        String AadharNumber =  MonthlyAadharNumber.getText().toString();
        String FullName =  MonthlyFullName.getText().toString();
        String PhoneNumber = MonthlyPhoneNumber.getText().toString();
        String Dob =  MonthlyDob.getText().toString();
        String Age = MonthlyAge.getText().toString();
        String Month = spinnerMonths.getSelectedItem().toString();

        if (TextUtils.isEmpty(FullName)) {
            MonthlyFullName.setError("Please enter the details");
            MonthlyFullName.requestFocus();
        }
        if (TextUtils.isEmpty(Dob)) {
            MonthlyDob.setError("Please the dob");
            MonthlyDob.requestFocus();
        }
        if (TextUtils.isEmpty(Age)) {
            MonthlyAge.setError("Please the dob");
            MonthlyAge.requestFocus();
        }
        boolean allFieldsFilled =!TextUtils.isEmpty(Month) &&
                !TextUtils.isEmpty(FullName) &&
                !TextUtils.isEmpty(Dob) &&
                !TextUtils.isEmpty(PhoneNumber) &&
                !TextUtils.isEmpty(AadharNumber) &&
                !TextUtils.isEmpty(Age);

        Monthly monthly = new Monthly(AadharNumber,FullName,PhoneNumber,Dob, Age, Month);
        if(allFieldsFilled){

        reference.child("Monthly").child(AadharNumber).setValue(monthly, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                Toast.makeText(MontlyForm.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MontlyForm.this,PaymentMonthly.class));
            }

        });
    }
        else {
            Toast.makeText(MontlyForm.this,"Fields are empty",Toast.LENGTH_SHORT).show();
        }

    }
}
