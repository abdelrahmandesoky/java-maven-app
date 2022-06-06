#!/usr/bin/env groovy
def buildJar(){
    echo 'building the application ...'
    sh 'mvn package'
}

def buildImage() {
    echo 'building the image ...'
    docker.withRegistry( '', registryCredential ){
        sh "docker build -t desouky99/demo-app:${VERSION} . "
        sh "docker push desouky99/demo-app:${VERSION}"

        }
}

def testApp() {
    echo 'testing the application ... '
}

def deployApp() {
    //def dockerCmd = "docker run -d -p 8080:8080 desouky99/demo-app:${VERSION}"
    
    //def dockerComposeCmd="docker-compose -f docker-compose.yaml up --detach"
    
    def shellCmd="bash ./server"

    echo 'deploying Docker-compose to EC2 ...'
    echo "deploying version ${params.VERSION}"
    sshagent(['ec2-id']) {
    sh "scp server_cmds.sh ec2-user@3.80.225.123:/home/ec2-user"    
    sh "scp docker-compose.yaml ec2-user@3.80.225.123:/home/ec2-user"
    sh "ssh -o StrictHostKeyChecking=no ec2-user@3.80.225.123 ${shellCmd}"
    }
}
return this