package ba.com.kengur.image;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    public ImageEntity dtoToEntity(Image image);

    public Image entitytoDto(ImageEntity imageEntity);

    public List<ImageEntity> dtosToEntites(List<Image> image);

    public List<Image> entitestoDtos(List<ImageEntity> imageEntity);
}
