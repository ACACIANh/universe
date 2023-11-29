PROJECT_NAME=acacian/universe
VERSION=0.0.3
LATEST_TAG=latest

check_docker() {
  if ! docker info >/dev/null 2>&1; then
    echo "> Please start docker and try again!"
    exit 1
  fi
}

build_project() {
  echo "> Building project."
  ./gradlew clean build -b build.gradle.kts
  if [[ $? -ne 0 ]]; then
    echo "> Build failed."
    exit 1
  fi
  echo "> Build success."
}

build_docker_images() {
  echo "> Building docker images."
  docker build --platform linux/amd64 -t $PROJECT_NAME:$VERSION -t $PROJECT_NAME:$LATEST_TAG .
  if [[ $? -ne 0 ]]; then
    echo "> Docker build failed."
    exit 1
  fi
  echo "> Docker build success."
}

# Main script execution
check_docker
build_project
build_docker_images
