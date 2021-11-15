#!/usr/bin/env bash
apt-get update
apt-get install -y openssh-client
apt-get install -y sshpass
apt-get install -y git

git clone https://code.tavros.dlwhitehurst.com/jenkins-ci/devops.git
#mkdir /home/jenkins/agent/workspace/.ssh
#cp devops/ssh/id_rsa /home/jenkins/agent/workspace/.ssh/id_rsa
#cp devops/ssh/id_rsa.pub /home/jenkins/agent/workspace/.ssh/id_rsa.pub

#ls -la /home/jenkins/agent/workspace/.ssh



