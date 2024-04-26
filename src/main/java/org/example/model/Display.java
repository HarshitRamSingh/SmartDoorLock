package org.example.model;

public class Display {
    private String displayData;

    public String getDisplayData() {
        return this.displayData;
    }

    public void setDisplayData(String display) {
        this.displayData = display;
        System.out.println(display);
    }
}
