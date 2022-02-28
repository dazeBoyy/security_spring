# Тестовое задание на позицию Java разработчика

Реализовать SpringBoot приложение.

Приложение должно реализовывать два REST контроллера: один публичный и один приватный.

Доступ к методам приватного контроллера ограничить с помощью SpringSecurity. Метод аутентификации - логин/пароль. Для авторизации использовать JWT.

Приложение должно иметь два источника данных: один SQL(source1), второй NoSQL(source2). Оба источника должны быть доступны. Контроллер через сервис должен иметь возможность выбора источника данных для работы.

Публичный контроллер реализует методы:

● GET /items?src=source1 (либо /items?src=source2) возвращает JSON массив объектов Item. Аргументом src задается источник данных.

● GET /items/{id}/children/?src=source1 (либо /items/{id}/children/?src=source2) возвращает JSON массив объектов дочерних Item по id родителя. Аргументом src задается источник данных.

● GET /items/{id}/?src=source1 (либо /items/{id}/?src=source2) возвращает JSON объект Item по его id. Аргументом src задается источник данных.

● POST /items?src=source1 (либо /items?src=source2) сохраняет объект Item переданный в теле запроса в источнике данных, указанном в аргументе src

● PUT /items/{id}/?src=source1 (либо /items/{id}/?src=source2)обновляет объект Item переданный в теле запроса в источнике данных, указанном в аргументе src

Приватный контроллер реализует методы управления пользователями. Создать, Удалить, Изменить. Вновь созданные пользователи должны иметь доступ к приватному контроллеру. Удаленные пользователи НЕ должны иметь доступ к приватному контроллеру.

Объект Item имеет следующие поля:

● id (UUID)

● name (String)

● parentId (UUID)

Сборка проекта осуществляется с помощью Gradle. Конфигурация приложения с помощью аннотаций.

# Было реализованно два контроллера приватный и публичный
Для работы была использованна база данных MySQL.

И для работы в NoSQL был использован MongoDB

# Для того чтобы проверить работу публичных контроллеров следует обращаться по ссылкам:
# Для сохранения:
Для SQL
POST http://localhost:8080/items?src=source1
Для NoSQL
POST http://localhost:8080/items2?src=source2

# Для получения всех данных:
Для SQL
GET http://localhost:8080/items?src=source1
Для NoSQL
GET http://localhost:8080/items2?src=source2

# Для получения по id:
Для SQL
GET http://localhost:8080/items/{{id}}?src=source1
Для NoSQL
GET http://localhost:8080/items2/{{id}}?src=source1

# Для получения по id родителя:
Для SQL
 GET http://localhost:8080/items/{{parentId}}/children?src=source1
Для NoSQL
GET http://localhost:8080/items2/{{parentId}}/children?src=source2


# Для изменения данных:
Для SQL
PUT http://localhost:8080/items/{{id}}?src=source1
Для NoSQL
GET http://localhost:8080/items2/{{id}}?src=source2

# Для того чтобы проверить работу приватных контроллеров следует обращаться по ссылкам:

# Для создания нового пользователя:
POST http://localhost:8080/user/create
# Для изменения данных у пользователя:
PUT http://localhost:8080/user/{{id}}
# Для удаления по id:
DELETE http://localhost:8080/user/{{id}}
# Для получения по id родителя:
GET http://localhost:8080/user/{{id}}
