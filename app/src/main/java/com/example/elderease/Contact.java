package com.example.elderease;

public class Contact {
    private String name, phoneNum;
    public Contact(String name, String phoneNum){
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public String getName(){
        return name;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
