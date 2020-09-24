package ba.com.kengur.user;

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
@Table(name = "users", schema = "public")
@NamedQuery(name = "UserEntity.findAll", query = "SELECT r FROM UserEntity r")
@Getter
@Setter
public class UserEntity {

    @Id
    @SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String password;

    private String username;

    private Boolean enabled;

    @Lob
    private byte[] profilePic;

}
