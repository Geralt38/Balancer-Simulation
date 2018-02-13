package ru.isu.compmodels.imitation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Outputter implements Runnable{

    private List<Server> servers = new ArrayList<>();
    private boolean shouldStop = false;

    public Outputter(List<Server> servers) {
        this.servers = servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    @Override
    public void run() {
        while (!shouldStop) {
            System.out.print("Load: ");
            for (Server server : servers) {
                System.out.print(server.getCurrentLoad() + ", ");
            }
            System.out.println("");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutDown() {
        shouldStop = true;
        Thread.currentThread().interrupt();
    }
}
