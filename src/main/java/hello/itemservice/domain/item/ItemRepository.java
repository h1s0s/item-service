package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {//저장소 역할

    private static final Map<Long, Item> store = new HashMap<>(); //static
    /* 쓰레드 환경에서 싱글톤 패턴이 아닌 경우에는 ConcurrentHashMap을 사용하는게 좋음
    * long은 어토믹롱 */
    private static long sequence = 0L; //staic

    public Item save(Item item) {
        //저장 메소드, id값을 시퀀스로 부여하고 store Map에 넣음
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        //아이디 값에 해당하는 item 객체를 반환하는 메서드
        return store.get(id);
    }

    public List<Item> findAll(){
        //store에 저장된 값들을 배열로 리턴해주는 메서드
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam){
        //sotre ㅇ
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        // store 내부 값들을 지우는 메서드
        store.clear();
    }
}
