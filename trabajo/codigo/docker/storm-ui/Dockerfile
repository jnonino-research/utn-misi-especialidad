FROM jnonino/storm-base:0.9.6
MAINTAINER Julian Nonino <noninojulian@outlook.com>

# Puerto Interfaz Web
EXPOSE 8080

RUN chmod +x /usr/bin/config-supervisord.sh && \
    /usr/bin/config-supervisord.sh ui

# Iniciar Supervisor
CMD /usr/bin/start-supervisor.sh
