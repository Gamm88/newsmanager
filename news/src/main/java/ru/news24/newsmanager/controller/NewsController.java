package ru.news24.newsmanager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ru.news24.newsmanager.exception.NoEntityException;
import ru.news24.newsmanager.model.News;
import ru.news24.newsmanager.model.NewsType;
import ru.news24.newsmanager.repository.NewsRepository;
import ru.news24.newsmanager.repository.NewsTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class NewsController {

    @Autowired
    NewsRepository newsRepository;
    @Autowired
    NewsTypeRepository newsTypeRepository;

    // создание новости - Create
    @PostMapping("/news")
    public ResponseEntity<News> createNews(@RequestBody News news) {
        try {
            long newsTypeId = news.getIdNewsType();
            NewsType newsType = newsTypeRepository.findById(newsTypeId).
                    orElseThrow(() -> new NoEntityException(newsTypeId));
            News _news = new News();
            _news.setName(news.getName());
            _news.setShortDescription(news.getShortDescription());
            _news.setDescription(news.getDescription());
            _news.setIdNewsType(news.getIdNewsType());
            _news.setNewsType(newsType);
            return new ResponseEntity<>(newsRepository.save(_news), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // получение новости по ID - Read
    @GetMapping("/news/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable("id") long id) {
        Optional<News> newsData = newsRepository.findById(id);

        if (newsData.isPresent()) {
            return new ResponseEntity<>(newsData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // получить список всех новостей - ReadAll
    // или новостей только определённого типа (параметр: id типа новости)
    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews(@RequestParam(required = false) Long idNewsType) {
        try {
            List<News> news = new ArrayList<News>();
            if (idNewsType == null)
                newsRepository.findAll().forEach(news::add);
            else
                newsRepository.findByIdNewsType(idNewsType).forEach(news::add);
            if (news.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(news, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // обновление новость по ID - Update
    @PutMapping("/news/{id}")
    public ResponseEntity<News> updateNews(@PathVariable("id") long id, @RequestBody News news) {
        Optional<News> newsData = newsRepository.findById(id);

        if (newsData.isPresent()) {
            try {
                long newsTypeId = news.getIdNewsType();
                NewsType newsType = newsTypeRepository.findById(newsTypeId).
                        orElseThrow(() -> new NoEntityException(newsTypeId));
                News _news = new News();
                _news.setName(news.getName());
                _news.setShortDescription(news.getShortDescription());
                _news.setDescription(news.getDescription());
                _news.setIdNewsType(news.getIdNewsType());
                _news.setNewsType(newsType);
                return new ResponseEntity<>(newsRepository.save(_news), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // удаление типа новости по ID - Delete
    @DeleteMapping("/news/{id}")
    public ResponseEntity<HttpStatus> deleteNews(@PathVariable("id") long id) {
        try {
            newsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // удаление типа всех новостей - DeleteAll
    @DeleteMapping("/news")
    public ResponseEntity<HttpStatus> deleteAllNews() {
        try {
            newsRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}