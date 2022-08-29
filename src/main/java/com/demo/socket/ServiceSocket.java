package com.demo.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class ServiceSocket extends ServerSocket {

    private final String DEFAULT_SERVER_NAME = "TomShiDi-Cat";

    private String serverName = "";

    private final static String localInetAddress = "192.168.1.103";

    public ServiceSocket() throws IOException {
        this(18888, 50, InetAddress.getByName(localInetAddress));
        serverName = DEFAULT_SERVER_NAME;
    }

    public ServiceSocket(int port) throws IOException {
        super(port);
        this.serverName = DEFAULT_SERVER_NAME;
    }

    public ServiceSocket(int port,String serverName) throws IOException {
        super(port);
        if (serverName != null && !serverName.isEmpty()) {
            this.serverName = serverName;
        }
    }

    public ServiceSocket(int port, int backlog) throws IOException {
        super(port, backlog);
        this.serverName = DEFAULT_SERVER_NAME;
    }

    public ServiceSocket(int port, int backlog,String serverName) throws IOException {
        super(port, backlog);
        if (null != serverName && !serverName.isEmpty()) {
            this.serverName = serverName;
        }
    }

    public ServiceSocket(int port, int backlog, InetAddress bindAddr) throws IOException {
        super(port, backlog, bindAddr);
        this.serverName = DEFAULT_SERVER_NAME;
    }

    public ServiceSocket(int port, int backlog, InetAddress bindAddr,String serverName) throws IOException {
        super(port, backlog, bindAddr);
        if (null != serverName && !serverName.isEmpty()) {
            this.serverName = serverName;
        }
    }


}
