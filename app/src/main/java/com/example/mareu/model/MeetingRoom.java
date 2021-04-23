package com.example.mareu.model;


public enum MeetingRoom {
    ODIN("Odin"),
    THOR("Thor"),
    FREJIA("Frejia"),
    FENRIR("Fenrir"),
    RAGNAR("Ragnar"),
    BJORN("Bjorn");

    private final String text;

    /**
     * @param text
     */
    MeetingRoom(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

    public static String[] getRooms(){
        MeetingRoom[] meetingRoom = MeetingRoom.values();
        String[] rooms = new String[meetingRoom.length];
        for(int i=0;i<meetingRoom.length;i++){
            rooms[i]= String.valueOf(meetingRoom[i]);
        }
        return rooms;
    }

}
