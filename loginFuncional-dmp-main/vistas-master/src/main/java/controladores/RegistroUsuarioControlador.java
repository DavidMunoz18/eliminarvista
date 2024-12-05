package controladores;

import Servicios.RegistroUsuarioServicio;
import Dtos.RegistroUsuarioDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registroUsu")
public class RegistroUsuarioControlador extends HttpServlet {

    private RegistroUsuarioServicio registroUsuarioServicio;

    @Override
    public void init() throws ServletException {
        // Inicializar el servicio de registro de usuario
        this.registroUsuarioServicio = new RegistroUsuarioServicio();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recoger los parámetros del formulario de registro
        String nickname = request.getParameter("nicknameUsuario");
        String nombre = request.getParameter("nombreUsuario");
        String dni = request.getParameter("dniUsuario");
        String telefono = request.getParameter("telefonoUsuario");
        String correo = request.getParameter("emailUsuario");
        String password = request.getParameter("passwordUsuario");

        // Crear el objeto DTO con los datos del usuario
        RegistroUsuarioDto registroDto = new RegistroUsuarioDto();
        registroDto.setNicknameUsuario(nickname);
        registroDto.setNombreUsuario(nombre);
        registroDto.setDniUsuario(dni);
        registroDto.setTelefonoUsuario(telefono);
        registroDto.setEmailUsuario(correo);
        registroDto.setPasswordUsuario(password);

        // Llamar al servicio para registrar al usuario
        boolean registroExitoso = registroUsuarioServicio.registrarUsuario(registroDto);

        if (registroExitoso) {
            // Registro exitoso, redirigir a la página de inicio o login
            response.sendRedirect("index.jsp");
        } else {
            // Si el registro falló (correo ya existente), mostrar un mensaje de error
            request.setAttribute("errorMessage", "El correo ya está registrado.");
            request.getRequestDispatcher("registrarseUsuario.jsp").forward(request, response);
        }
    }
}