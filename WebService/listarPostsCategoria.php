<?php
require 'conexion.php';

$respuesta['exito'] = false;
$respuesta['lista'] = null;

// Obtener el parámetro enviado desde la app
$selectedOption = $_GET['selectedOption'];

// Consulta para cargar todas las publicaciones
$sqlAll = 'SELECT publicacion.*, usuario.correo, usuario.nombre, tipopublicacion.nombre as punombre, usuario.sexo_idsexo
FROM publicacion
JOIN usuario ON publicacion.usuario_idusuario = usuario.idusuario
JOIN tipopublicacion ON publicacion.tipo_idtipo = tipopublicacion.idtipopublicacion';

// Consulta para cargar publicaciones según el tipo seleccionado
$sqlByType = 'SELECT publicacion.*, usuario.correo, usuario.nombre, tipopublicacion.nombre as punombre, usuario.sexo_idsexo
FROM publicacion
JOIN usuario ON publicacion.usuario_idusuario = usuario.idusuario
JOIN tipopublicacion ON publicacion.tipo_idtipo = tipopublicacion.idtipopublicacion
WHERE tipopublicacion.idtipo = ' . $selectedOption;

// Usar la consulta dependiendo del parámetro recibido
if ($selectedOption !== "") {
    $result = mysqli_query($conexion, $sqlByType);
} else {
    $result = mysqli_query($conexion, $sqlAll);
}

$lista = array();
if ($result) {
    $respuesta['exito'] = true;

    while ($row = mysqli_fetch_assoc($result)) {
        $lista[] = $row;
    }

    $respuesta['lista'] = $lista;
}

header('Content-type: application/json');
echo json_encode($respuesta);
?>