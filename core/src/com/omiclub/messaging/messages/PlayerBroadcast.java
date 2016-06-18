package com.omiclub.messaging.messages;

/**
 * Created by janith on 6/18/16.
 */
public class PlayerBroadcast implements Message {

    private int messageCode;
    private int[] ids;
    private String[] names;

    public PlayerBroadcast() {
        this.messageCode = MessageCodes.PLAYER_BROADCAST;
    }

    public PlayerBroadcast(int[] ids, String[] names) {
        this.messageCode = MessageCodes.PLAYER_BROADCAST;
        this.ids = ids;
        this.names = names;
    }

    @Override
    public int getMessageCode() {
        return messageCode;
    }

    public int[] getIds() {
        return ids;
    }

    public String[] getNames() {
        return names;
    }
}
