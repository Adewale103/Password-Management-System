package africa.semicolon.passwordManagementSystem.services;

import africa.semicolon.passwordManagementSystem.dtos.requests.AddUrlRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.DeleteUrlRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.UpdateUrlRequest;
import africa.semicolon.passwordManagementSystem.dtos.response.AddUrlResponse;
import africa.semicolon.passwordManagementSystem.dtos.response.DeleteResponse;
import africa.semicolon.passwordManagementSystem.dtos.response.FindUrlResponse;
import africa.semicolon.passwordManagementSystem.dtos.response.UpdateUrlResponse;
import africa.semicolon.passwordManagementSystem.expections.UrlException;
import africa.semicolon.passwordManagementSystem.expections.UserNotFoundException;
import africa.semicolon.passwordManagementSystem.models.data.Url;
import africa.semicolon.passwordManagementSystem.models.data.User;
import africa.semicolon.passwordManagementSystem.models.repositories.UrlRepository;
import africa.semicolon.passwordManagementSystem.models.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UrlServiceImpl implements UrlService{
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public AddUrlResponse addUrl(AddUrlRequest request) {
        User foundUser = userRepository.findUserByEmail(request.getUsername())
                .orElseThrow(()-> new UrlException("User with this email does not exist"));
        Url url = new Url();
        if(foundUser.getPassword().equals(request.getUserPassword())){
            modelMapper.map(request,url);
            if(foundUser.getUrls().contains(url)){
                throw new UrlException("Url details already added!");
            }
            Url savedUrl = urlRepository.save(url);
            foundUser.getUrls().add(savedUrl);
            userRepository.save(foundUser);
        }
        else{throw new UrlException("Invalid details!");}
        AddUrlResponse response = new AddUrlResponse();
        modelMapper.map(foundUser,response);
        response.setMessage("url added successfully!");
        return response;

    }

    @Override
    public FindUrlResponse findUrl(String urlAddress) {
        Url url = urlRepository.findUrlByAddress(urlAddress).
                orElseThrow(()-> new UrlException("Url not found!"));
        FindUrlResponse response = new FindUrlResponse();
        modelMapper.map(url,response);
        return response;
    }

    @Override
    public UpdateUrlResponse updateUrl(String emailAddress, UpdateUrlRequest urlRequest) {
        User foundUser = userRepository.findUserByEmail(emailAddress).
                orElseThrow(()-> new UserNotFoundException("User not found!"));
        Set<Url> urls = foundUser.getUrls();
        UpdateUrlResponse response = new UpdateUrlResponse();
        urls.forEach(url -> {
            boolean updated = false;
            if(urlRequest.getUrlAddress().equals(url.getAddress())){
                if(!(urlRequest.getUrlPassword().equals("") || urlRequest.getUrlPassword() == null)){
                    url.setUrlPassword(urlRequest.getUrlPassword());
                    updated = true;
                }
                if(!(urlRequest.getUrlUserName().equals("") || urlRequest.getUrlUserName() == null)){
                    url.setUserName(urlRequest.getUrlUserName());
                    updated = true;
                }
                if(updated){
                    urlRepository.save(url);
                    foundUser.getUrls().add(url);
                    userRepository.save(foundUser);
                }
            }
        });
         return response;
        }

    @Override
    public DeleteResponse deleteUrl(DeleteUrlRequest request) {
        DeleteResponse response = new DeleteResponse();
        User user = userRepository.findUserByEmail(request.getEmail()).
                orElseThrow(()-> new UserNotFoundException("No user found!"));
        Url url = urlRepository.findUrlByAddress(request.getUrlAddress()).
                orElseThrow(()-> new UrlException("Url not found!") );
        Set<Url> urls = user.getUrls();
        urls.remove(url);
        urlRepository.delete(url);
        response.setMessage(request.getUrlAddress() +" has been successfully deleted!");
        return response;
    }


}

