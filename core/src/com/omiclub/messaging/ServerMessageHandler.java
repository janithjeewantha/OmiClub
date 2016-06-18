package com.omiclub.messaging;

import com.esotericsoftware.kryonet.Connection;
import com.omiclub.messaging.messages.GeneralMessage;
import com.omiclub.messaging.messages.Message;
import com.omiclub.messaging.messages.MessageCodes;
import com.omiclub.network.Server;

/**
 * Created by janith on 6/18/16.
 */
public class ServerMessageHandler implements Handler{

    private final Server server;

    public ServerMessageHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handle(Connection connection, Object object) {

        if(!(object instanceof Message)){
            return;
        }

        Message message = (Message) object;

        switch (message.getMessageCode()){
            case MessageCodes.CLIENT_NAME:
                GeneralMessage msg = (GeneralMessage) message;
                connection.setName(msg.getMessage());
                break;

        }
    }
}
