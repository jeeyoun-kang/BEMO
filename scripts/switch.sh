#!/usr/bin/env bash

PROJECT_ROOT="/home/ec2-user/app"
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 Port: $IDLE_PORT" >> $DEPLOY_LOG
    echo "> Port 전환" >> $DEPLOY_LOG
    echo "set \$service_url http://54.180.221.45:${IDLE_PORT};" >> $DEPLOY_LOG | sudo tee /etc/nginx/conf.d/service-url.inc

    sudo docker exec -d nginx nginx -s reload
    echo "> docker exec -it nginx nginx -s reload" >> $DEPLOY_LOG
}