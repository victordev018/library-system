package database;

public class DBException extends RuntimeException{

    private final long serialVersionUUID = 1L;

    public DBException(String message){
        super(message);
    }
}
