#!/usr/bin/env bash

REPOSITORY=/home/ec2-user/app
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

#echo "$TIME_NOW > 새 어플리케이션 배포" >> $DEPLOY_LOG
#JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "$TIME_NOW > JAR Name: $JAR_FILE" >> $DEPLOY_LOG
echo "$TIME_NOW > $JAR_FILE 실행" >> $DEPLOY_LOG

IDLE_PROFILE=$(find_idle_profile)

echo "$TIME_NOW > $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다." >> $DEPLOY_LOG
nohup java -jar -Dspring.profiles.active=$IDLE_PROFILE $JAR_FILE > $APP_LOG 2> $ERROR_LOG &

# nohup java -jar -Dspring.profiles.active=$IDLE_PROFILE $JAR_NAME > $APP_LOG 2>&1 &
