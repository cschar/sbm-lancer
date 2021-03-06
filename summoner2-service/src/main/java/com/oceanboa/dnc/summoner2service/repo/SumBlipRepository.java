package com.oceanboa.dnc.summoner2service.repo;

import java.util.List;

import com.oceanboa.dnc.summoner2service.model.SumBlip;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource
public interface SumBlipRepository extends PagingAndSortingRepository<SumBlip, Long> {

    List<SumBlip> findByName(@Param("name") String name);

}
