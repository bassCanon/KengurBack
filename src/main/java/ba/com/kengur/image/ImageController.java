package ba.com.kengur.image;

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
public class ImageController {

    private ImageRepository repository;

    private ImageMapper imageMapper;

    // Find
    @GetMapping("/images")
    List<Image> findAll() {
        return imageMapper.entitestoDtos(repository.findAll());
    }

    // Save
    @PostMapping("/images")
    Image createNewImage(@RequestBody ImageEntity newImage) {
        return imageMapper.entitytoDto(repository.save(newImage));
    }

    // Find
    @GetMapping("/images/{id}")
    Image findOne(@PathVariable Long id) {
        return imageMapper.entitytoDto(repository.findById(id).orElseThrow(() -> new EntityNotFound(id)));
    }

    // Save or update
    @PutMapping("/images/{id}")
    Image saveOrUpdate(@RequestBody ImageEntity newImage, @PathVariable Long id) {
        return imageMapper.entitytoDto(newImage);

    }

    // update author only
    @PatchMapping("/images/{id}")
    Image patch(@RequestBody Map<String, String> update, @PathVariable Long id) {
        return null;

    }

    @DeleteMapping("/images/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
