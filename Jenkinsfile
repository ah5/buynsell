#!groovy

node {

	stage('Checkout'){
		checkout scm
	}

	stage('Compile'){
		sh "gradle clean compileJava"
	}

}