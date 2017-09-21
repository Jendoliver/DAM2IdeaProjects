package com.company;

public class Album
{
    private String title;
    private String group;
    private int nsongs;
    private float duration;

    public Album(String title, String group, int nsongs, float duration) {
        this.title = title;
        this.group = group;
        this.nsongs = nsongs;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }
    public String getGroup() {
        return group;
    }
    public int getNsongs() {
        return nsongs;
    }
    public float getDuration() {
        return duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public void setNsongs(int nsongs) {
        this.nsongs = nsongs;
    }
    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String details() // toString
    {
        String details = "Title: " + getTitle() + "\nGroup: " + getGroup() + "\nNumber of songs: " + getNsongs() + "\nTotal duration: " + getDuration();
        return details;
    }
}
