В Main.java представлена конфигурация из трех серверов с мощностями 50, 300 и 500.

В Main.java также представлено три настройки генератора запросов, при которых одни алгоритмы балансировки справляются, а другие нет:

1) Random не справляется, Round Robin справляется.
Достаточно подобрать такие значения, при которых при Round Robin балансировщике самый слабый сервер будет едва справляться без повышения нагрузки, 
тогда при Random балансировщике будет велика вероятность, что на самый слабый сервер случайно поступит слишком много запросов и он 
не справится с нагрузкой, что легко наблюдается в примере.

2) Round Robin не справляется, а Weighted Round Robin справляется.
При Round Robin легко предсказать поступающую нагрузку на самый слабый сервер, и достаточно подобрать такие значения, что самый слабый сервер 
при Round Robin не будет справлятся, но будет при Weighted Round Robin, так как больше запросов будет отдаваться более мощному серверу.

3) Weighted Round Robin не справлсяется, а Statistical справляется.
Достаточно подобрать значения, дающие в секунду нагрузку, близкую к максимальной суммарной мощности системы. Тогда Weighted Round Robin будет иногда
не справляться из-за некоторой случайности распределения, а Statistical же будет справляться, так как он всегда берет наименее нагруженный сервер.

Числовые значения настройки генератора представлены в Main.java.
