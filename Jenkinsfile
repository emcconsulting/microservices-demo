node() {
	   
    stage 'Checkout'
        git url: 'https://github.com/emcconsulting/microservices-demo.git', branch: 'unit-integration-tests'
        def mvnHome = tool 'M3'
        //input 'Ready to go?'
    stage 'Maven Build'
		sh "sudo rm -rf /var/lib/jenkins/workspace/microservices-demo-kube1/target"
		sh "sudo ${mvnHome}/bin/mvn clean test"
}
