package ex3;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Update;

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
            Update insert = handle.createUpdate("INSERT INTO legoset VALUES (:number, :year, :pieces)");
            insert.bind("number", "60073")
                    .bind("year", 2015)
                    .bind("pieces", 233)
                    .execute();
            insert.bind("number", "75211")
                    .bind("year", 2018)
                    .bind("pieces", 519)
                    .execute();
            insert.bind("number", "21034")
                    .bind("year", 2017)
                    .bind("pieces", 468)
                    .execute();
            int totalPieces = handle.createQuery("SELECT SUM(pieces) FROM legoset").mapTo(Integer.class).one();
            System.out.println(totalPieces);
        }
    }

}
