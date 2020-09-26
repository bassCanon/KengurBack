package ba.com.kengur.article;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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

    @Id
    @SequenceGenerator(name = "ARTICLE_IMAGE_SEQ_GEN", sequenceName = "ARTICLE_IMAGE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_IMAGE_SEQ_GEN")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity articleEntity;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private ImageEntity imageEntity;
}
