package com.epam.university.java.core.task031;

import javax.net.ServerSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.SocketOption;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServerImpl implements Server {

    private ServerSocket serverSocket;
    private BufferedReader in;
    private volatile boolean stop = false;
    private volatile ArrayDeque<String> message = new ArrayDeque<>();
    private volatile List<Socket> sockets = new ArrayList<>();
    private int index = 0;
    private Listener listener = new Listener();

    /**
     * constructor.
     */
    public ServerImpl() {
        try {
            this.serverSocket = new ServerSocket(12000);
        } catch (Exception ex) {
            System.out.println();
        }
    }

    @Override
    public String readMessage() {
        try {
            Thread.sleep(100);
            Iterator<Socket> iterator = sockets.iterator();
            while (iterator.hasNext()) {
                try {
                    in = new BufferedReader(new InputStreamReader(iterator.next()
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
        listener.start();
    }

    @Override
    public void stop() {
        stop = true;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            stop = true;
        } catch (Exception ex) {
            System.out.println();
        }
    }

    class Listener extends Thread {

        @Override
        public void run() {
            try {
                while (!stop) {
                    Socket socket = serverSocket.accept();
                    sockets.add(socket);
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