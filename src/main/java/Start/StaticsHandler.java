package Start;

import Data.DbConnection.DbConnection;
import Data.Prio.PriorityDbManager;

public class StaticsHandler {

    public static void init() {

        dbConnection = new DbConnection();
        pdbmgr = new PriorityDbManager();
        pdbmgr.LoadPriority();
    }

    private static DbConnection dbConnection;
    private static  PriorityDbManager pdbmgr;

    public static DbConnection getDbConnection() {
        return dbConnection;
    }

    public static PriorityDbManager getPdbmgr() {
        return pdbmgr;
    }
}
