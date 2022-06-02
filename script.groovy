def buildJar(){
    echo 'building the application ...'
    sh 'mvn package'
}

def buildImage() {
    echo 'building the image ...'
    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t desouky99/demo-app:${VERSION} . "
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push nanajanashia/demo-app:${VERSION}"

        }
}

def testApp() {
    echo 'testing the application ... '
}

def deployApp() {
    echo 'deploying the application ...'
    echo "deploying version ${params.VERSION}"
}
return this
