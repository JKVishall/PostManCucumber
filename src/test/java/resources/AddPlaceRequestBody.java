package resources;

import pojo.LocationInfo;
import pojo.WebsiteDetails;

import java.util.ArrayList;
import java.util.List;

public class AddPlaceRequestBody {
    public WebsiteDetails addPlaceReqBody(String name, String phone_number, String address){
        WebsiteDetails bodyData = new WebsiteDetails();
        bodyData.setAccuracy(50);
        bodyData.setName(name);
        bodyData.setPhoneNumber(phone_number);
        bodyData.setAddress(address);
        bodyData.setWebsite("http://google.com");
        bodyData.setLanguage("French-IN");

        //since setTypes method requires a list of strings, we are creating a list of string and then add values to it and then send
        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        bodyData.setTypes(types);

        //since setLocation expects an object to be sent in it (locationInfo class object)
        //we need to create an object of locationInfo class and then send it inside setLocation
        LocationInfo ll = new LocationInfo();
        ll.setLat(-38.383494);
        ll.setLng(33.427362);

        bodyData.setLocation(ll);
        return bodyData;
    }
}
