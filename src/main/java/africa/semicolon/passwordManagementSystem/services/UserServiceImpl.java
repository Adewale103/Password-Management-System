package africa.semicolon.passwordManagementSystem.services;

import africa.semicolon.passwordManagementSystem.dtos.requests.AddUserRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.DeleteUserRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.UpdateUserRequest;
import africa.semicolon.passwordManagementSystem.dtos.response.DeleteResponse;
import africa.semicolon.passwordManagementSystem.dtos.response.UserResponse;
import africa.semicolon.passwordManagementSystem.expections.InvalidDetailsException;
import africa.semicolon.passwordManagementSystem.expections.UserAlreadyExistException;
import africa.semicolon.passwordManagementSystem.expections.UserNotFoundException;
import africa.semicolon.passwordManagementSystem.models.data.User;
import africa.semicolon.passwordManagementSystem.models.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserResponse createAccount(AddUserRequest addUserRequest) {
      if(userRepository.existsUserByEmail(addUserRequest.getEmail())){
          throw new UserAlreadyExistException("User already exists!");
      }
      User user = new User();
      if(isEmailValid(addUserRequest.getEmail())){
          if(isPasswordValid(addUserRequest.getPassword())){
              modelMapper.map(addUserRequest,user);
              userRepository.save(user);
          }
      }
      UserResponse userResponse = new UserResponse();
      modelMapper.map(user, userResponse);
      return userResponse;
    }

    @Override
    public UserResponse findUserByEmail(String email) {
        User foundUser = userRepository.findUserByEmail(email).
                orElseThrow(()-> new UserNotFoundException("User not found!"));
        UserResponse response = new UserResponse();
        modelMapper.map(foundUser,response);
        return response;
    }

    @Override
    public UserResponse updateUser(String email, UpdateUserRequest updateUserRequest) {
        User foundUser = userRepository.findUserByEmail(email).
                orElseThrow(()->new UserNotFoundException("User not found!"));
        boolean isUpdated = false;
        UserResponse response = new UserResponse();
        if(!(updateUserRequest.getEmail().equals("") || updateUserRequest.getEmail()== null)){
            foundUser.setEmail(updateUserRequest.getEmail());
            isUpdated = true;
        }
        if(!(updateUserRequest.getPassword().equals("") || updateUserRequest.getPassword()== null)){
            foundUser.setPassword(updateUserRequest.getPassword());
            isUpdated = true;
        }
        if(!(updateUserRequest.getUsername().equals("") || updateUserRequest.getUsername()== null)){
            foundUser.setUsername(updateUserRequest.getUsername());
            isUpdated = true;
        }
        if(!(updateUserRequest.getPhoneNumber().equals("") || updateUserRequest.getPhoneNumber()== null)){
            foundUser.setPhoneNumber(updateUserRequest.getPhoneNumber());
            isUpdated = true;
        }
        if(isUpdated){
            modelMapper.map(foundUser,response);
            userRepository.save(foundUser);}

        return response;
    }

    @Override
    public DeleteResponse deleteUser(DeleteUserRequest request) {
        DeleteResponse response = new DeleteResponse();
        User foundUser = userRepository.findUserByEmail(request.getEmail()).
                orElseThrow(()->new UserNotFoundException("User not found!"));
        userRepository.delete(foundUser);
        response.setMessage("User removed!");
        return response;
    }

    private boolean isPasswordValid(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){
            return true;
        }
        throw new InvalidDetailsException("""
                password must be valid having at least one Uppercase, one lowercase,
                one special character and length should be between 8 and 20 characters""");
    }

    private boolean isEmailValid(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            return true;
        }
        throw new InvalidDetailsException("Enter a valid email");
    }
}
