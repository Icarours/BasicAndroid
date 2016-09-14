package com.syl.phoneluck.bean;

/**
 * Created by j3767 on 2016/8/28.
 *
 * @Describe
 * @Called
 */
public class Phone {
    private String type;
    private String phoneName;
    private String location;
    private String phoneJx;

    public Phone() {
    }

    public Phone(String type, String phoneName, String location, String phoneJx) {
        this.type = type;
        this.phoneName = phoneName;
        this.location = location;
        this.phoneJx = phoneJx;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "type='" + type + '\'' +
                ", phoneName='" + phoneName + '\'' +
                ", location='" + location + '\'' +
                ", phoneJx='" + phoneJx + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneJx() {
        return phoneJx;
    }

    public void setPhoneJx(String phoneJx) {
        this.phoneJx = phoneJx;
    }
}
