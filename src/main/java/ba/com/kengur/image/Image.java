package ba.com.kengur.image;

import java.io.Serializable;

import lombok.Data;

@Data
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String imageLocation;
    private Long articleId;
    private String title;
    private String imageData;
}
