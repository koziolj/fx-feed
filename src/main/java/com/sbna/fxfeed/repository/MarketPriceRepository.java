package com.sbna.fxfeed.repository;

import com.sbna.fxfeed.model.MarketPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MarketPriceRepository extends CrudRepository<MarketPrice, Long> {

    @Query("select mp1 from MarketPrice mp1 where mp1.name =:name and mp1.time = (select max(mp2.time) from MarketPrice mp2 where mp2.name=mp1.name)")
    MarketPrice findLatestPrice(String name);

}