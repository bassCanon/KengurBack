package ba.com.kengur.image;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(source = "articleId", target = "articleEntity.id")
    public ImageEntity dtoToEntity(Image image);

    @InheritInverseConfiguration
    public Image entitytoDto(ImageEntity imageEntity);

    public List<ImageEntity> dtosToEntites(List<Image> image);

    public List<Image> entitestoDtos(List<ImageEntity> imageEntity);
}
