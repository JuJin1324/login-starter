package practice.login.sessionstarter.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.login.sessionstarter.domain.members.MemberRepository;
import practice.login.sessionstarter.domain.members.MemberDto;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/11/10
 */

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public MemberDto login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .map(MemberDto::toRead)
                .orElse(null);
    }
}
