def execute() {
    stage 'Build - clean install'
    sh 'mvn clean install'
    step $class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'

    stage 'Update sonar stats'
    sh 'mvn -o sonar:sonar'
}

return this;
