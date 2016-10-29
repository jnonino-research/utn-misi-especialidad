FROM jnonino/storm-base
MAINTAINER Julian Nonino <noninojulian@outlook.com>

# Puerto Interfaz Web
EXPOSE 8080

RUN /usr/bin/config-supervisord.sh ui

# Iniciar Supervisor
CMD /usr/bin/start-supervisor.sh
