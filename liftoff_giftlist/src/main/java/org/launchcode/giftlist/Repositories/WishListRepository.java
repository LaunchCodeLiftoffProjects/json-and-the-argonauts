package org.launchcode.giftlist.Repositories;

import org.launchcode.giftlist.Models.WishList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends CrudRepository<WishList, Integer> {

}
