package ru.news24.newsmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.news24.newsmanager.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByIdNewsType(Long idNewsType);
}
