def execute() {
    stage 'Build - clean install'
    sh 'mvn clean install'
    step $class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'
}

return this;
