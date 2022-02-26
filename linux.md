[Заглавная](README.md)

# Linux
+ [Открыть порт](#Открыть-порт)
+ [Запустить процесс демон на примере Angular 2 приложения](#Запустить-процесс-демон-на-примере-Angular-2-приложения)
+ [Отдельные команды](#Отдельные-команды)

[к оглавлению](#Linux)

##Открыть порт

```linux
netstat -tulpn
firewall-cmd --zone=public --add-port=5432/tcp --permanent
firewall-cmd --reload
netstat -tulpn
netstat -ano | grep '5432'
```

[к оглавлению](#Linux)

##Запустить процесс демон на примере Angular 2 приложения

В папке ```/etc/systemd/system/``` создаём файл ```test2.service```
В нём записываем:
```linux
[Unit]
Description=Darth Vader

[Service]
Type=oneshot
ExecStart=/usr/local/web/angularTrain/forms/launch.sh %N
RemainAfterExit=yes
```
Сам ```launch.sh```
```linux
#!/bin/bash
cd /usr/local/web/angularTrain/forms/
ng serve --host 45.76.32.22 --disable-host-check
echo "Printing text with newline"
```
Запускаем сервис-демон
```linux
systemctl daemon-reload && systemctl restart test2.service
```
Список демонов
```linux
journalctl -xe
```

[к оглавлению](#Linux)

##Отдельные команды

Создание алиасов
```linux
alias aliasname="cat somefile.txt"
```
Cписок запущенных процессов и их PID
```linux
ps -ax
```
Бесконечный цикл/ Раз в 2 секунды печает в консоль
```linux
while true; do echo "Hello"; sleep 2; done;
```

[к оглавлению](#Linux)

[Заглавная](README.md)