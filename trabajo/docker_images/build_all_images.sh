#!/usr/bin/env bash

docker build -t jnonino/ubuntu-base ubuntu-base
docker build -t jnonino/zookeeper zookeeper
docker build -t jnonino/kafka kafka
docker build -t jnonino/storm-base storm/storm-base
docker build -t jnonino/storm-nimbus storm/storm-nimbus
docker build -t jnonino/storm-supervisor storm/storm-supervisor
docker build -t jnonino/storm-ui storm/storm-ui
