node() {
	   
    stage 'Checkout'
        git url: 'https://github.com/emcconsulting/microservices-demo.git', branch: 'mini-kube'
        def mvnHome = tool 'M3'
        //input 'Ready to go?'
    stage 'Maven Build'
		sh "${mvnHome}/bin/mvn -B verify -Dmaven.test.skip=true"
		
	stage 'Upload to Artifactory'
	    def server = Artifactory.newServer url: 'http://192.168.89.138:8081/artifactory/', username: 'admin', password: 'password'
	            
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
	
	    //sh "sudo cp target/microservice-demo-1.1.0.RELEASE.jar src/main/docker/microservice-demo-1.1.0.RELEASE.jar" 

		//sh "sudo docker build -f src/main/docker/accountDockerfile -t emcdevops/accounts:pac ."
	    //sh "sudo docker build -f src/main/docker/registrationDockerfile -t emcdevops/registration:pac ."
	    sh "sudo docker build -f src/main/docker/webDockerfile -t emcdevops/web:mkub ."
	
        //env.PATH = "${mvnHome}/bin:${env.PATH}"
        //step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
        //step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
	//	sh "docker push emcdevops/microservice:master"
	   
	   stage 'Docker push'
	    sh "sudo docker login --username=emcdevops --password=Welcome@123"
	    //sh "sudo docker push emcdevops/accounts:pac"
		//sh "sudo docker push emcdevops/registration:pac"
		sh "sudo docker push emcdevops/web:mkub"
		
	   stage 'Kube Deployment'
	     sh 'sudo kubectl rolling-update web-deployment --image=emcdevops/web:mkub'
}
