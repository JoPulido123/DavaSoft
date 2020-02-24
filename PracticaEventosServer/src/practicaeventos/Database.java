package practicaeventos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rene Navarro
 */
public class Database {

    public Connection con;

    //URL que identifica a la base de datos que nos queremos conectar
    //private final String DB_URL = "jdbc:mysql://148.225.64.69:3306/db210215739";
    //private final String DB_URL = "jdbc:mysql://localhost:3306/COFFEES";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/Eventos";

    //Driver de JDBC que vamos a usar para conectarnos a la base de datos
    private final String DRIVER = "org.postgresql.Driver";
    private static Database DB = null;

    private Database() {
        super();
    }

    private Database(String user, String password) throws ClassNotFoundException {
        super();
        con = null;
        try {

            // Cargar el driver
           Class.forName(DRIVER);
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", password);
            // Abrir una conexion a la base de datos
            con = DriverManager.getConnection(DB_URL, props);

      
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            System.out.println("Codigo : " + ex.getErrorCode());
        }
    }

    // Abrir la conexión y regresar objeto Database
    public static Database getDatabase(String user, String pass) throws ClassNotFoundException {
        if (DB == null) {
            DB = new Database(user, pass);
        }
        return DB;
    }

    public ResultSet query(String sql) throws SQLException {

        ResultSet rs = null;
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        rs = statement.executeQuery(sql);

        return rs;
    }

    public ResultSet query(String sql, int scroll, int concur) throws SQLException {

        ResultSet rs = null;

        Statement statement = con.createStatement(scroll, concur);
        rs = statement.executeQuery(sql);

        return rs;
    }

    public int update(String sql) throws SQLException {
        int result = -1;

        Statement statement = con.createStatement( ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_UPDATABLE );
        result = statement.executeUpdate(sql);
        return result;
    }
    
    public DefaultTableModel ejecutaSelect(String consulta) throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        DefaultTableModel dtm = new DefaultTableModel();

        try {
            // Establece conexion con la base de datos
            con = DriverManager.getConnection(DB_URL,"Pulido", "Universo7788");

            // Crea un scroller
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            // Hace la consulta Select * Table
            rs = st.executeQuery(consulta);

            // Resultados de la tabla de la base de datos
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {

                // Se añade cada columna
                dtm.addColumn(rsmd.getColumnName(i));
            }
            int numeroRegistros = 0; // Se agregan los registros al modelo de datos
            if (rs.last()) { // Coloca el cursor en el último registro
                numeroRegistros = rs.getRow(); // Toma la cantidad de renglones
            }
            rs.beforeFirst(); // Coloca el cursor en el primer registro
            if (numeroRegistros > 0) {
                // Se crea un arreglo de Objetos ya que hay diferentes tipos de datos
                Object[] registros = new Object[rsmd.getColumnCount()];
                while (rs.next()) {
                    // Por cada columna obtiene el valor de la celda
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        // Toma los registros de cada celda
                        registros[i] = rs.getObject(i + 1);
                    }
                    // Añade los registros a los renglones
                    dtm.addRow(registros);
                }
            }

            // Regresa el modelo de la base de datos
            return dtm;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Excepción: " + ex.toString());
            System.out.println("Excepción: " + ex.toString());
        } finally {
            // Cierra las conexiones
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                System.out.println("Excepción: " + ex.toString());
            }

        }

        return null;
    }

}
