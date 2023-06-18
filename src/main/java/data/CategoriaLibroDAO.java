package data;

import model.CategoriaLibro;
import model.Tipo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaLibroDAO {
    private static final String NOMBRE_ARCHIVO = "C:/Users/abrah/OneDrive/Escritorio/UNIVERSIAS 2023/OneDrive/TAREAS DE PRAGRAMACION/casoGestionLibros/categorias.txt";

    public boolean agregarCategoria(CategoriaLibro categoria) {
        List<CategoriaLibro> categorias = obtenerCategoriasObjeto();

        // Verificar si la categoría ya existe
        for (CategoriaLibro c : categorias) {
            if (c.getNombre().equals(categoria.getNombre())) {
                return false; // La categoría ya existe, no se puede agregar
            }
        }

        try (FileWriter writer = new FileWriter(NOMBRE_ARCHIVO, true)) {
            String categoriaLinea = categoria.getNombre() + "\n";
            writer.write(categoriaLinea);
            return true; // La categoría se agregó correctamente
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Error al agregar la categoría
    }

    public static List<CategoriaLibro> obtenerCategoriasObjeto() {
        List<CategoriaLibro> categorias = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                CategoriaLibro categoria = new CategoriaLibro(linea.trim());
                categorias.add(categoria);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categorias;
    }





}
