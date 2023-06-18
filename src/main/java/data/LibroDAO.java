package data;


import model.CategoriaLibro;
import model.Libro;
import model.Tipo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LibroDAO {

    private static final String NOMBRE_ARCHIVO= "C:/Users/abrah/OneDrive/Escritorio/UNIVERSIAS 2023/OneDrive/TAREAS DE PRAGRAMACION/casoGestionLibros/libros.txt";

    public static boolean guardarLibro(Libro libro) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
            // Crear una cadena con los datos del libro a guardar en el formato deseado
            String libroString = libro.getCategoria().getNombre() + "|" + libro.getNombre() + "|" +libro.getEditorial() + "|" + libro.getAno() + "|" + libro.getTipo().getNombre();


            // Escribir la cadena en el archivo
            writer.write(libroString);
            writer.newLine();
            writer.flush();

            // Retornar true para indicar que el guardado fue exitoso
            return true;
        } catch (IOException e) {
            e.printStackTrace();

            // Retornar false en caso de error
            return false;
        }
    }

    public static List<Libro> buscarLibros(String nombre, String ano, String categoria) {
        List<Libro> librosEncontrados = new ArrayList<>();
       // Tipo tipo0=new Tipo("pirata");
        //CategoriaLibro categoriaLibro0=new CategoriaLibro("pirata");

        //Libro libroEncontrado0 = new Libro("pirata","pirata","1994", tipo0, categoriaLibro0);
       // librosEncontrados.add(libroEncontrado0 );

        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datosLibro = linea.split("\\|");
                String nombreCategoria = datosLibro[0].trim();

                String nombreLibro = datosLibro[1].trim();
                String editorialLibro = datosLibro[2].trim();
                String anoLibro = datosLibro[3].trim();
                String nombreTipo = datosLibro[4].trim();
                Tipo tipo = new Tipo(nombreTipo);
                CategoriaLibro categoriaLibro = new CategoriaLibro(nombreCategoria);
                // Compara los atributos extraídos con los criterios de búsqueda
                if (nombreLibro.equalsIgnoreCase(nombre) && anoLibro.equalsIgnoreCase(ano) && nombreCategoria.equalsIgnoreCase(categoria)) {
                    Libro libroEncontrado = new Libro(nombreLibro, editorialLibro, anoLibro, tipo, categoriaLibro);
                    librosEncontrados.add(libroEncontrado);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return librosEncontrados;
    }


    public static boolean darBaja(Libro libro) {
        try {
            // Obtener los datos del libro a eliminar
            String categoria = libro.getCategoria().getNombre().replaceAll("\\s+", "");
            String nombre = libro.getNombre().replaceAll("\\s+", "");
            String editorial = libro.getEditorial().replaceAll("\\s+", "");
            String ano = libro.getAno().replaceAll("\\s+", "");
            String formato = libro.getTipo().getNombre().replaceAll("\\s+", "");

            // Crear una cadena con los datos del libro a eliminar
            String libroString = categoria + "|" + nombre + "|" + editorial + "|" + ano + "|" + formato;

            // Leer el contenido actual del archivo
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO), StandardCharsets.UTF_8);

            // Bandera para indicar si se encontró un libro similar
            boolean encontrado = false;

            // Buscar y eliminar la línea que contiene los datos del libro
            for (Iterator<String> iterator = lineas.iterator(); iterator.hasNext();) {
                String linea = iterator.next().replaceAll("\\s+", "");
                if (linea.equals(libroString)) {
                    iterator.remove();
                    encontrado = true;
                    break;
                }
            }

            // Guardar el contenido actualizado en el archivo
            Files.write(Paths.get(NOMBRE_ARCHIVO), lineas, StandardCharsets.UTF_8);

            return encontrado; // Devolver true si se encontró un libro similar, false si no se encontró
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Indicar fallo en la eliminación y guardado del libro
        }
    }






    // Otros métodos de la clase LibroDAO
}
