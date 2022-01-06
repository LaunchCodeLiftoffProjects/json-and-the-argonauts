package org.launchcode.giftlist.repositories;

import org.launchcode.giftlist.models.Party;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends CrudRepository<Party, Integer> {

}
