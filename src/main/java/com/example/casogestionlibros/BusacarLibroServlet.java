package com.example.casogestionlibros;

import data.LibroDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CategoriaLibro;
import model.Libro;
import model.Tipo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.example.casogestionlibros.LibroServlet.generarTablaLibros;
import static data.LibroDAO.buscarLibros;

@WebServlet(name = "BuscarLibroServlet", value = "/BuscarLibroServlet")
public class BusacarLibroServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {





    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros de búsqueda
        String nombreCategoria = request.getParameter("categoria");
        String nombre = request.getParameter("nombre").trim();;
        String ano = request.getParameter("ano");

        // Lógica para buscar los libros en la base de datos
        List<Libro> librosEncontrados = LibroDAO.buscarLibros(nombre, ano, nombreCategoria);

        if (!librosEncontrados.isEmpty()) {
            // Generar tabla de libros en formato HTML
            String tablaLibros = generarTablaLibros(librosEncontrados);

            // Enviar tabla de libros al JSP
            request.setAttribute("tablaLibros", tablaLibros);
            RequestDispatcher respuesta1 = request.getRequestDispatcher("/libroEncontrado.jsp");
            respuesta1.forward(request, response);
        } else {
            RequestDispatcher respuesta2 = request.getRequestDispatcher("/libroNoEncontrado.jsp");
            respuesta2.forward(request, response);
        }
    }



}
