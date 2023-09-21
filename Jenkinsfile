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
                script {
                    // Define the GitHub repository URL
                    def githubRepoUrl = 'https://github.com/mirajulislam/Jenkins-and-Docker.git'

                    // Credentials ID for GitHub credentials configured in Jenkins
                    def githubCredentialsId = 'git-hub'

                    // Perform a Git checkout using GitHub credentials
                    checkout([$class: 'GitSCM',
                              branches: [[name: 'main']],
                              userRemoteConfigs: [[
                                  url: githubRepoUrl,
                                  credentialsId: githubCredentialsId
                              ]]])
                }
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
