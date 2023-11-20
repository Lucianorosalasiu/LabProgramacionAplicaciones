#!/bin/bash

# Colores
ROJO='\e[31m'
AMARILLO='\e[33m'
RESET='\e[0m'

BASE_DIR=$(pwd)
CENTRAL_SERVER_DIR=$BASE_DIR/servidor-central
CONFIG_FILE=$CENTRAL_SERVER_DIR/src/main/resources/config.properties

WEB_SERVICES_PORT=$(grep "WEB_SERVICES_PORT" "$CONFIG_FILE" | awk -F '=' '{print $2}' | tr -d '[:space:]')

# Se comprueba si el puerto utilizado por los WebServices está en uso
if lsof -i:$WEB_SERVICES_PORT &>/dev/null; then
    #Se obtiene el PID del proceso que corre en dicho puerto
    pidWS=$(lsof -ti :$WEB_SERVICES_PORT)
    kill $pidWS
    
    echo -e "\n${AMARILLO}¡Se ha terminado la ejecución de los WebServices correctamente!${RESET}\n"
    exit 1
else
    echo -e "\n${ROJO}¡Error! No hay ningún servicio escuchando en el puerto $WEB_SERVICES_PORT ${RESET}\n"
fi
