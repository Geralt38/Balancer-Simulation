package ru.isu.compmodels.imitation.balancers;

import ru.isu.compmodels.imitation.Request;
import ru.isu.compmodels.imitation.Server;

public class RoundRobinBalancer extends BalancerSkeleton {

    private int next = 0;

    @Override
    public Server balance(Request request) {
        next++;
        if (next >= servers.size()) {next = 0;}
        return servers.get(next);
    }
}
