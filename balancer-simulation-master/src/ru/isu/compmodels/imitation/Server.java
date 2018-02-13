package ru.isu.compmodels.imitation;

public interface Server extends Runnable{
    /**
     * Устанавливает производительность сервера в юнитах в секунду.
     * @param unitsPerSecond количество юнитов, у.е., которое сервер может обработать за секунду.
     */
    void setPerformance(int unitsPerSecond);

    /**
     * Возвращает производительность сервера, измеряемую в юнитах/в секунду.
     * @return
     */
    int getPerformance();


    /**
     * Постановка запроса в очередь. Все запросы обрабатываются по очереди. Внутренний поток должен выбирать из этой очереди
     * и обрабатывать поток из списка в соответствии с тяжестью запроса и производительностью сервера.
     * @param r
     */
    void process(Request r);

    /**
     * Возвращает текущую нагрузку в у.е.
     * @return
     */
    int getCurrentLoadUnits();

    /**
     * Возвращает текущую нагрузку в процентах от 0 до 100%
     */
    float getCurrentLoad();

    /**
     * Безопасное выключение сервера
     */
    public void shutDown();
}
