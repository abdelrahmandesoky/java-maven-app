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
    echo 'deploying the application ...'
    echo "deploying version ${params.VERSION}"
}
return this