package ru.news24.newsmanager.repository;

import ru.news24.newsmanager.model.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsTypeRepository extends JpaRepository<NewsType, Long> {
}
