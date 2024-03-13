package db;

import java.sql.*;
import java.sql.DriverManager;

public class demo {

        public static void main(String[] args) {

            String sql = "select * UserID from Users";
            String url = "jdbc:SqLite://localhost/Chat app/src/db/loginDB";
            String username = "MySQL81";
            String password = "3606";

            try {

                Connection conn = DriverManager.getConnection(url, username, password);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }

}
