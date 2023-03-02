#!/usr/bin/env bash

TIME_NOW=$(date +%c)
PROJECT_ROOT="/home/ec2-user/app"
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh
source ${ABSDIR}/switch.sh

DEPLOY_LOG="$PROJECT_ROOT/deploy.log"
IDLE_PORT=$(find_idle_port)

echo "$TIME_NOW> Health Check Start!" >> $DEPLOY_LOG
echo "$TIME_NOW> IDLE_PORT: $IDLE_PORT" >> $DEPLOY_LOG
echo "$TIME_NOW> curl -s http://54.180.221.45:$IDLE_PORT/" >> $DEPLOY_LOG
sleep 10

for RETRY_COUNT in {1..10}
do
  RESPONSE=$(curl -s http://54.180.221.45:${IDLE_PORT})
  UP_COUNT=$(echo ${RESPONSE} | grep 'html' | wc -l)

  if [ ${UP_COUNT} -ge 1 ]
  then # $up_count >= 1 ("html" 문자열이 있는지 검증)
      echo "$TIME_NOW> Health check 성공" >> $DEPLOY_LOG
      switch_proxy
      break
  else
      echo "$TIME_NOW> Health check의 응답을 알 수 없거나 혹은 실행 상태가 아닙니다." >> $DEPLOY_LOG
      echo "$TIME_NOW> Health check: ${RESPONSE}" >> $DEPLOY_LOG
  fi

  if [ ${RETRY_COUNT} -eq 10 ]
  then
    echo "$TIME_NOW> Health check 실패. " >> $DEPLOY_LOG
    echo "$TIME_NOW> 엔진엑스에 연결하지 않고 배포를 종료합니다." >> $DEPLOY_LOG
    exit 1
  fi

  echo "$TIME_NOW> Health check 연결 실패. 재시도..." >> $DEPLOY_LOG
  sleep 10
done
