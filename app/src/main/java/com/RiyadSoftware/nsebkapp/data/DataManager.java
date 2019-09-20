package com.RiyadSoftware.nsebkapp.data;

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
import com.RiyadSoftware.nsebkapp.data.remote.RibotsService;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class DataManager {

    private final RibotsService mRibotsService;

    @Inject
    public DataManager(RibotsService ribotsService/*, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper*/) {
        mRibotsService = ribotsService;
//        mPreferencesHelper = preferencesHelper;
//        mDatabaseHelper = databaseHelper;
    }

//    public PreferencesHelper getPreferencesHelper() {
//        return mPreferencesHelper;
//    }

//    public Observable<Ribot> syncRibots() {
//        return mRibotsService.getRibots()
//                .concatMap(new Function<List<Ribot>, ObservableSource<? extends Ribot>>() {
//                    @Override
//                    public ObservableSource<? extends Ribot> apply(@NonNull List<Ribot> ribots)
//                            throws Exception {
//                        return mDatabaseHelper.setRibots(ribots);
//                    }
//                });
//    }


    public Observable<OffersResponseModel> getOffers() {
        return mRibotsService.getHomeOffers();
    }

    public Observable<HomeModel> getHomeData(HomeRequest homeRequest) {
        return mRibotsService.getHomePage(homeRequest);
    }

    public Observable<DealsResponseModel> getProducts(String subID) {
        return mRibotsService.getProducts(subID);
    }


    public Observable<LoginResponse> login(LoginRequest loginRequest) {
        return mRibotsService.Login(loginRequest);
    }

    public Observable<VerifyCodeResponse> verifyForgetPasswordt(VerifyCodeRequest request) {
        return mRibotsService.verify(request);
    }
    public Observable<NewPasswordResponse> newPassword(NewPasswordRequest request) {
        return mRibotsService.newPassword(request);
    }
    public Observable<RegisterResponse> register(RegisterRequest registerRequest) {
        return mRibotsService.register(registerRequest);
    }

    public Observable<InterestResponseModel> getinterest(HomeRequest registerRequest) {
        return mRibotsService.getinterest(registerRequest);
    }

    public Observable<InterestResponseModel> SaveInterest(SaveInterestRequest saveInterestRequest) {
        return mRibotsService.SaveInterests(saveInterestRequest);
    }


    public Observable<RewardsResponse> getRewards(HomeRequest homeRequest) {
        return mRibotsService.getRewards(homeRequest);
    }
    public Observable<ReplaceAwardResponse> getReplaceAwards(ReplaceAwardRequest replaceAwardRequest) {
        return mRibotsService.getReplaceAwards(replaceAwardRequest);
    }




    public Observable<MyFavResponse> getMyFav(HomeRequest homeRequest) {
        return mRibotsService.getMyFav(homeRequest);
    }

    public Observable<DealsResponseModel> getCurrentDelas() {
        return mRibotsService.geCurrentDeals();
    }

    public Observable<DealsResponseModel> getPastDelas() {
        return mRibotsService.gePastDeals();
    }

    public Observable<CountriesResponse> getCountries() {
        return mRibotsService.getCountries();
    }


    public Observable<VerifyResponse> verifyUser(Map<String, String> values) {
        return mRibotsService.verifyUSer(values);
    }

    public Observable<ForgetResponse> forgetPassword(Map<String, String> values) {
        return mRibotsService.forgetPassword(values);
    }
    public Observable<TermsResponse> getTerms() {
        return mRibotsService.getTerms();
    }
    public Observable<TermsResponse> getPolicy() {
        return mRibotsService.getPolicy();
    }
    public Observable<TermsResponse> getAbout() {
        return mRibotsService.getAbout();
    }


    public Observable<DealsResponseModel> getEndedDeals() {
        return mRibotsService.geEndedDeals();
    }

    public Observable<DealsResponseModel> getMyDeals() {
        return mRibotsService.geMyDeals();
    }


    public Observable<EditprofileResponse> editProfile(Map<String, String> values) {
        return mRibotsService.editProfile(values);
    }

    public Observable<CitiesModel> getCities(CityRequest cityRequest) {
        return mRibotsService.getCities(cityRequest);
    }


    public Observable<Addressresponse> AddAddress(AddressRequest cityRequest) {
        return mRibotsService.AddAddress(cityRequest);
    }

    public Observable<VerifyResponse> logout(HomeRequest homeRequest) {
        return mRibotsService.logout(homeRequest);
    }

    public Observable<PackageResponse> getPackages(HomeRequest homeRequest) {
        return mRibotsService.getPackages(homeRequest);
    }

    public Observable<CategoriesResponce> getcategries(HomeRequest homeRequest) {
        return mRibotsService.getCategories(homeRequest);
    }

    public Observable<CategoriesResponce> getSubcategries(SubCatRequest homeRequest) {
        return mRibotsService.getSubCategories(homeRequest);
    }

    public Observable<ChargeHistoryResponse> getChargeHistory(HomeRequest homeRequest) {
        return mRibotsService.getChargeHistory(homeRequest);
    }
    public Observable<ContactResponse> ContactUs(ContactRequest homeRequest) {
        return mRibotsService.ContactUs(homeRequest);
    }


    public Observable<CategoryProductResponse> getCatsProducts(CategoryProductsRequest homeRequest) {
        return mRibotsService.getCatsProducts(homeRequest);
    }


    public Observable<CouponResponse> getCouponResult(CouponRequest couponRequest) {
        return mRibotsService.getCouponResult(couponRequest);
    }




    public Observable<MydealsResponse> getMydeals(HomeRequest homeRequest) {
        return mRibotsService.getMyDeals(homeRequest);
    }

    public Observable<AddTicketResponse> AddTicket(AddTicketRequest addTicketRequest) {
        return mRibotsService.AddTicket(addTicketRequest);
    }


    public Observable<DealDetailsResponse> getDealDetails(DealDetailsRequest dealDetailsRequest) {
        return mRibotsService.getDealDetails(dealDetailsRequest);
    }


    public Observable<AddFavResponse> AddFav(AddFavRequest homeRequest) {
        return mRibotsService.AddFavourite(homeRequest);
    }


    public Observable<ChargeResponse> Charge(ChargeRequest chargeRequest) {
        return mRibotsService.Charge(chargeRequest);
    }

    public Observable<CurrencyResponse> getCurrenciesList(CurrencyRequest currencyRequest) {
        return mRibotsService.getCurrencies(currencyRequest);
    }
}
