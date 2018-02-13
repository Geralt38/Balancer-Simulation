package ru.isu.compmodels.imitation;

import ru.isu.compmodels.imitation.balancers.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Server> l = new ArrayList<>();
        int[] loads = {50, 300, 500};
        for (int load : loads) {
            DummyServer serv = new DummyServer();
            serv.setPerformance(load);
            Thread t = new Thread(serv);
            t.start();
            l.add(serv);
        }
        Balancer balancer = new RoundRobinBalancer();
        balancer.setServerPool(l);
        Thread t = new Thread(new RequestGenerator(balancer,21 ,  140)); //Random doesn't handle but RoundRobin handles
        //Thread t = new Thread(new RequestGenerator(balancer,6 , 10));  //RoundRobin doesn't handle but WeightedRoundRobin handles
        //Thread t = new Thread(new RequestGenerator(balancer,17 , 20)); //WeightedRoundRobin doesn't handle but Statistical handles
        t.start();
        Thread t1 = new Thread(new Outputter(l));
        t1.start();
    }
}

