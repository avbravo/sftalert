echo '________________________________________'
echo ' '
echo 'Sube archivos desde House a Docker'
echo ' '
echo '________________________________________'
echo 'Iniciando proceso....'
echo '      subiendo [accreditation.gz]'
docker cp /home/avbravo/Descargas/accreditation.gz be67313bb936:/home/avbravo/accreditation.gz
echo '      subiendo [configurationjmoordbdb.gz]'
docker cp /home/avbravo/Descargas/configurationjmoordbdb.gz be67313bb936:/home/avbravo/configurationjmoordbdb.gz
echo '      subiendo [sft.gz]' 
docker cp /home/avbravo/Descargas/sft.gz be67313bb936:/home/avbravo/sft.gz

echo '      subiendo [historydb.gz]' 
docker cp /home/avbravo/Descargas/historydb.gz  be67313bb936:/home/avbravo/historydb.gz 

echo '      subiendo [transporte.gz]' 
docker cp /home/avbravo/Descargas/transporte.gz  be67313bb936:/home/avbravo/transporte.gz 

echo 'Proceso [finalizado]'



# docker cp /home/avbravo/Descargas/restoredb.sh be67313bb936:/home/avbravo/restoredb.sh
# docker cp /home/avbravo/Descargas/restoredb.sh be67313bb936:/home/avbravo/backup.sh

