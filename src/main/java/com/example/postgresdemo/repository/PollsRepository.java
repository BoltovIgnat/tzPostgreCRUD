package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Polls;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PollsRepository extends JpaRepository<Polls, Long> {

    List<Polls> findByByNameOrActiveOrDateStartOrderByNameDesc(String name, Boolean active, Date datestart, Pageable pageable);

    List<Polls> findByByNameOrActiveOrDateStartOrderByNameAsc(String name, Boolean active, Date datestart, Pageable pageable);

    List<Polls> findByByNameOrActiveOrDateStartOrderByDateStartDesc(String name, Boolean active, Date datestart, Pageable pageable);

    List<Polls> findByByNameOrActiveOrDateStartOrderByDateStartAsc(String name, Boolean active, Date datestart, Pageable pageable);

}
