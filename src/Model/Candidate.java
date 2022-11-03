package Model;

public abstract class Candidate {
    private String fullName;
    private String birthDay;
    private String address;
    private String homeTown;
    private String phone;
    private String email;


    public Candidate(String fullName, String birthDay, String address, String homeTown, String phone, String email) {
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.address = address;
        this.homeTown = homeTown;
        this.phone = phone;
        this.email = email;
    }

    protected Candidate() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public abstract String toString();


}
