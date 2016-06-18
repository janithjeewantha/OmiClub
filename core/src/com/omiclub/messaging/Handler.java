package com.omiclub.messaging;

import com.esotericsoftware.kryonet.Connection;

/**
 * Created by janith on 6/18/16.
 */
public interface Handler {

    public void handle(Connection connection, Object object);

}
