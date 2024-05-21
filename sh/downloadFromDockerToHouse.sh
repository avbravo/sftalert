echo '________________________________________'
echo ' '
echo 'Download from Docker to House'
echo ' '
echo '________________________________________'
echo 'Iniciando proceso....'
echo '      download [accreditation.gz]'
  docker cp be67313bb936:/home/avbravo/accreditation.gz  /home/avbravo/Descargas/accreditation.gz

echo '      download [configurationjmoordbdb.gz]'
docker cp be67313bb936:/home/avbravo/configurationjmoordbdb.gz  /home/avbravo/Descargas/configurationjmoordbdb.gz

echo '      download [sft.gz]'
docker cp be67313bb936:/home/avbravo/sft.gz  /home/avbravo/Descargas/sft.gz


echo '      download [historydb.gz]'
docker cp be67313bb936:/home/avbravo/historydb.gz  /home/avbravo/Descargas/historydb.gz

echo '      download [transporte.gz]'
docker cp be67313bb936:/home/avbravo/transporte.gz  /home/avbravo/Descargas/transporte.gz


echo 'Proceso [finalizado]'


