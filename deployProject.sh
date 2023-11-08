#!/bin/bash

<< 'Objetivo-del-script'
    Este script se encarga de compilar y ejecutar tanto el componente servidor-central
    así como el de la estacio-de-trabajo. Además, compila el componente
    servidor-web y deja una copia del .war en la carpeta webapps de tomcat.
Objetivo-del-script

# Colores
ROJO='\e[31m'
VERDE_HACKER='\e[1;32m'
AMARILLO='\e[33m'
CIAN='\e[36m'
RESET='\e[0m'

# Ruta base del proyecto
BASE_DIR=$(pwd)

# Ruta logs
LOGS_DIR=$BASE_DIR/deployment_logs

# Rutas a los diferentes componentes del proyecto
CENTRAL_SERVER_DIR=$BASE_DIR/servidor-central
WORKSTATION_DIR=$BASE_DIR/estacion-de-trabajo
WEB_SERVER_DIR=$BASE_DIR/servidor-web

# Rutas de tomcat
CATALINA_PATH=/opt/tomcat/bin
TOMCAT_WEBAPPS=/opt/tomcat/webapps


# ==== Config para recibir argumentos ==== #
OPTIONS=123
LONGOPTS=onlyWorkstation,onlyCentralServer,onlyWebServer

PARSED=$(getopt --options=$OPTIONS --longoptions=$LONGOPTS --name "$0" -- "$@")
eval set -- "$PARSED"

RUN_WORKSTATION=false
RUN_CENTRALSERVER=false
RUN_WEBSERVER=false

while true; do
  case "$1" in
    -1 | --onlyWorkstation ) RUN_WORKSTATION=true; shift ;;
    -2 | --onlyCentralServer ) RUN_CENTRALSERVER=true; shift ;;
    -3 | --onlyWebServer ) RUN_WEBSERVER=true; shift ;;
     -- ) shift; break ;;
    * ) break ;;
  esac
done
# ==== Config para recibir argumentos ==== #

# Verifica si no está creada la carpeta de los logs, y en caso afirmativo, la crea
createLogFolder(){
  if [ ! -d "$LOGS_DIR" ]; then
    echo -e "${CIAN}Directorio de logs no encontrado."
    echo -e "Creando /deployment_logs...\n${RESET}"
    mkdir $LOGS_DIR
  fi
}

deployCentralServer() {
    echo -e "${CIAN}=========== Compilación de servidor-central y despliegue de webservices ===========${RESET}"

    echo -e "${VERDE_HACKER}\nEntrando a directorio $CENTRAL_SERVER_DIR...${RESET}"
    cd $CENTRAL_SERVER_DIR

    echo -e "${VERDE_HACKER}\nLimpiando, compilando e instalando paquete en repositorio local...${RESET}"
    mvn clean install > $LOGS_DIR/mvn_info_unparsed.log 2> $LOGS_DIR/mvn_error_unparsed.log

    echo -e "${VERDE_HACKER}\nLogs generados en $LOGS_DIR/${RESET}"
    # Se parsean los logs generados para evitar símbolos con errores de caracterización
    cat $LOGS_DIR/mvn_info_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > $LOGS_DIR/mvn_info_central_server.log
    cat $LOGS_DIR/mvn_error_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > $LOGS_DIR/mvn_error_central_server.log

    # Se borran los logs sin parsear
    rm -rf $LOGS_DIR/mvn_info_unparsed.log
    rm -rf $LOGS_DIR/mvn_error_unparsed.log

    echo -e "${VERDE_HACKER}\nEstableciendo permisos a script de carga de datos...${RESET}"
    chmod +x cargarDatos.sh

    echo -e "${VERDE_HACKER}\nEntrando a directorio $CENTRAL_SERVER_DIR/target/...${RESET}"
    cd $CENTRAL_SERVER_DIR/target/

    echo -e "${VERDE_HACKER}\nEstableciendo permisos a .jar compilado previamente...${RESET}"
    chmod +x $CENTRAL_SERVER_DIR/target/servidor-central-1.0-SNAPSHOT-jar-with-dependencies.jar

    # Se comprueba si el puerto 8889 ya está en uso
    if lsof -i:8889 &>/dev/null; then
        pid=$(lsof -i:8889 | awk 'NR==2 {print $2}')
        echo -e "${ROJO}\n¡Error! El puerto 8889 ya está en uso con el PID: ${RESET} ${CIAN}$pid${RESET}\n"
        exit 1
    else
        echo -e "${VERDE_HACKER}\nEjecutando WebServicePublisher del servidor-central en segundo plano...\n${RESET}"
        java -jar $CENTRAL_SERVER_DIR/target/servidor-central-1.0-SNAPSHOT-jar-with-dependencies.jar & > $LOGS_DIR/central_server_info.log 2> $LOGS_DIR/central_server_error.log

        # Brinda una pausa para esperar que el proceso se ejecute correctamente
        sleep 5

        # Obtiene el PID del proceso corriendo en background
        pid=$(lsof -i:8889 | awk 'NR==2 {print $2}')
        echo -e "${VERDE_HACKER}\nEl PID del proceso es:${RESET} ${AMARILLO}$pid (Puedes terminarlo con el comando: kill $pid)\n${RESET}"
    fi
}

