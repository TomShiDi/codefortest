package com.demo.socket;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ClientSocketRunnable implements Runnable {

//    private Socket clientSocket;

    @Override
    public void run() {

        OutputStream outputStream = null;
        InputStream inputStream = null;
        ClientSocket clientSocket = null;
        try {
            clientSocket = new ClientSocket();
            if (clientSocket != null) {
                inputStream = clientSocket.getInputStream();
                outputStream = clientSocket.getOutputStream();
                dataProcess(inputStream, outputStream);
                clientSocket.shutdownInput();
                clientSocket.shutdownOutput();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
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
            if (null != clientSocket) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void dataProcess(InputStream inputStream, OutputStream outputStream) {
        String inputData = "";
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(outputStream);
            dataInputStream = new DataInputStream(inputStream);
            if (dataInputStream.available() > 0) {
                inputData = dataInputStream.readUTF();
            }

            if (inputData != null && !inputData.isEmpty()) {
                System.out.println("message from server: " + inputData);
            }
            dataOutputStream.write((new Date().toString() + ": client receive message ->" + inputData).getBytes());
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
            if (null != dataOutputStream) {
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
