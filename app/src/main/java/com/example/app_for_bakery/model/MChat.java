package com.example.app_for_bakery.model;

import java.util.Date;

public class MChat {
    public String message;
    public int messageType;
    public Date messageTime = new Date();


    public MChat(String message, int messageType) {
        this.message = message;
        this.messageType = messageType;
    }
}
