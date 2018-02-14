package ru.isu.compmodels.imitation;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Outputter implements Runnable{

    private List<Server> servers = new ArrayList<>();
    private boolean shouldStop = false;
    private LinkedList<Double> time;
    private LinkedList<LinkedList<Double>> loads;
    final XYChart chart;
    final SwingWrapper<XYChart> sw;

    public Outputter(List<Server> servers) {

        this.servers = servers;

        time = new LinkedList<>();
        time.add(0D);

        loads = new LinkedList<>();

        chart = QuickChart.getChart("Server Load Chart", "Time", "Load", "server 1 ("+servers.get(0).getPerformance()+")", new double[] {0D}, new double []{0D});

        for (Server server : servers) {
            LinkedList<Double> list = new LinkedList<>();
            list.add(0D);
            loads.add(list);
            if (servers.indexOf(server) != 0) {
                chart.addSeries("server " + (servers.indexOf(server)+1) + " (" +servers.get(servers.indexOf(server)).getPerformance()+")", new double[]{0D});
            }
        }

        sw = new SwingWrapper<XYChart>(chart);
        sw.displayChart();
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    @Override
    public void run() {
        while (!shouldStop) {
            System.out.print("Load: ");
            time.add(time.getLast()+0.5D);
            for (int i = 0; i < servers.size(); i++) {
                System.out.print(servers.get(i).getCurrentLoad() + ", ");
                loads.get(i).add((double)servers.get(i).getCurrentLoad());
                chart.updateXYSeries("server " + (i+1) + " (" +servers.get(i).getPerformance()+")", time, loads.get(i), null);
            }

            if (time.size() > 10) {
                time.removeFirst();
                for (LinkedList list: loads) {
                    list.removeFirst();
                }
            }

            sw.repaintChart();
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

    private double[] listToDoubleArray(List<Double> list) {
        double[] array = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
