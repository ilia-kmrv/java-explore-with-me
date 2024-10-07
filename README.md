# Explore With Me
Бэкенд сервиса-афиши, в котором можно поделиться информации о каком-либо интересном событии и собрать компанию для участия в нём.

## Описание
Стэк: Java, Spring Boot, PostgreSQL, JPA (Hibernate), JUnit5, Mockito, Docker

*API (swagger)*
[основной сервис](https://github.com/ilia-kmrv/java-explore-with-me/blob/main/ewm-main-service-spec.json) 

[сервис статистики](https://github.com/ilia-kmrv/java-explore-with-me/blob/main/ewm-stats-service-spec.json)

*Функциональность:*
- два сервиса: один для хранения статистики, второй с основной
  функциональностью;
- CRUD для пользователей, событий, категорий и подборок событий, запросов на
  участие в событии;
- добавление комментариев к событиям;
- модерация событий, подборок, комментариев админом;
- сохранение статистики хитов, получение статистики с сервера;

## Инструкция
*Требования*
- Java 11
- Maven
- Docker

*Установка*
```bash
git clone https://github.com/ilia-kmrv/java-explore-with-me.git
```
