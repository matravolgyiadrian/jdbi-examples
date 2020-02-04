package ex1;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

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
            handle.execute("INSERT INTO legoset VALUES ('60073', 2015, 233)");
            handle.execute("INSERT INTO legoset VALUES ('75211', 2018, 519)");
            handle.execute("INSERT INTO legoset VALUES ('21034', 2017, 468)");
            int numOfLegoSets = handle.createQuery("SELECT COUNT(*) FROM legoset").mapTo(Integer.class).one();
            System.out.println(numOfLegoSets);
            int totalPieces = handle.createQuery("SELECT SUM(pieces) FROM legoset").mapTo(Integer.class).one();
            System.out.println(totalPieces);
        }
    }

}