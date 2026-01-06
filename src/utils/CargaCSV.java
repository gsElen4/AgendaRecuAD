package utils;

import java.io.*;
import java.sql.*;
import factory.ConnectionFactory;

public class CargaCSV {
 public static void cargarAutores(String ruta) throws Exception {

        String sql = "INSERT INTO Autor (nombre,email) VALUES (?,?)";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta));
             Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            con.setAutoCommit(false);
            String linea;
            int i = 0;

            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");

                if (!Validaciones.validarNombre(d[0]) || !Validaciones.validarEmail(d[1])) {
                    System.out.println("Fila inválida: " + linea);
                    continue;
                }

                ps.setString(1, d[0]);
                ps.setString(2, d[1]);
                ps.addBatch();

                if (++i % 100 == 0) ps.executeBatch();
            }
            ps.executeBatch();
            con.commit();
        }
    }
}
