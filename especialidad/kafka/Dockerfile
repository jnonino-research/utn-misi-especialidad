FROM alpine:3.3

ENV JAVA_VERSION=8 \
    JAVA_UPDATE=77 \
    JAVA_BUILD=03 \
    JAVA_HOME="/usr/lib/jvm/default-jvm" \
    ORACLE_COOKIE_HEADER="Cookie: oraclelicense=accept-securebackup-cookie;" \
    JDK_DOWNLOAD_URL="http://download.oracle.com/otn-pub/java/jdk/${JAVA_VERSION}u${JAVA_UPDATE}-b${JAVA_BUILD}/jdk-${JAVA_VERSION}u${JAVA_UPDATE}-linux-x64.tar.gz" \
    SCALA_VERSION=2.11 \
    KAFKA_VERSION=0.9.0.1 \
    KAFKA_HOME="/opt/kafka_${SCALA_VERSION}-{$KAFKA_VERSION}"


RUN apk add --no-cache --virtual=build-dependencies wget ca-certificates && \
    cd "/tmp" && \
    wget --header ${ORACLE_COOKIE_HEADER} ${JDK_DOWNLOAD_URL} && \
    tar -xzf "jdk-${JAVA_VERSION}u${JAVA_UPDATE}-linux-x64.tar.gz" && \
    mkdir -p "/usr/lib/jvm" && \
    mv "/tmp/jdk1.${JAVA_VERSION}.0_${JAVA_UPDATE}" "/usr/lib/jvm/java-${JAVA_VERSION}-oracle" && \
    ln -s "java-${JAVA_VERSION}-oracle" "$JAVA_HOME" && \
    ln -s "$JAVA_HOME/bin/"* "/usr/bin/" && \
    rm -rf "$JAVA_HOME/"*src.zip && \
    apk del build-dependencies && \
    rm "/tmp/"* && \
    cd ~ && \
    wget http://mirrors.dcarsat.com.ar/apache/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz && \
    tar xzf kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz
    cd kafka_${SCALA_VERSION}-${KAFKA_VERSION} && \
    ./bin/zookeeper-server-start.sh config/zookeeper.properties && \
    ./bin/kafka-server-start.sh config/server.properties

ADD build/lib/kafka-1.0-SNAPSHOT.jar ~/

RUN cd ~ && \
    java -jar kafka-1.0-SNAPSHOT.jar


#RUN apk add --no-cache --virtual=build-dependencies wget ca-certificates && \
#    cd "/tmp" && \
#    wget --header "Cookie: oraclelicense=accept-securebackup-cookie;" \
#        "http://download.oracle.com/otn-pub/java/jdk/${JAVA_VERSION}u${JAVA_UPDATE}-b${JAVA_BUILD}/jdk-${JAVA_VERSION}u${JAVA_UPDATE}-linux-x64.tar.gz" && \
#    tar -xzf "jdk-${JAVA_VERSION}u${JAVA_UPDATE}-linux-x64.tar.gz" && \
#    mkdir -p "/usr/lib/jvm" && \
#    mv "/tmp/jdk1.${JAVA_VERSION}.0_${JAVA_UPDATE}" "/usr/lib/jvm/java-${JAVA_VERSION}-oracle" && \
#    ln -s "java-${JAVA_VERSION}-oracle" "$JAVA_HOME" && \
#    ln -s "$JAVA_HOME/bin/"* "/usr/bin/"