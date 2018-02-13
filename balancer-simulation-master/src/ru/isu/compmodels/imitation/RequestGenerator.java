package ru.isu.compmodels.imitation;

import ru.isu.compmodels.imitation.balancers.Balancer;

import java.util.Random;

public class RequestGenerator implements Runnable {

    private boolean shouldStop = false;
    private Balancer balancer;
    private int unitsPerRequest;
    private int sleepTime;

    public RequestGenerator(Balancer balancer, int unitsPerRequest, int sleepTime) {
        setBalancer(balancer);
        this.unitsPerRequest = unitsPerRequest;
        this.sleepTime = sleepTime;
    }

    public void setBalancer(Balancer balancer) {
        this.balancer = balancer;
    }

    @Override
    public void run() {
        Random r = new Random();
        while(!shouldStop){
            SimpleRequest request = new SimpleRequest(unitsPerRequest);
            balancer.process(request);
            try {
                Thread.sleep(sleepTime);
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
