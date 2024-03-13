package com.accreditation.configuration;


import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@ApplicationPath("api")
@OpenAPIDefinition(info = @Info(
        title = "RESTful API",
        description = "capitulo 02",
        version = "1.0.0-Snapshot",
        contact = @Contact(
                name = "AVbravo",
                email = "avbravo@gmail.com",
                url = "https://avbravo.blogspot.com")
),
        servers = {
            @Server(url = "http://localhost:8080/", description = "Local Development Server "),}
)
@BasicAuthenticationMechanismDefinition(realmName = "admin-realm")
public class JakartaRestConfiguration extends Application {
    
}
