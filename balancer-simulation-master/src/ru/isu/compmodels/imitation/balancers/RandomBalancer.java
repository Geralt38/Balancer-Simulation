package ru.isu.compmodels.imitation.balancers;

import ru.isu.compmodels.imitation.Request;
import ru.isu.compmodels.imitation.Server;

import java.util.Random;

public class RandomBalancer extends BalancerSkeleton {

    @Override
    public Server balance(Request request) {
        Random r = new Random();
        return servers.get(r.nextInt(servers.size()));
    }
}
