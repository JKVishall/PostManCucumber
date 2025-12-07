package resources;
//enum is a special class in Java which has only collection of constants and collection of methods
public enum APIResources {
    addPlaceAPI("maps/api/place/add/json"),
    getPlaceAPI("maps/api/place/get/json"),
    deletePlaceAPI("maps/api/place/delete/json");

    String resource;

    APIResources(String resource){
        this.resource = resource;
    }

    public static String endPointOf(String name){
        return  APIResources.valueOf(name).resource;
    }


}
