package com.example.restaurant.data.remote;
import com.example.restaurant.data.model.api.general.BasicResponse;
import com.example.restaurant.data.model.api.homeCycle.Category;
import com.example.restaurant.data.model.api.homeCycle.Item;
import com.example.restaurant.data.model.api.homeCycle.Order;
import com.example.restaurant.data.model.api.userCycle.ForgetPassword;
import com.example.restaurant.data.model.api.general.Page;
import com.example.restaurant.data.model.api.userCycle.Region;
import com.example.restaurant.data.model.api.userCycle.UserData;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import static com.example.restaurant.utils.Constants.MEDTHOD_CODE;
import static com.example.restaurant.utils.Constants.METHOD_ADD_CATEGORY;
import static com.example.restaurant.utils.Constants.METHOD_CITIES;
import static com.example.restaurant.utils.Constants.METHOD_DELETE_CATEGORY;
import static com.example.restaurant.utils.Constants.METHOD_GET_CATEGORIES;
import static com.example.restaurant.utils.Constants.METHOD_GET_ITEMS;
import static com.example.restaurant.utils.Constants.METHOD_GET_ORDERS;
import static com.example.restaurant.utils.Constants.METHOD_LOGIN;
import static com.example.restaurant.utils.Constants.METHOD_REGION;
import static com.example.restaurant.utils.Constants.METHOD_RESET_PASSWORD;
import static com.example.restaurant.utils.Constants.METHOD_UPDATE_CATEGORY;

public interface RestaurantApi {


    //////// User Cycle/////////

    //login fragment
    @FormUrlEncoded
    @POST(METHOD_LOGIN)
    Call<BasicResponse<UserData>> getLoginData(@Field("email") String email,

                                               @Field("password") String password);
    //forget password Fragment
    @FormUrlEncoded
    @POST(MEDTHOD_CODE)
    Call<BasicResponse<ForgetPassword>> getCode(@Field("email") String email);

    //reset password fragment
    @FormUrlEncoded
    @POST(METHOD_RESET_PASSWORD)
    Call<BasicResponse> setNewPassword(@Field("code") String code,
                                       @Field("password") String password,
                                       @Field("password_confirmation") String newPassword);
    //get cities for spinner
    @GET(METHOD_CITIES)
    Call<BasicResponse<List<Region>>> getCities();

    //get region for spinner
    @GET(METHOD_REGION)
    Call<BasicResponse<List<Region>>> getRegion(@Query("city_id") String cityId);

    // register restaurant
    @POST("restaurant/sign-up")
    @Multipart
    Call<BasicResponse> registerRestaurant(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("password_confirmation") RequestBody passwordConfirmation,
            @Part("phone") RequestBody phone,
            @Part("whatsapp") RequestBody whatsApp,
            @Part("region_id") RequestBody regionId,
            @Part("delivery_cost") RequestBody deliveryCost,
            @Part("minimum_charger") RequestBody minimumCharger,
            @Part MultipartBody.Part photo,
            @Part("delivery_time") RequestBody deliveryTime);


    //////// Home Cycle/////////

    //// categories

    //get all categories
    @GET(METHOD_GET_CATEGORIES)
    Call<BasicResponse<Page<Category>>> getCategories(@Query("api_token") String apiToken,
                                                      @Query("page") int page );

    // add category
    @Multipart
    @POST(METHOD_ADD_CATEGORY)
    Call<BasicResponse<Category>> addCategory(@Part("name") RequestBody name,
                                              @Part MultipartBody.Part photo,
                                              @Part("api_token") RequestBody apiToken);

    @Multipart
    @POST(METHOD_UPDATE_CATEGORY)
    Call<BasicResponse<Category>> updateCategory(@Part("name") RequestBody name,
                                                 @Part MultipartBody.Part photo,
                                                 @Part("api_token") RequestBody apiToken,
                                                 @Part("category_id") RequestBody categoryId);

    @FormUrlEncoded
    @POST(METHOD_DELETE_CATEGORY)
    Call<BasicResponse> deleteCategory(@Field("api_token") String apiToken,
                                       @Field("category_id") String categoryId);


    //// Items

    //get all items
    @GET(METHOD_GET_ITEMS)
    Call<BasicResponse<Page<Item>>> getItems(@Query("api_token") String apiToken,
                                            @Query("category_id") String categoryId);

    //// Orders

    //get orders
    @GET(METHOD_GET_ORDERS)
    Call<BasicResponse<Page<Order>>> getOrders(@Query("api_token") String apiToken,
                                               @Query("state") String state,
                                               @Query("page") String page);
}
