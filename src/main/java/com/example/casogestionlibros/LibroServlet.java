package com.example.casogestionlibros;

import data.CategoriaLibroDAO;
import data.LibroDAO;
import data.TipoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CategoriaLibro;
import model.Libro;
import model.Tipo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static data.LibroDAO.buscarLibros;

@WebServlet(name = "LibroServlet", value = "/LibroServlet")
public class LibroServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        super.init();


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // AGREGAR LIBRO
        RequestDispatcher respuesta1 = request.getRequestDispatcher("/libroAgregadoCorrectamente.jsp");
        RequestDispatcher respuesta2 = request.getRequestDispatcher("/errorAgregarLibro.jsp");

        String categoriaNombre = request.getParameter("categoria");
        String nombre = request.getParameter("nombre");
        String editorial = request.getParameter("editorial");
        String ano = request.getParameter("ano");
        String formatoNombre = request.getParameter("formato");

        // Crear objeto Tipo y CategoriaLibro
        Tipo formato = new Tipo(formatoNombre);
        CategoriaLibro categoria = new CategoriaLibro(categoriaNombre);

        // Crear instancia del libro
        Libro libro = new Libro(nombre, editorial, ano, formato, categoria);

        // Lógica para agregar el libro a la base de datos
        LibroDAO libroDAO = new LibroDAO();
        boolean exito = libroDAO.guardarLibro(libro);

        if (exito) {
            request.setAttribute("libro", libro);
            respuesta1.forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("Error al agregar el libro");
            respuesta2.forward(request, response);
        }

        List<String> categorias = obtenerCategorias();
        System.out.println("Categorías de libros:");
        for (String jsjsjs : categorias) {
            System.out.println(jsjsjs);
        }




    }


  //METODOS UTILIZADOS EN EL JSP PATA OBTENER LAS LISTAS DESPLEGABLES
    public static List<String> obtenerCategorias() {
       List<CategoriaLibro> listaCategorias = CategoriaLibroDAO.obtenerCategoriasObjeto();


        List<String> categorias = new ArrayList<>();



        for (CategoriaLibro categoria : listaCategorias) {
            categorias.add(categoria.getNombre());
        }

        return categorias;
    }
    public static List<String> obtenerFormatos() {
        List<Tipo> listaTipo = TipoDAO.obtenerTipos();
        List<String> formatos = new ArrayList<>();

        for (Tipo tipo : listaTipo) {
            formatos.add(tipo.getNombre());
        }

        return formatos;
    }


    public static String generarTablaLibros(List<Libro> libros) {
        StringBuilder tabla = new StringBuilder();

        tabla.append("<table>");
        tabla.append("<tr>");
        tabla.append("<th>Nombre</th>");
        tabla.append("<th>Editorial</th>");
        tabla.append("<th>Año</th>");
        tabla.append("<th>Tipo</th>");
        tabla.append("<th>Categoría</th>");
        tabla.append("</tr>");

        for (Libro libro : libros) {
            String nombre = libro.getNombre().trim();
            String editorial = libro.getEditorial().trim();
            String ano = libro.getAno().trim();
            String nombreTipo = libro.getTipo().getNombre().trim();
            String nombreCategoria = libro.getCategoria().getNombre().trim();

            tabla.append("<tr>");
            tabla.append("<td>").append(nombre).append("</td>");
            tabla.append("<td>").append(editorial).append("</td>");
            tabla.append("<td>").append(ano).append("</td>");
            tabla.append("<td>").append(nombreTipo).append("</td>");
            tabla.append("<td>").append(nombreCategoria).append("</td>");
            tabla.append("</tr>");
        }

        tabla.append("</table>");

        return tabla.toString();
    }





}
