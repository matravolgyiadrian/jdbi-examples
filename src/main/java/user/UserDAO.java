package user;

import org.jdbi.v3.sqlobject.config.RegisterArgumentFactory;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterColumnMapper(LocalDateColumnMapper.class)
@RegisterArgumentFactory(LocalDateArgumentFactory.class)
@RegisterBeanMapper(User.class)
public interface UserDAO {

    @SqlUpdate("""
        CREATE TABLE user (
            id IDENTITY PRIMARY KEY,
            username VARCHAR NOT NULL,
            password VARCHAR NOT NULL,
            name VARCHAR NOT NULL,
            email VARCHAR,
            gender VARCHAR,
            dob VARCHAR,
            enabled BOOLEAN

            )
        """)
    void createTable();

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO user VALUES (:id, :username, :password, :name, :email, :gender, :dob, :enabled)")
    Long insert(@BindBean User user);

    @SqlQuery("SELECT * FROM user WHERE id = :id")
    Optional<User> findById(@Bind("id") long id);

    @SqlQuery("SELECT * FROM user WHERE username = :username")
    Optional<User> findByUsername(@Bind("username") String username);

    @SqlUpdate("DELETE FROM user WHERE username = :username")
    void delete(@BindBean User user);

    @SqlQuery("SELECT * FROM user")
    List<User> list();
}
