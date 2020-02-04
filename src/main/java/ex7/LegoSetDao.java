package ex7;

import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RegisterBeanMapper(LegoSet.class)
public interface LegoSetDao extends SqlObject {

    static final String SELECT_ALL = """
                    SELECT l.number l_number, l.year l_year, l.pieces l_pieces, t.tag t_tag
                    FROM legoset l LEFT OUTER JOIN legoset_tags t ON l.number = t.number
                    """;

    @SqlUpdate("""
        CREATE TABLE legoset (
            number VARCHAR PRIMARY KEY,
            year INTEGER NOT NULL,
            pieces INTEGER NOT NULL
        )
        """
    )
    void createLegoSetTable();

    @SqlUpdate("""
        CREATE TABLE legoset_tags(
            number VARCHAR,
            tag VARCHAR,
            PRIMARY KEY (number, tag),
            FOREIGN KEY (number) REFERENCES legoset ON DELETE CASCADE
        )
        """
    )
    void createTagsTable();

    default void createTables() {
        createLegoSetTable();
        createTagsTable();
    }

    @SqlUpdate("INSERT INTO legoset VALUES (:number, :year, :pieces)")
    void insertLegoSet(@BindBean LegoSet legoSet);

    @SqlBatch("INSERT INTO legoset_tags VALUES (:number, :tag)")
    void insertLegoSetTags(@Bind("number") String number, @Bind("tag") Set<String> tags);

    default void insertLegoSetWithTags(LegoSet legoSet) {
        insertLegoSet(legoSet);
        insertLegoSetTags(legoSet.getNumber(), legoSet.getTags());
    }

    @SqlQuery("SELECT * FROM legoset WHERE number = :number")
    Optional<LegoSet> getLegoSet(@Bind("number") String number);

    @SqlQuery("SELECT * FROM legoset ORDER BY number")
    List<LegoSet> listLegoSets();

    default Optional<LegoSet> getLegoSetWithTags(String number) {
        return getHandle().createQuery(SELECT_ALL + " WHERE l.number = :number")
                .bind("number", number)
                .registerRowMapper(BeanMapper.factory(LegoSet.class, "l"))
                .reduceRows((Map<String, LegoSet> map, RowView rowView) -> {
                    LegoSet legoSet = map.computeIfAbsent(rowView.getColumn("l_number", String.class),
                            id -> rowView.getRow(LegoSet.class));
                    if (rowView.getColumn("t_tag", String.class) != null) {
                        String tag = rowView.getColumn("t_tag", String.class);
                        legoSet.addTag(tag);
                    }
                }).findFirst();
    }

    default List<LegoSet> listLegoSetsWithTags() {
        return getHandle().createQuery(SELECT_ALL)
                .registerRowMapper(BeanMapper.factory(LegoSet.class, "l"))
                .reduceRows((Map<String, LegoSet> map, RowView rowView) -> {
                    LegoSet legoSet = map.computeIfAbsent(rowView.getColumn("l_number", String.class),
                            id -> rowView.getRow(LegoSet.class));
                    if (rowView.getColumn("t_tag", String.class) != null) {
                        String tag = rowView.getColumn("t_tag", String.class);
                        legoSet.addTag(tag);
                    }
                }).collect(Collectors.toList());
    }

}