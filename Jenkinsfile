pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials') // Docker Hub credentials ID
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM',
                          branches: [[name: 'main']], // Use 'main' branch
                          userRemoteConfigs: [[url: 'https://github.com/mirajulislam/Jenkins-and-Docker.git']]]) // Use your repository URL
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    def dockerImage = docker.build("mirajulislam/spring-boot-docker-jenkins-docker-hub:latest")

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
