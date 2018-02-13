package ru.isu.compmodels.imitation;

import java.util.Queue;
import java.util.concurrent.*;

public class DummyServer implements Server {

    BlockingQueue<Request> requests = new LinkedBlockingQueue<>();
    private int unitsPerSecond;
    private boolean shouldStop=false;

    @Override
    public void setPerformance(int unitsPerSecond) {
        this.unitsPerSecond = unitsPerSecond;
    }

    @Override
    public int getPerformance() {
        return unitsPerSecond;
    }

    @Override
    public void process(Request r) {
        try {
            requests.put(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCurrentLoadUnits() {
        int sum  = 0;
        for (Request request : requests) {
            sum+=request.getLoad();
        }
        return sum;
    }

    @Override
    public float getCurrentLoad() {
        return Math.min(actualLoad(), 100);
    }


    /**
     * Реальная нагрузка измеряется в объеме запросов в юнитах, деленных на производительность. Может быть Чуровских 136%
     * @return
     */
    private float actualLoad() {
        return  (float) getCurrentLoadUnits() / (float) getPerformance() * 100F;
    }

    @Override
    public void run() {
        while(!shouldStop){
            try {
                Request r = requests.take();
                //симуляция обработки запроса, просто спим некоторое время
                Thread.sleep((long)(((double) r.getLoad() /  (double) getPerformance())*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void shutDown() {
        shouldStop = true;
        //Будим, если он уснул по take на пустой очереди
        Thread.currentThread().interrupt();
    }

}
