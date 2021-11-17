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
		stage('Clean Tests Stage') {
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
		stage('Deploy and Quality Gate Stage') {
			parallel {
				stage('Deploy to Nexus') {
					steps {
						script{
							bat "mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=Timesheet-spring-boot-core-data-jpa-mvc-REST-1 -Dversion=2.0 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-2.0.war"
						}
					}
				}
				stage('Quality Gate') {
					steps {
						script{
  
 
                             bat "${mvnHome}/bin/mvn -U sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=24f961d9132858cb14dacacdda2c6699a06d25be"
                            
                            
						}    
					}
				}
			}
		}
	     stage('docker') {
					steps {
						script{
  
 
                             bat "docker-compose down"
                             bat "docker-compose up --build -d"
                            
                            
						}    
                        }   
	}
	   
    }
   
  
    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 25, unit: 'MINUTES')
    }
}
