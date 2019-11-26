node(){

		def repoURL='https://github.com/gabrielstar/cucumber.git'

		stage("Prepare Workspace"){
			cleanWs()
			env.WORKSPACE_LOCAL=sh(returnStdout:true,script:'pwd').trim()
			echo"Workspace set to:"+env.WORKSPACE_LOCAL
		}
		stage('Checkout Self'){
			git branch:'master',credentialsId:'',url:repoURL
		}
		stage('Cucumber Tests'){
			sh """
				cd ${env.WORKSPACE_LOCAL}
				mvn clean test
			"""
		}
}
