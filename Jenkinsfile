pipeline {
    agent any

    tools {
        maven 'Maven'  // Ensure the Maven tool is configured in Jenkins
        jdk 'JDK'      // Ensure the JDK tool is configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Example: SCP, Docker deploy, or AWS deploy
                // Add commands/scripts to deploy your application
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}