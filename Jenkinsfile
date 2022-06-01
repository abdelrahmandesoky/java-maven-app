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
                    gv.buildApp()
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
                    gv.testApp()
                }
            }
        }
        stage('deploy'){
            {
                steps {
                    script{
                        env.ENV = input message:"Selece environment to deploy to ", ok: "Done" ,parameters:[choice(name:'ONE',choices:['dev','staging','prod'],description:'')]
                        gv.deployApp()
                        echo "Deploy to ${ENV}"
                    }
                }
            }
        }
    }
}
