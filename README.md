# TestCaseForSHIFT
Инструкция по запуску:

1.Скопировать проект

2.Выставить SDK 17

3.Создать в локальной базе данных h2 схемы двух таблиц при помощи следующих SQL-запросов:

Create table INTERVAL_LETTERS (id int primary key, start char, last char)

Create table INTERVAL_DIGITS (id int primary key, start int, last int)

Выставить в application.properties свои данные базы данных.

Эндпоинты для обращений:

localhost:8081/interval/merge?kind=letters

localhost:8081/interval/merge?kind=digits

localhost:8081/interval/min?kind=letters

localhost:8081/interval/min?kind=digits


Тестовые запросы для Postman:

Для параметра letters: [["a","f"],["d","j"],["r","z"]]

Для параметра digits: [[1,4],[3,6],[8,10]]
