FROM jnonino/storm-base
MAINTAINER Julian Nonino <noninojulian@outlook.com>

# Puerto Workers 1, 2, 3 y 4
# Puerto LogViewer
EXPOSE 6700 6701 6702 6703 8000

RUN /usr/bin/config-supervisord.sh supervisor && \
    /usr/bin/config-supervisord.sh logviewer

# Iniciar Supervisor
CMD /usr/bin/start-supervisor.sh
