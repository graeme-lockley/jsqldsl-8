node {
  git(url: 'https://github.com/graeme-lockley/jsqldsl-8.git')
  sh('mvn clean install')
  step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
}
