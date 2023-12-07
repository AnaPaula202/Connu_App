<?php
require 'conexion.php'; // Asegúrate de tener un archivo de conexión

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    if (isset($_GET['idUsuario'])) {
        $idUsuario = $_GET['idUsuario'];

        $query = "SELECT * FROM usuario WHERE idusuario = $idUsuario";
        $result = mysqli_query($conexion, $query);

        if ($result) {
            if (mysqli_num_rows($result) > 0) {
                $usuario = mysqli_fetch_assoc($result);
                $respuesta['nombre'] = $usuario['nombre'];
                $respuesta['correo'] = $usuario['correo'];

                $respuesta['exito'] = true;
            } else {
                $respuesta['exito'] = false;
                $respuesta['msj'] = 'Usuario no encontrado';
            }
        } else {
            $respuesta['exito'] = false;
            $respuesta['msj'] = 'Error en la consulta';
        }
    } else {
        $respuesta['exito'] = false;
        $respuesta['msj'] = 'No se proporcionó un idUsuario';
    }
} else {
    $respuesta['exito'] = false;
    $respuesta['msj'] = 'Método no permitido';
}

header('Content-type: application/json');
echo json_encode($respuesta);
?>