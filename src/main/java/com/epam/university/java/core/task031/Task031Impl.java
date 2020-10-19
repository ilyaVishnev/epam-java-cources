package com.epam.university.java.core.task031;

public class Task031Impl implements Task031 {

    public Task031Impl() {

    }

    @Override
    public Client createClient() {
        Client client = new ClientImpl();
        return client;
    }

    @Override
    public Server createServer() {
        Server server = new ServerImpl();
        return server;
    }
}
