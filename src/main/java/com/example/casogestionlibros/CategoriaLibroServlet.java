





package com.example.casogestionlibros;


import data.CategoriaLibroDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CategoriaLibro;
import model.Libro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CategoriaLibroServlet", value = "/CategoriaLibroServlet")
public class CategoriaLibroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // AGREGAR CATEGORIA
        String nombreCategoria = request.getParameter("nombreCategoria");
        RequestDispatcher respuesta1 = request.getRequestDispatcher("/categoriaAgregada.jsp");
        RequestDispatcher respuesta2 = request.getRequestDispatcher("/errorAgregarCategoria.jsp");

        // Crear instancia de la categoría de libro
        CategoriaLibro categoria = new CategoriaLibro(nombreCategoria);

        // Lógica para agregar la categoría a la base de datos
        CategoriaLibroDAO categoriaDAO = new CategoriaLibroDAO();
        boolean exito = categoriaDAO.agregarCategoria(categoria);

        if (exito) {
            request.setAttribute("categoria", categoria);
            respuesta1.forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("Error al agregar la categoría");
            respuesta2.forward(request, response);

        }




    }
}




