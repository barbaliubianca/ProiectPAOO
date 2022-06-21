package game;

import java.sql.SQLException;

public class DataBaseException extends SQLException
{
    public DataBaseException(String message) {
        super(message);
    }
}

