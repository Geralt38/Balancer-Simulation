package ru.isu.compmodels.imitation.balancers;


import ru.isu.compmodels.imitation.Request;
import ru.isu.compmodels.imitation.Server;

public class StatisticalBalancer extends BalancerSkeleton{

    @Override
    public Server balance(Request request) {
        Server targerServer = servers.get(0);
        float minLoad = targerServer.getCurrentLoad();
        for (int i = 1; i < servers.size(); i++ ) {
            if (servers.get(i).getCurrentLoad() < minLoad) {
                targerServer = servers.get(i);
                minLoad = targerServer.getCurrentLoad();
            }
        }
        return targerServer;
    }
}
