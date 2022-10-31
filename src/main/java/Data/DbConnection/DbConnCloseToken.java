package Data.DbConnection;

public class DbConnCloseToken {

    private boolean cancel = false;

    public void cancel() {
        cancel = true;
    }

    public boolean isCancel() {
        return cancel;
    }
}
