#!/bin/bash

verde_hacker='\e[1;32m'
amarillo='\e[33m'
cian='\e[36m'
reset='\e[0m'

<< 'Objetivo-del-script'
    Este script se encarga de compilar y ejecutar tanto el componente servidor-central
    así como el de la estacio-de-trabajo. Además, despliega compila el componente
    servidor-web y lo mueve a la carpeta webapps de tomcat.
Objetivo-del-script

clear

# ========================= Deploy de servidor-central ========================= #

echo -e "${cian}=========== Compilación de servidor-central y despliegue de webservices ===========${reset}"

echo -e "${verde_hacker}\nEntrando a directorio servidor-central/...${reset}"
cd servidor-central/

echo -e "${verde_hacker}\nLimpiando, compilando e instalando paquete en repositorio local...${reset}"
mvn clean install > ../deployment_logs/mvn_info_unparsed.log 2> ../deployment_logs/mvn_error_unparsed.log

echo -e "${verde_hacker}\nLogs generados en ../deployment_logs/${reset}"
# Se parsean los logs generados para evitar símbolos con errores de caracterización
cat ../deployment_logs/mvn_info_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > ../deployment_logs/mvn_info_servidor_central.log
cat ../deployment_logs/mvn_error_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > ../deployment_logs/mvn_error_servidor_central.log

# Se borran los logs sin parsear
rm -rf ../deployment_logs/mvn_info_unparsed.log
rm -rf ../deployment_logs/mvn_error_unparsed.log

echo -e "${verde_hacker}\nEstableciendo permisos a script de carga de datos...${reset}"
chmod +x cargarDatos.sh

echo -e "${verde_hacker}\nEntrando a directorio servidor-central/target/...${reset}"
cd target/

echo -e "${verde_hacker}\nEstableciendo permisos a .jar compilado previamente...${reset}"
chmod +x servidor-central-1.0-SNAPSHOT-jar-with-dependencies.jar

echo -e "${verde_hacker}\nEjecutando WebServicePublisher del servidor-central en background...\n${reset}"
java -jar servidor-central-1.0-SNAPSHOT-jar-with-dependencies.jar &

# Se brinda una pausa para esperar que el proceso se ejecute correctamente
sleep 5

# Se obtiene el PID del proceso corriendo en background
pid=$(lsof -i:8889 | awk 'NR==2 {print $2}')
echo -e "${verde_hacker}\nEl PID del proceso es:${reset} ${amarillo}$pid (Puedes terminarlo con el comando: kill $pid)\n${reset}"

echo -e "${cian}=========== Compilación y ejecución de estacion-de-trabajo ===========${reset}"

# ========================= Ejecución de estacion-de-trabajo ========================= #
echo -e "${verde_hacker}\nEntrando a directorio estacion-de-trabajo/...${reset}"
cd ../../estacion-de-trabajo/

echo -e "${verde_hacker}\nLimpiando, compilando e instalando paquete en repositorio local...${reset}"
mvn clean install > ../deployment_logs/mvn_info_unparsed.log 2> ../deployment_logs/mvn_error_unparsed.log

echo -e "${verde_hacker}\nLogs generados en ../deployment_logs/${reset}"
# Se parsean los logs generados para evitar símbolos con errores de caracterización
cat ../deployment_logs/mvn_info_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > ../deployment_logs/mvn_info_workstation.log
cat ../deployment_logs/mvn_error_unparsed.log | sed -r "s/\x1B\[[0-9;]*[mK]//g" > ../deployment_logs/mvn_error_workstation.log

# Se borran los logs sin parsear
rm -rf ../deployment_logs/mvn_info_unparsed.log
rm -rf ../deployment_logs/mvn_error_unparsed.log

echo -e "${verde_hacker}\nEstableciendo permisos a script de carga de datos...${reset}"
chmod +x cargarDatos.sh

echo -e "${verde_hacker}\nEntrando a directorio estacion-de-trabajo/target/...${reset}"
cd target/

echo -e "${verde_hacker}\nEstableciendo permisos a .jar compilado previamente...${reset}"
chmod +x estacion-de-trabajo-1.0-SNAPSHOT-jar-with-dependencies.jar

echo -e "${verde_hacker}\nEjecutando aplicación de escritorio de estacion-de-trabajo en background...\n${reset}"
java -jar estacion-de-trabajo-1.0-SNAPSHOT-jar-with-dependencies.jar &

# Se brinda una pausa para esperar que el proceso se ejecute correctamente
sleep 5

# Se obtiene el PID del proceso corriendo en background

nombre_proceso="estacion-de-trabajo-1.0-SNAPSHOT-jar-with-dependencies.jar"

# Utiliza pgrep para buscar el PID del proceso
pid2=$(pgrep -f "$nombre_proceso")

echo -e "${verde_hacker}\nEl PID del proceso es:${reset} ${amarillo}$pid2 (Puedes terminarlo con el comando: kill $pid2 o desde la interfaz gráfica)\n${reset}"

