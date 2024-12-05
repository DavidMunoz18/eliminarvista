package Servicios;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import Dtos.UsuarioDtos;

public class UsuarioApiService {

    private static final String API_BASE_URL = "http://localhost:8081/usuarios";

    // Método para modificar un usuario
    public String modificarUsuario(long idUsuario, String nuevoNombre, String nuevoTelefono, byte[] nuevaFoto) {
        String boundary = "*****" + System.currentTimeMillis() + "*****"; // Límite para multipart
        try {
            URL url = new URL(API_BASE_URL + "/modificar/" + idUsuario);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            conn.setDoOutput(true);

            // Construir cuerpo multipart
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

            // Campo nuevoNombre
            if (nuevoNombre != null) {
                dos.writeBytes("--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"nuevoNombre\"\r\n\r\n");
                dos.writeBytes(nuevoNombre + "\r\n");
            }

            // Campo nuevoTelefono
            if (nuevoTelefono != null) {
                dos.writeBytes("--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"nuevoTelefono\"\r\n\r\n");
                dos.writeBytes(nuevoTelefono + "\r\n");
            }

            // Campo nuevaFoto
            if (nuevaFoto != null) {
                dos.writeBytes("--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"nuevaFoto\"; filename=\"foto.jpg\"\r\n");
                dos.writeBytes("Content-Type: image/jpeg\r\n\r\n");
                dos.write(nuevaFoto);
                dos.writeBytes("\r\n");
            }

            dos.writeBytes("--" + boundary + "--\r\n");
            dos.flush();
            dos.close();

            // Leer respuesta
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                StringBuilder response = new StringBuilder();
                try (Scanner scanner = new Scanner(conn.getInputStream())) {
                    while (scanner.hasNextLine()) {
                        response.append(scanner.nextLine());
                    }
                }
                return response.toString();
            } else {
                return "Error: Código de respuesta HTTP " + responseCode;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al conectar con la API: " + e.getMessage();
        }
    }
    
    
    // Método para obtener usuario por ID
    public UsuarioDtos obtenerUsuarioPorId(long idUsuario) {
        try {
            URL url = new URL(API_BASE_URL + "/buscar/" + idUsuario);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                // Leer la respuesta (usuario) y convertirla en un objeto UsuarioDTO
                Scanner scanner = new Scanner(conn.getInputStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();

                // Usamos Jackson ObjectMapper para convertir la respuesta JSON en un objeto UsuarioDTO
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response.toString(), UsuarioDtos.class);
            } else {
                return null; // Si no encuentra al usuario, retornamos null
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Error al conectar con la API
        }
    }

}


