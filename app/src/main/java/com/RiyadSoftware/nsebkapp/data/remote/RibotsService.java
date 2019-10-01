package com.RiyadSoftware.nsebkapp.data.remote;

import com.RiyadSoftware.nsebkapp.Application;
import com.RiyadSoftware.nsebkapp.Ui.Address.AddressRequest;
import com.RiyadSoftware.nsebkapp.Ui.Address.Addressresponse;
import com.RiyadSoftware.nsebkapp.Ui.Contact.ContactRequest;
import com.RiyadSoftware.nsebkapp.Ui.Contact.ContactResponse;
import com.RiyadSoftware.nsebkapp.Ui.EditProfile.EditprofileResponse;
import com.RiyadSoftware.nsebkapp.Ui.Inteerst.InterestResponseModel;
import com.RiyadSoftware.nsebkapp.Ui.Inteerst.SaveInterestRequest;
import com.RiyadSoftware.nsebkapp.Ui.Reset.ForgetResponse;
import com.RiyadSoftware.nsebkapp.Ui.Terms.TermsResponse;
import com.RiyadSoftware.nsebkapp.activities.VerifyForgetPassword;
import com.RiyadSoftware.nsebkapp.data.CountriesResponse;
import com.RiyadSoftware.nsebkapp.data.models.AddFavRequest;
import com.RiyadSoftware.nsebkapp.data.models.AddFavResponse;
import com.RiyadSoftware.nsebkapp.data.models.AddTicketRequest;
import com.RiyadSoftware.nsebkapp.data.models.AddTicketResponse;
import com.RiyadSoftware.nsebkapp.data.models.CategoriesResponce;
import com.RiyadSoftware.nsebkapp.data.models.CategoryProductResponse;
import com.RiyadSoftware.nsebkapp.data.models.CategoryProductsRequest;
import com.RiyadSoftware.nsebkapp.data.models.ChargeHistoryResponse;
import com.RiyadSoftware.nsebkapp.data.models.ChargeRequest;
import com.RiyadSoftware.nsebkapp.data.models.ChargeResponse;
import com.RiyadSoftware.nsebkapp.data.models.CitiesModel;
import com.RiyadSoftware.nsebkapp.data.models.CityRequest;
import com.RiyadSoftware.nsebkapp.data.models.DealDetailsRequest;
import com.RiyadSoftware.nsebkapp.data.models.DealDetailsResponse;
import com.RiyadSoftware.nsebkapp.data.models.DealsResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.Finishdeal.FinishDealRequest;
import com.RiyadSoftware.nsebkapp.data.models.Finishdeal.FinishDealResponse;
import com.RiyadSoftware.nsebkapp.data.models.HomeModel;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.data.models.LoginRequest;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.data.models.MyFavResponse;
import com.RiyadSoftware.nsebkapp.data.models.MydealsResponse;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.PackageResponse;
import com.RiyadSoftware.nsebkapp.data.models.RegisterRequest;
import com.RiyadSoftware.nsebkapp.data.models.RegisterResponse;
import com.RiyadSoftware.nsebkapp.data.models.RewardsResponse;
import com.RiyadSoftware.nsebkapp.data.models.SubCatRequest;
import com.RiyadSoftware.nsebkapp.data.models.VerifyResponse;
import com.RiyadSoftware.nsebkapp.data.models.awards.ReplaceAwardRequest;
import com.RiyadSoftware.nsebkapp.data.models.awards.ReplaceAwardResponse;
import com.RiyadSoftware.nsebkapp.data.models.coupon.CouponRequest;
import com.RiyadSoftware.nsebkapp.data.models.coupon.CouponResponse;
import com.RiyadSoftware.nsebkapp.data.models.currency.CurrencyRequest;
import com.RiyadSoftware.nsebkapp.data.models.currency.CurrencyResponse;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.NewPasswordRequest;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.NewPasswordResponse;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.VerifyCodeRequest;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.VerifyCodeResponse;
import com.RiyadSoftware.nsebkapp.util.Constant;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RibotsService {


    @POST("Advertisements")
    Observable<OffersResponseModel> getHomeOffers();

    @POST("Awards")
    Observable<RewardsResponse> getRewards(@Body HomeRequest homeRequest);

    @POST("DealDetails")
    Observable<ReplaceAwardResponse> getReplaceAwards(@Body ReplaceAwardRequest replaceAwardRequest);

    @POST("forget_password")
    Observable<ForgetResponse> forgetPassword(@Body Map<String ,String> values);

    @POST("TermsConditions")
    Observable<TermsResponse> getTerms();
    @POST("Policy")
    Observable<TermsResponse> getPolicy();
    @POST("AboutUs")
    Observable<TermsResponse> getAbout();

    @GET("get_product")
    Observable<DealsResponseModel> getProducts(@Query("subid") String subID);

    @POST("Register")
    Observable<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @POST("login")
    Observable<LoginResponse> Login(@Body LoginRequest loginRequest);

    @POST("--------------")
    Observable<VerifyCodeResponse> verify(@Body VerifyCodeRequest request);

    @POST("reset_password")
    Observable<NewPasswordResponse> newPassword(@Body NewPasswordRequest newPasswordRequest );


    @POST("Interests")
    Observable<InterestResponseModel> getinterest(@Body HomeRequest loginRequest);

    @POST("SaveInterests")
    Observable<InterestResponseModel> SaveInterests(@Body SaveInterestRequest saveInterestRequest);

    @POST("MyFavorite")
    Observable<MyFavResponse> getMyFav(@Body HomeRequest homeRequest);

    @POST("Favorite")
    Observable<AddFavResponse> AddFavourite(@Body AddFavRequest loginRequest);


    @GET("future_date")
    Observable<DealsResponseModel> geCurrentDeals();

    @GET("online_product")
    Observable<DealsResponseModel> gePastDeals();

    @GET("the_past_date")
    Observable<DealsResponseModel> geEndedDeals();

    @POST("Countries")
    Observable<CountriesResponse> getCountries();

    @POST("verify_code")
    Observable<VerifyResponse> verifyUSer(@Body Map<String ,String> values);

    @POST("loguser")
    Observable<DealsResponseModel> geMyDeals();


    @POST("editprofile")
    Observable<EditprofileResponse> editProfile(@Body Map<String ,String> values);


    @POST("homePage")
    Observable<HomeModel> getHomePage(@Body HomeRequest Token);

    @POST("Cities")
    Observable<CitiesModel> getCities(@Body CityRequest cityRequest);


    @POST("AddAddress")
    Observable<Addressresponse> AddAddress(@Body AddressRequest cityRequest);


    @POST("logout")
    Observable<VerifyResponse> logout(@Body HomeRequest homeRequest);

    @POST("Packages")
    Observable<PackageResponse> getPackages(@Body HomeRequest Token);

    @POST("Charge")
    Observable<ChargeResponse> Charge(@Body ChargeRequest chargeRequest);

    @POST("Categorys")
    Observable<CategoriesResponce> getCategories(@Body HomeRequest Token);

    @POST("AddTicket")
    Observable<AddTicketResponse> AddTicket(@Body AddTicketRequest addTicketRequest);

    @POST("DealDetails")
    Observable<DealDetailsResponse> getDealDetails(@Body DealDetailsRequest dealDetailsRequest);

    @POST("SubCategorys")
    Observable<CategoriesResponce> getSubCategories(@Body SubCatRequest Token);

    @POST("ChargesHistory")
    Observable<ChargeHistoryResponse> getChargeHistory(@Body HomeRequest homeRequest);


    @POST("ContactUs")
    Observable<ContactResponse> ContactUs(@Body ContactRequest homeRequest);


    @POST("MyDeals")
    Observable<MydealsResponse> getMyDeals(@Body HomeRequest homeRequest);

    @POST("SubCategoryDeals")
    Observable<CategoryProductResponse> getCatsProducts(@Body CategoryProductsRequest homeRequest);


    @POST("useCoupon")
    Observable<CouponResponse> getCouponResult(@Body CouponRequest couponRequest);

    @POST("currencies")
    Observable<CurrencyResponse> getCurrencies(@Body CurrencyRequest currencyRequest);


    @POST("finishDeal")
    Observable<FinishDealResponse> getFinishDeal(@Body FinishDealRequest request);



    /******** Helper class that sets up a new services *******/
    class Creator {

        public static RibotsService newRibotsService() {
            Gson gson = new GsonBuilder()
//                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


            //Create a new Interceptor.
            Interceptor headerAuthorizationInterceptor = new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {

                    okhttp3.Request request = chain.request();
                    Headers headers = request.headers().newBuilder()
                            .add("lang", new SharedPrefDueDate(Application.getAppContext()
                    ).getLanguage()).add("c_id", new SharedPrefDueDate(Application.getAppContext()
                            ).getCurrency()).build();
                    request = request.newBuilder().headers(headers).build();
                    return chain.proceed(request);
                }
            };
            //Add the interceptor to the client builder.

            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(150, TimeUnit.SECONDS)
                    .readTimeout(150, TimeUnit.SECONDS).writeTimeout(150 , TimeUnit.SECONDS)
                    .addInterceptor(headerAuthorizationInterceptor)
                    .addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(RibotsService.class);
        }
    }
}
