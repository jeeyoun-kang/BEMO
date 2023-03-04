#!/usr/bin/env bash

TIME_NOW=$(date +%c)
DEPLOY_LOG="/home/ec2-user/app/deploy.log"

function find_idle_profile()
{
    RESPONSE_CODE=$(sudo curl -s -o /dev/null -w "%{http_code}" http://43.201.158.62/)

    if [ ${RESPONSE_CODE} -ge 400 ] # 400 보다 크면 (즉, 40x/50x 에러 모두 포함)
    then
        echo "$TIME_NOW> RESPONSE ERROR" >> $DEPLOY_LOG
    else
        CURRENT_PROFILE=$(sudo curl -s http://43.201.158.62/)
        CHECK_USER=$(echo ${CURRENT_PROFILE} | grep 'real1' | wc -l)
        echo "$TIME_NOW> 현재 프로필 $CURRENT_PROFILE" >> $DEPLOY_LOG
    fi
    

    if [ ${CHECK_USER} -ge 1 ]
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
