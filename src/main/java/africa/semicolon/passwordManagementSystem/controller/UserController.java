package africa.semicolon.passwordManagementSystem.controller;

import africa.semicolon.passwordManagementSystem.dtos.requests.AddUserRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.DeleteUserRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.UpdateUserRequest;
import africa.semicolon.passwordManagementSystem.dtos.response.ApiResponse;
import africa.semicolon.passwordManagementSystem.expections.PasswordManagementException;
import africa.semicolon.passwordManagementSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody AddUserRequest addUserRequest){
        try{
            var response = userService.createAccount(addUserRequest);
            ApiResponse theResponse = new ApiResponse(response,true);
            return new ResponseEntity<>(theResponse, HttpStatus.CREATED);
        }
        catch(PasswordManagementException ex){
            ApiResponse response = new ApiResponse(ex.getMessage(),false);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/searchUser/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email){
        try{
            var response = userService.findUserByEmail(email);
            ApiResponse theResponse = new ApiResponse(response,true);
            return new ResponseEntity<>(theResponse,HttpStatus.FOUND);
        }
        catch (PasswordManagementException ex){
            ApiResponse response = new ApiResponse(ex.getMessage(),false);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/updateUser/{email}")
    public ResponseEntity<?> updateUserDetails(@PathVariable String email, @RequestBody UpdateUserRequest request){
        try{
            var response = userService.updateUser(email,request);
            ApiResponse theResponse = new ApiResponse(response,true);
            return new ResponseEntity<>(theResponse,HttpStatus.ACCEPTED);
        }
        catch (PasswordManagementException ex){
            ApiResponse response = new ApiResponse(ex.getMessage(),false);
            return new ResponseEntity<>(response,HttpStatus.NOT_MODIFIED);
        }
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserRequest request){
        try{
            ApiResponse response = new ApiResponse(userService.deleteUser(request), true);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }
        catch (PasswordManagementException ex){
            ApiResponse response = new ApiResponse(ex.getMessage(),false);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}
