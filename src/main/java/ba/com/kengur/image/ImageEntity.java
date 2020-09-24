package ba.com.kengur.image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "image", schema = "public")
@NamedQuery(name = "ImageEntity.findAll", query = "SELECT r FROM ImageEntity r")
@Getter
@Setter
public class ImageEntity {

    @Id
    @SequenceGenerator(name = "IMAGE_SEQ_GEN", sequenceName = "IMAGE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_SEQ_GEN")
    private Long id;

    @Lob
    @Column(name = "image_location")
    private String imageLocation;

    private String title;

}
