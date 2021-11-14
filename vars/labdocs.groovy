#!/usr/bin/env groovy
import com.ms3_inc.tavros.jenkins.Utilities

def call() {
    def utils = new Utilities(this)
    pipeline {
        agent {
            kubernetes {
                yaml '''
                    apiVersion: v1
                    kind: Pod
                    spec:
                      containers:
                      - name: maven
                        image: maven:3.6.3-jdk-11
                        securityContext:
                          runAsUser: 1000
                        command:
                        - sleep
                        args:
                        - infinity
                '''
                defaultContainer 'maven'
            }
        }
        stages {
            stage('Create RSA Public/Private Key') {
                steps {
                    script {
                        utils.createKeys()
                    }
                    sh 'cat ~/.ssh/id_rsa.pub'
                }
            }
            stage('Clone Repository') {
                steps  {
                    script {
                        utils.cloneRepo()
                    }
                }
            }
            stage('Build and Package Project') {
                steps  {
                    sh 'cd labdocs'
                    container('maven') {
                        script {
                            utils.packageProject()
                        }
                    }
                }
            }
            stage('SCP Documents') {
                environment {
                    COMMIT_MSG = "[tavros-quickstart] Initial commit"
                }
                steps {
                   sh 'cd target/generated-docs'
                   sh 'scp -r * david@dell-nfs:/srv/nfs4/k8s/pvc-9a2fe61c-e5fb-4386-adf3-083caebece7d/'
                }
            }
        }
    }
}
