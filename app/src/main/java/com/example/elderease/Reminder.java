package com.example.elderease;

public class Reminder {
    private String time, text;
    public Reminder(String time, String text){
        this.text = text;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
