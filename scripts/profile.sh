#!/usr/bin/env bash

TIME_NOW=$(date +%c)
DEPLOY_LOG="/home/ec2-user/app/deploy.log"

function find_idle_profile()
{
    RESPONSE_CODE=$(sudo curl -s -o /dev/null -w "%{http_code}" http://54.180.221.45/)

    if [ ${RESPONSE_CODE} -ge 400 ] # 400 보다 크면 (즉, 40x/50x 에러 모두 포함)
    then
        echo "$TIME_NOW> RESPONSE ERROR" >> $DEPLOY_LOG
    else
        CURRENT_PROFILE=$(sudo curl -s http://54.180.221.45/)
        echo "$TIME_NOW> 현재 프로필 $CURRENT_PROFILE" >> $DEPLOY_LOG
    fi
    

    if [ ${CURRENT_PROFILE} == real1 ]
    then
      IDLE_PROFILE=real2
    else
      IDLE_PROFILE=real1
    fi

    echo "${IDLE_PROFILE}"
}
# 쉬고 있는 profile의 port 찾기
function find_idle_port()
{
    IDLE_PROFILE=$(find_idle_profile)

    if [ ${IDLE_PROFILE} == real1 ]
    then
      echo "8081"
    else
      echo "8082"
    fi
}
