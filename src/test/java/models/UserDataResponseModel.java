package models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDataResponseModel {
    private UserDataModel data;

    public UserDataModel getData() {
        return data;
    }
}
