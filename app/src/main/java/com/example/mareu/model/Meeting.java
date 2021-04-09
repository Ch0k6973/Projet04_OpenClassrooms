package com.example.mareu.model;

import java.util.Date;
import java.util.Objects;

public class Meeting {

    private long id;

    private int color;

    private MeetingRoom room;

    private Date mDate;

    private String subject;

    private String member;

    public Meeting(long id, int color, MeetingRoom room, Date date, String subject, String member) {
        this.id = id;
        this.color = color;
        this.room = room;
        this.mDate = date;
        this.subject = subject;
        this.member = member;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public MeetingRoom getRoom() {
        return room;
    }

    public void setRoom(MeetingRoom room) {
        this.room = room;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
