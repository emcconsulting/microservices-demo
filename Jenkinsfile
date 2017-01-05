node("docker") {
	   // docker.withRegistry('https://hub.docker.com','docker-hub') { 
    stage 'Checkout'
        git url: 'https://github.com/emcconsulting/microservices-demo.git', branch: 'pipeline-as-code'
        def mvnHome = tool 'M3'
        //input 'Ready to go?'
    stage 'Maven Build'
		sh "${mvnHome}/bin/mvn -B verify -Dmaven.test.skip=true"
		
	stage 'Docker Build'
		//sh "ls"
		sh "pwd"
		//sh "cd /var/lib/jenkins/workspace/microservices-demo-poc"
		//sh "ls"
	    sh "cp target/microservice-demo-1.1.0.RELEASE.jar src/main/docker/microservice-demo-1.1.0.RELEASE.jar" 
        sh "ls"
	//	def app = docker.build("emcdevops/microservice:master", 'src/main/docker')
		//sh "cd /var/jenkins/workspace/microservices-demo-poc/src"
		sh "ls"
		sh "docker build -f src/main/docker/accountDockerfile -t emcdevops/accounts:pac ."
	    sh "docker build -f src/main/docker/registrationDockerfile -t emcdevops/registration:pac ."
	    sh "docker build -f src/main/docker/webDockerfile -t emcdevops/web:pac ."
	
		//def app = docker.build "micro-services" 'src/main/docker/webDockerfile'
		//def pcImg =Docker.build("examplecorp/spring-petclinic:master", '.') 
        //env.PATH = "${mvnHome}/bin:${env.PATH}"
        //step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
        //step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
	//	sh "docker push emcdevops/microservice:master"
		//app.push 'master'
		 //docker push emcdevops/${service}:${version} 
		
        //def server = Artifactory.newServer url: 'http://10.63.39.117:8081/artifactory/', username: 'admin', password: 'password'
	    //}
	   
	   
	   stage 'Docker push'
	    sh "docker login --username=emcdevops --password=Welcome@123"
	    sh "docker push emcdevops/accounts:pac"
		sh "docker push emcdevops/registration:pac"
		sh "docker push emcdevops/web:pac"
}