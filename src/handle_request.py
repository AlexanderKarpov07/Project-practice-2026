import json
import socket
from datetime import datetime
from pathlib import Path


HOST = "localhost"
PORT = 8080
BASE_DIR = Path(__file__).resolve().parent
SITE_IMG_DIR = BASE_DIR.parent / "site" / "img"


PROJECT_INFO = {
    "project": "Eltech",
    "student": "Карпов Александр Владимирович",
    "group": "251-371",
    "technology": "HTTP-сервер на Python socket",
    "status": "demo",
}


def http_response(body: bytes, status: str = "200 OK", content_type: str = "text/html; charset=utf-8") -> bytes:
    headers = (
        f"HTTP/1.1 {status}\r\n"
        f"Content-Length: {len(body)}\r\n"
        f"Content-Type: {content_type}\r\n"
        "Connection: close\r\n"
        "\r\n"
    )
    return headers.encode("utf-8") + body


def render_index() -> bytes:
    generated_at = datetime.now().strftime("%d.%m.%Y %H:%M:%S")
    html = f"""<!doctype html>
<html lang="ru">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Eltech HTTP Demo</title>
  <style>
    body {{
      margin: 0;
      font-family: Arial, sans-serif;
      color: #101828;
      background: #f6f8fb;
    }}
    main {{
      max-width: 920px;
      margin: 0 auto;
      padding: 48px 20px;
    }}
    section {{
      background: #ffffff;
      border: 1px solid #d0d5dd;
      border-radius: 10px;
      padding: 28px;
      box-shadow: 0 12px 32px rgba(16, 24, 40, 0.08);
    }}
    h1 {{
      margin: 0 0 14px;
      font-size: 42px;
    }}
    p {{
      line-height: 1.6;
    }}
    code {{
      background: #eef4ff;
      padding: 2px 6px;
      border-radius: 5px;
    }}
    img {{
      width: 100%;
      margin-top: 20px;
      border: 1px solid #eaecf0;
      border-radius: 8px;
    }}
  </style>
</head>
<body>
  <main>
    <section>
      <h1>Eltech HTTP Demo</h1>
      <p>Это демонстрационная страница, которую отдаёт HTTP-сервер, реализованный с нуля на Python без веб-фреймворков.</p>
      <p>Сервер связан с проектом Eltech: он показывает краткую информацию о мобильном приложении Московского Политеха и предоставляет JSON-эндпоинт <code>/api/status</code>.</p>
      <p><strong>Студент:</strong> {PROJECT_INFO["student"]}, группа {PROJECT_INFO["group"]}</p>
      <p><strong>Сгенерировано:</strong> {generated_at}</p>
      <img src="/architecture.svg" alt="Архитектура проекта Eltech">
    </section>
  </main>
</body>
</html>"""
    return html.encode("utf-8")


def render_status() -> bytes:
    payload = {
        **PROJECT_INFO,
        "routes": ["/", "/api/status", "/architecture.svg"],
        "generated_at": datetime.now().isoformat(timespec="seconds"),
    }
    return json.dumps(payload, ensure_ascii=False, indent=2).encode("utf-8")


def read_svg(name: str) -> bytes | None:
    path = SITE_IMG_DIR / name
    if not path.exists():
        return None
    return path.read_bytes()


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

    body = b"<h1>404 Not Found</h1><p>Route not found.</p>"
    return http_response(body, status="404 Not Found")


def run_server() -> None:
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server.bind((HOST, PORT))
    server.listen(5)
    print(f"HTTP-сервер запущен: http://{HOST}:{PORT}")

    try:
        while True:
            client, address = server.accept()
            request = client.recv(4096).decode("utf-8", errors="ignore")
            response = handle_request(request)
            client.sendall(response)
            client.close()
            print(f"[{address[0]}:{address[1]}] {request.splitlines()[0] if request else 'EMPTY'}")
    except KeyboardInterrupt:
        print("Сервер остановлен.")
    finally:
        server.close()


if __name__ == "__main__":
    run_server()
