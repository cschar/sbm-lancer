package com.oceanboa.dnc.summoner2service.repo;


import java.util.List;

import com.oceanboa.dnc.summoner2service.model.SumLog;
import org.springframework.data.repository.CrudRepository;

public interface SumLogRepository extends CrudRepository<SumLog, Long> {

    //custom query method
    List<SumLog> findByLastName(String lastName);
}
