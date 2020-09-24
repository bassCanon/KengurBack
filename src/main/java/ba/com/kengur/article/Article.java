package ba.com.kengur.article;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String contentBit;
    private String content;
    private Long userId;
    private List<String> images;
}
