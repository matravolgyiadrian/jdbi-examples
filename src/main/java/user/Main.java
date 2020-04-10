package user;


import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.sql.Types;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            UserDAO dao = handle.attach(UserDAO.class);
            dao.createTable();
            User user = User.builder()
                    .name("James Bond")
                    .username("007")
                    .password("password")
                    .gender(User.Gender.MALE)
                    .enabled(Boolean.TRUE)
                    .dob(LocalDate.parse("1920-11-11"))
                    .build();

            dao.insert(user);

            user = User.builder()
                    .name("Sherlock Holmes")
                    .username("sholmes")
                    .password("password")
                    .gender(User.Gender.MALE)
                    .enabled(Boolean.TRUE)
                    .dob(LocalDate.parse("1980-02-12"))
                    .build();

            dao.insert(user);

            user = User.builder()
                    .name("Agent 47")
                    .username("hitman")
                    .password("password")
                    .gender(User.Gender.MALE)
                    .enabled(Boolean.FALSE)
                    .dob(LocalDate.parse("2000-03-02"))
                    .build();

            dao.insert(user);

            System.out.println("<<findByID>> "+dao.findById(3));
            System.out.println("<<finByUsername>> "+dao.findByUsername("007"));
            dao.delete(user);
            dao.list().stream().forEach(System.out::println);
        }
    }
}
