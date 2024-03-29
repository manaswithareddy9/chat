# Makefile for building and running Spring Boot application in Docker

# Define variables
DOCKER_IMAGE_NAME = chat-app-image

# Clean up Docker artifacts
docker-clean:
		docker stop $$(docker ps -aq)
		docker rm $$(docker ps -aq)
		docker rmi chat-web

# Note: This will remove all Docker images from your local machine
docker-clean-images:
		docker rmi $$(docker images -aq)

# Build the Spring Boot application
compose:
		docker-compose up -d

# Build the Docker image
docker-build: compose
		mvn clean package flyway:migrate -X

# Run the Docker container
docker-run:
		docker run -p 8081:8081 $(DOCKER_IMAGE_NAME)

# Clean up built artifacts
clean:
		mvn clean
	
k6-load-test: 
		docker-compose run k6 run /scripts/loadtest.js -e TARGET_VUS=5