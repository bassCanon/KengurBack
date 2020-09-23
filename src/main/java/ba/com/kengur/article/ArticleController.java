package ba.com.kengur.article;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ba.com.kengur.error.EntityNotFound;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ArticleController {

    private ArticleRepository repository;

    private ArticleMapper articleMapper;

    @GetMapping("/articles")
    List<Article> findAll() {
        return articleMapper.entitestoDtos(repository.findAll());
    }

    @PostMapping("/articles")
    ArticleEntity createNewArticle(@RequestBody Article newArticle) {
        return repository.save(articleMapper.dtoToEntity(newArticle));
    }

    // Find
    @GetMapping("/articles/{id}")
    Article findOne(@PathVariable Long id) {
        return articleMapper.entitytoDto(repository.findById(id).orElseThrow(() -> new EntityNotFound(id)));
    }

    // Save or update
    @PutMapping("/articles/{id}")
    Article saveOrUpdate(@RequestBody ArticleEntity newArticle, @PathVariable Long id) {
        return articleMapper.entitytoDto(newArticle);

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
