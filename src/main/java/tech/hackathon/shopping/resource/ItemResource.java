package tech.hackathon.shopping.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.hackathon.shopping.domain.Item;
import tech.hackathon.shopping.service.ItemService;

@RestController
@RequestMapping("/api")
public class ItemResource {

    private final ItemService itemService;

    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/item")
    public Item save(@RequestBody Item item){
        return itemService.save(item);
    }
}
