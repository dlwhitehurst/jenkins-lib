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
            stage('Clone Labdocs Repo') {
                steps  {
                    script {
                        utils.cloneRepo()
                    }
                }
            }
            stage('Build and Package Project') {
                steps  {
                    script {
                        utils.buildAndPackage()
                    }
                }
            }
            stage('Deploy Project Site') {
                steps  {
                    script {
                        utils.deploy()
                    }
                }
            }
        }
    }
}
