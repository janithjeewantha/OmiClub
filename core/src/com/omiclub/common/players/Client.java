package com.omiclub.common.players;

import com.esotericsoftware.kryonet.Connection;

/**
 * Created by janith on 7/18/16.
 */
public class Client {
    private int id;
    private String name;
    private Connection connection;

    public Client(int id) {
        this.id = id;
    }

    public Client(int id, Connection connection) {
        this.id = id;
        this.connection = connection;
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

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return id == client.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
