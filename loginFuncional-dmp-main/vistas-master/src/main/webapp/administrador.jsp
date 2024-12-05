<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Usuarios</title>
</head>
<body>
    <!-- Formulario para modificar usuario -->
    <form action="modificarUsuario" method="post" enctype="multipart/form-data">
        <h2>Modificar Usuario</h2>
        <label for="idUsuario">ID Usuario:</label>
        <input type="number" id="idUsuario" name="idUsuario" required><br><br>

        <label for="nuevoNombre">Nuevo Nombre:</label>
        <input type="text" id="nuevoNombre" name="nuevoNombre"><br><br>

        <label for="nuevoTelefono">Nuevo Teléfono:</label>
        <input type="text" id="nuevoTelefono" name="nuevoTelefono"><br><br>

        <label for="nuevaFoto">Nueva Foto:</label>
        <input type="file" id="nuevaFoto" name="nuevaFoto" accept="image/*"><br><br>

        <button type="submit">Modificar Usuario</button>
    </form>

    <!-- Formulario para eliminar usuario -->
    <form action="eliminarUsuario" method="post">
        <h2>Eliminar Usuario</h2>
        <label for="idUsuarioEliminar">ID Usuario:</label>
        <input type="number" id="idUsuarioEliminar" name="idUsuario" required><br><br>

        <button type="submit">Eliminar Usuario</button>
    </form>

    <!-- Formulario para eliminar club -->
    <form action="eliminarClub" method="post">
        <h2>Eliminar Club</h2>
        <label for="idClubEliminar">ID Club:</label>
        <input type="number" id="idClubEliminar" name="idClub" required><br><br>

        <button type="submit">Eliminar Club</button>
    </form>

    <!-- Mostrar resultado de las operaciones -->
    <div>
        <h3>Resultado:</h3>
        <p>${resultado}</p>
    </div>
</body>
</html>
