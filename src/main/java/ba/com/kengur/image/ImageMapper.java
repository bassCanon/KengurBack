package ba.com.kengur.image;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    public ImageEntity dtoToEntity(Image user);

    public Image entitytoDto(ImageEntity userEntity);

    public List<ImageEntity> dtosToEntites(List<Image> user);

    public List<Image> entitestoDtos(List<ImageEntity> userEntity);
}
