#!/usr/bin/env bash

if [ "$#" -ne 1 ]; then
    echo "usage: ./`basename "$0"` <port>"
    echo "example: ./`basename "$0"` 8082"
    exit 1
fi

SERVICE_JAR_FILE="./build/libs/`ls ./build/libs | grep -v stubs`"

echo "Starting $SERVICE_JAR_FILE on port $1 with \"production\"-profile activated."
java -Dserver.port=$1 -Dspring.profiles.active=production -jar $SERVICE_JAR_FILE