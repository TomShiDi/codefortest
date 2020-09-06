package com.demo.test;

import com.demo.socket.ClientSocketRunnable;
import com.demo.socket.ServiceSocketRunnable;
import org.junit.Test;

public class SocketTest {

    @Test
    public void ServerSocketTest() {
    }

    @Test
    public void ClientSocketTest() {
        Thread clientThread = new Thread(new ClientSocketRunnable());
        clientThread.start();
    }
}
