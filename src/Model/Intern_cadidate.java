package Model;

import Model.Candidate;

public class Intern_cadidate extends Candidate {
    private String majorStudy;
    private int semesterStudying;
    private String school;
    private String expectedDateGraduation;


    public Intern_cadidate(String fullName, String birthDay, String address, String homeTown, String phone, String email) {
        super(fullName, birthDay, address, homeTown, phone, email);
    }



    public Intern_cadidate(String fullName, String birthDay, String address, String homeTown, String phone, String email, String majorStudy, int semesterStudying, String school, String expectedDateGraduation) {
        super(fullName, birthDay, address, homeTown, phone, email);
        this.majorStudy = majorStudy;
        this.semesterStudying = semesterStudying;
        this.school = school;
        this.expectedDateGraduation = expectedDateGraduation;
    }

    public String getMajorStudy() {
        return majorStudy;
    }

    public void setMajorStudy(String majorStudy) {
        this.majorStudy = majorStudy;
    }

    public int getSemesterStudying() {
        return semesterStudying;
    }

    public void setSemesterStudying(int semesterStudying) {
        this.semesterStudying = semesterStudying;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getExpectedDateGraduation() {
        return expectedDateGraduation;
    }

    public void setExpectedDateGraduation(String expectedDateGraduation) {
        this.expectedDateGraduation = expectedDateGraduation;
    }

    @Override
    public String toString() {
        return "Model.Intern_cadidate{" +
                "majorStudy='" + majorStudy + '\'' +
                ", semesterStudying='" + semesterStudying + '\'' +
                ", school='" + school + '\'' +
                ", expectedDateGraduation='" + expectedDateGraduation + '\'' +
                '}';
    }


}
