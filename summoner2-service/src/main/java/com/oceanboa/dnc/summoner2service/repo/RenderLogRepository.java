package com.oceanboa.dnc.summoner2service.repo;


import java.util.List;

import com.oceanboa.dnc.summoner2service.model.RenderLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.OrderBy;


//@CrossOrigin
public interface RenderLogRepository extends PagingAndSortingRepository<RenderLog, Long> {

    //custom query method
    List<RenderLog> findByLastName(String lastName);


    @OrderBy("id ASC")
    List<RenderLog> findAll();

//    https://docs.spring.io/spring-data/rest/docs/current/reference/html/#_paging


    //    e.g. localhost:8080/resourcename/search/reverse?page=x
    @RestResource(path = "reverse")
    Page findAllByOrderByIdDesc(Pageable p);


}
