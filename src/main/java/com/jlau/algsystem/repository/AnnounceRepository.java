package com.jlau.algsystem.repository;

import com.jlau.algsystem.entity.Announce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cxr1205628673 on 2020/5/9.
 */
@Repository
public interface AnnounceRepository extends JpaRepository<Announce,Integer>{

}
