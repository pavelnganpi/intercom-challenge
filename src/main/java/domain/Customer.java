package domain;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Customer {
    @SerializedName("user_id")
    public Integer userId;
    @SerializedName("name")
    public String name;
    @SerializedName("latitude")
    public Double latitude;
    @SerializedName("longitude")
    public Double longitude;
}

