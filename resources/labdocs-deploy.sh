#!/usr/bin/env bash

# send all to dell-nfs (192.168.1.20)
#pvc-9a2fe61c-e5fb-4386-adf3-083caebece7d/

# user is root here not david
ssh-keygen -t rsa -f $HOME/.ssh/id_rsa <<< y
echo "Abc123" | sshpass ssh-copy-id -o StrictHostKeyChecking=no david@192.168.1.20
cd labdocs/target/generated-docs
scp -r * david@192.168.1.20:/srv/nfs4/k8s/pvc-9a2fe61c-e5fb-4386-adf3-083caebece7d/

