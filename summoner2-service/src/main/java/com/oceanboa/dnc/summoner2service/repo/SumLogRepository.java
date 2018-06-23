package com.oceanboa.dnc.summoner2service.repo;


import java.util.List;

import com.oceanboa.dnc.summoner2service.model.SumLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.OrderBy;


//@CrossOrigin
public interface SumLogRepository extends PagingAndSortingRepository<SumLog, Long> {

    //custom query method
    List<SumLog> findByLastName(String lastName);


    @OrderBy("id ASC")
    List<SumLog> findAll();

//    https://docs.spring.io/spring-data/rest/docs/current/reference/html/#_paging


    //    e.g. localhost:8080/resourcename/search/reverse?page=x
    @RestResource(path = "reverse")
    Page findAllByOrderByIdDesc(Pageable p);


}