deployWorkstation() {
    echo -e "${CIAN}=========== Compilación y ejecución de estacion-de-trabajo ===========${RESET}"

    echo -e "${VERDE_HACKER}\nEntrando a directorio $WORKSTATION_DIR...${RESET}"
    cd $WORKSTATION_DIR

    echo -e "${VERDE_HACKER}\nLimpiando, compilando e instalando paquete en repositorio local...${RESET}"
    mvn clean install > $LOGS_DIR/mvn_info_unparsed.log 2> $LOGS_DIR/mvn_error_unparsed.log

    echo -e "${VERDE_HACKER}\nLogs generados en $LOGS_DIR/${RESET}"
    # Se parsean los logs generados para evitar símbolos con errores de caracterización
    cat $LOGS_DIR/mvn_info_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > $LOGS_DIR/mvn_info_workstation.log
    cat $LOGS_DIR/mvn_error_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > $LOGS_DIR/mvn_error_workstation.log

    # Se borran los logs sin parsear
    rm -rf $LOGS_DIR/mvn_info_unparsed.log
    rm -rf $LOGS_DIR/mvn_error_unparsed.log

    echo -e "${VERDE_HACKER}\nEstableciendo permisos a script de carga de datos...${RESET}"
    chmod +x cargarDatos.sh

    echo -e "${VERDE_HACKER}\nEntrando a directorio $WORKSTATION_DIR/target/...${RESET}"
    cd target/

    echo -e "${VERDE_HACKER}\nEstableciendo permisos a .jar compilado previamente...${RESET}"
    chmod +x $WORKSTATION_DIR/target/estacion-de-trabajo-1.0-SNAPSHOT-jar-with-dependencies.jar

    echo -e "${VERDE_HACKER}\nEjecutando aplicación de escritorio de estacion-de-trabajo en segundo plano...\n${RESET}"
    java -jar $WORKSTATION_DIR/target/estacion-de-trabajo-1.0-SNAPSHOT-jar-with-dependencies.jar &  > $LOGS_DIR/workstation_info.log 2> $LOGS_DIR/workstation_error.log

    # Se brinda una pausa para esperar que el proceso se ejecute correctamente
    sleep 5

    # Se obtiene el PID del proceso corriendo en background

    nombre_proceso="estacion-de-trabajo-1.0-SNAPSHOT-jar-with-dependencies.jar"

    # Utiliza pgrep para buscar el PID del proceso
    pid2=$(pgrep -f "$nombre_proceso")

    echo -e "${VERDE_HACKER}\nEl PID del proceso es:${RESET} ${AMARILLO}$pid2 (Puedes terminarlo con el comando: kill $pid2 o desde la interfaz gráfica)\n${RESET}" 
}

deployWebServer() {
    echo -e "${CIAN}=========== Compilación de servidor-web y despliegue en Tomcat ===========${RESET}"

    echo -e "${VERDE_HACKER}\nEntrando a directorio $WEB_SERVER_DIR...${RESET}"
    cd $WEB_SERVER_DIR

    echo -e "${VERDE_HACKER}\nLimpiando, compilando e instalando paquete en repositorio local...${RESET}"
    mvn clean install > $LOGS_DIR/mvn_info_unparsed.log 2> $LOGS_DIR/mvn_error_unparsed.log

    echo -e "${VERDE_HACKER}\nLogs generados en $LOGS_DIR/${RESET}"
    # Se parsean los logs generados para evitar símbolos con errores de caracterización
    cat $LOGS_DIR/mvn_info_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > $LOGS_DIR/mvn_info_web_server.log
    cat $LOGS_DIR/mvn_error_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > $LOGS_DIR/mvn_error_web_server.log

    # Se borran los logs sin parsear
    rm -rf $LOGS_DIR/mvn_info_unparsed.log
    rm -rf $LOGS_DIR/mvn_error_unparsed.log

    echo -e "${VERDE_HACKER}\nEntrando a directorio $WEB_SERVER_DIR/target/...${RESET}"
    cd $WEB_SERVER_DIR/target/

    echo -e "${VERDE_HACKER}\nDesplegando .war en $TOMCAT_WEBAPPS...\n${RESET}"
    cp $WEB_SERVER_DIR/target/servidor-web-1.0-SNAPSHOT.war $TOMCAT_WEBAPPS

    echo -e "${VERDE_HACKER}\nIniciando WebServer Tomcat...${RESET}"
    cd $CATALINA_PATH
    ./startup.sh

    echo -e "${VERDE_HACKER}\nPara terminar la ejecución de tomcat es posible usar el siguiente comando:"
    echo -e "${RESET} ${CIAN}$CATALINA_PATH/shutdown.sh${RESET}"
}

# Argumento = -1 / --onlyWorkstation
if [ "$RUN_WORKSTATION" = true ]; then
    clear
    createLogFolder
    deployWorkstation
fi

# Argumento = -2 / --onlyCentralServer
if [ "$RUN_CENTRALSERVER" = true ]; then
    clear
    createLogFolder
    deployCentralServer
fi

# Argumento = -3 / --onlyWebServer
if [ "$RUN_WEBSERVER" = true ]; then
    clear
    createLogFolder
    deployWebServer
fi

# Si no se indicó nada, ejecutar todos
if [ "$RUN_WORKSTATION" = false ] && [ "$RUN_CENTRALSERVER" = false ] && [ "$RUN_WEBSERVER" = false ]; then
    clear
    createLogFolder
    deployCentralServer
    deployWorkstation
    deployWebServer
fi