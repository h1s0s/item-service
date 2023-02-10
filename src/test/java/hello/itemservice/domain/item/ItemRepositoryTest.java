package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @Test
    void save(){
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());

        assertThat(findItem).isEqualTo(savedItem);//두개가 같은지 테스트하라
    }

    @Test
    void findAll(){
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        //when
        List<Item> items = itemRepository.findAll();

        //then
        assertThat(items).hasSize(2);
        assertThat(items).contains(itemA, itemB);
    }

    @Test
    void updateTest() {
        //given
        Item itemA = new Item("itemA", 10000, 10);

        itemRepository.save(itemA);

        //when
        Item updateParam = new Item("itemB", 20000, 20);
        itemRepository.update(itemA.getId(), updateParam);

        //then
        Item foundItem = itemRepository.findById(itemA.getId());

        assertThat(foundItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(foundItem.getQuantity()).isEqualTo(updateParam.getQuantity());
        assertThat(foundItem.getPrice()).isEqualTo(updateParam.getPrice());
    }

    @AfterEach //모든 테스트가 끝난 후에 동작하게 하는 어노테이션
    void afterEach() {
        itemRepository.clearStore();
    }
}
