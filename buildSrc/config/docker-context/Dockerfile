FROM registry.cn-shanghai.aliyuncs.com/yingzhuo/openjdk:17-alpine

LABEL maintainer="应卓 yingzhor@gmail.com"

USER root:root

COPY *.jar /opt/app.jar
COPY startup.sh /opt/script/entrypoint.sh

ENTRYPOINT ["bash", "/opt/script/entrypoint.sh"]
