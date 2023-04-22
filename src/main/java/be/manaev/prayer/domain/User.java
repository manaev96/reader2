package be.manaev.prayer.domain;

import java.util.Objects;

public class User {
    String phonenumber;
    String facebookid;
    String name;
    String familyname;
    String gender;
    String city;
    String province;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(familyname, user.familyname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyname);
    }

    public User(String phonenumber, String facebookid, String name, String familyname, String gender, String city, String province) {
        this.phonenumber = phonenumber;
        this.facebookid = facebookid;
        this.name = name;
        this.familyname = familyname;
        this.gender = gender;
        this.city = city;
        this.province = province;
    }

    public User() {
    }


    public String getPhonenumber() {
        return phonenumber;
    }
    public String getFacebookid() {
        return facebookid;
    }
    public String getName() {
        return name;
    }
    public String getFamilyname() {
        return familyname;
    }
    public String getGender() {
        return gender;
    }
    public String getCity() {
        return city;
    }
    public String getProvince() {
        return province;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public void setFacebookid(String facebookid) {
        this.facebookid = facebookid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    @Override
    public String toString() {
        return "GSM: " + phonenumber + "\t\t\t" + "naam: " + name + "\t" + familyname + "\t" + gender + ", " + city + ", " + province + ", " + facebookid;
    }
}
