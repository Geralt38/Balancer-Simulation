package ru.isu.compmodels.imitation.balancers;

import ru.isu.compmodels.imitation.Request;
import ru.isu.compmodels.imitation.Server;

import java.util.Collection;
import java.util.Random;


public class WeightedRoundRobinBalancer extends BalancerSkeleton {
    private int sumUnitsPerSecond;
    Random r = new Random();

    @Override
    public void setServerPool(Collection<Server> servers) {
        super.setServerPool(servers);
        for (Server server: servers) {
            sumUnitsPerSecond += server.getPerformance();
        }
    }

    @Override
    public Server balance(Request request) {
        int chance = r.nextInt(sumUnitsPerSecond);
        int partialSum = 0;
        for (Server server: servers) {
            partialSum += server.getPerformance();
            if (chance < partialSum) {
                return server;
            }
        }
        return null;
    }
}
