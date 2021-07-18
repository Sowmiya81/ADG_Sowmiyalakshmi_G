package com.task4.dogbreeds.Network;

public class ImgUrl{
    public String message;
    public String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public ImgUrl(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
