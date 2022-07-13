package africa.semicolon.passwordManagementSystem.services;

import africa.semicolon.passwordManagementSystem.dtos.requests.AddUserRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.DeleteUserRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.UpdateUserRequest;
import africa.semicolon.passwordManagementSystem.dtos.response.DeleteResponse;
import africa.semicolon.passwordManagementSystem.dtos.response.UserResponse;

public interface UserService {
    UserResponse createAccount(AddUserRequest addUserRequest);
    UserResponse findUserByEmail(String email);
    UserResponse updateUser(String email, UpdateUserRequest updateUserRequest);
    DeleteResponse deleteUser(DeleteUserRequest request);
}
