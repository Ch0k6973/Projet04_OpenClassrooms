package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(1, -14654801, MeetingRoom.FREJIA, getDate(), "Coronavirus", "antoine.lamontagne@free.fr; dummy.gouvernement@null.com"),
            new Meeting(2, -14654801, MeetingRoom.RAGNAR, getDate(), "Gilets Jaunes", "antoine.lamontagne@free.fr; dummy.gouvernement@null.com"),
            new Meeting(3, -14654801, MeetingRoom.THOR, getDate(), "Campagne Présidentiel", "antoine.lamontagne@free.fr; dummy.gouvernement@null.com")
    );

    private static Date getDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_MEETING);
    }
}
