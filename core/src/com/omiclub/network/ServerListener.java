package com.omiclub.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.omiclub.common.GameData;
import com.omiclub.common.players.Client;
import com.omiclub.messaging.messages.GeneralMessage;
import com.omiclub.messaging.messages.Message;
import com.omiclub.messaging.messages.MessageCodes;
import java.util.ArrayList;

/**
 * Created by janith on 7/21/16.
 */
public class ServerListener extends Listener {

    private static final int MAX_CLIENTS = 1;
    private boolean serverIsFull;
    private ArrayList<Client> players;
    private int namesSet = 0;

    @Override
    public void connected(Connection connection) {
        if(serverIsFull){
            connection.sendTCP(new GeneralMessage(MessageCodes.SERVER_FULL));
            return;
        }

        players = GameData.getPlayers();
        players.add(new Client(connection.getID(), connection));

        if(players.size() == MAX_CLIENTS){
            serverIsFull = true;
        }
    }

    @Override
    public void disconnected(Connection connection) {
        super.disconnected(connection);
    }

    @Override
    public void received(Connection connection, Object o) {
        if(!(o instanceof Message)){
            return;
        }
        Message msg = (Message) o;
        switch (msg.getMessageCode()){
            case MessageCodes.CLIENT_NAME:
                Client client = players.get(players.indexOf(new Client(connection.getID())));
                client.setName(((GeneralMessage)msg).getMessage());
                ++namesSet;
                if (serverIsFull && namesSet == MAX_CLIENTS){
                    GameData.setPlayersReady(true);
                }
                break;
        }
    }

    private void broadcastPlayers() {
//        Message broadcast = new PlayerBroadcast(ids, names);
//        server.sendToAllTCP(broadcast);
    }

}
