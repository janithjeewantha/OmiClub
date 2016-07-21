package com.omiclub.common.players;

import com.esotericsoftware.kryonet.Client;

/**
 * Created by janith on 7/18/16.
 */
public class Server {
    private int id = 0;
    private String name;
    private Client client;

    public Server(int id, Client client) {
        this.id = id;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
