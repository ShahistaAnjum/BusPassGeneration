package com.example.buspass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentMonthly extends AppCompatActivity {
    Button monthlyPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_monthly);
        monthlyPay = findViewById(R.id.monthlyPay);
        monthlyPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentMonthly.this,Payment_Successful.class));
            }
        });
    }
}