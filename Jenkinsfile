def gv
pipeline{

    agent any
    parameters {
        choice(name:'VERSION',choices:['1.0.1','1.0.2','1.1.0'],description:'')
        booleanParam(name:'executeTests',defaultValue:true,description:'')
    }
    stages{
        stage('init'){
            steps{
                script{
                    gv = load "script.groovy"
                }
            }
        }
        stage('builds'){
            steps {
                script{
                    gv.buildapp()
                }
            }
        }
        stage('test'){
            when{
                expression{
                    params.executeTests
                }
            }
            steps {
                script{
                    gv.testapp
                }
            }
        }
        stage('deploy'){
            steps{
                script{
                    gv.deployapp()
                }
            }
        }
    }
}