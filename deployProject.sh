#!/bin/bash

<< 'Objetivo-del-script'
    Este script se encarga de compilar y ejecutar tanto el componente servidor-central
    así como el de la estacion-de-trabajo. Además, compila el componente
    servidor-web y deja una copia del .war en la carpeta webapps de tomcat.
Objetivo-del-script

TITULO="
     _____           _                     _   ___   __
    |_   _|   _ _ __(_)___ _ __ ___   ___ | | | \ \ / /
      | || | | | '__| / __| '_ \` _ \ / _ \| | | |\ V /
      | || |_| | |  | \__ \ | | | | | (_) | |_| | | |  
      |_| \__,_|_|  |_|___/_| |_| |_|\___/ \___/  |_|  
"

# Colores
ROJO='\e[31m'
VERDE_HACKER='\e[1;32m'
AMARILLO='\e[33m'
CIAN='\e[36m'
RESET='\e[0m'
BOLD='\033[1m' # Negrita
COLOR_FLICKER='\033[5m' # Letras parpadeantes

# Ruta base del proyecto
BASE_DIR=$(pwd)

# Ruta logs
LOGS_DIR=$BASE_DIR/deployment_logs

# Rutas a los diferentes componentes del proyecto
CENTRAL_SERVER_DIR=$BASE_DIR/servidor-central
WORKSTATION_DIR=$BASE_DIR/estacion-de-trabajo
WEB_SERVER_DIR=$BASE_DIR/servidor-web

# Nombre de los paquetes compilados
CENTRAL_SERVER_JAR_NAME='servidor-central-1.0-SNAPSHOT-jar-with-dependencies.jar'
WORKSTATION_JAR_NAME='estacion-de-trabajo-1.0-SNAPSHOT-jar-with-dependencies.jar'
WEB_SERVER_WAR_NAME='servidor-web-1.0-SNAPSHOT.war'

# Archivo de configuración, del cual se cargan propiedades tanto en este script
# de desploy, como también en el propio proyecto.
CONFIG_FILE=$CENTRAL_SERVER_DIR/src/main/resources/config.properties

# Rutas de tomcat
CATALINA_PATH=$(grep "CATALINA_PATH" "$CONFIG_FILE" | awk -F '=' '{print $2}' | tr -d '[:space:]')
TOMCAT_WEBAPPS_DIR=$(grep "TOMCAT_WEBAPPS_DIR" "$CONFIG_FILE" | awk -F '=' '{print $2}' | tr -d '[:space:]')

# Datos de acceso al host remoto
IP_REMOTE_HOST=$(grep "IP_REMOTE_HOST" "$CONFIG_FILE" | awk -F '=' '{print $2}' | tr -d '[:space:]')
REMOTE_USER=$(grep "REMOTE_USER" "$CONFIG_FILE" | awk -F '=' '{print $2}' | tr -d '[:space:]')
DESTINATION_PATH=$(grep "DESTINATION_PATH" "$CONFIG_FILE" | awk -F '=' '{print $2}' | tr -d '[:space:]')

# Puerto donde corren los WebServices
WEB_SERVICES_HOST=$(grep "WEB_SERVICES_HOST" "$CONFIG_FILE" | awk -F '=' '{print $2}' | tr -d '[:space:]')
WEB_SERVICES_PORT=$(grep "WEB_SERVICES_PORT" "$CONFIG_FILE" | awk -F '=' '{print $2}' | tr -d '[:space:]')

# ==== Config para recibir argumentos ==== #
OPTIONS=hv123
LONGOPTS=help,vars,onlyWorkstation,onlyCentralServer,onlyWebServer

PARSED=$(getopt --options=$OPTIONS --longoptions=$LONGOPTS --name "$0" -- "$@")
eval set -- "$PARSED"

VIEW_USER_GUIDE=false
VIEW_VARS=false
RUN_WORKSTATION=false
RUN_CENTRALSERVER=false
RUN_WEBSERVER=false

while true; do
  case "$1" in
    -h | --help ) VIEW_USER_GUIDE=true; shift;;
	-v | --vars ) VIEW_VARS=true; shift;;
    -1 | --onlyCentralServer ) RUN_CENTRALSERVER=true; shift ;;
    -2 | --onlyWorkstation ) RUN_WORKSTATION=true; shift ;;
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

viewUserGuide(){
    echo -e "${VERDE_HACKER}Guía de script para despliegue automatizado de proyecto:"
    echo -e "${BOLD}${COLOR_FLICKER}${TITULO}${RESET}"

    echo -e "${VERDE_HACKER}Uso: ${RESET}${CIAN}./deployProject.sh [OPCIÓN]${RESET}\n"
    echo -e "${VERDE_HACKER}Ejemplo: ${RESET}${CIAN}./deployProject.sh --onlyCentralServer${RESET}\n"
    
    echo -e "${VERDE_HACKER}Opciones:${RESET}"
    echo -e "${VERDE_HACKER}  -h, --help                      ${RESET}${CIAN}Muestra este menú${RESET}"
    echo -e "${VERDE_HACKER}  -v, --vars                      ${RESET}${CIAN}Muestra variables de entorno utilizadas en script${RESET}"
    echo -e "${VERDE_HACKER}  -1, --onlyCentralServer         ${RESET}${CIAN}Compila y despliega los WebServices${RESET}"
    echo -e "${VERDE_HACKER}  -2, --onlyWorkstation           ${RESET}${CIAN}Compila y ejecuta la aplicación de escritorio${RESET}"
    echo -e "${VERDE_HACKER}  -3, --onlyWebServer             ${RESET}${CIAN}Compila y despliega la aplicación web dentro del Tomcat${RESET}"       
}

viewVars(){
    echo -e "${CIAN}======== Variables de entorno ========${RESET}\n"

    echo -e "${CIAN}Directorios: ${RESET}"
    echo -e "${VERDE_HACKER}  BASE_DIR: ${RESET}${CIAN}${BASE_DIR}${RESET}"
    echo -e "${VERDE_HACKER}  LOGS_DIR: ${RESET}${CIAN}${LOGS_DIR}${RESET}"
    echo -e "${VERDE_HACKER}  CENTRAL_SERVER_DIR: ${RESET}${CIAN}${CENTRAL_SERVER_DIR}${RESET}"
    echo -e "${VERDE_HACKER}  WORKSTATION_DIR: ${RESET}${CIAN}${WORKSTATION_DIR}${RESET}"
    echo -e "${VERDE_HACKER}  WEB_SERVER_DIR: ${RESET}${CIAN}${WEB_SERVER_DIR}${RESET}"

    echo -e "${CIAN}\nPaquetes: ${RESET}"
    echo -e "${VERDE_HACKER}  CENTRAL_SERVER_JAR_NAME: ${RESET}${CIAN}${CENTRAL_SERVER_JAR_NAME}${RESET}"
    echo -e "${VERDE_HACKER}  WORKSTATION_JAR_NAME: ${RESET}${CIAN}${WORKSTATION_JAR_NAME}${RESET}"
    echo -e "${VERDE_HACKER}  WEB_SERVER_WAR_NAME: ${RESET}${CIAN}${WEB_SERVER_WAR_NAME}${RESET}"
    
    echo -e "${CIAN}\nFichero de propiedades: ${RESET}"
    echo -e "${VERDE_HACKER}  CONFIG_FILE: ${RESET}${CIAN}${CONFIG_FILE}${RESET}"

    echo -e "${CIAN}\nRutas Tomcat: ${RESET}"
    echo -e "${VERDE_HACKER}  CATALINA_PATH: ${RESET}${CIAN}${CATALINA_PATH}${RESET}"
    echo -e "${VERDE_HACKER}  TOMCAT_WEBAPPS_DIR: ${RESET}${CIAN}${TOMCAT_WEBAPPS_DIR}${RESET}"

    echo -e "${CIAN}\nWebServices: ${RESET}"
    echo -e "${VERDE_HACKER}  WEB_SERVICES_HOST: ${RESET}${CIAN}${WEB_SERVICES_HOST}${RESET}"
    echo -e "${VERDE_HACKER}  WEB_SERVICES_PORT: ${RESET}${CIAN}${WEB_SERVICES_PORT}${RESET}"

    echo -e "${CIAN}\nHost remoto: ${RESET}" 
    echo -e "${VERDE_HACKER}  IP_REMOTE_HOST: ${RESET}${CIAN}${IP_REMOTE_HOST}${RESET}"
    echo -e "${VERDE_HACKER}  REMOTE_USER: ${RESET}${CIAN}${REMOTE_USER}${RESET}"
    echo -e "${VERDE_HACKER}  DESTINATION_PATH: ${RESET}${CIAN}${DESTINATION_PATH}${RESET}\n"
}

deployCentralServer() {
    chmod +x killWebServices.sh
    echo -e "${CIAN}=========== Compilación de servidor-central y despliegue de webservices ===========${RESET}"

    echo -e "${VERDE_HACKER}\nEntrando a directorio $CENTRAL_SERVER_DIR...${RESET}"
    cd $CENTRAL_SERVER_DIR

    echo -e "${VERDE_HACKER}\nEstableciendo permisos a script de carga de datos...${RESET}"
    chmod +x cargarDatos.sh

    echo -e "${VERDE_HACKER}\nCargando datos de prueba...${RESET}"
    echo -e "${AMARILLO}"
    ./cargarDatos.sh
    echo -e "${RESET}"

    echo -e "${VERDE_HACKER}\nLimpiando, compilando e instalando paquete en repositorio local...${RESET}"
    mvn clean install > $LOGS_DIR/mvn_info_unparsed.log 2> $LOGS_DIR/mvn_error_unparsed.log

    echo -e "${VERDE_HACKER}\nLogs generados en $LOGS_DIR/${RESET}"
    # Se parsean los logs generados para evitar símbolos con errores de caracterización
    cat $LOGS_DIR/mvn_info_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > $LOGS_DIR/mvn_info_central_server.log
    cat $LOGS_DIR/mvn_error_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > $LOGS_DIR/mvn_error_central_server.log

    # Se borran los logs sin parsear
    rm -rf $LOGS_DIR/mvn_info_unparsed.log
    rm -rf $LOGS_DIR/mvn_error_unparsed.log

    echo -e "${VERDE_HACKER}\nEntrando a directorio $CENTRAL_SERVER_DIR/target/...${RESET}"
    cd $CENTRAL_SERVER_DIR/target/

    echo -e "${VERDE_HACKER}\nEstableciendo permisos a .jar compilado previamente...${RESET}"
    chmod +x $CENTRAL_SERVER_DIR/target/$CENTRAL_SERVER_JAR_NAME

    # Se comprueba si el puerto utilizado por los WebServices ya está en uso
    if lsof -i:$WEB_SERVICES_PORT &>/dev/null; then
        pid=$(lsof -ti :$WEB_SERVICES_PORT)
        echo -e "${ROJO}\n¡Error! El puerto $WEB_SERVICES_PORT ya está en uso con el PID: ${RESET} ${CIAN}$pid${RESET}\n"
        exit 1
    else
        echo -e "${VERDE_HACKER}\nEjecutando WebServicePublisher del servidor-central en segundo plano...\n${RESET}"
        java -jar $CENTRAL_SERVER_DIR/target/$CENTRAL_SERVER_JAR_NAME & > $LOGS_DIR/central_server_info.log 2> $LOGS_DIR/central_server_error.log

        # Brinda una pausa para esperar que el proceso se ejecute correctamente
        sleep 5

        # Obtiene el PID del proceso corriendo en background
        pid=$(lsof -ti :$WEB_SERVICES_PORT)
        echo -e "${VERDE_HACKER}\nEl PID del proceso es:${RESET} ${AMARILLO}$pid (Puedes terminarlo con el comando: ./killWebServices.sh)\n${RESET}"
    fi
}

deployWorkstation() {
    echo -e "${CIAN}=========== Compilación y ejecución de estacion-de-trabajo ===========${RESET}"

    echo -e "${VERDE_HACKER}\nEntrando a directorio $WORKSTATION_DIR...${RESET}"
    cd $WORKSTATION_DIR

    echo -e "${VERDE_HACKER}\nEstableciendo permisos a script de carga de datos...${RESET}"
    chmod +x cargarDatos.sh

    echo -e "${VERDE_HACKER}\nLimpiando, compilando e instalando paquete en repositorio local...${RESET}"
    mvn clean install > $LOGS_DIR/mvn_info_unparsed.log 2> $LOGS_DIR/mvn_error_unparsed.log

    echo -e "${VERDE_HACKER}\nLogs generados en $LOGS_DIR/${RESET}"
    # Se parsean los logs generados para evitar símbolos con errores de caracterización
    cat $LOGS_DIR/mvn_info_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > $LOGS_DIR/mvn_info_workstation.log
    cat $LOGS_DIR/mvn_error_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > $LOGS_DIR/mvn_error_workstation.log

    # Se borran los logs sin parsear
    rm -rf $LOGS_DIR/mvn_info_unparsed.log
    rm -rf $LOGS_DIR/mvn_error_unparsed.log

    echo -e "${VERDE_HACKER}\nEntrando a directorio $WORKSTATION_DIR/target/...${RESET}"
    cd target/

    echo -e "${VERDE_HACKER}\nEstableciendo permisos a .jar compilado previamente...${RESET}"
    chmod +x $WORKSTATION_DIR/target/$WORKSTATION_JAR_NAME

    echo -e "${VERDE_HACKER}\nEjecutando aplicación de escritorio de estacion-de-trabajo en segundo plano...\n${RESET}"
    java -jar $WORKSTATION_DIR/target/$WORKSTATION_JAR_NAME &  > $LOGS_DIR/workstation_info.log 2> $LOGS_DIR/workstation_error.log

    # Se brinda una pausa para esperar que el proceso se ejecute correctamente
    sleep 5
    
    # Se obtiene el PID del proceso corriendo en background
    pid=$(pgrep -f "$WORKSTATION_JAR_NAME")

    echo -e "${VERDE_HACKER}\nEl PID del proceso es:${RESET} ${AMARILLO}$pid (Puedes terminarlo con el comando: kill $pid o desde la interfaz gráfica)\n${RESET}" 
}

deployWebServer() {
    echo -e "${CIAN}=========== Compilación de servidor-web y despliegue en Tomcat ===========${RESET}"

    # Se obtiene el PID del proceso en el puerto 8889
    pid=$(lsof -ti :$WEB_SERVICES_PORT)
    
    if [ -n "$pid" ]; then
        # Obtener el nombre del proceso a partir del PID
        nombre_proceso_pid=$(ps -p $pid -o cmd=)
        # Verificar si el nombre del proceso coincide
        if [[ "$nombre_proceso_pid" == *"$CENTRAL_SERVER_JAR_NAME"* ]]; then
            # Si los WebServices están activos y corriendo en el puerto 8889 se compila y despliega el servidor-web
            echo -e "${VERDE_HACKER}\nEntrando a directorio $WEB_SERVER_DIR...${RESET}"
            cd $WEB_SERVER_DIR
            
            echo -e "${VERDE_HACKER}\nLimpiando, compilando e instalando paquete en repositorio local...${RESET}" 
            mvn clean install -Dip-host=${WEB_SERVICES_HOST} -Dport=${WEB_SERVICES_PORT} > $LOGS_DIR/mvn_info_unparsed.log 2> $LOGS_DIR/mvn_error_unparsed.log
        
            echo -e "${VERDE_HACKER}\nLogs generados en $LOGS_DIR/${RESET}"
            # Se parsean los logs generados para evitar símbolos con errores de caracterización
            cat $LOGS_DIR/mvn_info_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > $LOGS_DIR/mvn_info_web_server.log
            cat $LOGS_DIR/mvn_error_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > $LOGS_DIR/mvn_error_web_server.log
        
            # Se borran los logs sin parsear
            rm -rf $LOGS_DIR/mvn_info_unparsed.log
            rm -rf $LOGS_DIR/mvn_error_unparsed.log
        
            echo -e "${VERDE_HACKER}\nEntrando a directorio $WEB_SERVER_DIR/target/...${RESET}"
            cd $WEB_SERVER_DIR/target/

            # En caso de que el host sea local, se mueve el .war al directorio de tomcat de la misma máquina en la
            # que corre el script. En caso contrario se envía al host remoto.
            if [ "$IP_REMOTE_HOST" == "localhost" ]; then
                echo -e "${VERDE_HACKER}\nDesplegando .war en $TOMCAT_WEBAPPS_DIR...\n${RESET}"
                cp $WEB_SERVER_DIR/target/$WEB_SERVER_WAR_NAME $TOMCAT_WEBAPPS_DIR

                echo -e "${VERDE_HACKER}\nIniciando WebServer Tomcat...${RESET}"    
                cd $CATALINA_PATH
                echo -e "${AMARILLO}"
                ./startup.sh
                echo -e "${RESET}"

                echo -e "${VERDE_HACKER}\nPara terminar la ejecución de tomcat es posible usar el siguiente comando:${RESET} ${CIAN}$CATALINA_PATH/shutdown.sh${RESET}"
            else
                echo -e "${VERDE_HACKER}\nEnviando .war a directorio $DESTINATION_PATH del host remoto...\n${RESET}"

                # Se construye el comando scp
                # Es importante que el puerto 22 esté habilitado en el host remoto: sudo ufw allow 22.
                # También es importante que se haya generado la clave privada en el host origen
                comando_scp="scp -q -P 22 -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null $WEB_SERVER_DIR/target/$WEB_SERVER_WAR_NAME $REMOTE_USER@$IP_REMOTE_HOST:$DESTINATION_PATH"

                # Ejecuta el comando scp
                eval "$comando_scp"
            fi          
        else
            echo -e "${ROJO}\n¡Error! No es posible compilar el servidor web. Parece ser que el servicio que corre en el puerto $PUERTO no son los WebServices.${RESET}\n"
        fi
    else
        echo -e "${ROJO}\n¡Error! Debes ejecutar los WebServices del servidor-central antes de compilar el servidor-web.${RESET}\n"
    fi
}

# ================================ Ejecución del script ================================ #

# Argumento = -h / --help
if [ "$VIEW_USER_GUIDE" = true ]; then
    clear
    viewUserGuide
fi

# Argumento = -v / --vars
if [ "$VIEW_VARS" = true ]; then
    clear
    viewVars
fi

# Argumento = -1 / --onlyCentralServer
if [ "$RUN_CENTRALSERVER" = true ]; then
    clear
    createLogFolder
    deployCentralServer
fi

# Argumento = -2 / --onlyWorkstation
if [ "$RUN_WORKSTATION" = true ]; then
    clear
    createLogFolder
    deployWorkstation
fi

# Argumento = -3 / --onlyWebServer
if [ "$RUN_WEBSERVER" = true ]; then
    clear
    createLogFolder
    deployWebServer
fi

# Si no se indicó nada, ejecutar todos
if [ "$VIEW_USER_GUIDE" = false ] && [ "$VIEW_VARS" = false ] && [ "$RUN_WORKSTATION" = false ] && [ "$RUN_CENTRALSERVER" = false ] && [ "$RUN_WEBSERVER" = false ]; then
    clear
    createLogFolder
    deployCentralServer
    deployWorkstation
    deployWebServer
fi
