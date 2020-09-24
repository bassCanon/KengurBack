package ba.com.kengur.article;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ba.com.kengur.image.ImageEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "article_image", schema = "public")
@NamedQuery(name = "ArticleImageEntity.findAll", query = "SELECT r FROM ArticleImageEntity r")
@Getter
@Setter
public class ArticleImageEntity {

    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity articleEntity;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private ImageEntity imageEntity;
}
