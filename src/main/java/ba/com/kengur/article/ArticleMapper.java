package ba.com.kengur.article;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    public ArticleEntity dtoToEntity(Article user);

    public Article entitytoDto(ArticleEntity userEntity);

    public List<ArticleEntity> dtosToEntites(List<Article> user);

    public List<Article> entitestoDtos(List<ArticleEntity> userEntity);
}
