package com.epam.university.java.core.task031;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientImpl implements Client {

    private Socket socket;
    private volatile PrintWriter out;
    private volatile boolean illigal = false;

    public ClientImpl() {

    }

    @Override
    public void sendMessage(String message) {
        if (message == null) {
            illigal = true;
        }
        out.println(message);
    }

    @Override
    public void start() {
        try {
            socket = new Socket("localhost", 12000);
            out = new PrintWriter(new OutputStreamWriter(socket
                    .getOutputStream()), true);
        } catch (Exception ex) {
            System.out.println();
        }
    }

    @Override
    public void stop() {
        try {
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (illigal) {
            throw new IllegalArgumentException();
        }
    }
}


