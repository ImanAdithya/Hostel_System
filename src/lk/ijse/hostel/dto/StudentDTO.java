package lk.ijse.hostel.dto;

public class StudentDTO {
    private String stId;
    private String stName;

    public StudentDTO() {
    }

    public StudentDTO(String stId, String stName, String address, String contact, String dob, String gender) {
        this.stId = stId;
        this.stName = stName;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
    }

    private String address;
    private String contact;
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
