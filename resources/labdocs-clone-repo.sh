#!/usr/bin/env bash

apt-get install -y git
git clone https://github.com/dlwhitehurst/labdocs.git
cd labdocs
mvn clean package
cd target/generated-docs
echo "Abc123" | sshpass scp -r * david@dell-nfs:/srv/nfs4/k8s/pvc-9a2fe61c-e5fb-4386-adf3-083caebece7d/
