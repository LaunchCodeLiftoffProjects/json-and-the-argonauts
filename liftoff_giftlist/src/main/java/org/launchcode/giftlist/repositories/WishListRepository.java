package org.launchcode.giftlist.repositories;

import org.launchcode.giftlist.models.WishList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends CrudRepository<WishList, Integer> {

}
