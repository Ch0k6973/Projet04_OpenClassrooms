package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService{

    private List<Meeting> mMeetingList = DummyMeetingGenerator.generateMeeting();

    public List<Meeting> getMeeting() {
        return mMeetingList;
    }

    public void deleteMeeting(Meeting meeting) {
        mMeetingList.remove(meeting);
    }

    public void createMeeting(Meeting meeting) {
        mMeetingList.add(meeting);
    }
}
