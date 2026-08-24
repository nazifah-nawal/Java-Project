package Database;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class InsertData {

    protected Connection connection;

    public InsertData(Connection connection) {
        this.connection = connection;
    }

   
    public abstract String insert();
    
    protected String handleSQLException(SQLException e) {
        if (e.getErrorCode() == 1062) {
            return e.getMessage();
        }

        e.printStackTrace();
        return "Error";
    }
}

