package practice.login.filterstarter.domain.members;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/11/11
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDto implements Serializable {
    private Long   id;
    private String loginId;
    private String password;
    private String name;
    private String email;

    @Builder
    private MemberDto(Long id, String loginId, String password, String name, String email) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public static MemberDto toCreate(String loginId, String password, String name, String email) {
        return MemberDto.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .email(email)
                .build();
    }

    public static MemberDto toUpdate(String name, String email) {
        return MemberDto.builder()
                .name(name)
                .email(email)
                .build();
    }

    public static MemberDto toRead(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .name(member.getName())
                .build();
    }
}
