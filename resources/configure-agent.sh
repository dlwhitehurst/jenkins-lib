#!/usr/bin/env bash
# This script will install Java, Apache Maven, and generate RSA keys.
#sudo apt-get install -y openjdk-11-jre openjdk-11-jdk
#sudo apt-get install -y

#echo "JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/" >> /etc/environment
#source /etc/environment

#echo $JAVA_HOME

#sudo apt-get update
#sudo apt-get install -y maven

#yum install -y openssh openssh-clients
apt-get update
apt-get install -y openssh-client
ssh-keygen -t rsa -N '' -f ~/.ssh/id_rsa <<< y

apt-get install -y sshpass
echo "Abc123" | sshpass ssh-copy-id david@192.168.1.20
#ssh-copy-id david@192.168.1.20

