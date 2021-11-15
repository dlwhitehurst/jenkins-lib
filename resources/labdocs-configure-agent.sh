#!/usr/bin/env bash
apt-get update
apt-get install -y openssh-client
apt-get install -y sshpass
apt-get install -y git

git clone https://code.tavros.dlwhitehurst.com/jenkins-ci/devops.git
mkdir ~/.ssh
cp devops/ssh/id_rsa ~/.ssh/id_rsa
cp devops/ssh/id_rsa.pub ~/.ssh/id_rsa.pub


