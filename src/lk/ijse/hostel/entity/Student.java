package lk.ijse.hostel.entity;

public class Student {
    private String stId;
    private String stName;
    private String address;

    public Student() {
    }

    private String contact;

    public Student(String stId, String stName, String address, String contact, String dob, String gender) {
        this.setStId (stId);
        this.setStName (stName);
        this.setAddress (address);
        this.setContact (contact);
        this.setDob (dob);
        this.setGender (gender);
    }

    private String dob;
    private String gender;


    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stId='" + stId + '\'' +
                ", stName='" + stName + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
