package ba.com.kengur.image;

import java.io.Serializable;
import java.util.List;

import ba.com.kengur.article.Article;
import lombok.Data;

@Data
public class ImageLinkRequest implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    private Image[] selectedImages;
    private List<Image> images;
    private Long articleId;
    private List<Article> articles;
}
