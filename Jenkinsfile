node {
    stage 'Checkout'
        git url: 'https://github.com/emcconsulting/microservices-demo.git'
        def mvnHome = tool 'M3'
        //input 'Ready to go?'
    stage 'Build'
        //env.PATH = "${mvnHome}/bin:${env.PATH}"
        sh "${mvnHome}/bin/mvn -B verify -Dmaven.test.skip=true"
        step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
        //step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
        def server = Artifactory.newServer url: 'http://10.63.39.117:8081/artifactory/', username: 'admin', password: 'password'
    
     
}
