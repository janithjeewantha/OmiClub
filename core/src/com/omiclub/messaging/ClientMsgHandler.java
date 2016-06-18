package com.omiclub.messaging;

import com.esotericsoftware.kryonet.Connection;
import com.omiclub.messaging.messages.Message;
import com.omiclub.messaging.messages.MessageCodes;
import com.omiclub.network.Client;

/**
 * Created by janith on 6/18/16.
 */
public class ClientMsgHandler implements Handler {

    private Client client;

    public ClientMsgHandler(Client client) {
        this.client = client;
    }

    @Override
    public void handle(Connection connection, Object object) {

        if(!(object instanceof Message)){
            return;
        }

        Message message = (Message) object;

        switch (message.getMessageCode()){
            case MessageCodes.SERVER_FULL:
                ////////////////////////////
                break;

        }
    }
}
