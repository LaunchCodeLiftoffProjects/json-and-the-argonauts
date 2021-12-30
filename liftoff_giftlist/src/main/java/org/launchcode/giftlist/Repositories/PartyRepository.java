package org.launchcode.giftlist.Repositories;

import org.launchcode.giftlist.Models.Party;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends CrudRepository<Party, Integer> {

}
