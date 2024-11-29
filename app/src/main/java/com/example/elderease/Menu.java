package com.example.elderease;

public class Menu {
    private int iconId;
    private String text;
    public Menu(int iconId, String text){
        this.iconId = iconId;
        this.text = text;
    }

    public int getIconId() {
        return iconId;
    }

    public String getText() {
        return text;
    }
}
