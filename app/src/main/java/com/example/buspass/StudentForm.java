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

public class StudentForm extends AppCompatActivity {
    private static String value=null;

    EditText StudentAdmissionNumber;
    EditText StudentFirstName;
    EditText StudentFatherName;
    EditText StudentPhoneNumber;
    EditText StudentDob;
    EditText StudentAadharNumber;
    EditText StudentInstitutionName;
    Spinner StudentSpinnerFrom;
    Spinner StudentSpinnerTo;
    Button  studentSubmit;

    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        StudentAdmissionNumber = findViewById(R.id.StudentAdmissionNumber);
        StudentFirstName = findViewById(R.id.StudentFirstName);
        StudentFatherName = findViewById(R.id.StudentFatherName);
        StudentPhoneNumber = findViewById(R.id.StudentPhoneNumber);
        StudentDob = findViewById(R.id. StudentDob);
        StudentAadharNumber = findViewById(R.id.StudentAadharNumber);
        StudentInstitutionName= findViewById(R.id.StudentInstitutionName);
        StudentSpinnerFrom = findViewById(R.id.  StudentSpinnerFrom);
        StudentSpinnerTo = findViewById(R.id. StudentSpinnerTo);
        studentSubmit = findViewById(R.id. studentSubmit);

        studentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String AadharNumber = StudentAadharNumber.getText().toString();
                if (TextUtils.isEmpty(AadharNumber)) {
                    StudentAadharNumber.setError("The characters should be 12");
                    StudentAadharNumber.requestFocus();
                } else if (AadharNumber.length() != 12) {
                    StudentAadharNumber.setError("The characters should be 12");
                    StudentAadharNumber.requestFocus();
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
                                Toast.makeText(StudentForm.this,"Please enter correct Aadhar Number",Toast.LENGTH_SHORT).show();
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

        String PhoneNumber = StudentPhoneNumber.getText().toString();
        if (TextUtils.isEmpty(PhoneNumber)) {
            StudentPhoneNumber.setError("Please the phone");
            StudentPhoneNumber.requestFocus();
        } else if (PhoneNumber.length() != 10) {
            StudentPhoneNumber.setError("The characters should be 10");
            StudentPhoneNumber.requestFocus();
        }
        if (value != null){
            if (value.equals(PhoneNumber)){
                Log.d("firebase","equal");
                insertData();
            }//close of inner if
            else{
                Log.d("firebase","not equal");
                Toast.makeText(StudentForm.this,"Enter correct Data",Toast.LENGTH_SHORT).show();

            }//close of inner else

        }//close of outer if

    }// close of performComparision function
    private void insertData() {
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Student");
        String AdmissionNumber = StudentAdmissionNumber.getText().toString();
        String FullName = StudentFirstName.getText().toString();
        String FatherName = StudentFatherName.getText().toString();
        String PhoneNumber = StudentPhoneNumber.getText().toString();
        String Dob = StudentDob.getText().toString();
        String AadharNumber = StudentAadharNumber.getText().toString();
        String Institution = StudentInstitutionName.getText().toString();
        String From = StudentSpinnerFrom.getSelectedItem().toString();
        String To = StudentSpinnerTo.getSelectedItem().toString();
        if (TextUtils.isEmpty(AdmissionNumber)) {
            StudentAdmissionNumber.setError("Please enter the details");
            StudentAdmissionNumber.requestFocus();
        }
        if (TextUtils.isEmpty(FullName)) {
            StudentFirstName.setError("Please enter the details");
            StudentFirstName.requestFocus();
        }
        if (TextUtils.isEmpty(FatherName)) {
            StudentFatherName.setError("Please enter the details");
            StudentFatherName.requestFocus();
        }
        if (TextUtils.isEmpty(PhoneNumber)) {
            StudentPhoneNumber.setError("Please the phone");
            StudentPhoneNumber.requestFocus();
        } else if (PhoneNumber.length() != 10) {
            StudentPhoneNumber.setError("The characters should be 10");
            StudentPhoneNumber.requestFocus();
        }
        if (TextUtils.isEmpty(AadharNumber)) {
            StudentAadharNumber.setError("The characters should be 12");
            StudentAadharNumber.requestFocus();
        } else if (AadharNumber.length() != 12) {
            StudentAadharNumber.setError("The characters should be 12");
            StudentAadharNumber.requestFocus();
        }

        if (TextUtils.isEmpty(Institution)) {
            StudentInstitutionName.setError("Please the dob");
            StudentInstitutionName.requestFocus();
        }
        boolean allFieldsFilled = !TextUtils.isEmpty(AdmissionNumber) &&
                !TextUtils.isEmpty(FullName) &&
                !TextUtils.isEmpty(FatherName) &&
                !TextUtils.isEmpty(PhoneNumber) &&
                !TextUtils.isEmpty(AadharNumber) &&
                !TextUtils.isEmpty(Institution);


        Students students = new Students(AdmissionNumber, FullName, FatherName, PhoneNumber, Dob, AadharNumber, Institution, From, To);
        if (allFieldsFilled) {
            reference.child(AadharNumber).setValue(students, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    Toast.makeText(StudentForm.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StudentForm.this,PaymentMonthly.class));
                }
            });

        }
        else{
            Toast.makeText(StudentForm.this,"Fields are empty",Toast.LENGTH_SHORT).show();
        }
    }
}