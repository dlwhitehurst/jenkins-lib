#!/usr/bin/env bash

yum install -y openssh openssh-client
ssh-keygen -t rsa -N '' -f ~/.ssh/id_rsa <<< y
