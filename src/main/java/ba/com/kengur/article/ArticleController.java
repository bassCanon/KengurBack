package ba.com.kengur.article;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import ba.com.kengur.error.EntityNotFound;
import ba.com.kengur.image.Image;
import ba.com.kengur.image.ImageController;
import ba.com.kengur.user.UserEntity;
import ba.com.kengur.user.UserRepository;
import ba.com.kengur.util.ResponseConverter;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ArticleController {

    private ArticleRepository repository;

    private ArticleMapper articleMapper;

    private UserRepository userRepository;

    private ImageController imageController;

    private ArticleImageMapper articleImageMapper;

    private ArticleImageRepository articleImageRespository;

    @GetMapping
    List<Article> findAll() {
        return articleMapper.entitestoDtos(repository.findAll());
    }

    @PostMapping(value = "/create/article", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ArticleEntity createNewArticle(@ModelAttribute(value = "article") ArticleUploadRequest newArticle, final Principal principal,
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
    @GetMapping("/article/{id}")
    public Article findOne(@PathVariable Long id) {
        return articleMapper.entitytoDto(repository.findById(id).orElseThrow(() -> new EntityNotFound(id)));
    }

    // Save or update
    @PutMapping("/article/{id}")
    public Article saveOrUpdate(@RequestBody ArticleEntity newArticle, @PathVariable Long id) {
        return articleMapper.entitytoDto(newArticle);

    }

    // Save or update
    @PostMapping(value = "/create/article/link", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String linkImagesToArticle(final Principal principal, final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        List<Image> selectedImages = new ArrayList<>();
        String[] s = request.getParameterValues("selectedImages");
        String[] a = request.getParameterValues("articleId");
        for (int i = 0; i < s.length; i++) {
            String json = ResponseConverter
                    .convertToJson(s[i].replace("Image(", "").replace(")", "\"").replace(", ", "\"").replace("=", "=\""));
            Image image = ResponseConverter.readValue(json, Image.class);
            selectedImages.add(image);
        }
        List<ArticleImageEntity> ents = new ArrayList<>();

        for (Image image : selectedImages) {
            ArticleImage artImg = new ArticleImage();
            artImg.setArticleId(Long.parseLong(a[0]));
            artImg.setImageId(image.getId());
            ents.add(articleImageMapper.dtoToEntity(artImg));
        }

        articleImageRespository.saveAll(ents);
        return "Linked";

    }

    // update author only
    @PatchMapping("/article/{id}")
    public ArticleEntity patch(@RequestBody Map<String, String> update, @PathVariable Long id) {
        return null;

    }

    @DeleteMapping("/article/{id}")
    public void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/article/all")
    public List<Article> getAll() {
        return articleMapper.entitestoDtos(repository.findAll());
    }

}
