package com.oceanboa.dnc.summoner2service.repo;


import java.util.List;

import com.oceanboa.dnc.summoner2service.model.SumLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
//@RepositoryRestResource(collectionResourceRel = "rest/sumlogs", path = "rest/sumlogs")
public interface SumLogRepository extends PagingAndSortingRepository<SumLog, Long> {

    //custom query method
    List<SumLog> findByLastName(String lastName);
}
