# README

java spring boot render service

## Setup

```
sudo -u postgres createuser <username>
sudo -u postgres createdb <dbname>
sudo -u postgres psql
psql=# alter user <username> with encrypted password '<password>';
grant all privileges on database <dbname> to <username> ;


after set env for jar to pick up:
export SBMLANCER_DB_NAME=sbmlancer_db
export SBMLANCER_DB_USERNAME=sbmlancer
export SBMLANCER_DB_PASSWORD=sbmlancer-password
export ALLOWED_CORS=<UI hostname>

mvn package
java -jar target/<name>
```


## example systemd unit file
```
[Unit]
Description=SBM lancer java service
Requires=postgresql.service      
After=postgresql.service

[Service]
WorkingDirectory=/var/www/sbm-lancer/summoner2-service
ExecStart=/usr/bin/java -jar target/summoner2-service-0.0.1-SNAPSHOT.jar
Environment=SBMLANCER_DB_USERNAME=sbmlancer SBMLANCER_DB_NAME=sbmlancer_db SBMLANCER_DB_PASSWORD=sbmlancer-password ALLOWED_CORS=http://example.com

Restart=always


[Install]
WantedBy=multi-user.target
```
