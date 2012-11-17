
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class administradorBD {

    private String CONNECTION_STRING = "jdbc:mysql://";
    private String URL = "localhost:3306/test";
    private String USER = "roger";
    private String PASSWORD = "toast";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private Connection conn;
    private static administradorBD singleton;
    public static final String SELECT_STRING = "";

    private administradorBD() {
    }

    public static administradorBD getInstance() {
        if (singleton == null) {
            singleton = new administradorBD();
        }
        return singleton;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    private Connection getConnection(boolean resetConnection) throws Exception {
        if (resetConnection) {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(CONNECTION_STRING + URL, USER, PASSWORD);
        } else {
            if (conn == null) {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(CONNECTION_STRING + URL, USER, PASSWORD);
            }
        }
        return conn;
    }

    public ResultSet selectQuery(String p_query, Properties p_parameters) throws Exception {
        PreparedStatement s = getConnection(false).prepareStatement("");
        String whereClause = "";
        if (p_parameters != null) {
            int index = 1;
            whereClause = " WHERE ";
            for (String key : p_parameters.stringPropertyNames()) {
                String value = p_parameters.getProperty(key);
                whereClause += key + "=? AND";
                s.setString(index, value);
                index++;
            }
            whereClause = whereClause.substring(0, whereClause.length() - 4);
        }
        ResultSet rs = s.executeQuery(p_query + whereClause);
        return rs;
    }

    public boolean updateQuery(String p_query, String p_table, Properties p_parameters) throws Exception {
        PreparedStatement s = getConnection(false).prepareStatement("");
        String whereClause = "";
        if (p_parameters != null) {
            int index = 1;
            whereClause = " WHERE ";
            for (String key : p_parameters.stringPropertyNames()) {
                String value = p_parameters.getProperty(key);
                whereClause += key + "=? AND";
                s.setString(index, value);
                index++;
            }
            whereClause = whereClause.substring(0, whereClause.length() - 4);
        }
        return s.execute(p_query + whereClause);
    }

    public boolean insertQuery(String p_query, String[] p_parameters) throws Exception {
        PreparedStatement s = getConnection(false).prepareStatement("");
        if (p_parameters != null) {
            int index = 1;
            for (String value : p_parameters) {
                s.setString(index, value);
                index++;
            }
        }
        return s.execute(p_query);
    }

    public boolean deleteQuery(String p_table, String p_id) throws Exception {
        String query = String.format("DELETE FROM %s WHERE %s_ID=%s", p_table, p_table, p_id);
        PreparedStatement s = getConnection(false).prepareStatement(query);
        return s.execute();
    }

    public boolean login(String p_username, String p_password) throws Exception {
        boolean entrada = false;
        String username = "", password = "";
        String sqlStatement = "SELECT * FROM USER WHERE USERNAME=? AND PASSWORD=?";
        PreparedStatement st = getConnection(false).prepareStatement(sqlStatement);
        st.setString(1, p_username);
        st.setString(2, p_password);
        ResultSet rs = getConnection(false).createStatement().executeQuery(sqlStatement);
        while (rs.next()) {
            username = rs.getString("nombreUsuario");
            password = rs.getString("password");
        }
        if (username.isEmpty() == false && password.isEmpty() == false) {
            entrada = true;
        } else {
            entrada = false;
        }
        return entrada;
    }
}
