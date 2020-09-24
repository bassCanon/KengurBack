package ba.com.kengur.image;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import ba.com.kengur.error.EntityNotFound;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/image")
@AllArgsConstructor
public class ImageController {

    private ImageRepository repository;

    private ImageMapper imageMapper;

    private Cloudinary cloudinary;

    @GetMapping
    List<Image> findAll() {
        return imageMapper.entitestoDtos(repository.findAll());
    }

    @SuppressWarnings("unchecked")
    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    Image createNewImage(@RequestBody Image newImage) throws IOException {
        File tempImage = new File("C:\\Kengur\\temp.jpg");
        FileUtils.writeByteArrayToFile(tempImage, Base64.getDecoder().decode(newImage.getImageData()));
        Map<String, String> params = ObjectUtils.asMap("public_id", "kengur/" + newImage.getTitle(), "overwrite", true, "notification_url",
                "https://mysite/notify_endpoint", "resource_type", "image");
        Map uploadResult = cloudinary.uploader().upload(tempImage, params);
        newImage.setImageLocation((String) uploadResult.get("url"));
        return imageMapper.entitytoDto(repository.save(imageMapper.dtoToEntity(newImage)));
    }

    @GetMapping("{id}")
    Image findOne(@PathVariable Long id) {
        return imageMapper.entitytoDto(repository.findById(id).orElseThrow(() -> new EntityNotFound(id)));
    }

    @PutMapping("{id}")
    Image saveOrUpdate(@RequestBody ImageEntity newImage, @PathVariable Long id) {
        return imageMapper.entitytoDto(newImage);

    }

    @PatchMapping("{id}")
    Image patch(@RequestBody Map<String, String> update, @PathVariable Long id) {
        return null;

    }

    @DeleteMapping("{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
