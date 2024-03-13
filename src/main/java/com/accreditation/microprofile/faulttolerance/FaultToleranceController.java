package com.accreditation.microprofile.faulttolerance;

import com.accreditation.repository.ApplicativeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.Date;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;


@Path("/faulttolerance")
@ApplicationScoped
public class FaultToleranceController {

    @Inject
    ApplicativeRepository applicativeRepository;

    @Fallback(fallbackMethod = "fallback") // better use FallbackHandler
    @Timeout(500)
    @GET
    public String contadorApplicative() {
        Integer count = 0;
        try {
            Date fecha = new Date();
            count = applicativeRepository.findAll().stream().filter(p -> (p.getActive().equals(true))).map(_item -> 1).reduce(count, Integer::sum);//            applicativeList.forEach(action);
        } catch (Exception e) {
            //
        }
        return "Total de applicativees creados despues son " + String.valueOf(count);
    }

    public String fallback() {

        return "Fallback respuesta por tiempo de espera agotado.";
    }
}
