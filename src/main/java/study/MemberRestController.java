package study;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {

    private final MemberDao2 memberDao;

    public MemberRestController(MemberDao2 memberDao) {
        this.memberDao = memberDao;
    }

    @GetMapping("/api/members")
    public void insertMember() {
        var member = new Member(1L, "choi", 20, "test@email.com");
        memberDao.insertMember(member);
    }

    @GetMapping("/api/members/{id}")
    public Member selectMember(@PathVariable Long id) {
        return memberDao.selectMember(id);
    }

}
