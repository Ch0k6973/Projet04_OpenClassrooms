package com.example.mareu.model;

import java.util.Objects;

public class Meeting {

    private long id;

    private MeetingRoom room;

    private Hour hour;

    private String subject;

    private String member;

    public Meeting(long id, MeetingRoom room, Hour hour, String subject, String member) {
        this.id = id;
        this.hour = hour;
        this.room = room;
        this.subject = subject;
        this.member = member;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MeetingRoom getRoom() {
        return room;
    }

    public void setRoom(MeetingRoom room) {
        this.room = room;
    }

    public Hour getHour() {
        return hour;
    }

    public void setHour(Hour hour) {
        this.hour = hour;
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
