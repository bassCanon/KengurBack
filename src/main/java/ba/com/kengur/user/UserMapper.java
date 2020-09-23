package ba.com.kengur.user;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserEntity dtoToEntity(User user);

    public User entitytoDto(UserEntity userEntity);

    public List<UserEntity> dtosToEntites(List<User> user);

    public List<User> entitestoDtos(List<UserEntity> userEntity);
}
