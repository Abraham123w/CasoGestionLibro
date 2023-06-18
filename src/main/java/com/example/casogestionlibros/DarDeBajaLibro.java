package com.example.casogestionlibros;

import data.CategoriaLibroDAO;
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

@WebServlet(name = "DarDeBajaLibro", value = "/DarDeBajaLibro")
public class DarDeBajaLibro extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // RESPUESTAS
        RequestDispatcher respuesta1 = request.getRequestDispatcher("/exitoDarBaja.jsp");
        RequestDispatcher respuesta2 = request.getRequestDispatcher("/errorDarBaja.jsp");

        // Obtener los parámetros del formulario
        String categoriaNombre = request.getParameter("categoria");
        String nombre = request.getParameter("nombre").trim();
        String editorial = request.getParameter("editorial");
        String ano = request.getParameter("ano");
        String formatoNombre = request.getParameter("formato");

        // Crear objeto Tipo y CategoriaLibro
        Tipo formato = new Tipo(formatoNombre);
        CategoriaLibro categoria = new CategoriaLibro(categoriaNombre);

        // Crear instancia del libro
        Libro libro = new Libro(nombre, editorial, ano, formato, categoria);

        // Lógica para dar de baja el libro
        LibroDAO libroDAO = new LibroDAO();
        boolean exito = libroDAO.darBaja(libro);

        List<Libro> libroDarBaja = new ArrayList<>();
        libroDarBaja.add(libro);

        if (exito) {

            // Generar tabla de libros en formato HTML

            String tablaLibros = generarTablaLibros(libroDarBaja);
            // Enviar tabla de libros al JSP
            request.setAttribute("tablaLibros", tablaLibros);
            respuesta1.forward(request, response);

        } else {
            PrintWriter out = response.getWriter();
            out.println("Error al dar de baja el libro");
            respuesta2.forward(request, response);
        }
    }
}
