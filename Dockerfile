# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM payara/6.2023.11-jdk17

COPY target/accreditation.war $DEPLOY_DIR