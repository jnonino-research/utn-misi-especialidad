FROM jnonino/storm-base
MAINTAINER Julian Nonino <noninojulian@outlook.com>

RUN /usr/bin/config-supervisord.sh nimbus && \
    /usr/bin/config-supervisord.sh drpc

# Exponer nimbus.thrift.port
# Exponer drpc.port
# Exponer drpc.invocations.port
EXPOSE 6627 3772 3773

# Iniciar supervisor
CMD /usr/bin/start-supervisor.sh