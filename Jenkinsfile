node(){

		def repoURL='https://github.com/gabrielstar/cucumber.git'

		stage("Prepare Workspace"){
			cleanWs()
			env.WORKSPACE_LOCAL=sh(returnStdout:true,script:'pwd').trim()
			env.BUILD_TIME=sh(returnStdout:true,script:'date +%F-%T').trim()
			echo"Workspace set to:"+env.WORKSPACE_LOCAL
			echo "Build time:"+env.BUILD_TIME
		}
		stage('Checkout Self'){
			git branch:env.BRANCH_NAME,credentialsId:'',url:repoURL
		}
		stage('Cucumber Tests'){
			withMaven(maven:'maven35'){
			sh"""
				cd ${env.WORKSPACE_LOCAL}
				mvn clean test
			"""
			}
		}
		stage('Expose report'){
			archive"**/cucumber.json"
			cucumber'**/cucumber.json'
		}
		stage('Import results to Xray'){

			def description = "[BUILD_URL|${env.BUILD_URL}]"
			def labels = '["automated_regression","regression"]'
			def environment = "DEV1"
			def testExecutionFieldId = 10007
			def testEnvironmentFieldName = "customfield_10132"
			def projectKey = "XRAY"
			def xrayConnectorId = 'server-27b4bb80-25c8-439e-a68a-b7e89fa5d038'
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

			step([$class: 'XrayImportBuilder', endpointName: '/cucumber/multipart', importFilePath: 'target/cucumber.json', importInfo: info, inputInfoSwitcher: 'fileContent', serverInstance: xrayConnectorId])
		}
}
