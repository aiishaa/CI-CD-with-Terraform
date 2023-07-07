def buildJar(){
    echo "building the application..."
    sh "mvn package"
}

def buildImage(String imageName){
    sh "docker build -t $imageName ."
}

def dockerLogin(){
    echo "building the docker image..."
    WithCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        sh "echo $PASS | docker login -u $USER --password-stdin"
    }
}

def dockerpush(String imageName){
    echo "pushing the docker image..."
    sh "docker push $imageName"
}
return this