package ba.com.kengur.article;

import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ba.com.kengur.error.EntityNotFound;
import ba.com.kengur.image.Image;
import ba.com.kengur.image.ImageController;
import ba.com.kengur.user.UserEntity;
import ba.com.kengur.user.UserRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {

    private ArticleRepository repository;

    private ArticleMapper articleMapper;

    private UserRepository userRepository;

    private ImageController imageController;

    @GetMapping
    List<Article> findAll() {
        return articleMapper.entitestoDtos(repository.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ArticleEntity createNewArticle(@ModelAttribute(value = "article") ArticleUploadRequest newArticle, final Principal principal,
            final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        UserEntity user = userRepository.findByUsername(principal.getName());
        newArticle.setUserId(user.getId());
        newArticle.setContentBit(newArticle.getContent().substring(0, 50));
        ArticleEntity art = repository.save(articleMapper.dtoToEntity(newArticle));
        Image image = new Image();
        image.setArticleId(art.getId());
        image.setImageData(Base64.getEncoder().encodeToString(newArticle.getImageData().getBytes()));
        image.setTitle(art.getTitle());
        imageController.createNewImage(image);
        return art;
    }

    // Find
    @GetMapping("{id}")
    Article findOne(@PathVariable Long id) {
        return articleMapper.entitytoDto(repository.findById(id).orElseThrow(() -> new EntityNotFound(id)));
    }

    // Save or update
    @PutMapping("{id}")
    Article saveOrUpdate(@RequestBody ArticleEntity newArticle, @PathVariable Long id) {
        return articleMapper.entitytoDto(newArticle);

    }

    // update author only
    @PatchMapping("{id}")
    ArticleEntity patch(@RequestBody Map<String, String> update, @PathVariable Long id) {
        return null;

    }

    @DeleteMapping("{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
