package DTO;

public class LibroDTO {
    private int IdLibro;
    private int IDEditorial;
    private String TituloLibro;
    private String ISBN;

    public int getIDLibro() {
        return IdLibro;
    }
    public void setIdLibro(int idLibro) {
        IdLibro = idLibro;
    }

    
    public String getTituloLibro() {
        return TituloLibro;
    }
    public void setTituloLibro(String tituloLibro) {
        TituloLibro = tituloLibro;
    }
    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public int getIDEditorial() {
        return IDEditorial;
    }

    public void setIDEditorial(int IDEditorial) {
        this.IDEditorial = IDEditorial;
    }
}


