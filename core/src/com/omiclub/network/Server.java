package com.omiclub.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.omiclub.messaging.Handler;
import com.omiclub.messaging.ServerMessageHandler;
import com.omiclub.messaging.messages.GeneralMessage;
import com.omiclub.messaging.messages.Message;
import com.omiclub.messaging.messages.MessageCodes;
import com.omiclub.messaging.messages.PlayerBroadcast;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by janith on 6/18/16.
 */
public class Server {

    private static Server serverInstance;
    private int MAX_CLIENTS = 3;
    private com.esotericsoftware.kryonet.Server server;
    private Listener listener;
    private ArrayList<Connection> clients;
    private Kryo kryo;
    private Handler handler;
    private boolean serverIsFull = false;
    private String serverName;

    private Server(String serverName) {
        this.serverName = serverName;
        server = new com.esotericsoftware.kryonet.Server();
        clients = new ArrayList<Connection>();
        handler = new ServerMessageHandler(this);
        initListener();
    }

    public static Server getServer(String serverName){
        if(serverInstance == null){
            serverInstance = new Server(serverName);
        }
        return serverInstance;
    }

    public boolean startServer(){
        server.start();
        //System.out.println("Starting Server...");
        try {
            server.bind(54555, 54777);
            //System.out.println("Server Bound to Port");
        } catch (IOException ex) {
            //System.out.println("Bind Error: " + ex.getMessage());
            return false;
        }

        kryo = server.getKryo();
        kryo.register(GeneralMessage.class);
        kryo.register(MessageCodes.class);
        server.addListener(listener);

        return true;
    }

    private void initListener() {
        listener = new Listener(){
            @Override
            public void received(Connection connection, Object object) {
                System.out.println("Packet Received: " + object.toString());
                handler.handle(connection, object);
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                System.out.println("Client Disconnected:" + connection);
            }

            @Override
            public void connected(Connection connection) {
                if(serverIsFull){
                    connection.sendTCP(new GeneralMessage(MessageCodes.SERVER_FULL));
                    return;
                }
                clients.add(connection);
                if(clients.size() == MAX_CLIENTS){
                    serverIsFull = true;
                    //Send IDs & names to everyone
                    broadcastPlayers();

                }
            }
        };
    }

    private void broadcastPlayers() {
        int[] ids = new int[4];
        String[] names = new String[4];

        ids[0] = 0;
        names[0]= serverName;

        for (int i = 1; i < MAX_CLIENTS+1; i++) {
            ids[i] = clients.get(i).getID();
            names[i] = clients.get(i).toString();
        }

        Message broadcast = new PlayerBroadcast(ids, names);
        server.sendToAllTCP(broadcast);
    }

    public void stopServer(){
        server.sendToAllTCP(new GeneralMessage(MessageCodes.SERVER_DISCONNECTING));
        if(server != null){
            server.stop();
            server.close();
        }
    }
}
