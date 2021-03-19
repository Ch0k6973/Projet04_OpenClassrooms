package com.example.mareu.service;

import com.example.mareu.model.Hour;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(1, MeetingRoom.FREJIA, new Hour(15,30), "Coronavirus", "antoine.lamontagne@free.fr; dummy.gouvernement@null.com"),
            new Meeting(2, MeetingRoom.RAGNAR, new Hour(11,30), "Gilets Jaunes", "antoine.lamontagne@free.fr; dummy.gouvernement@null.com"),
            new Meeting(3, MeetingRoom.THOR, new Hour(17,00), "Campagne Présidentiel", "antoine.lamontagne@free.fr; dummy.gouvernement@null.com")
    );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_MEETING);
    }
}
