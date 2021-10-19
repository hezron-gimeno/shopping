package tech.hackathon.shopping.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.hackathon.shopping.domain.Item;
import tech.hackathon.shopping.repository.ItemRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item save(Item item) {
        item.setLastBought(LocalDate.now());
        LocalDate completionDate=calculateDepletionDate(item);
        item.setExpectedDepletionDate(completionDate);

        log.debug("About to save item : {}",item);

        return itemRepository.save(item);
    }

    public LocalDate calculateDepletionDate(Item item){

        LocalDate completionDate=null;
        switch (item.getPeriodType()){
            case DAY:
                completionDate=item.getLastBought().plusDays(item.getLastingPeriodUnit());
                break;
            case WEEK:
                completionDate=item.getLastBought().plusWeeks(item.getLastingPeriodUnit());
                break;
            case MONTH:
                completionDate=item.getLastBought().plusMonths(item.getLastingPeriodUnit());
                break;
            case YEAR:
                completionDate=item.getLastBought().plusYears(item.getLastingPeriodUnit());
                break;
        }
        return completionDate;
    }

    public List<Item> findAll() {

        log.info("About to find all items");

        Iterable<Item> itemIterable = itemRepository.findAll();

        log.info("Items : {}",itemIterable);

        List<Item> items = StreamSupport.stream(itemIterable.spliterator(),false).collect(Collectors.toList());

        return items;
    }

    public Item update(Item item) {

        if(item.getId()==null){
            save(item);
        }

        return itemRepository.save(item);
    }

    public Item findOneById(Integer id){

        Item item = itemRepository.findById(id).orElseThrow();

        return item;
    }
}
