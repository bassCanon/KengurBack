package ba.com.kengur.article;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleUploadRequest extends Article {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    MultipartFile imageData;

}
