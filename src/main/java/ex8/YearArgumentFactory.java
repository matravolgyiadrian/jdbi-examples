package ex8;

import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;

import java.sql.Types;
import java.time.Year;

public class YearArgumentFactory extends AbstractArgumentFactory<Year> {

    public YearArgumentFactory() {
        super(Types.INTEGER);
    }

    @Override
    protected Argument build(Year value, ConfigRegistry config) {
        return (position, statement, ctx) -> statement.setInt(position, value.getValue());
    }

}
