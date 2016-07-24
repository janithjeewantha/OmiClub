package com.omiclub.network;

import com.esotericsoftware.kryonet.*;
import com.omiclub.common.GameData;
import com.omiclub.messaging.messages.GeneralMessage;
import com.omiclub.messaging.messages.Message;
import com.omiclub.messaging.messages.MessageCodes;
import com.omiclub.messaging.messages.PlayerBroadcast;

/**
 * Created by janith on 7/23/16.
 */
public class ClientListener extends Listener {

    private Client client;

    public ClientListener(Client client) {
        this.client = client;
    }

    @Override
    public void connected(Connection connection) {
        connection.sendTCP(new GeneralMessage(MessageCodes.CLIENT_NAME, GameData.getPlayerName()));
    }

    @Override
    public void disconnected(Connection connection) {
        super.disconnected(connection);
    }

    @Override
    public void received(Connection connection, Object o) {
        Message msg = (Message) o;
        switch (msg.getMessageCode()){
            case MessageCodes.PLAYER_BROADCAST:
                PlayerBroadcast players = (PlayerBroadcast) msg;
                CommonData.setPlayers(players.getIds(), players.getNames());
                CommonData.setPlayersAquired(true);
                break;
            case MessageCodes.HAND_DISTRIBUTION:

        }
    }
}
