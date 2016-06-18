package com.omiclub.messaging.messages;

/**
 * Created by janith on 6/18/16.
 */
public class GeneralMessage implements Message{

    private int messageCode;
    private String message;

    public GeneralMessage(int messageType) {
        this.messageCode = messageType;
    }

    public GeneralMessage(int messageCode, String message) {
        this.messageCode = messageCode;
        this.message = message;
    }

    @Override
    public int getMessageCode() {
        return messageCode;
    }

    public String getMessage() {
        return message;
    }

}
