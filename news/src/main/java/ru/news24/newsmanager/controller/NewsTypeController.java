package ru.news24.newsmanager.controller;

import java.util.List;
import java.util.Optional;

import ru.news24.newsmanager.model.NewsType;
import ru.news24.newsmanager.repository.NewsTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class NewsTypeController {

    @Autowired
    NewsTypeRepository newsTypeRepository;

    // создание типа новости - Create
    @PostMapping("/newstype")
    public ResponseEntity<NewsType> createNewsType(@RequestBody NewsType newsType) {
        try {
            NewsType _newsType = new NewsType();
            _newsType.setName(newsType.getName());
            _newsType.setColor(newsType.getColor());
            newsTypeRepository.save(_newsType);
            return new ResponseEntity<>(_newsType, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // получение типа новости по ID - Read
    @GetMapping("/newstype/{id}")
    public ResponseEntity<NewsType> getNewsTypeById(@PathVariable("id") long id) {
        Optional<NewsType> newsTypeData = newsTypeRepository.findById(id);

        if (newsTypeData.isPresent()) {
            return new ResponseEntity<>(newsTypeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // получить список всех типов новостей - ReadAll
    @GetMapping("/newstype")
    public ResponseEntity<List<NewsType>> getAllNewsType() {
        try {
            if (newsTypeRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(newsTypeRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // обновление типа новости по ID - Update
    @PutMapping("/newstype/{id}")
    public ResponseEntity<NewsType> updateNewsType(@PathVariable("id") long id, @RequestBody NewsType newsType) {
        Optional<NewsType> newsData = newsTypeRepository.findById(id);

        if (newsData.isPresent()) {
            NewsType _newsType = newsData.get();
            _newsType.setName(newsType.getName());
            _newsType.setColor(newsType.getColor());
            return new ResponseEntity<>(newsTypeRepository.save(_newsType), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // удаление типа новости по ID - Delete
    @DeleteMapping("/newstype/{id}")
    public ResponseEntity<HttpStatus> deleteNewsType(@PathVariable("id") long id) {
        try {
            newsTypeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // удаление типа всех новостей - DeleteAll
    @DeleteMapping("/newstype")
    public ResponseEntity<HttpStatus> deleteAllNewsType() {
        try {
            newsTypeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}