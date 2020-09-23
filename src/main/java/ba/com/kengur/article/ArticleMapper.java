package ba.com.kengur.article;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(source = "userId", target = "userEntity.id")
    public ArticleEntity dtoToEntity(Article user);

    @InheritInverseConfiguration
    public Article entitytoDto(ArticleEntity userEntity);

    public List<ArticleEntity> dtosToEntites(List<Article> user);

    public List<Article> entitestoDtos(List<ArticleEntity> userEntity);
}
