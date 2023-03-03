#!/usr/bin/env bash

REPOSITORY="/home/ec2-user/app"
JAR_FILE="$REPOSITORY/spring-webapp.jar"
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh
TIME_NOW=$(date +%c)

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$REPOSITORY/deploy.log"

IDLE_PORT=$(find_idle_port)

echo "$TIME_NOW > Build 파일 복사" >> $DEPLOY_LOG
echo "$TIME_NOW > cp $REPOSITORY/*.jar $REPOSITORY/"

cp $REPOSITORY/build/libs/*.jar $JAR_FILE      # 새로운 jar file 계속 덮어쓰기

echo "$TIME_NOW > $JAR_NAME 에 실행권한 추가" >> $DEPLOY_LOG
chmod +x $JAR_NAME

echo "$TIME_NOW > JAR Name: $JAR_FILE" >> $DEPLOY_LOG
echo "$TIME_NOW > $JAR_FILE 실행" >> $DEPLOY_LOG

IDLE_PROFILE=$(find_idle_profile)

echo "$TIME_NOW > $JAR_FILE 를 profile=$IDLE_PROFILE 로 실행합니다." >> $DEPLOY_LOG
nohup java -jar -Dspring.profiles.active=$IDLE_PROFILE


