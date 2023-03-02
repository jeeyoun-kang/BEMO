#!/usr/bin/env bash

REPOSITORY=/home/ec2-user/app
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$REPOSITORY/deploy.log"

IDLE_PORT=$(find_idle_port)

echo "> Build 파일 복사" >> $DEPLOY_LOG
echo "> cp $REPOSITORY/*.jar $REPOSITORY/"

cp $REPOSITORY/zip/*.jar $REPOSITORY      # 새로운 jar file 계속 덮어쓰기

echo "> 새 어플리케이션 배포" >> $DEPLOY_LOG
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME" >> $DEPLOY_LOG

echo "> $JAR_NAME 에 실행권한 추가" >> $DEPLOY_LOG

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행" >> $DEPLOY_LOG

IDLE_PROFILE=$(find_idle_profile)

echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다." >> $DEPLOY_LOG
# nohup java -jar -Dspring.profiles.active= $JAR_NAME > sudo $REPOSITORY/nohup.out 2>&1 & 
nohup java -jar \
    -Dspring.profiles.active=$IDLE_PROFILE \
    $REPOSITORY/$JAR_NAME 2>&1 &
