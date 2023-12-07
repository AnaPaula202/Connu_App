<?php
require 'conexion.php';
$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST;

$respuesta['exito'] = false;
$respuesta['lista'] = null;

$idUsuario = $post['idUsuario'];

$sql = "SELECT publicacion.*, usuario.correo, usuario.nombre, tipopublicacion.nombre as punombre, usuario.sexo_idsexo 
FROM publicacion 
JOIN usuario ON publicacion.usuario_idusuario = usuario.idusuario 
JOIN tipopublicacion ON publicacion.tipo_idtipo = tipopublicacion.idtipopublicacion 
WHERE usuario_idusuario = $idUsuario";
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