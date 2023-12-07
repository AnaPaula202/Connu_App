<?php
require 'conexion.php';

$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST;

$respuesta['exito'] = false;
$respuesta['msj'] = '';

$idPublicacion = filtrado($post['postId']);

$query = "SELECT * FROM publicacion WHERE idpublicacion = $idPublicacion";
$resultado = mysqli_query($conexion, $query);

if ($resultado) {
    if (mysqli_num_rows($resultado) > 0) {
        $fila = mysqli_fetch_assoc($resultado);

        // Obtener los detalles del post
        $respuesta['exito'] = true;
        $respuesta['tipo'] = $fila['tipo_idtipo'];
        $respuesta['contenido'] = $fila['contenido'];
        $respuesta['img'] = $fila['img1'];
    } else {
        $respuesta['msj'] = 'No se encontró la publicación con el ID proporcionado';
    }
} else {
    $respuesta['msj'] = 'Error al ejecutar la consulta: ' . mysqli_error($conexion);
}

header('Content-type: application/json');
echo json_encode($respuesta);

function filtrado($datos)
{
    $datos = trim($datos);
    $datos = stripslashes($datos);
    $datos = htmlspecialchars($datos);
    return $datos;
}
?>
