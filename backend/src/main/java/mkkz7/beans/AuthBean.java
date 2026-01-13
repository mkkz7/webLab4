package mkkz7.beans;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import mkkz7.db.entities.User;
import mkkz7.exceptions.AuthValidationException;
import mkkz7.exceptions.UserAlreadyExistsException;
import mkkz7.exceptions.UserNotFoundException;
import mkkz7.utils.HashMonster;
import mkkz7.utils.DTO.UserDTO;

@Stateless
public class AuthBean {
    @EJB
    private ServiceBean serviceBean;

    public void validate(UserDTO userDTO) throws AuthValidationException {
        if(userDTO.getUsername() == null || userDTO.getPassword() == null){
            throw new AuthValidationException("Username or password is null!");
        }

        if(userDTO.getPassword().length() < 3 || userDTO.getUsername().length() < 3){
            throw new AuthValidationException("Password or username is too short!(Less than 3 symbols)");
        }

        if(userDTO.getPassword().equalsIgnoreCase(userDTO.getUsername())){
            throw new AuthValidationException("Password is the same as username!");
        }
    }

    public void clear(){
        serviceBean.deletePoints();
    }

    public User findById(long id){
        return serviceBean.findById(id);
    }

    public boolean isRegistered(String username){
        return serviceBean.findByUsername(username) != null;
    }

    public User loginUser(UserDTO userDTO){
        validate(userDTO);
        User user = serviceBean.findByUsername(userDTO.getUsername());

        if(user == null){
            throw new UserNotFoundException(userDTO.getUsername());
        }

        BCrypt.Result result = HashMonster.verifyPassword(userDTO.getPassword(), user.getPassword_hash());

        if(!result.verified){
            throw new AuthValidationException("Incorrect password or username!");
        }

        return user;
    }

    public User regUser(UserDTO userDTO){
        validate(userDTO);
        if(isRegistered(userDTO.getUsername())){
            throw new UserAlreadyExistsException(userDTO.getUsername());
        }
        User user = User.builder()
                .username(userDTO.getUsername())
                .password_hash(HashMonster.generateHash(userDTO.getPassword()))
                .build();
        serviceBean.register(user);
        return user;
    }
}
