
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class koneksi {
     public static Connection koneksikita() {
        Connection koneksi = null;
        try {
            // Informasi koneksi ke MySQL
            String url = "jdbc:mysql://localhost:3306/aplikasi_inventaris_aset";
            String user = "root";
            String password = "";

            // Membuat koneksi
            koneksi = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi ke database berhasil.");
        } catch (SQLException e) {
            System.err.println("Error koneksi: " + e.getMessage());
        }
        return koneksi;
    }

    
    
}
