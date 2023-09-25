package com.example.demo.Repositories;

import com.example.demo.Classes.DigitsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface DigitsRepository extends JpaRepository<DigitsDto, Integer> {
    @Modifying
    @Query(value="insert into interval_digits (start, last) values (:start,:last)",nativeQuery = true)
    @Transactional
    void save(@Param("start") int start, @Param("last")int last);

    @Query(value="SELECT * FROM INTERVAL_DIGITS  WHERE start IN ((SELECT Min(start) FROM INTERVAL_DIGITS ), (SELECT MIN (last) FROM INTERVAL_DIGITS ))",nativeQuery = true)
    @Transactional
    DigitsDto min();
}
