#!/usr/bin/env bash

yum install -y openssh openssh-clients
ssh-keygen -t rsa -N '' -f ~/.ssh/id_rsa <<< y
