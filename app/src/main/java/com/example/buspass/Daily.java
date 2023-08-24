package com.example.buspass;

public class Daily {
    String AadharNumber;
    String FullName;
    String PhoneNumber;
    String Dob;
    String Age;
    String Date;

    public Daily(String aadharNumber, String fullName, String phoneNumber, String dob, String age /*String date*/) {
        AadharNumber = aadharNumber;
        FullName = fullName;
        PhoneNumber = phoneNumber;
        Dob = dob;
        Age = age;
        //Date = date;
    }

    public Daily() {
    }

    public String getAadharNumber() {
        return AadharNumber;
    }

    public String getFullName() {
        return FullName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getDob() {
        return Dob;
    }

    public String getAge() {
        return Age;
    }

   /* public String getDate() {
        return Date;
    }*/

    public void setAadharNumber(String aadharNumber) {
        AadharNumber = aadharNumber;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public void setAge(String age) {
        Age = age;
    }

    /*public void setDate(String date) {
        Date = date;
    }*/
}
