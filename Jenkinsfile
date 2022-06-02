def gv

pipeline {
    agent any
    tools{
        maven 'Maven'
    }
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                        echo 'building the image ...'
                        withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PWD', usernameVariable: 'USER')]) {
                        sh "docker build -t desouky99/demo-app:${VERSION} . "
                        sh "echo ${PWD}| docker login -u ${USER} --password-stdin"
                        sh "docker push nanajanashia/demo-app:${VERSION}"

                }
            }
        }
        }
        stage("test") {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    env.ENV = input message: "Select the environment to deploy to", ok: "Done", parameters: [choice(name: 'ONE', choices: ['dev', 'staging', 'prod'], description: '')]    

                    gv.deployApp()
                    echo "Deploying to ${ENV}"
                }
            }
        }
    }   
}
