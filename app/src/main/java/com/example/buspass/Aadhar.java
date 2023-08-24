package com.example.buspass;

public class Aadhar {
 String AadharNumber;
 String PhoneNumber;
 String Dob;

    public Aadhar(String aadharNumber, String phoneNumber, String dob) {
        AadharNumber = aadharNumber;
        PhoneNumber = phoneNumber;
        Dob = dob;
    }

    public Aadhar() {
    }

    public String getAadharNumber() {
        return AadharNumber;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getDob() {
        return Dob;
    }

    public void setAadharNumber(String aadharNumber) {
        AadharNumber = aadharNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setDob(String dob) {
        Dob = dob;
    }
}
