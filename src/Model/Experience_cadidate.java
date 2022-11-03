package Model;

import Model.Candidate;

import java.util.List;

public class Experience_cadidate extends Candidate {
    private double numberOfExp;

    private String oldWorkPlace;
    private List<Skill> skills;
    public Experience_cadidate() {

    }
    public Experience_cadidate(String fullName, String birthDay, String address, String homeTown, String phone, String email) {
        super(fullName, birthDay, address, homeTown, phone, email);

    }

    public Experience_cadidate(String fullName, String birthDay, String address, String homeTown, String phone, String email, double numberOfExp, List<Skill> skills, String oldWorkPlace) {
        super(fullName, birthDay, address, homeTown, phone, email);
        this.numberOfExp = numberOfExp;
        this.skills = skills;
        this.oldWorkPlace = oldWorkPlace;
    }


    public double getNumberOfExp() {
        return numberOfExp;
    }

    public String setNumberOfExp(double numberOfExp) {
        if(numberOfExp > 0 && numberOfExp < 100) {
            return "Du lieu D";
        }
        return "Sai dinh dang so nam kinh nghiem";
//        this.numberOfExp = numberOfExp;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public String getOldWorkPlace() {
        return oldWorkPlace;
    }

    public void setOldWorkPlace(String oldWorkPlace) {
        this.oldWorkPlace = oldWorkPlace;
    }

    @Override
    public String toString() {
        return "Model.Experience_cadidate{" +
                "numberOfExp=" + numberOfExp +
                ", skill='" + skills + '\'' +
                ", oldWorkPlace='" + oldWorkPlace + '\'' +
                '}';
    }




}
