package me.ricky.guides.securityguides.repository;

import me.ricky.guides.securityguides.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    @Query(value = "from Notice n where CURDATE() BETWEEN  n.noticBegDt AND n.noticEndDt")
    List<Notice> findAllActiveNotices();
}