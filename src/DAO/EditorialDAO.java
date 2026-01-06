package DAO;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.Validaciones;


public class EditorialDAO {
    public void CrearEditoral(String nombre) throws Exception {
        if (!Validaciones.validarNombre(nombre)) {
            System.out.println("Nombre inválido");
            return;
        }

        String sql = "INSERT INTO Editorial (nombre) VALUES (?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
     }

    }

    public void ConsultarEditoral(int id) throws Exception {
    String sql = """
        SELECT e.nombre, l.titulo
        FROM Editorial e
        LEFT JOIN Libro l ON e.id = l.editorial_id
        WHERE e.id = ?
    """;

    try (Connection con = ConnectionFactory.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        boolean existe = false;
        while (rs.next()) {
            if (!existe) {
                System.out.println("Editorial: " + rs.getString("nombre"));
                System.out.println("Libros:");
                existe = true;
            }
            if (rs.getString("titulo") != null)
                System.out.println(" - " + rs.getString("titulo"));
        }

        if (!existe) System.out.println("Editorial no encontrada");
    }
}

    public void ListaEditoriales(){

    }

    public void ModificaEditorial(int id, String nombre) throws Exception {
    if (!Validaciones.validarNombre(nombre)) {
        System.out.println("Nombre inválido");
        return;
    }

    String sql = "UPDATE Editorial SET nombre=? WHERE id=?";

    try (Connection con = ConnectionFactory.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombre);
        ps.setInt(2, id);

        if (ps.executeUpdate() == 0)
            System.out.println("Editorial no encontrada");
        else
            System.out.println("Editorial modificada");
    }

    }

    public void EliminaEditorial(int id) throws Exception {
        String count = "SELECT COUNT(*) FROM Libro WHERE editorial_id=?";
        String del = "DELETE FROM Editorial WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection()) {
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(count);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                System.out.println("Tiene libros asociados");
                con.rollback();
                return;
            }

            ps = con.prepareStatement(del);
            ps.setInt(1, id);
            ps.executeUpdate();
            con.commit();
        }
    }
        
    }

