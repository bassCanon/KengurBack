package ba.com.kengur.user;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String password;
    private byte[] profilePic;
    private String firstName;
    private String lastName;

}
