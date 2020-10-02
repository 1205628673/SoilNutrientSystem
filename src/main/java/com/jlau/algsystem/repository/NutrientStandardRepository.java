package com.jlau.algsystem.repository;

import com.jlau.algsystem.entity.NutrientStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cxr1205628673 on 2020/5/13.
 */
@Repository
public interface NutrientStandardRepository extends JpaRepository<NutrientStandard,Integer>{
}
