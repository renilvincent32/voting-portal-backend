#!/usr/bin/env bash

docker run -d \
  --name voting-app-postgres \
  -e POSTGRES_USER=user \
  -e POSTGRES_PASSWORD=password \
  -e POSTGRES_DB=voting-app-db \
  -p 5432:5432 \
  postgres:14.4