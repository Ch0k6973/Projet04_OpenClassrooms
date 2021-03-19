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
}
