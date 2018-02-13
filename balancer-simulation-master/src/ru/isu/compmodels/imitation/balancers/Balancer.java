package ru.isu.compmodels.imitation.balancers;

import ru.isu.compmodels.imitation.Request;
import ru.isu.compmodels.imitation.Server;

import java.util.Collection;

public interface Balancer {
    /**
     * Позволяет задать пул серверов для балансировщика нагрузки
     * @param servers - пул серверов
     */
    void setServerPool(Collection<Server> servers);

    /**
     * Возвращает пул серверов балансировщика
     * @return коллекцию серверов, используемых балансировщиком
     */
    Collection<Server> getServerPool();

    /**
     * Балансировка. Балансировщик должен выбрать сервер, который будет обрабатывать новый входящий запрос
     * @param request
     * @return
     */
    Server balance(Request request);

    /**
     * Обработка входящего запроса. Предварительно решается задача балансировки, выбора сервера. Затем выбранному серверу
     * передается запрос для обработки.
     * @param request
     */
    void process(Request request);

}
