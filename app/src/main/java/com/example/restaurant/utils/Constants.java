package com.example.restaurant.utils;

public interface Constants {

    //api
    String METHOD_LOGIN = "client/login";
    String MEDTHOD_CODE = "client/reset-password";
    String METHOD_RESET_PASSWORD ="client/new-password";
    String METHOD_CITIES ="cities-not-paginated";
    String METHOD_REGION = "regions-not-paginated";
    String METHOD_GET_CATEGORIES = "restaurant/my-categories";
    String METHOD_ADD_CATEGORY = "restaurant/new-category";
    String METHOD_UPDATE_CATEGORY = "restaurant/update-category";
    String METHOD_DELETE_CATEGORY = "restaurant/delete-category";
    String METHOD_GET_ITEMS = "restaurant/my-items";
    String METHOD_GET_ORDERS = "restaurant/my-orders";

    //shared preference
    String KEY_API_TOKEN = "apiToken";
    String KEY_CODE = "code";

    //api token
    String API_TOKEN = "Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx";

    String IMAGE_KEY = "imageKey";
    int NETWORK_TIMEOUT = 3000;



}
