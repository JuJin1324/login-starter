package practice.login.sessionstarter.domain.members;

import practice.login.sessionstarter.domain.repositories.CommonRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/11/11
 */
public interface MemberRepository extends CommonRepository<Member, Long> {
    List<Member> findAll();

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findById(Long aLong);

    <S extends Member> S save(S entity);

    <S extends Member> void delete(S entity);
}
