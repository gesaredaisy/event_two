package com.onchiri.daisy.eventplanner.Model;

public class Guest {

    public int guestID;
    public String guestName;
    public String guestEmail;
    public String age;
    public String guestGender;
    public String eventName;
    public String notesGuest;
    public String Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


    public String getNotesGuest() {
        return notesGuest;
    }

    public void setNotesGuest(String notesGuest) {
        this.notesGuest = notesGuest;
    }



    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }



    public String getGuestGender() {
        return guestGender;
    }

    public void setGuestGender(String guestGender) {
        this.guestGender = guestGender;
    }



    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }



    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }



    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }



    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }



}
