package com.task4.dogbreeds.Network;

public class Root{
    public Object getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public Root(Object message, String status) {
        this.message = message;
        this.status = status;
    }

    public Object message;
    public String status;
}
