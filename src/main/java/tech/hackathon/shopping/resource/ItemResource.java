package tech.hackathon.shopping.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.hackathon.shopping.domain.Item;
import tech.hackathon.shopping.service.ItemService;

import java.util.List;

@Slf4j
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

    @GetMapping("/item")
    public List<Item> findAll(){

        return itemService.findAll();
    }

    @PutMapping("/item")
    public Item update(@RequestBody Item item){
        return itemService.update(item);
    }

    @GetMapping("/item/{id}")
    public Item getOne(@PathVariable Integer id){
        log.info("Item with id : {}",id);
        return itemService.findOneById(id);
    }
}
