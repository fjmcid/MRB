package Negocio;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class administradorBD {

    private String CONNECTION_STRING = "jdbc:mysql://";
    private String URL = "localhost:3306/MRB";
    private String USER = "root";
    private String PASSWORD = "admin";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private Connection conn;
    private static administradorBD singleton;
    public static final String SELECT_STRING_MRB = "SELECT * FROM MRB";
    public static final String SELECT_STRING_DISCREPANCY_CODE = "SELECT * FROM DEFECT WHERE DEFECT_ID < 19 AND DEFECT_ID > 0";
    public static final String SELECT_STRING_MODELS="SELECT MODEL_ID, MODEL_TITLE FROM MODEL";
    public static final String SELECT_STRING_DEFECT="SELECT DEFECT_ID, DEFECT_TITLE FROM DEFECT WHERE DEFECT_ID < 19 AND DEFECT_ID > 0";
    public static final String SelECT_STRING_PARTXDEFECT="SELECT PART_ID FROM DEFECT_X_PART";
    
    private administradorBD() {
    }

    public boolean Connect() throws Exception {
        return (getConnection(true) != null);
    }

    public void Disconnect() throws Exception {
        conn = null;
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

    public int insertQuery(String p_table, Properties p_parameters) throws Exception {
        if (p_parameters != null) {
            PreparedStatement s = getConnection(false).prepareStatement("");
            String p_query = String.format("INSERT INTO %s ", p_table);
            if (p_parameters != null) {
                int index = 1;
                String columns = "(";
                String values = "(";
                for (Object value : p_parameters.keySet()) {
                    String val = (String) value;
                    if (val != null) {
                        columns += val + ",";
                        values += "?,";
                        s.setString(index, p_parameters.getProperty(val));
                        index++;
                    }
                }
                columns = columns.substring(0, columns.length() - 1) + ")";
                values = values.substring(0, values.length() - 1) + ")";
                p_query += String.format("%s VALUES %s", columns, values);
            }
            return s.executeUpdate(p_query, PreparedStatement.RETURN_GENERATED_KEYS);
        } else {
            throw new Exception("No parameters where added to the insert query.");
        }
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

    public String[] getColumnNames(ResultSet rs) throws Exception {
        String[] columnNames = null;
        if (rs != null && rs.next()) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            columnNames = new String[numColumns];
            for (int i = 1; i < numColumns + 1; i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
            }
        }
        return columnNames;
    }

    public String[][] getResultSetData(ResultSet rs) throws Exception {
        String[][] resultSetData = null;
        if (rs != null && rs.next()) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            String[] columnNames = new String[numColumns];
            for (int i = 1; i < numColumns + 1; i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
            }
            List<String[]> rows = new ArrayList<String[]>();
            while (rs.next()) {
                String[] row = new String[numColumns];
                for (int i = 0; i < columnNames.length; i++) {
                    row[i] = rs.getString(columnNames[i]);
                }
                rows.add(row);
            }
            rs.close();
            resultSetData = new String[numColumns][rows.size() + 1];

            for (int i = 0; i < rows.size(); i++) {
                String[] row = null;
                if (i == 0) {
                    row = columnNames;
                } else {
                    row = rows.get(i - 1);
                }
                for (int j = 0; j < columnNames.length; j++) {
                    resultSetData[i][j] = row[j];
                }
            }
        }
        return resultSetData;
    }
}
