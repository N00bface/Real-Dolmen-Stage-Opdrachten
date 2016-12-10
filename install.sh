#!/usr/bin/env bash
#wget -N http://chromedriver.storage.googleapis.com/2.10/chromedriver_linux64.zip -P chrome/
#unzip chrome/chromedriver_linux64.zip -d chrome/
#chmod +x chrome/chromedriver
#sudo mv -f chrome/chromedriver /usr/local/share/chromedriver
#sudo ln -s /usr/local/share/chromedriver /usr/local/bin/chromedriver
#sudo ln -s /usr/local/share/chromedriver /usr/bin/chromedriver

wget -O neo4j-community-3.0.1.tar.gz http://neo4j.com/artifact.php?name=neo4j-community-3.0.1-unix.tar.gz
tar -xzvf neo4j-community-3.0.1.tar.gz -o neo4j-community-3.0.1/
neo4j-community-3.0.1/bin/neo4j start
sleep 20
curl -H "Content-Type: application/json" -X POST -d '{"password":"neo4j"}' -u neo4j:neo4j http://localhost:7474/browser
./neo4j-community-3.0.1/bin/neo4j-shell -c < setup.cql