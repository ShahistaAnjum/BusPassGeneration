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

public class FacultyForm extends AppCompatActivity {
    private static String value=null;

    EditText FacultyAadharNumber;
    EditText FacultyFacultyId;
    EditText FacultyFullName;
    EditText FacultyPhoneNumber;
    EditText FacultyDob;
    EditText FacultyInstitutionName;
    Spinner FacultySpinnerFrom;
    Spinner FacultySpinnerTo;
    Button Facultysubmit;

    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_form);
        FacultyAadharNumber = findViewById(R.id.FacultyAadharNumber);
        FacultyFacultyId = findViewById(R.id.FacultyFacultyId);
        FacultyFullName = findViewById(R.id.FacultyFullName);
        FacultyPhoneNumber = findViewById(R.id.FacultyPhoneNumber);
        FacultyDob = findViewById(R.id.FacultyDob);
        FacultyInstitutionName = findViewById(R.id.FacultyInstitutionName);
        FacultySpinnerFrom = findViewById(R.id.FacultySpinnerFrom);
        FacultySpinnerTo = findViewById(R.id. FacultySpinnerTo);
        Facultysubmit = findViewById(R.id.FacultySubmit);

        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Faculty");
        Facultysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String AadharNumber = FacultyAadharNumber.getText().toString();
                if (TextUtils.isEmpty(AadharNumber)) {
                    FacultyAadharNumber.setError("The characters should be 12");
                    FacultyAadharNumber.requestFocus();
                } else if (AadharNumber.length() != 12) {
                    FacultyAadharNumber.setError("The characters should be 12");
                    FacultyAadharNumber.requestFocus();
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
                                Toast.makeText(FacultyForm.this,"enter correct Aadhar Number",Toast.LENGTH_SHORT).show();
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

        String PhoneNumber = FacultyPhoneNumber.getText().toString();
        if (TextUtils.isEmpty(PhoneNumber)) {
            FacultyPhoneNumber.setError("Please the phone");
            FacultyPhoneNumber.requestFocus();
        } else if (PhoneNumber.length() != 10) {
            FacultyPhoneNumber.setError("The characters should be 10");
            FacultyPhoneNumber.requestFocus();
        }
        if (value != null){
            if (value.equals(PhoneNumber)){
                Log.d("firebase","equal");
                insertData();
                startActivity(new Intent(FacultyForm.this,PaymentMonthly.class));
            }//close of inner if
            else{
                Log.d("firebase","not equal");
                Toast.makeText(FacultyForm.this,"Enter correct Data",Toast.LENGTH_SHORT).show();

            }//close of inner else

        }//close of outer if

    }// close of performComparision function

    private void insertData(){
        String AadharNumber =   FacultyAadharNumber.getText().toString();
        String FacultyId =  FacultyFacultyId.getText().toString();
        String FullName =  FacultyFullName.getText().toString();
        String PhoneNumber =  FacultyPhoneNumber.getText().toString();
        String Dob =  FacultyDob.getText().toString();
        String Institution =  FacultyInstitutionName.getText().toString();
        String From  =  FacultySpinnerFrom.getSelectedItem().toString();
        String To =   FacultySpinnerTo.getSelectedItem().toString();
        if (TextUtils.isEmpty(FacultyId)) {
            FacultyFacultyId.setError("Please enter the details");
            FacultyFacultyId.requestFocus();
        }
        if (TextUtils.isEmpty(FullName)) {
            FacultyFullName.setError("Please enter the details");
            FacultyFullName.requestFocus();
        }
        if (TextUtils.isEmpty(Dob)) {
            FacultyDob.setError("Please enter the details");
            FacultyDob.requestFocus();
        }

        if (TextUtils.isEmpty(Institution)) {
            FacultyInstitutionName.setError("Please the dob");
            FacultyInstitutionName.requestFocus();
        }
        boolean allFieldsFilled = !TextUtils.isEmpty(FacultyId) &&
                !TextUtils.isEmpty(FullName) &&
                !TextUtils.isEmpty(Dob) &&
                !TextUtils.isEmpty(PhoneNumber) &&
                !TextUtils.isEmpty(AadharNumber) &&
                !TextUtils.isEmpty(Institution);
        if(allFieldsFilled) {
            Faculties faculties = new Faculties(AadharNumber, FacultyId, FullName, PhoneNumber, Dob, Institution, From, To);

            reference.child(AadharNumber).setValue(faculties, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    Toast.makeText(FacultyForm.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(FacultyForm.this,"Fields are empty",Toast.LENGTH_SHORT).show();
        }

    }
}