package Database;

import java.sql.Connection;

public abstract class InsertData {

    protected Connection connection;

    public InsertData(Connection connection) {
        this.connection = connection;
    }

   
    public abstract String insert();
}

