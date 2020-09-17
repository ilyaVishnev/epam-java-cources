package com.epam.university.java.core.task031;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;

public class ServerImpl implements Server {

    private ServerSocket serverSocket;
    private BufferedReader in;
    private volatile boolean stop = false;
    private volatile ArrayDeque<String> message = new ArrayDeque<>();
    private volatile ArrayDeque<Socket> sockets = new ArrayDeque<>();
    private int index = 0;

    public ServerImpl() {
    }

    @Override
    public String readMessage() {
        try {
            while (!sockets.isEmpty()) {
                Reader reader = new Reader(sockets.poll());
                reader.start();
                reader.join();
            }
        } catch (Exception ex) {
            System.out.println();
        }
        if (message.isEmpty()) {
            return "";
        }
        return message.pollLast();
    }

    @Override
    public void start() {
        try {
            this.serverSocket = new ServerSocket(5000);
            new Listener().start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            for (Socket socket : sockets) {
                socket.close();
            }
            serverSocket.close();
            stop = true;
        } catch (IOException ex) {
            System.out.println();
        }

    }

    class Listener extends Thread {

        @Override
        public void run() {
            try {
                while (!stop) {
                    sockets.add(serverSocket.accept());
                }
            } catch (Exception e) {
                System.out.println();
            }
        }
    }

    class Reader extends Thread {

        private Socket socket;

        public Reader(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket
                        .getInputStream()));
                try {
                    String input;
                    while (in.ready()) {
                        message.add(in.readLine());
                    }
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println();
            }
        }
    }
}
