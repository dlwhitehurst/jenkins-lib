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
                        image: adoptopenjdk/maven-openjdk11
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
                    sh 'cd /home/jenkins/agent/workspace/Labdocs Asciidoc Deploy/labdocs'
                    script {
                        utils.packageProject()
                    }
                }
            }
            stage('SCP Documents') {
                steps {
                   sh 'cd target/generated-docs'
                   sh 'echo "Abc123" | sshpass scp -r * david@dell-nfs:/srv/nfs4/k8s/pvc-9a2fe61c-e5fb-4386-adf3-083caebece7d/'
                }
            }
        }
    }
}
