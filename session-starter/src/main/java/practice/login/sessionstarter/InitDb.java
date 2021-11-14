package practice.login.sessionstarter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import practice.login.sessionstarter.domain.items.Item;
import practice.login.sessionstarter.domain.items.ItemRepository;
import practice.login.sessionstarter.domain.members.Member;
import practice.login.sessionstarter.domain.members.MemberRepository;

import javax.annotation.PostConstruct;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/11/14
 */

@Component
@RequiredArgsConstructor
public class InitDb {
    private final MemberRepository memberRepository;
    private final ItemRepository   itemRepository;

    @PostConstruct
    public void postConstruct() {
        initMembers();
        initItems();
    }

    private void initMembers() {
        memberRepository.save(new Member("test", "test!", "테스터"));
    }

    private void initItems() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
