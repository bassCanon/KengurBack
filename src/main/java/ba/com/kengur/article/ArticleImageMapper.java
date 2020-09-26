package ba.com.kengur.article;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleImageMapper {

    @Mapping(source = "articleId", target = "articleEntity.id")
    @Mapping(source = "imageId", target = "imageEntity.id")
    public ArticleImageEntity dtoToEntity(ArticleImage user);

    @InheritInverseConfiguration
    public ArticleImage entitytoDto(ArticleImageEntity articleImageEntity);

    public List<ArticleImageEntity> dtosToEntites(List<ArticleImage> articleImage);

    public List<ArticleImage> entitestoDtos(List<ArticleImageEntity> articleImageEntity);
}
