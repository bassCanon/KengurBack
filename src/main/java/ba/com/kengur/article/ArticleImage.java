package ba.com.kengur.article;

import java.io.Serializable;

import lombok.Data;

@Data
public class ArticleImage implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long articleId;
    private Long imageId;

}
