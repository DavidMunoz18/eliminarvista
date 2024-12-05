package controladores;

import Servicios.EliminarUsuarioServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/eliminarUsuario")
public class EliminarUsuarioControlador extends HttpServlet {

    private EliminarUsuarioServicio eliminarUsuarioServicio = new EliminarUsuarioServicio();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recuperar el ID del usuario desde los parámetros del formulario
            long idUsuario = Long.parseLong(request.getParameter("idUsuario"));

            // Llamar al servicio para eliminar al usuario
            String resultado = eliminarUsuarioServicio.eliminarUsuario(idUsuario);

            // Redirigir con el resultado
            request.setAttribute("resultado", resultado);
            request.getRequestDispatcher("administrador.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de usuario inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la solicitud: " + e.getMessage());
        }
    }
}
