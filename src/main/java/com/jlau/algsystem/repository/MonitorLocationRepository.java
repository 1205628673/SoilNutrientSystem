package com.jlau.algsystem.repository;

import com.jlau.algsystem.entity.MonitorLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cxr1205628673 on 2020/4/30.
 */
@Repository
public interface MonitorLocationRepository extends JpaRepository<MonitorLocation,Integer>{
    @Query(value = "SELECT m FROM MonitorLocation m JOIN FETCH m.nutrient")
    List<MonitorLocation> findAllMonitors();

    @Query(value = "SELECT m FROM MonitorLocation m JOIN  m.nutrient")
    Page<MonitorLocation> findAllMonitorsByPage(Pageable pageable);
}
