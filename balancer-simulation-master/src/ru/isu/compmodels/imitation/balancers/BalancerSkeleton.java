package ru.isu.compmodels.imitation.balancers;

import ru.isu.compmodels.imitation.Request;
import ru.isu.compmodels.imitation.Server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BalancerSkeleton implements Balancer{

    protected List<Server> servers = new ArrayList<>();

    @Override
    public void setServerPool(Collection<Server> servers) {
        this.servers = (List<Server>) servers;
    }

    @Override
    public Collection<Server> getServerPool() {
        return servers;
    }

    @Override
    public void process(Request request) {
        balance(request).process(request);
    }
}
