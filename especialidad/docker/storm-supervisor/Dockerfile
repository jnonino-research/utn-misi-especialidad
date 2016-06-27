FROM jnonino/storm-base:0.9.6
MAINTAINER Julian Nonino <noninojulian@outlook.com>

# Puerto Worker 1
# Puerto Worker 2
# Puerto Worker 3
# Puerto Worker 4
# Puerto LogViewer
EXPOSE 6700 6701 6702 6703 8000

RUN chmod +x /usr/bin/config-supervisord.sh && \
    /usr/bin/config-supervisord.sh supervisor && \
    /usr/bin/config-supervisord.sh logviewer

# Iniciar Supervisor
CMD /usr/bin/start-supervisor.sh
