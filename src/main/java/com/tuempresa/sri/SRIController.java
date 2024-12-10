package com.tuempresa.sri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sri")
public class SRIController {

    @Autowired
    private SriService sriService;

    public SRIController(SriService sriService) {
        this.sriService = sriService;
    }

    @GetMapping("/verificar-contribuyente")
    public String verificarContribuyente(@RequestParam String numeroRuc) {
        return sriService.verificarContribuyente(numeroRuc);
    }

    @GetMapping("/verificar-puntos-licencia")
    public String verificarPuntosLicencia(@RequestParam String cedula) {
        return sriService.verificarPuntosLicencia(cedula);
    }
}
