// properties(
//                 [
//                     parameters
//                     (
//                             [
//                                 string
//                                         (
//                                             defaultValue: env.buildnumber, 
//                                             description: 'Build number', 
//                                             name: 'BuildNo', 
//                                             trim: false
//                                         )
//                             ]
//                     )
//                 ]
//             )

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
                // script {
                //     def bRun = build 'DeployPipeline' 
                //     for(String line : bRun.getRawBuild().getLog(100)){
                //         echo "${line}"
                //     }

                // }

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
                    // jiraAddComment comment: 'download artifact from orgauto-cicd', idOrKey: 'LOC-10', site: 'Jira-Local-Site'
                }
                           
            }
            
        }
        stage('Cleanup'){
			steps{
				echo 'Cleaning....'
				deleteDir()
			}
		}
        // stage('JIRA') {
        //     steps {
        //         script {
        //         def testIssue = [fields: [ project: [key: 'LOC'],
        //                          summary: 'New JIRA Created from Jenkins.',
        //                          description: 'New JIRA Created from Jenkins.',
        //                          issuetype: [id: '10002']]]

        //         response = jiraNewIssue issue: testIssue, site: 'Jira-Local-Site'
        //         echo response.successful.toString()
        //         echo response.data.toString()
        //            }
        //     }
        // }

    }

}