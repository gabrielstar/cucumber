node() {

    def repoURL = 'https://github.com/gabrielstar/cucumber.git'

    stage("Prepare Workspace") {
        cleanWs()
        env.WORKSPACE_LOCAL = sh(returnStdout: true, script: 'pwd').trim()
        env.BUILD_TIME = sh(returnStdout: true, script: 'date +%F-%T').trim()
        echo "Workspace set to:" + env.WORKSPACE_LOCAL
        echo "Build time:" + env.BUILD_TIME
    }
    stage('Checkout Self') {
        git branch: 'xray_video', credentialsId: '', url: repoURL
    }
    stage('Cucumber Tests') {
        withMaven(maven: 'maven35') {
            sh """
			cd ${env.WORKSPACE_LOCAL}
			mvn clean test
		"""
        }
    }
    stage('Expose report') {
        archive "**/cucumber.json"
        cucumber '**/cucumber.json'
    }
	stage('Import results to Xray') {

		def description = "[BUILD_URL|${env.BUILD_URL}]"
		def labels = '["regression","automated_regression"]'
		def environment = "DEV"
		def testExecutionFieldId = 9
		def testEnvironmentFieldName = "customfield_11805"
		def projectKey = "WOO"
		def xrayConnectorId = '251dbe1a-40ef-4145-8d12-6bb6c87fef98'
		def info = '''{
				"fields": {
					"project": {
					"key": "''' + projectKey + '''"
				},
				"labels":''' + labels + ''',
				"description":"''' + description + '''",
				"summary": "Automated Regression Execution @ ''' + env.BUILD_TIME + ' ' + environment + ''' " ,
				"issuetype": {
				"id": "''' + testExecutionFieldId + '''"
				},
				"''' + testEnvironmentFieldName + '''" : [
				"''' + environment + '''"
				]
				}
				}'''

			echo info

			step([$class: 'XrayImportBuilder', endpointName: '/cucumber/multipart', importFilePath: 'target/cucumber.json', importInfo: info, inputInfoSwitcher: 'fileContent', serverInstance: xrayConnectorId])
		}
}
