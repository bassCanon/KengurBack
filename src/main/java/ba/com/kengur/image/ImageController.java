package ba.com.kengur.image;

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
public class ImageController {

    @Autowired
    private ImageRepository repository;

    // Find
    @GetMapping("/images")
    List<ImageEntity> findAll() {
        return repository.findAll();
    }

    // Save
    @PostMapping("/images")
    // return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    ImageEntity createNewImage(@RequestBody ImageEntity newImage) {
        return repository.save(newImage);
    }

    // Find
    @GetMapping("/images/{id}")
    ImageEntity findOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    // Save or update
    @PutMapping("/images/{id}")
    ImageEntity saveOrUpdate(@RequestBody ImageEntity newImage, @PathVariable Long id) {
        return newImage;

    }

    // update author only
    @PatchMapping("/images/{id}")
    ImageEntity patch(@RequestBody Map<String, String> update, @PathVariable Long id) {
        return null;

    }

    @DeleteMapping("/images/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
