package models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDataModel {
    int id;
    String email;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;
    String avatar;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatar() {
        return avatar;
    }
}