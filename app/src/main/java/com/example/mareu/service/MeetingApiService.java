package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;

import java.util.Date;
import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeeting();

    List<Meeting> getMeetingDate(Date date);

    List<Meeting> getMeetingRoom(MeetingRoom room);

    void deleteMeeting(Meeting meeting);

    void createMeeting(Meeting meeting);
}

