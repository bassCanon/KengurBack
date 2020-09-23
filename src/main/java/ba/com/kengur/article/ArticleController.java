package ba.com.kengur.article;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ba.com.kengur.error.BookNotFoundException;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository repository;

    // Find
    @GetMapping("/articles")
    List<ArticleEntity> findAll() {
        return repository.findAll();
    }

    // Save
    @PostMapping("/articles")
    // return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    ArticleEntity createNewArticle(@RequestBody ArticleEntity newArticle) {
        return repository.save(newArticle);
    }

    // Find
    @GetMapping("/articles/{id}")
    ArticleEntity findOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    // Save or update
    @PutMapping("/articles/{id}")
    ArticleEntity saveOrUpdate(@RequestBody ArticleEntity newArticle, @PathVariable Long id) {
        return newArticle;

    }

    // update author only
    @PatchMapping("/articles/{id}")
    ArticleEntity patch(@RequestBody Map<String, String> update, @PathVariable Long id) {
        return null;

    }

    @DeleteMapping("/articles/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
