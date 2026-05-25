# Техническое руководство: HTTP-сервер с нуля для проекта Eltech

## 1. Назначение работы

В рамках части 2.2 проектной практики был выбран вариант **«Практическая реализация технологии с нуля»**. В качестве технологии реализован минимальный HTTP-сервер на Python без Flask, Django, FastAPI и других веб-фреймворков.

Сервер тематически связан с проектом **Eltech**: он отдаёт демонстрационную HTML-страницу о мобильном приложении, JSON-эндпоинт со статусом проекта и SVG-схему архитектуры.

## 2. Исходные файлы

| Файл | Назначение |
| --- | --- |
| `src/handle_request.py` | исходный код HTTP-сервера |
| `src/README.md` | инструкция по запуску и описание маршрутов |
| `site/practical.html` | страница сайта с описанием вариативной части |

## 3. Архитектура решения

Сервер состоит из нескольких логических блоков:

1. **Socket-сервер** - принимает TCP-соединения на `localhost:8080`.
2. **Парсер HTTP-запроса** - читает первую строку запроса и извлекает путь.
3. **Маршрутизатор** - выбирает обработчик для `/`, `/api/status`, `/architecture.svg`.
4. **Генератор HTML** - формирует страницу о проекте Eltech.
5. **JSON-эндпоинт** - возвращает машинно-читаемое описание статуса проекта.
6. **Раздача SVG** - отдаёт локальную схему архитектуры.

```text
Браузер
  └── HTTP-запрос
        └── Python socket server
              ├── GET /                 -> HTML
              ├── GET /api/status       -> JSON
              ├── GET /architecture.svg -> SVG
              └── другое                -> 404
```

## 4. Пошаговая реализация

### Шаг 1. Создание TCP-сокета

Для начала создаётся TCP-сокет, который слушает порт `8080`.

```python
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
server.bind(("localhost", 8080))
server.listen(5)
```

### Шаг 2. Приём запроса

Сервер принимает подключение, читает байты запроса и декодирует их как UTF-8.

```python
client, address = server.accept()
request = client.recv(4096).decode("utf-8", errors="ignore")
```

### Шаг 3. Ручная сборка HTTP-ответа

Ответ собирается вручную: статусная строка, заголовки, пустая строка и тело ответа.

```python
def http_response(body: bytes, status: str = "200 OK", content_type: str = "text/html; charset=utf-8") -> bytes:
    headers = (
        f"HTTP/1.1 {status}\r\n"
        f"Content-Length: {len(body)}\r\n"
        f"Content-Type: {content_type}\r\n"
        "Connection: close\r\n"
        "\r\n"
    )
    return headers.encode("utf-8") + body
```

### Шаг 4. Маршрутизация

Функция `handle_request` извлекает путь из HTTP-запроса и выбирает нужный ответ.

```python
def handle_request(request: str) -> bytes:
    first_line = request.splitlines()[0] if request.splitlines() else ""
    parts = first_line.split()
    path = parts[1] if len(parts) >= 2 else "/"

    if path == "/":
        return http_response(render_index())

    if path == "/api/status":
        return http_response(render_status(), content_type="application/json; charset=utf-8")

    if path == "/architecture.svg":
        svg = read_svg("architecture.svg")
        if svg is not None:
            return http_response(svg, content_type="image/svg+xml")

    return http_response(b"<h1>404 Not Found</h1>", status="404 Not Found")
```

## 5. Реализованные маршруты

| Маршрут | Тип ответа | Описание |
| --- | --- | --- |
| `/` | HTML | демонстрационная страница о проекте Eltech |
| `/api/status` | JSON | сведения о проекте, студенте, группе и технологии |
| `/architecture.svg` | SVG | схема архитектуры проекта |
| любой другой путь | HTML 404 | сообщение об ошибке маршрута |

## 6. Модификация сверх базовой реализации

Базовая часть HTTP-сервера показывает, как принять запрос и вернуть HTML. Сверх этого были добавлены:

* JSON-эндпоинт `/api/status`;
* отдача локального SVG-файла;
* HTML-страница, связанная с проектом Eltech;
* обработка несуществующих маршрутов через `404 Not Found`;
* логирование запросов в консоль.

## 7. Запуск

Из корня репозитория:

```bash
python src/handle_request.py
```

После запуска открыть в браузере:

```text
http://localhost:8080/
```

Дополнительная проверка:

```text
http://localhost:8080/api/status
http://localhost:8080/architecture.svg
http://localhost:8080/not-found
```

## 8. Проверка результата

| Проверка | Ожидаемый результат |
| --- | --- |
| Открытие `/` | отображается HTML-страница Eltech |
| Открытие `/api/status` | возвращается JSON |
| Открытие `/architecture.svg` | отображается SVG-схема |
| Открытие неизвестного пути | возвращается 404 |
| Остановка `Ctrl+C` | сервер завершает работу без зависания |

## 9. Связь с проектной деятельностью

Хотя HTTP-сервер является самостоятельной реализацией технологии, он связан с проектом Eltech содержательно. Сервер показывает страницу о мобильном приложении и имитирует простой backend-эндпоинт, который может отдавать статус проекта или сервисную информацию.

Такой вариант не копирует основной проект, а демонстрирует отдельную технологию из задания, расширенную тематикой проектной деятельности.

## 10. Вывод

В результате была реализована базовая серверная технология с нуля: TCP-сокет, ручной разбор HTTP-запроса, маршрутизация, HTML/JSON/SVG-ответы и обработка ошибок. Работа помогла понять, что веб-фреймворки скрывают значительную часть низкоуровневой логики HTTP, которую в этом задании пришлось реализовать самостоятельно.
