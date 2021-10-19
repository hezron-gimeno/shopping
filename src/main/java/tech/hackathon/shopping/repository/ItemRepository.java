package tech.hackathon.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tech.hackathon.shopping.domain.Item;

public interface ItemRepository extends CrudRepository<Item,Integer> {
}
