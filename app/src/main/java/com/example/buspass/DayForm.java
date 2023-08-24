package com.example.buspass;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DayForm extends AppCompatActivity {

private static String value=null;
    EditText /*dateformat,*/DailyAadharNumber,DailyFullName,DailyPhoneNumber,DailyDob,DailyAge;
    Button DailySubmit;
    //int year;
    //int month;
    //int day;
    String Key,Phone;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_form);
        DailySubmit = findViewById(R.id.DailySubmit);
        DailyAadharNumber = findViewById(R.id.DailyAadharNumber);
        DailyFullName = findViewById(R.id. DailyFullName);
        DailyPhoneNumber = findViewById(R.id.DailyPhoneNumber);
        DailyDob = findViewById(R.id.DailyDob);
        DailyAge = findViewById(R.id.DailyAge);
        //dateformat = findViewById(R.id.dateformateID);


        String AadharNumber =  DailyAadharNumber.getText().toString();
        String PhoneNumber = DailyPhoneNumber.getText().toString();



       // Calendar calendar = Calendar.getInstance();
       /* dateformat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(DayForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateformat.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });*/
        DailySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AadharNumber =  DailyAadharNumber.getText().toString();
                if (TextUtils.isEmpty(AadharNumber)) {
                    DailyAadharNumber.setError("The characters should be 12");
                    DailyAadharNumber.requestFocus();
                } else if (AadharNumber.length() != 12) {
                    DailyAadharNumber.setError("The characters should be 12");
                    DailyAadharNumber.requestFocus();
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
                                Toast.makeText(DayForm.this,"enter correct Aadhar Number",Toast.LENGTH_SHORT).show();
                                Log.d("firebase", "Value is null");

                            }//close of inner else

                        }//close of outer else
                    } // close of onComplete
                });// close of onComplteListner
                performComparision(value);
                //}else{
                    //Toast.makeText(DayForm.this,"Enter correct Data",Toast.LENGTH_SHORT).show();
                //}
            }
        });
    }
    private void performComparision(String value){

        String PhoneNumber = DailyPhoneNumber.getText().toString();
        if (TextUtils.isEmpty(PhoneNumber)) {
            DailyPhoneNumber.setError("Please the phone");
            DailyPhoneNumber.requestFocus();
        } else if (PhoneNumber.length() != 10) {
            DailyPhoneNumber.setError("The characters should be 10");
            DailyPhoneNumber.requestFocus();
        }
        if (value != null){
            if (value.equals(PhoneNumber)){
                Log.d("firebase","equal");
                insertData();

            }//close of inner if
            else{
                Log.d("firebase","not equal");
                Toast.makeText(DayForm.this,"Enter correct Data",Toast.LENGTH_SHORT).show();

            }//close of inner else

        }//close of outer if

    }// close of performComparision function
    private void insertData(){
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Citizen");
        String AadharNumber =  DailyAadharNumber.getText().toString();
        String FullName =  DailyFullName.getText().toString();
        String PhoneNumber = DailyPhoneNumber.getText().toString();
        String Dob =   DailyDob.getText().toString();
        String Age =  DailyAge.getText().toString();
       // String Date =  dateformat.getText().toString();
        if (TextUtils.isEmpty(FullName)) {
            DailyFullName.setError("Please enter the details");
            DailyFullName.requestFocus();
        }
        if (TextUtils.isEmpty(Dob)) {
            DailyDob.setError("Please the dob");
            DailyDob.requestFocus();
        }
        if (TextUtils.isEmpty(Age)) {
            DailyAge.setError("Please the dob");
            DailyAge.requestFocus();
        }
        boolean allFieldsFilled = !TextUtils.isEmpty(FullName) &&
                !TextUtils.isEmpty(Dob) &&
                !TextUtils.isEmpty(PhoneNumber) &&
                !TextUtils.isEmpty(AadharNumber) &&
                !TextUtils.isEmpty(Age);

        Daily daily = new Daily(AadharNumber,FullName,PhoneNumber,Dob, Age);
        if(allFieldsFilled) {

            reference.child("Daily").child(AadharNumber).setValue(daily, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    Toast.makeText(DayForm.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DayForm.this,PaymentMonthly.class));

                }
            });

        }
        else{
            Toast.makeText(DayForm.this,"Fields are empty",Toast.LENGTH_SHORT).show();
        }
    }

}
