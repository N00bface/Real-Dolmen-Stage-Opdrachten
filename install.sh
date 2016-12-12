#!/usr/bin/env bash
wget "https://neo4j.com/artifact.php?name=neo4j-community-3.0.3-unix.tar.gz"
tar -xvzf "artifact.php?name=neo4j-community-3.0.3-unix.tar.gz"
ls
./neo4j-community-3.0.3/bin/neo4j start
sleep 10
#curl -v -H "Content-Type: application/json" -X POST -d '{"password":"tanzania"}' -u neo4j:neo4j http://localhost:7474/browser
echo "done sleeping"
./neo4j-community-3.0.3/bin/neo4j-shell -v -c < setup.cql