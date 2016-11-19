#!/usr/bin/env bash
apt-get install google-chrome-stable
wget -O neo4j-community-3.0.1.tar.gz http://neo4j.com/artifact.php?name=neo4j-community-3.0.1-unix.tar.gz
tar -xzvf neo4j-community-3.0.1.tar.gz -o neo4j-community-3.0.1/
neo4j-community-3.0.1/bin/neo4j start
sleep 20
curl -H "Content-Type: application/json" -X POST -d '{"password":"tanzania"}' -u neo4j:neo4j http://localhost:7474/user/neo4j/password
./neo4j-community-3.0.1/bin/neo4j-shell -c < setup.cql
#ddd