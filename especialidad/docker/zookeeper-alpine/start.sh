#!/bin/bash

if [[ -z "${ZK_ID}" || -z "${ZK_SERVERS}" ]]; then
	echo "Please set ZK_ID and ZK_SERVERS environment variables first."
	exit 1
fi

echo "${ZK_SERVERS}" | tr ' ' '\n' | tee -a /opt/zookeeper/conf/zoo.cfg
echo "${ZK_ID}" | tee /opt/zookeeper/data/myid
/opt/zookeeper/bin/zkServer.sh start-foreground