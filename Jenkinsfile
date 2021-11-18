pipeline {
    agent any
	
    environment {
        EMAIL_RECIPIENTS = 'archene9@gmail.com'
		def mvnHome = tool 'Maven'
    }
    stages {
        stage('Clone Repo') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'main']],
                doGenerateSubmoduleConfigurations: false,gitTool: 'Default',
                submoduleCfg: [], userRemoteConfigs: [[ 
                url: 'https://github.com/Benletaiefarchene/TimeSheet.git']]])
            }
        }
		stage('Clean Test Stage') {
            steps {
                script{
                    bat "${mvnHome}/bin/mvn clean test"
				}
            }
        }
        stage('Clean Compile Stage') {
            steps {
                script{
                    bat "${mvnHome}/bin/mvn clean compile "
				}
            }
        }
		stage('Deploy Nexus Stage') {
			parallel {
				stage('Deploy to Nexus') {
					steps {
						script{
							bat "mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=Timesheet-spring-boot-core-data-jpa-mvc-REST-1 -Dversion=2.0 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-2.0.war"
						}
					}
				}
				stage('Sonar Stage') {
					steps {
						script{
  
 
                             bat "${mvnHome}/bin/mvn -U sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=24f961d9132858cb14dacacdda2c6699a06d25be"
                            
                            
						}    
					}
				}
			}
		}
	    
	    
	   
    }
	 post { 
        success {
                emailext (
                    to: "${EMAIL_RECIPIENTS}",
                    replyTo: "${EMAIL_RECIPIENTS}",
                    subject: "[BuildResult][${currentBuild.currentResult}] - Job '${env.JOB_NAME}' (${env.BUILD_NUMBER})",
                    mimeType: 'text/html',
                    body: '''${JELLY_SCRIPT, template="custom-html.jelly"}'''
                )
        }
		failure {
                emailext (
                    to: "${EMAIL_RECIPIENTS}",
                    replyTo: "${EMAIL_RECIPIENTS}",
                    subject: "[BuildResult][${currentBuild.currentResult}] - Job '${env.JOB_NAME}' (${env.BUILD_NUMBER})",
                    mimeType: 'text/html',
                    body: '''${JELLY_SCRIPT, template="custom-html.jelly"}'''
                )
        }
		aborted {
			     emailext (
                    to: "${EMAIL_RECIPIENTS}",
                    replyTo: "${EMAIL_RECIPIENTS}",
                    subject: "[BuildResult][${currentBuild.currentResult}] - Job '${env.JOB_NAME}' (${env.BUILD_NUMBER})",
                    mimeType: 'text/html',
                    body: '''${JELLY_SCRIPT, template="custom-html.jelly"}'''
                )
		}
    }
   
  
    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 25, unit: 'MINUTES')
    }
}
