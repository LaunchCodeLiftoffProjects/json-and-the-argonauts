package org.launchcode.giftlist.repositories;

import org.launchcode.giftlist.models.Item;
import org.launchcode.giftlist.models.WishList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    List<Item> findAllBywishList(WishList wishList);
}
