#!/usr/bin/env bash

apt-get install -y git
git clone https://github.com/dlwhitehurst/labdocs.git
cd labdocs
mvn clean package
cd target/generated-docs

# mount pvc directory using NFS
apt-get update
apt-get install -y nfs-common

mkdir -p /mnt/k8s
mount 192.168.1.20:/srv/nfs4/k8s /mnt/k8s
mkdir -p /mnt/k8s/apache

cp -r * /mnt/k8s/apache/

#pvc-9a2fe61c-e5fb-4386-adf3-083caebece7d/
