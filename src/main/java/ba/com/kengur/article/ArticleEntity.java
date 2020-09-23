package ba.com.kengur.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ba.com.kengur.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "article", schema = "public")
@NamedQuery(name = "ArticleEntity.findAll", query = "SELECT r FROM ArticleEntity r")
@Getter
@Setter
public class ArticleEntity {

    @Id
    @SequenceGenerator(name = "ARTICLE_SEQ_GEN", sequenceName = "ARTICLE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_SEQ_GEN")
    private Long id;

    private String text;

    @Column(name = "content_bit")
    private String contentBit;

    private String content;

    // bi-directional many-to-one association to UserEntity
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
