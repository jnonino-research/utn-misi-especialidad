#!/bin/bash

if [[ -z "${ZK_ID}" || -z "${ZK_SERVERS}" ]]; then
    exit 1
fi

echo "${ZK_SERVERS}" | tr ' ' '\n' | tee -a /etc/zookeeper/conf/zoo.cfg
echo "${ZK_ID}" | tee /var/lib/zookeeper/myid
/usr/share/zookeeper/bin/zkServer.sh start-foreground
