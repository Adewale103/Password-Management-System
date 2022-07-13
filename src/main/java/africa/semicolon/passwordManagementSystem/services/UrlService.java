package africa.semicolon.passwordManagementSystem.services;

import africa.semicolon.passwordManagementSystem.dtos.requests.AddUrlRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.DeleteUrlRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.UpdateUrlRequest;
import africa.semicolon.passwordManagementSystem.dtos.response.AddUrlResponse;
import africa.semicolon.passwordManagementSystem.dtos.response.DeleteResponse;
import africa.semicolon.passwordManagementSystem.dtos.response.FindUrlResponse;
import africa.semicolon.passwordManagementSystem.dtos.response.UpdateUrlResponse;

public interface UrlService {
AddUrlResponse addUrl(AddUrlRequest request);
FindUrlResponse findUrl(String urlAddress);
UpdateUrlResponse updateUrl(String emailAddress, UpdateUrlRequest request);
DeleteResponse deleteUrl(DeleteUrlRequest request);
}
