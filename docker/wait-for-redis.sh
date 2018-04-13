#!/bin/sh

while ! nc -z redis 6379 ; do
    echo "Waiting for upcoming Redis"
    sleep 1
done

java -jar -Dspring.profiles.active=prod /opt/stumpy-service/lib/stumpy-service.jar