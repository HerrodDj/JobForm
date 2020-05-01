package Model;

import android.media.Image;

import java.io.Serializable;
import java.util.Date;

public class Form implements Serializable {

    private String name;
    private String lastName;
    private String streetAdrees;
    private String streetAdrees2;
    private String ciudad;
    private String state;
    private String ZipCode;
    private String country;
    private String email;
    private String areaCode;
    private String phoneNumber;
    private String applyingJob;
    private Date date;
    private Image resume;

    public Form(String name, String lastName, String streetAdrees, String streetAdrees2, String ciudad, String state, String zipCode, String country, String email, String areaCode, String phoneNumber, String applyingJob, Date date, Image resume) {
        this.name = name;
        this.lastName = lastName;
        this.streetAdrees = streetAdrees;
        this.streetAdrees2 = streetAdrees2;
        this.ciudad = ciudad;
        this.state = state;
        this.ZipCode = zipCode;
        this.country = country;
        this.email = email;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
        this.applyingJob = applyingJob;
        this.date = date;
        this.resume = resume;
    }

    public Form(String name, String lastName, String streetAdrees, String streetAdrees2, String ciudad, String state, String zipCode, String country, String email, String areaCode, String phoneNumber, String applyingJob, Date date) {
        this.name = name;
        this.lastName = lastName;
        this.streetAdrees = streetAdrees;
        this.streetAdrees2 = streetAdrees2;
        this.ciudad = ciudad;
        this.state = state;
        this.ZipCode = zipCode;
        this.country = country;
        this.email = email;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
        this.applyingJob = applyingJob;
        this.date = date;
        this.resume=null;
    }

    public Form() {
        this(null,null,null,null,null,null,null,null,null,null,null,null,null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAdrees() {
        return streetAdrees;
    }

    public void setStreetAdrees(String streetAdrees) {
        this.streetAdrees = streetAdrees;
    }

    public String getStreetAdrees2() {
        return streetAdrees2;
    }

    public void setStreetAdrees2(String streetAdrees2) {
        this.streetAdrees2 = streetAdrees2;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getApplyingJob() {
        return applyingJob;
    }

    public void setApplyingJob(String applyingJob) {
        this.applyingJob = applyingJob;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Image getResume() {
        return resume;
    }

    public void setResume(Image resume) {
        this.resume = resume;
    }

    @Override
    public String toString() {
        return "Form{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", streetAdrees='" + streetAdrees + '\'' +
                ", streetAdrees2='" + streetAdrees2 + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", state='" + state + '\'' +
                ", ZipCode='" + ZipCode + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", applyingJob='" + applyingJob + '\'' +
                ", date=" + date +
                ", resume=" + resume +
                '}';
    }
}
