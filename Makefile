include .env

docker.network:
	@echo "Verifying/Creating docker network: $(DOCKER_NETWORK)"
	@docker network inspect $(DOCKER_NETWORK) >/dev/null 2>&1 || docker network create --driver bridge $(DOCKER_NETWORK)

docker.start: docker.network
	@echo "Starting the application: $(APPLICATION)"
	@docker-compose -p $(APPLICATION) up --build -d

docker.stop: docker.network
	@echo "Stopping the application: $(APPLICATION)"
	@docker rm -f $(shell docker ps --filter label=com.docker.compose.project=$(APPLICATION) -a -q)