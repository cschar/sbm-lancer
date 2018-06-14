# README

java spring boot render service

## Setup

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

install pycurl macosx

virtualenv env
source env/bin/activate
brew install openssl
export LDFLAGS=-L/usr/local/opt/openssl/lib
export CPPFLAGS=-I/usr/local/opt/openssl/include
export PYCURL_SSL_LIBRARY=openssl
pip install --install-option="--with-openssl" --install-option="--openssl-dir=/usr/local/opt/openssl" pycurl


