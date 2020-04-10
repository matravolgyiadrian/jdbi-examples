package user;

import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class LocalDateColumnMapper implements ColumnMapper<LocalDate> {

    @Override
    public LocalDate map(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException {
        return LocalDate.parse(r.getString(columnNumber));
    }
}
