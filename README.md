#Instalacion
Esta configurado para que ecuche en el puerto 9002



# Crear imagen docker
 
```shell
docker build -t payara-hello .

```

# sftalert
sistema de accreditaciones



#Execute docker compose
docker-compose up -d

java -jar -Xmx512m target/sftalert.jar 



Para ejecutar uberjar
java -jar payara-micro-6.2024.5.jar --deploy sftalert.war --outputUberJar sftalert.jar


#Ejecutar el war


java -jar /home/avbravo/software/payara/payara-micro-6.2024.5.jar  --deploy /home/avbravo/NetBeansProjects/u/utp/sftalert/target/sftalert.war --noHazelcast --logo --port 9080


#Crear  el Uberjar
java -jar   /home/avbravo/software/payara/payara-micro-6.2024.5.jar --deploy /home/avbravo/NetBeansProjects/u/utp/sftalert/sftalert/target/sftalert.war --outputUberJar /home/avbravo/Descargas/sftalert.jar 



# Ejecutar JAR

Use --noHazelcast en lugar de --nocluster
 
 java -jar -Xmx512m sftalert.jar --noHazelcast --logo --port 9080 >>log.txt


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

java -jar sftalert-bundle.jar
````

#@ViewEntity
Utiliza vistas para mejorar el rendimiento de muchas operaciones



## _Migracion

TarjetaRepositoryImpl.
   (*)save()

TarjetaSupplier. IconoSupplier, TipoTarjetaSupplier, TareaSupplier, ComentarioSupplier, EtiquetaSupplier, ImpedimentoSupplier

    (*)toDocument
    (*)toDocument
    
    1246
    
    1247
    
    1248
    
    ## Tarjetas
    Agregar a la 1291 y 1292
    1291 y 1292
    
      "tipotarjeta" : {
    "idtipotarjeta" : NumberLong(12)
  },
