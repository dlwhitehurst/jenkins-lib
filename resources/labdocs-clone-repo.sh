#!/usr/bin/env bash

apt-get install -y git
git clone https://github.com/dlwhitehurst/labdocs.git
cd labdocs
mvn clean package
cd target/generated-docs

# mount pvc directory using NFS
apt-get update
apt-get install -y nfs-common

mkdir -p /mnt/pvc-9a2fe61c-e5fb-4386-adf3-083caebece7d
mount 192.168.1.20:/srv/nfs4/k8s/pvc-9a2fe61c-e5fb-4386-adf3-083caebece7d /mnt/pvc-9a2fe61c-e5fb-4386-adf3-083caebece7d

cp * /mnt/pvc-9a2fe61c-e5fb-4386-adf3-083caebece7d/
