#!/usr/bin/env bash

cd /home/jenkins/agent/workspace/labdocs/target/generated-docs
ls -la
# send all to dell-nfs (192.168.1.20)
#pvc-9a2fe61c-e5fb-4386-adf3-083caebece7d/
ssh-keygen -t rsa -f ~/.ssh/id_rsa <<< y
echo "Abc123" | sshpass ssh-copy-id david@192.168.1.20
scp cp -r * david@192.168.1.20:/srv/nfs4/k8s/pvc-9a2fe61c-e5fb-4386-adf3-083caebece7d/
