<?php
require 'conexion.php';

$respuesta['exito'] = false;
$respuesta['lista'] = null;

$sql = 'SELECT publicacion.*, usuario.correo, usuario.nombre, tipopublicacion.nombre as punombre, usuario.sexo_idsexo
FROM publicacion
JOIN usuario ON publicacion.usuario_idusuario = usuario.idusuario
JOIN tipopublicacion ON publicacion.tipo_idtipo = tipopublicacion.idtipopublicacion;';
$result = mysqli_query($conexion, $sql);

$lista = array();
if($result){
    $respuesta['exito'] = true;

    while($row = mysqli_fetch_assoc($result)){
        $lista[] = $row;
    }

    $respuesta['lista'] = $lista;
}

header('Content-type: application/json');
echo json_encode($respuesta);
?>