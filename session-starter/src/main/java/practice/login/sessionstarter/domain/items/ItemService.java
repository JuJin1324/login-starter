package practice.login.sessionstarter.domain.items;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.login.sessionstarter.dtos.ItemDto;
import practice.login.sessionstarter.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/11/14
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Long createItem(ItemDto itemDto) {
        Item item = new Item(itemDto.getItemName(), itemDto.getPrice(), itemDto.getQuantity());
        itemRepository.save(item);
        return item.getId();
    }

    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream()
                .map(ItemDto::toRead)
                .collect(Collectors.toList());
    }

    public ItemDto getSingleItem(Long itemId) {
        return itemRepository.findById(itemId)
                .map(ItemDto::toRead)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    public void updateItem(Long itemId, ItemDto itemDto) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(ResourceNotFoundException::new);
        item.update(itemDto.getItemName(), itemDto.getPrice(), itemDto.getQuantity());
    }

    @Transactional
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(ResourceNotFoundException::new);
        itemRepository.delete(item);
    }
}
