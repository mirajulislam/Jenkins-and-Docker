pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials') // Docker Hub credentials ID
        GIT_EXECUTABLE = "C:\\Program Files\\Git\\bin\\git.exe"  // Specify the Git executable path
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Use the explicitly specified Git executable path
                    def checkoutCmd = """${GIT_EXECUTABLE} checkout -f ca5aaa4b7f224b8c245b2a6fc513de38bec1b1b0"""
                    sh checkoutCmd
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    def dockerImage = docker.build("your-dockerhub-username/your-docker-image-name:latest")

                    // Authenticate with Docker Hub using credentials
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_HUB_CREDENTIALS) {
                        // Push the Docker image to Docker Hub
                        dockerImage.push()
                    }
                }
            }
        }
    }
}
