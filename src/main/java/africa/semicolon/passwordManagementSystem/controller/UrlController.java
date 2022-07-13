package africa.semicolon.passwordManagementSystem.controller;


import africa.semicolon.passwordManagementSystem.dtos.requests.AddUrlRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.DeleteUrlRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.UpdateUrlRequest;
import africa.semicolon.passwordManagementSystem.dtos.response.ApiResponse;
import africa.semicolon.passwordManagementSystem.expections.PasswordManagementException;
import africa.semicolon.passwordManagementSystem.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/url")
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping("/addUrl")
    public ResponseEntity<?> addUrl(@RequestBody AddUrlRequest addUrlRequest){
        try{
            var response = urlService.addUrl(addUrlRequest);
            ApiResponse theResponse = new ApiResponse(response,true);
            return new ResponseEntity<>(theResponse, HttpStatus.ACCEPTED);
        }
        catch (PasswordManagementException ex){
            ApiResponse response = new ApiResponse(ex.getMessage(),false);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/search/{address}")
    public ResponseEntity<?> findUrlDetails(@PathVariable String address){
        try{
            var response = urlService.findUrl(address);
            ApiResponse theResponse = new ApiResponse(response, true);
            return new ResponseEntity<>(theResponse,HttpStatus.FOUND);
        }
        catch (PasswordManagementException ex){
            ApiResponse response = new ApiResponse(ex.getMessage(),false);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/updateUrl/{emailAddress}")
    public ResponseEntity<?> updateUrlDetails(@PathVariable String emailAddress,
                                              @RequestBody UpdateUrlRequest request){
        try {
            var response = urlService.updateUrl(emailAddress, request);
            ApiResponse theResponse = new ApiResponse(response,true);
            return new ResponseEntity<>(theResponse,HttpStatus.ACCEPTED);
        }
        catch(PasswordManagementException ex){
            ApiResponse response = new ApiResponse(ex.getMessage(),false);
            return new ResponseEntity<>(response,HttpStatus.NOT_MODIFIED);
        }

    }
    @DeleteMapping("/deleteUrl")
    public ResponseEntity<?> deleteUrl(@RequestBody DeleteUrlRequest request){
        try{
            ApiResponse response = new ApiResponse(urlService.deleteUrl(request),true);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (PasswordManagementException ex){
            ApiResponse response = new ApiResponse(ex.getMessage(),false);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }


}
