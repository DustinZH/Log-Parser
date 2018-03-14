package com.ef.db;

import com.ef.db.mysql.MySQLConnection;

/**
 * Created by dustin on 2018/3/10.
 */

public class DBConnectionFactory {
    private static final String DEFAULT_DB = "mysql";

    /**
     *
     * @param db name of different db
     * @return return db connection instance
     */
    public static DBConnection getDBConnection(String db) {
        switch (db) {
            case "mysql":
                return MySQLConnection.getInstance();
            // You may try other dbs and add them here.
            default:
                throw new IllegalArgumentException("Invalid db " + db);
        }
    }

    /**
     * The Default db is MySQL
     * @return return MySQL connection instance
     */
    public static DBConnection getDBConnection(){
        return getDBConnection(DEFAULT_DB);
    }


}
