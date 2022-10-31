package Data.Prio;

import Data.DbConnection.DbConnCloseToken;
import Data.DbConnection.DbConnection;
import Start.StaticsHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PriorityDbManager {

    private  ArrayList<Priority> priorities = new ArrayList<Priority>();
    public void LoadPriority()
    {
        DbConnCloseToken c = new DbConnCloseToken();
       ResultSet rs = StaticsHandler.getDbConnection().executeReader("SELECT * FROM priority",c);
        try {
            priorities.add(new Priority(rs.getInt("id"), rs.getString("name"), rs.getInt("level"), rs.getString("description"), rs.getInt("colorid")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Priority priority : priorities) {
            System.out.println(priority.toString());
        }

        c.cancel();
    }

    public void AddPriority(Priority p) {
        DbConnCloseToken c = new DbConnCloseToken();
        ResultSet rs = StaticsHandler.getDbConnection().executeReader("SELECT * FROM sqlite_sequence WHERE name like 'priority'", c);
        int id;
        try {
            id = rs.getInt("seq");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        c.cancel();
        p.setId(id + 1);
        StaticsHandler.getDbConnection().executeQuery("INSERT INTO priority(name, level, description, colorid) VALUES ('"+p.getName()+"', "+p.getLevel()+ ", '" +p.getDescription()+"', "+p.getColorId()+")");
        priorities.add(p);
        for (Priority priority : priorities) {
            System.out.println(priority.toString());
        }
        c.cancel();
    }

    public ArrayList<Priority> getPriorities() {
        return priorities;
    }
}
