package com.example.mareu;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.DummyMeetingGenerator;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.ui.activity.MeetingListActivity;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {

    private int ITEMS_COUNT;

    private MeetingApiService mApiService;

    private List<Meeting> meetings;

    @Before
    public void setUp() {
        mApiService = DI.getMeetingApiService();
        ITEMS_COUNT = mApiService.getMeeting().size();
        meetings = mApiService.getMeeting();
    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETING;
        expectedMeetings.equals(meetings);
    }

    @Test
    public void myMeetingList_filterAction_filterByDate() {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.MILLISECOND, 0);
        mCalendar.set(2021,
                3,
                21,
                10,
                10,
                0
        );
        Meeting meeting = new Meeting(ITEMS_COUNT,
                0,
                MeetingRoom.RAGNAR,
                mCalendar.getTime(),
                "Sujet",
                "Membres"
        );
        mApiService.createMeeting(meeting);
        List<Meeting> list = mApiService.getMeetingDate(mCalendar.getTime());
        assertEquals(list.get(0), meeting);
    }

    @Test
    public void myMeetingList_filterAction_filterByRoom() {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.MILLISECOND, 0);
        mCalendar.set(2021,
                3,
                21,
                10,
                10,
                0
        );
        Meeting meeting = new Meeting(ITEMS_COUNT,
                0,
                MeetingRoom.ODIN,
                mCalendar.getTime(),
                "Sujet",
                "Membres"
        );
        mApiService.createMeeting(meeting);
        List<Meeting> list = mApiService.getMeetingRoom(MeetingRoom.ODIN);
        assertEquals(list.get(0), meeting);
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = meetings.get(0);
        mApiService.deleteMeeting(meetingToDelete);
        assertFalse(meetings.contains(meetingToDelete));
    }

    @Test
    public void createMeetingWithSuccess() {
        int size = meetings.size();
        mApiService.createMeeting(meetings.get(0));
        assertEquals(mApiService.getMeeting().size(), size + 1);
    }

}