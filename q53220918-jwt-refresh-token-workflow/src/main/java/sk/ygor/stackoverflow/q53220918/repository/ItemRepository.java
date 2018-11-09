package sk.ygor.stackoverflow.q53220918.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.ygor.stackoverflow.q53220918.domain.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

}
