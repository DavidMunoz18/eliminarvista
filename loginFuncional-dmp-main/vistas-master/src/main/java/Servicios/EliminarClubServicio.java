package Servicios;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class EliminarClubServicio {

    private static final String API_BASE_URL = "http://localhost:8081/gestionEliminar"; // URL base de la API para clubes

    public String eliminarClub(long idClub) {
        HttpURLConnection connection = null;

        try {
            // Construir la URL para la solicitud DELETE
            URL url = new URL(API_BASE_URL + "/club/" + idClub);

            // Abrir la conexión
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setConnectTimeout(5000); // Tiempo de espera para conexión
            connection.setReadTimeout(5000); // Tiempo de espera para lectura

            // Verificar el código de respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) { // 204 No Content
                return "Club eliminado exitosamente.";
            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) { // 404 Not Found
                return "Club no encontrado.";
            } else {
                return "Error al eliminar el club. Código de respuesta: " + responseCode;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Error al conectar con la API: " + e.getMessage();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
