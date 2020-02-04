package ex8;

import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class YearColumnMapper implements ColumnMapper<Year> {

    @Override
    public Year map(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException {
        return Year.of(r.getInt(columnNumber));
    }

}
