package com.demo.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class ClientSocket extends Socket {

    private String DEFAULT_CLIENT_NAME = "Tom";

    private String clientName = "";

    private final static String remoteInetAddress = "192.168.1.103";

    public ClientSocket() throws IOException{
//        super();
        this("127.0.0.1", 18888);
        clientName = DEFAULT_CLIENT_NAME;
    }

    public ClientSocket(String clientName) {
        super();
        if (null != clientName && !clientName.isEmpty()) {
            this.clientName = clientName;
        }
    }

    public ClientSocket(String host, int port) throws IOException {
        super(host, port);
        clientName = DEFAULT_CLIENT_NAME;
    }

    public ClientSocket(String host, int port,String clientName) throws IOException {
        super(host, port);
        if (null != clientName && !clientName.isEmpty()) {
            this.clientName = clientName;
        }
    }

    public ClientSocket(InetAddress address, int port) throws IOException {
        super(address, port);
        clientName = DEFAULT_CLIENT_NAME;
    }

    public ClientSocket(InetAddress address, int port, String clientName) throws IOException {
        super(address, port);
        if (null != clientName && !clientName.isEmpty()) {
            this.clientName = clientName;
        }
    }

    public ClientSocket(String host, int port, InetAddress localAddr, int localPort) throws IOException {
        super(host, port, localAddr, localPort);
        clientName = DEFAULT_CLIENT_NAME;
    }

    public ClientSocket(String host, int port, InetAddress localAddr, int localPort,String clientName) throws IOException {
        super(host, port, localAddr, localPort);
        if (null != clientName && !clientName.isEmpty()) {
            this.clientName = clientName;
        }
    }

    public String getClientName() {
        return clientName;
    }
}
