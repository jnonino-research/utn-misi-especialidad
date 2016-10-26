#!/usr/bin/env bash

docker build --build-arg http_proxy=http://proxy-us.intel.com:911 -t jnonino/ubuntu-base ubuntu-base
docker build --build-arg http_proxy=http://proxy-us.intel.com:911 -t jnonino/zookeeper zookeeper
docker build --build-arg http_proxy=http://proxy-us.intel.com:911 -t jnonino/kafka kafka
docker build --build-arg http_proxy=http://proxy-us.intel.com:911 -t jnonino/storm-base storm/storm-base
docker build --build-arg http_proxy=http://proxy-us.intel.com:911 -t jnonino/storm-nimbus storm/storm-nimbus
docker build --build-arg http_proxy=http://proxy-us.intel.com:911 -t jnonino/storm-supervisor storm/storm-supervisor
docker build --build-arg http_proxy=http://proxy-us.intel.com:911 -t jnonino/storm-ui storm/storm-ui
