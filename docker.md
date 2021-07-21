[Заглавная](README.md)

# Docker

+ [Commands](#Commands)
+ [Examples](#Examples)
+ [Compose](#Compose)
+ [Registry](#Registry)

## Commands

[docker_1]:img/docker_1.JPG
[docker_2]:img/docker_2.JPG
[docker_3]:img/docker_3.JPG
[docker_4]:img/docker_4.JPG

[hostname][:post][/username/]<imagename>[:tag] - где хранится образ

Cписок зарегистрированных образов
```
docker images
```
Запуск контейнера
```
docker run -it ubuntu // -it контейнер в интерактивном режиме в текущем терминале //
docker run --rm ubuntu cat /etc/os-release // выполнит команду cat  внутри контейнера? а затем -rm уничтожит этот контейнер после выполнения команды
docker run -d --name %somename% -p 8080:80 %ubuntu% // запуск в демонизированном виде, чтобы знать какой контейнер запущен. 8080 порт контейнера снаружи, 80 порт который транслируется наружу
docker run -it -v %parent_dir%:%child_dir% %cont_name% // Запустить контейнер и папку из родительской системы продублировать внутри контейнера
```
Список контейнеров
```
docker ps // список запущенных контейнеров
docker ps -a // покажет все контейнеры, даже остановленные
```
Удалить контейнер или образ
```
docker rm -f %id container% // -f force сработает даже для запущенных контейнеров
docker rm -f $(docker ps -aq) // удалить все контейнеры, даже запущенные, а на вход в команду принять результат команды вывода всего списка id контейнеров
docker rmi %cont id%// удаление образа 
```
Лог контейнера
```
docker logs %id container%
docker logs -t %id container% // c таймкодом
docker logs -tf %id container% // следит за логом, а не просто выводит его std.out. ctrl+c - выйти из слежки
```
```
docker attach %id контейнера%  // подключиться к запущенному контейнеру
docker start %id контекнера%  // запуск остановленного контекнера
docker diff %id container% //  содержимое read/write слоя
docker restart %id container% // перезапуск контейнера и его процессов
docker commit %id container% test:1 // сохранит образ текущего контейнера и даст ему имя test и тэг 1. Это неправильно
docker build -t py . // собрать образ в директории "." и назвать "py"
docker tag py my_py // Даёт образу новый тэг
docker inspect container %id container% // даст информацию о контейнере 
docker top %id container% // покажет процессы запущенные внутри контейнера с их реальными pid
docker exec -it py /bin/bash // запустить процесс в контейнере и -it запускает интерактивный режим
docker stats // ресурсы затрачиваемые ресурсы
docker hystory %id container% // история изменений файлов образа
```
```
FROM python               // исходный образ c встроенным python
WORKDIR /app              // объявление рабочей директории
RUN pip install flask     // запуск команды в контейнере по установке пакета flask
ADD app.py /app/app.py    // копирует файл из родительской системы в образ
EXPOSE 80                 // проверяет порт, можно упустить
CMD ["python","app.py"]   // запуск приложения app.py в контейнере. Выполнится, если в команде запуска после названия образа не будет никакой команды. Если команда будет, то выполнится только инструкция  ENRTY
```

ctrl+d // выйти из интерактивного режима образа
ctrl+p а далее ctrl+q // отключиться и оставить запущенным
alpine - лёгкий дистрибутив линукса

![icon][docker_1]
![icon][docker_2]
![icon][docker_3]
![icon][docker_4]

[к оглавлению](#Intro)

##  Compose

```
docker-compose -f app.yml build // собирает образ на основе файлов
docker-compose -f app.yml up -d //  запускает собранные образы, -d для демонизированного отображения
docker-compose -f app.yml logs -tf // лог с группы контейнеров, собранных из app.yml инструкции
```

[к оглавлению](#Intro)

##  Registry

Запуск локального registry
--restart что делать в случае падения (restart policy) Это запуск registry, считай репозитория для образов
```
docker run -d -p 5000:5000 -v %parent_dir%:%child_dir% --restart always --name registry registry:2

docker tag py localhost:5000/py //  сначала тегаем образ

docker push localhost:5000/py // пушим образ в registry

docker pull localhost:5000/py  // скачать из репозитория registry
```
REST API
```
curl localhost:5000/v2/_catalog  // список образов
```

[к оглавлению](#Intro)

##  Examples

1) Простой контейнер висящий на 8080 порту.
Файл app.py
```python
from flask import Flask
app = Flask(__name__)
@app.route("/")
def hello():
    html = "<H1> Hello! </H1>"
    return html
if __name__=="__main__":
    app.run(host="0.0.0.0",port=80)
```
Файл Dockerfile
```docker
FROM python
WORKDIR /app
RUN pip install flask
ADD app.py /app/app.py
VOLUME /volume
EXPOSE 80
CMD ["python","app.py"]
```
Сначала билдим, потом запускаем
```
docker build -t %imagename% %workdir%
docker run -d --name %cont_name% -p 8080:80 %image_name%
```


2) Два контейнера. Один отвечает на 8080 порту, другой это Redis - маленькая база, что хранит счетчик запросов
Файл app.py
```python
from flask import Flask
from redis import Redis
app = Flask(__name__)
db = Redis (host="redis")

@app.route("/")
def hello():
    visitsCounter = db.incr('visitsCounter')
    html = "<H1> Hello!!! </H1>" \
    "<b>Visits: </b>{visits}"\
    "</br>"

    return html.format(visits=visitsCounter)

if __name__=="__main__":
    app.run(host="0.0.0.0",port=80)
```
Файл Dockerfile
```docker
FROM python:3.8
WORKDIR /app
RUN pip install flask redis
ADD app.py /app/app.py
EXPOSE 80
CMD ["python","app.py"]
```
app.yml
```yml
web:
     build: .
     dockerfile: Dockerfile
     links:
            - redis
     ports:
            - "8081:80"
redis:
     image: redis
```

[к оглавлению](#Intro)

[Заглавная](README.md)