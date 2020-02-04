package ex8;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.Year;

public class Main {

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            LegoSetDao dao = handle.attach(LegoSetDao.class);
            dao.createTable();
            dao.insertLegoSet(new LegoSet("60073", Year.of(2015), 233));
            dao.insertLegoSet(new LegoSet("75211", Year.of(2018), 519));
            dao.insertLegoSet(new LegoSet("21034", Year.of(2017), 468));
            dao.listLegoSets().stream().forEach(System.out::println);
        }
    }

}
