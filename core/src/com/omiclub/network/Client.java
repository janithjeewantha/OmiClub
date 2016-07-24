package com.omiclub.network;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.omiclub.messaging.ClientMsgHandler;
import com.omiclub.messaging.Handler;
import com.omiclub.messaging.messages.GeneralMessage;
import com.omiclub.messaging.messages.Message;
import com.omiclub.messaging.messages.MessageCodes;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by janith on 6/18/16.
 */
public class Client {

    private int clientID;
    private String clientName;
    private static Client clientInstance;
    private Listener listener;
    private com.esotericsoftware.kryonet.Client client;
    private InetAddress host;
    private Handler handler;

    private Client(String clientName) {
        this.clientName = clientName;
        client = new com.esotericsoftware.kryonet.Client();
        Thread clientTread = new Thread(client);
        clientTread.start();
        handler = new ClientMsgHandler(this);
        listener = new ClientListener(this);
        client.addListener(listener);
    }

    public static Client getClient(String clientName){
        if(clientInstance == null){
            clientInstance = new Client(clientName);
        }
        return clientInstance;
    }

    public boolean discoverHost(){
        host = client.discoverHost(54777, 10000);
        if (host == null) {
            client.stop();
            return false;
        }
        return true;
    }

    public boolean connectToHost(){
        try {
            client.connect(10000, host, 54555, 54777);
            return true;
        } catch (IOException ex) {
            client.stop();
            return false;
        }
    }

    public boolean isConnected(){
        return client.isConnected();
    }

    private void sendName(){
        Message nameMessage = new GeneralMessage(MessageCodes.CLIENT_NAME, this.clientName);
        client.sendTCP(nameMessage);
    }

    public void setID(int clientID){
        this.clientID = clientID;
    }
}
