node(){

		def repoURL='https://github.com/gabrielstar/cucumber.git'

		stage("Prepare Workspace"){
			cleanWs()
			env.WORKSPACE_LOCAL=sh(returnStdout:true,script:'pwd').trim()
			echo"Workspace set to:"+env.WORKSPACE_LOCAL
		}
		stage('Checkout Self'){
			git branch:'xray',credentialsId:'',url:repoURL
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
			step([$class:'XrayImportBuilder',endpointName:'/cucumber',importFilePath:'target/cucumber.json',projectKey:'XRAY',serverInstance:'27b4bb80-25c8-439e-a68a-b7e89fa5d038'])
		}
}
