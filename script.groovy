def buildapp() {
    echo 'building the application ...'
}

def testapp() {
    echo 'testing the application ... '
}

def deployapp() {
    echo 'deploying the application ...'
    echo "deploying version ${params.VERSION}"
}
return this
