[Заглавная](README.md)

# Linux
+ [Открыть порт](#Открыть-порт)
+ [Запустить процесс демон на примере Angular 2 приложения](#Запустить-процесс-демон-на-примере-Angular-2-приложения)

## Открыть порт

```linux
netstat -tulpn
firewall-cmd --zone=public --add-port=5432/tcp --permanent
firewall-cmd --reload
netstat -tulpn
```

[к оглавлению](#Linux)

## Запустить процесс демон на примере Angular 2 приложения

На примере мы запускаем Angular 2 приложение.
в папке ```/etc/systemd/system/``` создаём файл ```test2.service```
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

[Заглавная](README.md)