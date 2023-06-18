<%@ page import="java.util.List" %>
<%@ page import="com.example.casogestionlibros.LibroServlet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Agregar Libro</title>
  <link rel="stylesheet" type="text/css" href="estilos.css">
</head>
<body>
<h1>Agregar Libro</h1>
<form action="LibroServlet" method="POST">
  <label for="categoria">Categoría:</label>
  <select name="categoria" id="categoria">
    <%-- Iterar sobre la lista de categorías obtenida desde LibroServlet --%>
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

  <label for="editorial">Editorial:</label>
  <input type="text" name="editorial" id="editorial"><br>

  <label for="ano">Año:</label>
  <input type="text" name="ano" id="ano"><br>

  <label for="formato">Formato:</label>
  <select name="formato" id="formato">
    <%-- Obtener la lista de formatos desde TipoServlet --%>
    <%
      List<String> formatos = (List<String>) LibroServlet.obtenerFormatos();
      for (String formato : formatos) {
    %>
    <option value="<%= formato %>"><%= formato %></option>
    <%-- Fin del bucle --%>
    <% } %>
  </select><br>

  <input type="submit" value="Agregar">
</form>
</body>
</html>
