package Data.DbConnection;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DbConnection  {


    private String path;

    public DbConnection() {
        URL resource = getClass().getResource("/db.db");
        path = null;
        try {
            path = new File(resource.toURI()).getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection open() {

        try {
            Connection c = DriverManager.getConnection(String.format("jdbc:sqlite:%s",path));
            System.out.println("Connected");
            return c;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public void executeQuery(String query)   {
        Connection connection = open();
        try {
            Statement s = connection.createStatement();
            s.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }

    public void executeQueryAsync(String query) {
        Connection connection = open();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Statement s = connection.createStatement();
                        s.executeQuery(query);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }finally {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            thread.start();
    }

    public ResultSet executeReader(String query, DbConnCloseToken c) {
        Connection connection = open();
        Statement s;
        try {
            s = connection.createStatement();
            ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
            ses.scheduleAtFixedRate(()-> {
                if(c.isCancel()) {
                    try {
                        s.close();
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
            }, 1000, 500, TimeUnit.MILLISECONDS);
            return s.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public Connection getConnection() {
        return open();
    }
}
