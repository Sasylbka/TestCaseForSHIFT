package com.example.demo.Repositories;

import com.example.demo.Classes.LettersDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LettersRepository extends JpaRepository<LettersDto, Integer> {
    @Modifying
    @Query(value="insert into interval_letters (start, last) values (:start,:last)", nativeQuery = true)
    @Transactional
    void save(@Param("start") char start,@Param("last") char last);
    @Query(value="SELECT * FROM INTERVAL_LETTERS  WHERE start IN ((SELECT Min(start) FROM INTERVAL_LETTERS ), (SELECT MIN (last) FROM INTERVAL_LETTERS ))",nativeQuery = true)
    @Transactional
    LettersDto min();
}
