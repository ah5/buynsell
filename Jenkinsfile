#!groovy

node {

	stage('Checkout'){
		checkout scm
	}

	stage('Build'){
		sh "gradle tasks"
	}

}