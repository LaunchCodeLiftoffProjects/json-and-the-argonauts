package org.launchcode.giftlist.repositories;

import org.launchcode.giftlist.models.Party;
import org.launchcode.giftlist.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends CrudRepository<Party, Integer> {
//  List<Party> findAllBylistOwner(User user);
}
