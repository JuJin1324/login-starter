package practice.login.interceptorstarter.domain.members;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.login.interceptorstarter.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/11/11
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long createMember(MemberDto memberDto) {
        Member member = new Member(memberDto.getLoginId(), memberDto.getPassword(),
                memberDto.getName());
        memberRepository.save(member);
        return member.getId();
    }

    public List<MemberDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(MemberDto::toRead)
                .collect(Collectors.toList());
    }

    public MemberDto getSingleMember(Long memberId) {
        return memberRepository.findById(memberId)
                .map(MemberDto::toRead)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    public void updateMember(Long memberId, MemberDto memberDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(ResourceNotFoundException::new);
        member.update(memberDto.getName(), memberDto.getEmail());
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(ResourceNotFoundException::new);
        memberRepository.delete(member);
    }
}
