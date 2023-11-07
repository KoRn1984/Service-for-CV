# Service-for-CV #
==================================
## Service for managing applicants
----------------------------------
Данное приложение является RESTful веб-сервисом и предназначено для управления кандидатами с различными уровнями компетенции, претендующими на различные должности и направления в компании.
<hr>

### Для управления сервисом доступны следующие сущности:
1. Направление (название, описание);
2. Тест (название, описание, список применимых направлений);
3. Кандидат (фамилия, имя, отчество, фотография, описание, резюме (CV-файл), список возможных направлений);
4. Тест кандидата (кандидат, тест, история сдачи кандидатом теста).
<hr>

##### Сервис построен на следующем стеке технологий:
- Java 17
- Spring Boot 3.1.5
- Maven 4.0.0
- DB PostgreSQL
- Liquibase
- Docker & Docker Compose
- Lombok
- Mapstruct
- Log4j2
- Open API & Swagger 3.0
<hr>

Сервис запускается через Docker Compose (для запуска контейнеров через Docker Compose используются следующие команды: docker-compose up -d — запустить проект (запуск контейнеров в фоновом режиме), docker-compose down — остановить проект и docker-compose build — собрать проект).
<hr>
В файле docker-compose.yml, кроме самого REST-сервиса (доступен на http://localhost:8080/api/v1/), прописан также образ базы данных PostgreSQL, контейнер с которой запускается перед стартом сервиса, и графический клиент pgAdmin 4 (доступен на http://localhost:5050/).
<hr>
Миграция базы данных реализована на Liquibase (файлами changelogs), которые создают структуру базы данных PostgreSQL (создают таблицы и заполняют их первоначальными данными).
<hr>

**Сервис имеет следующие настройки:**
- порт сервера: 8080
- порт базы данных: 5324
--- имя базы данных: cv_management
--- логин базы данных: korn
--- пароль базы данных: postgres
- порт графического клиента pgAdmin 4: 5050
--- логин: admin@admin.com
--- пароль: root
<hr>

[Ссылка для документации по API на Swagger 3.0]: (http://localhost:8080/swagger-ui/index.html)

Хранение фотографий и файлов с резюме организовано в контейнере с сервисом в папке */aploads/*
