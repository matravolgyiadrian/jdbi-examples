package ex4;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;

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
            batch.bind("number", "60073")
                    .bind("year", 2015)
                    .bind("pieces", 233)
                    .add();
            batch.bind("number", "75211")
                    .bind("year", 2018)
                    .bind("pieces", 519)
                    .add();
            batch.bind("number", "21034")
                    .bind("year", 2017)
                    .bind("pieces", 468)
                    .add();
            batch.execute();
            int totalPieces = handle.createQuery("SELECT SUM(pieces) FROM legoset").mapTo(Integer.class).one();
            System.out.println(totalPieces);
        }
    }

}
