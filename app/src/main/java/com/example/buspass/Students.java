package com.example.buspass;

public class Students {

    String AdmissionNumber;
    String FullName;
    String FatherName;
    String PhoneNumber;
    String Dob;
    String AadharNumber;
    String Institution;
    String From;
    String To;


    public Students(String admissionNumber, String fullName, String fatherName, String phoneNumber, String dob, String aadharNumber, String institution, String from, String to) {
        AdmissionNumber = admissionNumber;
        FullName = fullName;
        FatherName = fatherName;
        PhoneNumber = phoneNumber;
        Dob = dob;
        AadharNumber = aadharNumber;
        Institution = institution;
        From = from;
        To = to;
    }

    public Students() {
    }

    public String getAdmissionNumber() {
        return AdmissionNumber;
    }

    public String getFullName() {
        return FullName;
    }

    public String getFatherName() {
        return FatherName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getDob() {
        return Dob;
    }

    public String getAadharNumber() {
        return AadharNumber;
    }

    public String getInstitution() {
        return Institution;
    }

    public String getFrom() {
        return From;
    }

    public String getTo() {
        return To;
    }

    public void setAdmissionNumber(String admissionNumber) {
        AdmissionNumber = admissionNumber;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public void setAadharNumber(String aadharNumber) {
        AadharNumber = aadharNumber;
    }

    public void setInstitution(String institution) {
        Institution = institution;
    }

    public void setFrom(String from) {
        From = from;
    }

    public void setTo(String to) {
        To = to;
    }
}
