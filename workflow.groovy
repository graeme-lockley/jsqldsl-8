def execute {
	sh('mvn clean install')
	step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
}

return this