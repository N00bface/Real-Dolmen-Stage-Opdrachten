#!/usr/bin/env bash
wget "https://neo4j.com/artifact.php?name=neo4j-community-3.1.0-unix.tar.gz"
tar -xzvf artifact.php\?name=neo4j-community-3.1.0-unix.tar.gz
ls
neo4j-community-3.1.0/bin/neo4j start
sleep 10
#curl -v -H "Content-Type: application/json" -X POST -d '{"password":"tanzania"}' -u neo4j:neo4j http://localhost:7474/browser
echo "done sleeping"
./neo4j-community-3.1.0/bin/neo4j-shell -f setup.cql