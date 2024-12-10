package com.tuempresa.sri;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class SriService {

    private final RestTemplate restTemplate;

    public SriService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String verificarContribuyente(String numeroRuc) {
        try {
            // Construir la URL para consultar el SRI
            String url = "https://srienlinea.sri.gob.ec/sri-catastro-sujeto-servicio-internet/rest/ConsolidadoContribuyente/existePorNumeroRuc?numeroRuc=" + numeroRuc;

            // Realizar la solicitud GET usando RestTemplate
            String response = restTemplate.getForObject(url, String.class);

            // Si la respuesta es "true", significa que es contribuyente
            if ("true".equalsIgnoreCase(response)) {
                return "Es contribuyente";
            } else {
                return "No existe como contribuyente";
            }
        } catch (HttpClientErrorException e) {
            // Manejar errores HTTP
            return "Error al verificar contribuyente: " + e.getMessage();
        }
    }


    public String verificarPuntosLicencia(String cedula) {
        try {
            // Construir la URL para consultar los puntos de licencia
            String url = "https://consultaweb.ant.gob.ec/PortalWEB/paginas/clientes/clp_grid_citaciones.jsp?ps_tipo_identificacion=CED&ps_identificacion=" + cedula;

            // Realizar la solicitud GET
            String response = restTemplate.getForObject(url, String.class);

            // Procesar la respuesta para extraer los puntos de licencia
            return procesarPuntosLicencia(response);
        } catch (HttpClientErrorException e) {
            // Manejar errores HTTP
            return "Error al verificar los puntos de licencia: " + e.getMessage();
        }
    }

    private String procesarPuntosLicencia(String response) {
        // Simula procesar la respuesta de la API
        if (response.contains("puntos")) {
            // Extraer y formatear los puntos de la licencia desde la respuesta
            // Esto es un ejemplo; ajusta según la estructura real del contenido
            return "Tienes X puntos en tu licencia.";
        } else {
            return "No se encontraron puntos asociados con la cédula proporcionada.";
        }
    }
}
