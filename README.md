# Explore With Me
Бэкенд сервиса-афиши, в котором можно поделиться информации о каком-либо интересном событии и собрать компанию для участия в нём.

## Описание
Стэк: Java, Spring Boot, PostgreSQL, JPA (Hibernate), JUnit5, Mockito, Docker

*API (swagger)*
- [Основной сервис](https://raw.githubusercontent.com/ilia-kmrv/java-explore-with-me/refs/heads/main/ewm-main-service-spec.json) 

- [Сервис статистики](https://raw.githubusercontent.com/ilia-kmrv/java-explore-with-me/refs/heads/main/ewm-stats-service-spec.json)

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
cd java-explore-with-me
mvn clean install
docker compose up
```

*Коллекции postman тестов*
- [Сервис
статистики](https://github.com/ilia-kmrv/java-explore-with-me/blob/main/postman/Explore%20with%20me%20-%20stats%20API.json)
- [Основной
сервис](https://github.com/ilia-kmrv/java-explore-with-me/blob/main/postman/Test%20Explore%20With%20Me%20-%20Main%20service.json)
- [Тесты для
comments](https://github.com/ilia-kmrv/java-explore-with-me/blob/main/postman/feature.json)
