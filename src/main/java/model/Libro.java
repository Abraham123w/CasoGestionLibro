package model;

public class Libro {
 private String nombre;
 private String editorial;
 private String ano;
 private Tipo tipo;

 private CategoriaLibro categoria;


    public Libro(String nombre, String editorial, String ano, Tipo tipo, CategoriaLibro categoria) {
        this.nombre =nombre;
        this.editorial = editorial;
        this.ano = ano;
        this.tipo = tipo;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public CategoriaLibro getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaLibro categoria) {
        this.categoria = categoria;
    }

}
