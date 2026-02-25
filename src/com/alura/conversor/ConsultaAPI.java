package com.alura.conversor;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConsultaAPI {

    private static final String API_KEY = System.getenv("EXCHANGE_API_KEY");

    public double obtenerTasa(String base, String destino)
            throws IOException, InterruptedException {

        if (API_KEY == null || API_KEY.isBlank()) {
            throw new RuntimeException("La API KEY no está configurada en las variables de entorno.");
        }

        String url = "https://v6.exchangerate-api.com/v6/"
                + API_KEY + "/pair/" + base + "/" + destino;

        HttpClient cliente = HttpClient.newHttpClient();

        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> respuesta =
                cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        JsonObject json = JsonParser
                .parseString(respuesta.body())
                .getAsJsonObject();

        if (!json.get("result").getAsString().equals("success")) {
            throw new RuntimeException("Error al obtener la tasa desde la API.");
        }

        return json.get("conversion_rate").getAsDouble();
    }
}