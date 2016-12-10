#!/usr/bin/env bash
wget -N http://chromedriver.storage.googleapis.com/2.10/chromedriver_linux64.zip -P chrome/
unzip chrome/chromedriver_linux64.zip -d chrome/
chmod +x chrome/chromedriver
sudo mv -f chrome/chromedriver /usr/local/share/chromedriver
sudo ln -s /usr/local/share/chromedriver /usr/local/bin/chromedriver
sudo ln -s /usr/local/share/chromedriver /usr/bin/chromedriver
wget "https://neo4j.com/artifact.php?name=neo4j-community-3.0.3-unix.tar.gz"
tar -xvzf "artifact.php?name=neo4j-community-3.0.3-unix.tar.gz"
ls
neo4j-community-3.0.3/bin/neo4j start
sleep 20
curl -H "Content-Type: application/json" -X POST -d '{"password":"tanzania"}' -u neo4j:neo4j http://localhost:7474/browser
./neo4j-community-3.0.3/bin/neo4j-shell -c < setup.cql