## update all user
```
mongo
use accreditation
db.getCollection('user').update({},{$set: {"email" : "avbravo@gmail.com"}},{multi: true })
db.getCollection('user').update({},{$set: {"password" : "XXdCn5xsqdWve/34pCP1lw=="}},{multi: true })


```


---
## House

### Docker

- Ver las imagenes
```
docker ps -a 
```

- House

```
docker exec -it be67313bb936  bash

```

## Subir script

```shell
docker cp /home/avbravo/Descargas/backup.sh be67313bb936:/home/avbravo/backup.sh
docker cp /home/avbravo/Descargas/restoredb.sh be67313bb936:/home/avbravo/restoredb.sh

```




## Backup MongoDB

Se puede hacer mediante el script backup.sh

o
mediante

```

cd /home/avbravo

mongodump --archive=accreditation.gz --gzip --db=accreditation

mongodump --archive=configurationjmoordbdb.gz --gzip --db=configurationjmoordbdb

mongodump --archive=sft.gz --gzip --db=sft


mongodump --archive=historydb.gz --gzip --db=historydb


mongodump --archive=sfthistory.gz --gzip --db=sfthistory

mongodump --archive=accreditationhistory.gz --gzip --db=accreditationhistory


```

### Copiarlos desde Docker al disco

-House
```
docker cp be67313bb936:/home/avbravo/accreditation.gz  /home/avbravo/Descargas/accreditation.gz

docker cp be67313bb936:/home/avbravo/configurationjmoordbdb.gz  /home/avbravo/Descargas/configurationjmoordbdb.gz

docker cp be67313bb936:/home/avbravo/sft.gz  /home/avbravo/Descargas/sft.gz

docker cp be67313bb936:/home/avbravo/historydb.gz  /home/avbravo/Descargas/historydb.gz

docker cp be67313bb936:/home/avbravo/sfthistory.gz  /home/avbravo/Descargas/sfthistory.gz

docker cp be67313bb936:/home/avbravo/accreditationhistory.gz  /home/avbravo/Descargas/accreditationhistory.gz

```
## Restauración

---
### Copiar desde Disco a Docker
```
docker cp /home/avbravo/Descargas/accreditation.gz be67313bb936:/home/avbravo/accreditation.gz

docker cp /home/avbravo/Descargas/configurationjmoordbdb.gz be67313bb936:/home/avbravo/configurationjmoordbdb.gz
  
docker cp /home/avbravo/Descargas/sft.gz be67313bb936:/home/avbravo/sft.gz

docker cp /home/avbravo/Descargas/historydb.gz be67313bb936:/home/avbravo/historydb.gz

docker cp /home/avbravo/Descargas/sfthistory.gz be67313bb936:/home/avbravo/sfthistory.gz

docker cp /home/avbravo/Descargas/accreditationhistory.gz be67313bb936:/home/avbravo/accreditationhistory.gz


```

### Ingresar a Docker

- Ver las imagenes
```
docker ps -a 
```

- House

```
docker exec -it be67313bb936  bash

```

### Restaurar un gzip
```
mongorestore --gzip --archive=accreditation.gz
 
mongorestore --gzip --archive=configurationjmoordbdb.gz

mongorestore --gzip --archive=sft.gz

mongorestore --gzip --archive=historydb.gz

mongorestore --gzip --archive=sfthistory.gz.gz

mongorestore --gzip --archive=accreditationhistory.gz




```


# Front End

## Iconos
### favicon
[https://iconmonstr.com/school-30-png/](https://iconmonstr.com/school-30-png/)

### icon-primeblocks.svg
[https://iconmonstr.com/rocket-17-svg/](https://iconmonstr.com/rocket-17-svg/)