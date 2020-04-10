package user;

import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;

import java.sql.Types;
import java.time.LocalDate;

public class LocalDateArgumentFactory extends AbstractArgumentFactory<LocalDate> {

    public LocalDateArgumentFactory() {
        super(Types.VARCHAR);
    }

    @Override
    public Argument build(LocalDate value, ConfigRegistry config) {
        return (position, statement, ctx) -> statement.setString(position, value.toString());
    }
}
