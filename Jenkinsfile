pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo "${env.JOB_BASE_NAME}"
                echo "${WORKSPACE}"
                echo "${currentBuild.number}"
                echo "${currentBuild.changeSets}"

            }
            
        }
        stage('Download') {            
            steps {
                echo "Executing another scripted pipeline Job"
                script {
                    def bRun = build 'DeployPipeline' 
                    for(String line : bRun.getRawBuild().getLog(100)){
                        echo "${line}"
                    }

                }

            }
        }

        stage('Publish and Download') { 
             when {
              branch 'main'
            }
            
            steps {
                
                script {
                    load "env.groovy"
                    def rtServer = Artifactory.server("ArtifactoryLocal")
                    rtServer.download spec: env.downloadSpec
                }
                           
            }
            
        }
        stage('Cleanup'){
			steps{
				echo 'Cleaning....'
				deleteDir()
			}
		}

    }

}