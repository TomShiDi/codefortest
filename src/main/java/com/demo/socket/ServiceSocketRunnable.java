package com.demo.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceSocketRunnable implements Runnable {

    private List<String> userList = null;

    private Socket socket;

    public ServiceSocketRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        if (null == userList) {
            userList = new ArrayList<>();
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            System.out.println("client onLine");
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
//                System.out.println("after getStream");
            dataProcess(inputStream,outputStream);
//                System.out.println("after dataProcess");

            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void dataProcess(InputStream inputStream,OutputStream outputStream) {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        String inputData = "";
        try {
//            System.out.println("before readUTF");
            dataOutputStream.writeUTF("Hello Client");
            inputData = dataInputStream.readUTF();
            dataOutputStream.writeUTF("server receive message");
            if (!inputData.isEmpty()) {
                System.out.println((new Date().toString() + ": receive message from client ->" + inputData));
            }
//            System.out.println("after readUTF");
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != dataInputStream) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != dataInputStream) {
                try {
                    dataOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
