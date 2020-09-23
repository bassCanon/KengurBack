package ba.com.kengur.image;

import java.io.Serializable;

import lombok.Data;

@Data
public class Image implements Serializable {

    private static final long serialVersionUID = -349756570482477939L;

    private Long id;
    private byte[] imageData;
    private Long articleId;
}
