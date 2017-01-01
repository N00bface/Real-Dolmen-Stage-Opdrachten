FROM ubuntu:16.04

USER root
# this is a non-interactive automated build - avoid some warning messages

# update dpkg repositories
RUN apt-get update

# install wget
RUN apt-get install -y wget

# get maven 3.2.2
RUN wget -O /tmp/apache-maven-3.2.2.tar.gz http://archive.apache.org/dist/maven/maven-3/3.2.2/binaries/apache-maven-3.2.2-bin.tar.gz

#get google-chrome-stable
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
# verify checksum
RUN echo "87e5cc81bc4ab9b83986b3e77e6b3095 /tmp/apache-maven-3.2.2.tar.gz" | md5sum -c

# install maven
RUN tar xzf /tmp/apache-maven-3.2.2.tar.gz -C /opt/
RUN ln -s /opt/apache-maven-3.2.2 /opt/maven
RUN ln -s /opt/maven/bin/mvn /usr/local/bin
RUN rm -f /tmp/apache-maven-3.2.2.tar.gz
ENV MAVEN_HOME /opt/maven

RUN apt-get update
# install git
RUN apt-get install -y git

# install nano
RUN apt-get install -y nano

#install unzip
RUN apt-get install -y unzip

#install chrome browser
RUN apt-get install -y google-chrome-stable

#install some more files
RUN apt-get install -y libgtk2.0-dev libnss3-dev libgconf-2-4

# remove download archive files
RUN apt-get clean

#setup libgtk
RUN /sbin/ldconfig -v
#install chromedriver
COPY /src/main/resources/chromedriver .
RUN chmod +x chromedriver

#update symlinks
RUN mv -f chromedriver /usr/local/share/chromedriver && \
    ln -s /usr/local/share/chromedriver /usr/local/bin/chromedriver && \
    ln -s /usr/local/share/chromedriver /usr/bin/chromedriver && \
    ln -s /usr/local/share/chromedriver /bin/chromedriver

# set shell variables for java installation
ENV java_version 1.8.0_11
ENV filename jdk-8u11-linux-x64.tar.gz
ENV downloadlink http://download.oracle.com/otn-pub/java/jdk/8u11-b12/$filename

# download java, accepting the license agreement
RUN wget --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" -O /tmp/$filename $downloadlink

# unpack java
RUN mkdir /opt/java-oracle && tar -zxf /tmp/$filename -C /opt/java-oracle/
ENV JAVA_HOME /opt/java-oracle/jdk$java_version
ENV PATH $JAVA_HOME/bin:$PATH

# configure symbolic links for the java and javac executables
RUN update-alternatives --install /usr/bin/java java $JAVA_HOME/bin/java 20000 && update-alternatives --install /usr/bin/javac javac $JAVA_HOME/bin/javac 20000

# copy the repo
RUN mkdir /dep
COPY . /dep

#run maven
RUN cd /dep && mvn clean install
