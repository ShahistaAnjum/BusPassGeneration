package com.example.buspass;

public class Faculties {
    String AadharNumber;
    String FacultyId;
    String FullName;
    String PhoneNumber;
    String Dob;
    String Institution;
    String From;
    String To;

    public Faculties(String aadharNumber, String facultyId, String fullName, String phoneNumber, String dob, String institution, String from, String to) {
        AadharNumber = aadharNumber;
        FacultyId = facultyId;
        FullName = fullName;
        PhoneNumber = phoneNumber;
        Dob = dob;
        Institution = institution;
        From = from;
        To = to;
    }

    public Faculties() {
    }

    public String getAadharNumber() {
        return AadharNumber;
    }

    public String getFacultyId() {
        return FacultyId;
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

    public String getInstitution() {
        return Institution;
    }

    public String getFrom() {
        return From;
    }

    public String getTo() {
        return To;
    }

    public void setAadharNumber(String aadharNumber) {
        AadharNumber = aadharNumber;
    }

    public void setFacultyId(String facultyId) {
        FacultyId = facultyId;
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
