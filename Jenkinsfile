podTemplate(name: 'jug-build', label: 'jug-build', inheritFrom: 'java-build') {
	node ('jug-build') {
        stage('Checkout Repository') {
            checkout scm
        }

        def doDeployment = false
        def doPushImage = false
        def gitBranch = env.BRANCH_NAME
        def gitCommit = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
        switch (gitBranch) {
            case "master":
                doDeployment = true
            case ~/(release|hotfix)\/.*/:
            case "develop":
                doPushImage = true
        }

		container('build') {
			stage('Tests') {
			    sh './gradlew check --no-daemon --info'
			}

            if (doPushImage) {
                stage('Docker build') {
                    sh './gradlew build buildDocker --no-daemon --info'
                }
                stage('Docker push') {
                    docker.withRegistry("https://kube-registry.int.nk360.de", "kube-registry") {
                        def app = docker.image("kube-registry.int.nk360.de/jug-api")
                        switch (gitBranch) {
                            case "master":
                                app.push("production")
                                break
                            case ~/(release|hotfix)\/.*/:
                                app.push("staging")
                                break
                            case "develop":
                                app.push("preview")
                                break
                        }
                        if (doDeployment) {
                            // push image with GIT_COMMIT tag
                            app.push(gitCommit)
                        }
                    }
                }
            }
		}

		if (doDeployment) {
		    container('kubectl') {
                stage('Trigger Deployment') {
                    switch (gitBranch) {
                        case "master":
                            echo "Deploying production"
                            sh "kubectl set image deployment/jug-api -n jug jug-api=kube-registry.int.nk360.de/jug-api:"+gitCommit
                            break
                    }
                }
		    }
		}
	}
}
