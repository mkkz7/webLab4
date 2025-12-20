package mkkz7.beans;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import mkkz7.entities.User;
import mkkz7.exceptions.AuthValidationException;
import mkkz7.exceptions.UserAlreadyExistsException;
import mkkz7.utils.UserDTO;

import java.util.HashMap;
import java.util.Map;

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

    public boolean isRegistered(String username){
        return serviceBean.findByUsername(username) != null;
    }

    public void loginUser(UserDTO userDTO){
        if(!(isRegistered(userDTO.getUsername()))){
            regUser(userDTO);
        }

        //Логика поиска пользователя по нику в бд и если есть, то збс
        //Для реализации нужен jpa и хубернейт
    }

    public boolean regUser(UserDTO userDTO){
        if(isRegistered(userDTO.getUsername())){
            throw new UserAlreadyExistsException(userDTO.getUsername());
        }
        User user = User.builder()
                .username(userDTO.getUsername())
                .password_hash(userDTO.getPassword())
                .salt("")
                .build();
        serviceBean.register(user);
        return true;
    }
}
