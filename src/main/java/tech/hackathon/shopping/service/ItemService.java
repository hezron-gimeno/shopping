package tech.hackathon.shopping.service;

import org.springframework.stereotype.Service;
import tech.hackathon.shopping.domain.Item;
import tech.hackathon.shopping.repository.ItemRepository;

import java.time.LocalDate;

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
}
