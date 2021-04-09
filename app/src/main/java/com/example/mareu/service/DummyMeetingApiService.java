package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService{

    private List<Meeting> mMeetingList = DummyMeetingGenerator.generateMeeting();

    public List<Meeting> getMeeting() {
        return mMeetingList;
    }

    public List<Meeting> getMeetingDate(Date date) {
        List<Meeting> meetingDate = new ArrayList<>();
        for (Meeting m : mMeetingList) {
            if (m.getDate().getDay() == date.getDay() && m.getDate().getMonth() == date.getMonth())
                meetingDate.add(m);
        }
        return meetingDate;
    }

    public List<Meeting> getMeetingRoom(MeetingRoom room) {
        List<Meeting> meetingRoom = new ArrayList<>();
        for (Meeting m : mMeetingList) {
            if (m.getRoom() == room)
                meetingRoom.add(m);
        }
        return meetingRoom;
    }

    public void deleteMeeting(Meeting meeting) {
        mMeetingList.remove(meeting);
    }

    public void createMeeting(Meeting meeting) {
        mMeetingList.add(meeting);
    }
}
