package Servicios;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class EliminarUsuarioServicio {

    private static final String API_BASE_URL = "http://localhost:8081/gestionEliminar"; // URL base de tu API

    public String eliminarUsuario(long idUsuario) {
        HttpURLConnection connection = null;

        try {
            // Construir la URL para la solicitud DELETE
            URL url = new URL(API_BASE_URL + "/usuario/" + idUsuario);

            // Abrir la conexión
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // Verificar el código de respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) { // 204 No Content
                return "Usuario eliminado exitosamente.";
            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) { // 404 Not Found
                return "Usuario no encontrado.";
            } else {
                return "Error al eliminar el usuario. Código de respuesta: " + responseCode;
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
