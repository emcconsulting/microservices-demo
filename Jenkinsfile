node() {
	   
    stage 'Checkout'
        git url: 'https://github.com/emcconsulting/microservices-demo.git', branch: 'unit-integration-pipelinedemo'
        def mvnHome = tool 'M3'
        //input 'Ready to go?'
    stage 'Maven Build'
		sh "sudo ${mvnHome}/bin/mvn clean test"
    stage 'Sonar Validation'	
		sh "${mvnHome}/bin/mvn sonar:sonar"
    //stage ('SonarQube analysis') {
    //withSonarQubeEnv('Sonar') {
      // requires SonarQube Scanner for Maven 3.2+
      //sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
    //}
  //}
	//stage('SonarQube analysis') {
    // requires SonarQube Scanner 2.8+
    //def scannerHome = tool 'sonar';
    //withSonarQubeEnv('sonar') {
      //sh "${scannerHome}/bin/sonar-scanner"
    //}
  //}
		
	stage 'Upload to Artifactory'
	    def server = Artifactory.newServer url: 'http://192.168.142.128:8081/artifactory/', username: 'admin', password: 'password'
	            
        def uploadSpec = """{
        "files": [
        {
          "pattern": "microservice-demo-1.1.0.RELEASE.jar",
          "target": "libs-release-local"
        }
        ]
        }"""
        
        server.upload(uploadSpec)

		
	stage 'Docker Build'
	    sh "sudo docker build -f src/main/docker/webDockerfile -t emcdevops/web:mkub ."
	    //sh "sudo docker build -f src/main/docker/accountDockerfile -t emcdevops/accounts:mkub ."
	    //sh "sudo docker build -f src/main/docker/registrationDockerfile -t emcdevops/registration:mkub ."
       stage 'Docker push'
	    sh "sudo docker login --username=emcdevops --password=Welcome@123"
	    //sh "sudo docker push emcdevops/accounts:pac"
		//sh "sudo docker push emcdevops/registration:pac"
		sh "sudo docker push emcdevops/web:mkub"
		//sh "sudo docker push emcdevops/accounts:mkub"
		//sh "sudo docker push emcdevops/registration:mkub"
	
	stage 'Docker Compose'
	    //sh "cd src/main/docker"
	    //sh "pwd" 
	    //sh "sudo docker-compose up -d src/main/docker " 
	    sh "(cd src/main/docker && sudo docker-compose up -d)"
	    
	stage 'Integration Tests'
	    sh "sudo docker run -v /var/lib/jenkins/workspace/microservices-demo-kube1:/opt/maven -v /root/.m2:/root/.m2 -w /opt/maven --link docker_mysql_1:mysql --link docker_accounts_1:accounts --net docker_default maven:3.3-jdk-8 mvn test -Dserver.port=2222 -Dserver.host=http://accounts -Dspring.profiles.active=Test"
	    sh "(cd src/main/docker && sudo docker-compose stop)"
	
	
	stage 'JMeter Performance Tests'
	    sh "sudo /opt/apache-jmeter-3.0/bin/jmeter -n -JEnvURL=192.168.149.138 -JPrtNum=2222  -Jusers=7  -JsuppressJMeterOutput=false -JjmeterLogLevel=DEBUG -Jmeter.save.saveservice.output_format=xml  -t '/var/lib/jenkins/workspace/Microservices-kube-build-job/src/test/jmeter/RestService-Template.jmx' -l '/var/lib/jenkins/workspace/Microservice-jmeter-performance-test-docker-job/target/jmeter/results/jtl/'${BUILD_NUMBER}'/microservices_test_plan.jtl' "
	
	stage 'Kube Deployment'
	    sh 'sudo kubectl rolling-update web-deployment --image=emcdevops/web:mkub'
}
