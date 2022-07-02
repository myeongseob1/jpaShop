package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    //변경감지에 의한 데이터 변경하는 방법 -> 실무에서는 merge 쓰지말고 변경감지를 써야함
    //merge로 업데이트를 할 경우 입력 안한 값은 null로 자동업데이트된다 -> 치명적인 장애로 이어질수 있다.
    @Transactional
    public Item updateItem(Long itemId,  String name,int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
        return findItem;
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public  Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
