package study;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao2 {

    private final JdbcClient jdbcClient;

    public MemberDao2(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void insertMember(Member member) {
        var sql = "insert into member(id, name, age, email) values (:id, :name, :age, :email);";
        jdbcClient.sql(sql)
                .param("id", member.id())
                .param("name", member.name())
                .param("age", member.age())
                .param("email", member.email())
                .update();
    }

    public Member selectMember(Long id) {
        var sql = "select id, name, age, email from member where id = :id";
        jdbcClient.sql(sql).param("id", id).query(getMemberRowMapper()).single();
    }

    private RowMapper<Object> getMemberRowMapper() {
        return (rs, rowNum) -> {
            var id = rs.getLong("id");
            var name = rs.getString("name");
            var age = rs.getInt("age");
            var email = rs.getString("email");
            return new Member(id, name, age, email);
        };
    }
}
