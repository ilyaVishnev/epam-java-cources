package com.epam.university.java.core.task031;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientImpl implements Client {

    private Socket socket;
    private PrintWriter out;

    public ClientImpl() {
    }

    @Override
    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public void start() {
        try {
            this.socket = new Socket("localhost", 5000);
            out = new PrintWriter(new OutputStreamWriter(socket
                    .getOutputStream()), true);
        } catch (IOException ex) {
            System.out.println();
        }
    }

    @Override
    public void stop() {
        try {
            out.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
