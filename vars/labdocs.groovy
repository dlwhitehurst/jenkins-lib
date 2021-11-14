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
                      - name: builder
                        image: ubuntu
                        command:
                        - sleep
                        args:
                        - infinity
                '''
                defaultContainer 'builder'
            }
        }
        stages {
            stage('Configure Build Agent') {
                steps {
                    script {
                        utils.configureAgent()
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
                    script {
                        utils.packageProject()
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
