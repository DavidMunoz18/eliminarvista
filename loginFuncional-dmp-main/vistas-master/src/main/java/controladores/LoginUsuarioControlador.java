package controladores;

import java.io.IOException;

import Servicios.AutentificacionServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginUsuario")
public class LoginUsuarioControlador extends HttpServlet {

    AutentificacionServicio servicio;

    @Override
    public void init() throws ServletException {
        this.servicio = new AutentificacionServicio();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recoger los parámetros del formulario
        String correo = request.getParameter("email");
        String password = request.getParameter("password");

        // Imprimir los valores para depuración
        System.out.println("Correo recibido: " + correo);
        System.out.println("Contraseña recibida: " + password);

        // Llamar al servicio para verificar el usuario
        boolean isValidUser = servicio.verificarUsuario(correo, password);

        System.out.println("isValidUser: " + isValidUser);

        if (isValidUser) {
            // Lógica para manejar el rol y redirigir
            String rol = servicio.getRol();  // Ahora puedes obtener el rol
            System.out.println("Rol del usuario: " + rol);
            if ("admin".equals(rol)) {
                // Redirigir al panel de administración
                response.sendRedirect("capaAdmin.jsp");
            } else if ("usuario".equals(rol)) {
                // Redirigir al panel de usuario
                response.sendRedirect("index.jsp");
            } else {
                // Rol desconocido
                request.setAttribute("errorMessage", "Rol desconocido.");
                request.getRequestDispatcher("iniciarSesionUsuario.jsp").forward(request, response);
            }
        } else {
            // Enviar un mensaje de error
            request.setAttribute("errorMessage", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("iniciarSesionUsuario.jsp").forward(request, response); // Redirigir de vuelta al formulario
        }
    }

}
