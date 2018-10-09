#!/usr/bin/env make

.PHONY: *

start:
	./gradlew bootRun
# start

# Refreshes the configurations
refresh:
	curl localhost:8080/actuator/refresh -d {} -H "Content-Type: application/json"
# refresh

# library