<%@ page import="java.util.List" %>
<%@ page import="com.example.casogestionlibros.LibroServlet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Buscar Libro</title>
</head>
<body>
<h1>Buscar Libro</h1>
<link rel="stylesheet" type="text/css" href="estilos.css">
<form action="BuscarLibroServlet" method="GET">
  <label for="categoria">Categoría:</label>
  <select name="categoria" id="categoria">
    <%-- Obtener la lista de categorías desde LibroServlet --%>
    <%
      List<String> categorias = (List<String>) LibroServlet.obtenerCategorias();
      for (String categoria : categorias) {
    %>
    <option value="<%= categoria %>"><%= categoria %></option>
    <%-- Fin del bucle --%>
    <% } %>
  </select><br>

  <label for="nombre">Nombre:</label>
  <input type="text" name="nombre" id="nombre"><br>

  <label for="ano">Año:</label>
  <input type="text" name="ano" id="ano"><br>

  <input type="submit" value="Buscar">
</form>
</body>
</html>
