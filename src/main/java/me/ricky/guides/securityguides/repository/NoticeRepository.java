package me.ricky.guides.securityguides.repository;

import me.ricky.guides.securityguides.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}