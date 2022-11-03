package Model;

import Model.Candidate;

public class Fresher_cadidate extends Candidate {

    private String graduatonTime;
    private String classification;
    private String graduationSchool;

    public Fresher_cadidate(String fullName, String birthDay, String address, String homeTown, String phone, String email) {
        super(fullName, birthDay, address, homeTown, phone, email);
    }

    public Fresher_cadidate(String fullName, String birthDay, String address, String homeTown, String phone, String email, String graduatonTime, String classification, String graduationSchool) {
        super(fullName, birthDay, address, homeTown, phone, email);
        this.graduatonTime = graduatonTime;
        this.classification = classification;
        this.graduationSchool = graduationSchool;
    }

    public String getGraduatonTime() {
        return graduatonTime;
    }

    public void setGraduatonTime(String graduatonTime) {
        this.graduatonTime = graduatonTime;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getGraduationSchool() {
        return graduationSchool;
    }

    public void setGraduationSchool(String graduationSchool) {
        this.graduationSchool = graduationSchool;
    }

    @Override
    public String toString() {
        return "Model.Fresher_cadidate{" +
                "graduatonTime='" + graduatonTime + '\'' +
                ", classification='" + classification + '\'' +
                ", graduationSchool='" + graduationSchool + '\'' +
                '}';
    }


}
