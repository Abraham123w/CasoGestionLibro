<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Agregar Categoría de Libro</title>
  <link rel="stylesheet" type="text/css" href="estilos.css">
</head>
<body>
<h1>Agregar Categoría de Libro</h1>

<form method="post" action="CategoriaLibroServlet">
  <label for="nombreCategoria">Nombre de la Categoría:</label>
  <input type="text" id="nombreCategoria" name="nombreCategoria" required>
  <br><br>
  <input type="submit" value="Agregar Categoría">
</form>
</body>
</html>