package org.launchcode.giftlist.repositories;

import org.launchcode.giftlist.models.User;
import org.launchcode.giftlist.models.WishList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends CrudRepository<WishList, Integer> {
    List<WishList> findAllBylistOwner(User user);
}
