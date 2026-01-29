#!/bin/bash

java \
  -Djava.security.egd=file:/dev/./urandom \
  -Dloader.system=false \
  -Dloader.path=/opt/lib,/opt/etc,/opt/secret \
  -Dspring.config.name=application \
  -Dspring.config.additional-location=file:/opt/etc/ \
  -jar /opt/app.jar \
  "$@"
