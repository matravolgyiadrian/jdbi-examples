package ex5;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        try (Handle handle = jdbi.open()) {
            handle.execute("""
                    CREATE TABLE legoset (
                    	number VARCHAR PRIMARY KEY,
                    	year INTEGER NOT NULL,
                    	pieces INTEGER NOT NULL
                    )
                    """
            );
            PreparedBatch batch = handle.prepareBatch("INSERT INTO legoset VALUES (:number, :year, :pieces)");
            batch.bindBean(new LegoSet("60073", 2015, 233)).add();
            batch.bindBean(new LegoSet("75211", 2018, 519)).add();
            batch.bindBean(new LegoSet("21034", 2017, 468)).add();
            batch.execute();
            List<LegoSet> legoSets = handle.createQuery("SELECT * FROM legoset WHERE year = :year ORDER BY number")
                    .bind("year", 2015)
                    .mapToBean(LegoSet.class)
                    .list();
            legoSets.stream().forEach(System.out::println);
        }
    }

}
