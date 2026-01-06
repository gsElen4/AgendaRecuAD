package DAO;

import factory.ConnectionFactory;
import java.sql.*;
import utils.Validaciones;

public class AutorDAO {
    public void CrearAutor(String nombre, String email) throws Exception{
 if (!Validaciones.validarNombre(nombre) || !Validaciones.validarEmail(email)) {
            System.out.println(" Datos inválidos");
            return;
        }
         String sql = "INSERT INTO Autor (nombre, email) VALUES (?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.executeUpdate();
            System.out.println("Autor creado");
        }
    }

    public void ConsultarAutor(int id) throws Exception{
        String sql = """
        SELECT a.nombre, a.email, l.titulo
        FROM Autor a
        LEFT JOIN Autor_Libro al ON a.id = al.autor_id
        LEFT JOIN Libro l ON l.id = al.libro_id
        WHERE a.id = ?
    """;

    try (Connection con = ConnectionFactory.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
         boolean existe = false;
        while (rs.next()) {
            if (!existe) {
                System.out.println("Autor: " + rs.getString("nombre")
                        + " (" + rs.getString("email") + ")");
                System.out.println("Libros:");
                existe = true;
            }
            if (rs.getString("titulo") != null)
                System.out.println(" - " + rs.getString("titulo"));
        }

        if (!existe) System.out.println("Autor no encontrado");
    }
    }

    public void ListaAutores()throws Exception {
        String sql = "SELECT id,nombre FROM Autor";
        try (Connection con = ConnectionFactory.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " - " + rs.getString(2));
            }
        }
    }

    public void ModificaAutor(int id, String nombre, String email) throws Exception {
         if (!Validaciones.validarNombre(nombre) || !Validaciones.validarEmail(email)) {
        System.out.println("Datos inválidos");
        return;
    }
      String sql = "UPDATE Autor SET nombre=?, email=? WHERE id=?";

    try (Connection con = ConnectionFactory.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombre);
        ps.setString(2, email);
        ps.setInt(3, id);

        if (ps.executeUpdate() == 0)
            System.out.println("Autor no encontrado");
            else
            System.out.println("Autor modificado");
    }
    }

    public void EliminaAutor(int id) throws Exception{
         String delRel = "DELETE FROM Autor_Libro WHERE autor_id=?";
    String delAutor = "DELETE FROM Autor WHERE id=?";

    try (Connection con = ConnectionFactory.getConnection()) {
        con.setAutoCommit(false);

        PreparedStatement ps = con.prepareStatement(delRel);
        ps.setInt(1, id);
        ps.executeUpdate();

        ps = con.prepareStatement(delAutor);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            con.rollback();
            System.out.println("Autor no encontrado");
            return;
        }
          con.commit();
        System.out.println("Autor eliminado");
    }
    }
}
