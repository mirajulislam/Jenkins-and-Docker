pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials')
        DOCKER_IMAGE_NAME = 'simple-maven-test'
        DOCKERFILE_PATH = 'Dockerfile' // Path to your Dockerfile
    }

    stages {
        stage('Checkout from GitHub') {
            steps {
                checkout scm
            }
        }

        stage('Build Maven Project') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${DOCKER_IMAGE_NAME}:${BUILD_NUMBER}", "--file ${DOCKERFILE_PATH} .")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withDockerRegistry([credentialsId: "${DOCKER_HUB_CREDENTIALS}", url: 'https://index.docker.io/v1/']) {
                    script {
                        dockerImage.push()
                    }
                }
            }
        }
    }
}
