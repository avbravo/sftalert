#Instalacion
Esta configurado para que ecuche en el puerto 9002



# Crear imagen docker
 
```shell
docker build -t payara-hello .

```

# accreditation
sistema de accreditaciones



#Execute docker compose
docker-compose up -d

java -jar -Xmx512m target/accreditation.jar 



Para ejecutar uberjar
java -jar payara-micro-6.2024.2.jar --deploy accreditation.war --outputUberJar accreditation.jar


#Ejecutar el war


java -jar /home/avbravo/software/payara/c.jar  --deploy /home/avbravo/NetBeansProjects/u/utp/accreditation-stack/master/accreditation/target/accreditation.war --noHazelcast --logo --port 9002


#Crear  el Uberjar
java -jar   /home/avbravo/software/payara/payara-micro-6.2024.2.jar --deploy /home/avbravo/NetBeansProjects/u/utp/accreditation/accreditation/target/accreditation.war --outputUberJar /home/avbravo/Descargas/accreditation.jar 



# Ejecutar JAR

Use --noHazelcast en lugar de --nocluster
 
 java -jar -Xmx512m accreditation.jar --noHazelcast --logo --port 9002 >>log.txt


## Mediante maven

Crear el war
```shell

mvn clean verify

cd target

````


Ejecutar 

```shell

mvn clean verify payara-micro:start

cd target

````

## Crear el uber jar

De esta manera el url solo tendria el ip , util para microservicios

```shell

mvn clean verify payara-micro:bundle

cd target

java -jar accreditation-bundle.jar
````

#@ViewEntity
Utiliza vistas para mejorar el rendimiento de muchas operaciones