package com.example.buspass;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDatabase extends AppCompatActivity {
    private TextWatcher textWatcher;

    EditText AdminAadharNumber;
    EditText AdminPhoneNumber;
    EditText AdminDob;
    Button AdminUpdate;

    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_database);
        AdminAadharNumber = findViewById(R.id. AdminAadharNumber);
        AdminPhoneNumber = findViewById(R.id.AdminPhoneNumber);
        AdminDob = findViewById(R.id.AdminDob);
        AdminUpdate = findViewById(R.id.AdminUpdate);


        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Aadhar");
        AdminUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertAadharData();
            }
        });




    }
    private void insertAadharData() {

        String AadharNumber = AdminAadharNumber.getText().toString().trim();
        String PhoneNumber = AdminPhoneNumber.getText().toString().trim();
        String Dob = AdminDob.getText().toString().trim();
        if (TextUtils.isEmpty(AadharNumber)) {
            AdminAadharNumber.setError("Please enter your AadharNumber");
            AdminAadharNumber.requestFocus();
        }
            else if (AadharNumber.length() != 12) {
                AdminAadharNumber.setError("The characters should be 12");
                AdminAadharNumber.requestFocus();
            }

        if (TextUtils.isEmpty(PhoneNumber)) {
            AdminPhoneNumber.setError("Please the phone");
            AdminPhoneNumber.requestFocus();
        }
            if (PhoneNumber.length() != 10) {
                AdminPhoneNumber.setError("The characters should be 10");
                AdminPhoneNumber.requestFocus();
            }

        if (TextUtils.isEmpty(Dob)) {
            AdminDob.setError("Please the dob");
            AdminDob.requestFocus();
        }
        boolean allFieldsFilled = !TextUtils.isEmpty(AadharNumber) &&
                !TextUtils.isEmpty(PhoneNumber) &&
                !TextUtils.isEmpty(Dob);
        Aadhar aadhar = new Aadhar(AadharNumber, PhoneNumber, Dob);
        if (allFieldsFilled) {

            reference.child(AadharNumber).setValue(aadhar, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    Toast.makeText(UpdateDatabase.this, "Data Inserted", Toast.LENGTH_SHORT).show();

                }
            });

        }
        else{
            Toast.makeText(UpdateDatabase.this,"Fields are empty",Toast.LENGTH_SHORT).show();
        }
    }
}