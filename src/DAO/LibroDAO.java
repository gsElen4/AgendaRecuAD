package DAO;

import factory.ConnectionFactory;
import java.sql.*;
import utils.Validaciones;


public class LibroDAO {

    public void CrearLibro(String titulo, String isbn, int editorialId) throws Exception{
        if (!Validaciones.validarTitulo(titulo) || !Validaciones.validarISBN(isbn)) {
            System.out.println("Datos inválidos");
            return;
        }

        String check = "SELECT COUNT(*) FROM Libro WHERE isbn=?";
        String insert = "INSERT INTO Libro (titulo,isbn,editorial_id) VALUES (?,?,?)";

        try (Connection con = ConnectionFactory.getConnection()) {

            PreparedStatement ps = con.prepareStatement(check);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                System.out.println("ISBN duplicado");
                return;
            }
             ps = con.prepareStatement(insert);
            ps.setString(1, titulo);
            ps.setString(2, isbn);
            ps.setInt(3, editorialId);
            ps.executeUpdate();
        }
    }

    public void ConsultarLibro(int id) throws Exception{
    String sql = """
        SELECT l.titulo, l.isbn, a.nombre
        FROM Libro l
        LEFT JOIN Autor_Libro al ON l.id = al.libro_id
        LEFT JOIN Autor a ON a.id = al.autor_id
        WHERE l.id = ? """;

    try (Connection con = ConnectionFactory.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        boolean existe = false;
        while (rs.next()) {
            if (!existe) {
                System.out.println("Libro: " + rs.getString("titulo")
                        + " | ISBN: " + rs.getString("isbn"));
                System.out.println("Autores:");
                existe = true;
            }
            if (rs.getString("nombre") != null)
                System.out.println(" - " + rs.getString("nombre"));
        }

        if (!existe) System.out.println("Libro no encontrado");
        }
    }

    public void ListaLibros() throws SQLException{
        String sql = "SELECT id,titulo, isbn, editorial FROM Libro";
        try (Connection con = ConnectionFactory.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " - " + rs.getString(2));
            }
        }
    }

    public void ModificaLibro(int id, String titulo, String isbn) throws Exception {
    if (!Validaciones.validarTitulo(titulo) || !Validaciones.validarISBN(isbn)) {
        System.out.println("Datos inválidos");
        return;
    }

    String sql = "UPDATE Libro SET titulo=?, isbn=? WHERE id=?";

    try (Connection con = ConnectionFactory.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, titulo);
        ps.setString(2, isbn);
        ps.setInt(3, id);

        if (ps.executeUpdate() == 0)
            System.out.println("Libro no encontrado");
        else
            System.out.println("Libro modificado");
    }
 }

    public void EliminaLibro(int id) throws Exception{
        String delRel = "DELETE FROM Autor_Libro WHERE libro_id=?";
    String delLibro = "DELETE FROM Libro WHERE id=?";

    try (Connection con = ConnectionFactory.getConnection()) {
        con.setAutoCommit(false);

        PreparedStatement ps = con.prepareStatement(delRel);
        ps.setInt(1, id);
        ps.executeUpdate();

        ps = con.prepareStatement(delLibro);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            con.rollback();
            System.out.println("Libro no encontrado");
            return;
        }
          con.commit();
        System.out.println("Libro eliminado");
    }
}
    

    public void relacionar(int libro, int autor) throws Exception {
        String sql = "INSERT INTO Autor_Libro VALUES (?,?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, autor);
            ps.setInt(2, libro);
            ps.executeUpdate();
        }
    }
}
