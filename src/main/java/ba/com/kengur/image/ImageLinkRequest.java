package ba.com.kengur.image;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ImageLinkRequest implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    private List<Long> selectedIds;
    private List<Image> images;

}
