package org.launchcode.giftlist.Repositories;

import org.launchcode.giftlist.Models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

}
