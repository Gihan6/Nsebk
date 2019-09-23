package com.RiyadSoftware.nsebkapp.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Register.RegisterPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Register.RegisterView;
import com.RiyadSoftware.nsebkapp.Ui.Register.VerifyActivity;
import com.RiyadSoftware.nsebkapp.adapters.CustomArrayAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.CountriesResponse;
import com.RiyadSoftware.nsebkapp.data.models.CitiesModel;
import com.RiyadSoftware.nsebkapp.data.models.CityRequest;
import com.RiyadSoftware.nsebkapp.data.models.RegisterRequest;
import com.RiyadSoftware.nsebkapp.data.models.RegisterResponse;
import com.RiyadSoftware.nsebkapp.data.models.VerifyResponse;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Register extends BaseActivity implements RegisterView {


    //init the main_color_border views
    @BindView(R.id.titleTV)
    TextView titleTV;
    @BindView(R.id.s1IV)
    ImageView s1IM;
    @BindView(R.id.s2IV)
    ImageView s2IM;
    @BindView(R.id.s3IV)
    ImageView s3IM;
    @BindView(R.id.s2TV)
    TextView s2TV;
    @BindView(R.id.s3TV)
    TextView s3TV;


    //init the three parent
    @BindView(R.id.parent1)
    LinearLayout parent1;
    @BindView(R.id.parent2)
    LinearLayout parent2;
    @BindView(R.id.parent3)
    LinearLayout parent3;
    @BindView(R.id.n1Btn)
    Button n1;
    @BindView(R.id.n2Btn)
    Button n2;


    //init the first parent
    @BindView(R.id.fullnameET)
    EditText fullnameET;
    @BindView(R.id.usernameET)
    EditText usernameET;
    @BindView(R.id.emailET)
    EditText emailET;

    @BindView(R.id.passET)
    EditText passET;
    @BindView(R.id.repassET)
    EditText repassET;

    @BindView(R.id.error_txt_r_1)
    TextView error_txt_r_1;
    @BindView(R.id.pass_iv)
    ImageView pass_iv;
    @BindView(R.id.passConfLN)
    LinearLayout passConfLN;

    //init the second parent
    @BindView(R.id.countrySP)
    Spinner countrySP;
    @BindView(R.id.cities_sp)
    Spinner cities_sp;
    @BindView(R.id.phoneET)
    EditText phoneET;
    @BindView(R.id.jobET)
    EditText jobET;
    @BindView(R.id.maleRB)
    RadioButton maleRB;
    @BindView(R.id.femaleRB)
    RadioButton femaleRB;
    @BindView(R.id.genderRG)
    RadioGroup genderRG;
    @BindView(R.id.birthET)
    EditText birthET;
    @BindView(R.id.check1)
    AppCompatCheckBox check1;
    @BindView(R.id.check2)
    AppCompatCheckBox check2;

    TextWatcher textWatcher;

    //init the third parent
    //init the pref
    SharedPrefDueDate pref;
    List<CountriesResponse.InnerDatum> coInnerData = new ArrayList<>();
    List<CitiesModel.Datum> citiesData = new ArrayList<>();
    int mCurrentStep = 1;
    // to choose the gender
    String gender = "";
    @Inject
    RegisterPresenter mRegisterPresenter;

    // inti the views
//    ArrayList<CountryModel> countries = new ArrayList<>();
//
//    ArrayAdapter<CharSequence> countryAdapter;


    // action the birthdate
    //firebase
    private FirebaseAuth mAuth;
    private FirebaseUser mFirebaseUser;
    // initialize DatePicker
    private DatePickerDialog fromDatePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        activityComponent().inject(this);
        mRegisterPresenter.attachView(this);
        //firebase intial
        mAuth = FirebaseAuth.getInstance();

        pref = new SharedPrefDueDate(this);


        //the city spinner
//        countryAdapter = ArrayAdapter.createFromResource(this,
//                R.array.gavor_array, android.R.layout.simple_spinner_item);
//
//        countryAdapter.setDropDownViewResource(R.layout.spinner_center_item);
//        countrySP.setAdapter(countryAdapter);

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (passConfLN.getVisibility() == View.VISIBLE) {
                    passConfLN.setVisibility(View.GONE);
                }
            }
        };

        TextWatcher passwatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!passET.getText().toString().isEmpty() && !repassET.getText().toString().isEmpty()) {
                    if (editable.toString().length() >= 6) {
                        passConfLN.setVisibility(View.VISIBLE);
                        if (passET.getText().toString().equals(repassET.getText().toString())) {
                            error_txt_r_1.setText(getString(R.string.valid_pass));
                            pass_iv.setImageResource(R.drawable.true_ic);
                        } else {
                            error_txt_r_1.setText(getString(R.string.regconpass));
                            pass_iv.setImageResource(R.drawable.error_icon);
                        }
                    } else {
                        passConfLN.setVisibility(View.INVISIBLE);
                    }
                }
            }
        };


        fullnameET.addTextChangedListener(textWatcher);
        usernameET.addTextChangedListener(textWatcher);
        emailET.addTextChangedListener(textWatcher);
        passET.addTextChangedListener(textWatcher);
        repassET.addTextChangedListener(textWatcher);

        passET.addTextChangedListener(passwatcher);
        repassET.addTextChangedListener(passwatcher);

        mRegisterPresenter.getCountries();

        //get the date using DatePickerDialog
        setDateTimeField();

        //action for edit wedding date Button
        birthET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show DatePickerDialog
                fromDatePickerDialog.show();
            }
        });


        countrySP.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (coInnerData.isEmpty()) {
                    mRegisterPresenter.getCountries();
                }
//                countrySP.performClick();
                return false;
            }
        });


    }

    /**
     * action the DatePickerDialog to get the date
     */
    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                ++monthOfYear;
                String date = year + "-" + (monthOfYear < 10 ? "0" + monthOfYear : monthOfYear) + "-" +
                        (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
//                if (validateDate(date))
                birthET.setText(date);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    private boolean validateDate(String selectedDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-mm-yyyy");

        try {

            Date oldDate = dateFormat.parse(selectedDate);
            System.out.println(oldDate);

            Date currentDate = new Date();

            long diff = currentDate.getTime() - oldDate.getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;

            if (oldDate.before(currentDate)) {

                Log.e("oldDate", "is previous date");
                Log.e("Difference: ", " seconds: " + seconds + " minutes: " + minutes
                        + " hours: " + hours + " days: " + days);
            } else {
                Toast.makeText(this, getString(R.string.error_in_data), Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
            // Log.e("toyBornTime", "" + toyBornTime);

        } catch (ParseException e) {

            e.printStackTrace();
        }
        return false;
    }

    @OnClick(R.id.n1Btn)
    void next1() {

        if (validate(1))
            show(2);


    }

    // here to add new register account
    @OnClick(R.id.n2Btn)
    void next2() {


        if (maleRB.isChecked()) {
            gender = "male";
        } else if (femaleRB.isChecked()) {
            gender = "female";
        } else {
            Toast.makeText(Register.this, getString(R.string.complete_data), Toast.LENGTH_LONG).show();
            return;
        }

        if (validate(2)) {
//            showLoader();
            RegisterRequest registerRequest = new RegisterRequest(
                    fullnameET.getText().toString(),
                    usernameET.getText().toString(),
                    emailET.getText().toString(),
                    passET.getText().toString(),
                    String.valueOf(coInnerData.get(countrySP.getSelectedItemPosition()).getId()),
                    gender,
                    String.valueOf(citiesData.get(cities_sp.getSelectedItemPosition()).getCity_id()),
                    jobET.getText().toString(),
                    birthET.getText().toString(),
                    phoneET.getText().toString()
            );
            mRegisterPresenter.register(registerRequest);

//            mAuth.createUserWithEmailAndPassword(emailET.getText().toString().trim(), passET.getText().toString())
//                    .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//
//                            if (!task.isSuccessful()) {
//
//                                hideLoader();
//
//
//                                Log.d("google", "error ---     " + Objects.requireNonNull(task.getException()).getMessage());
//
//                                show(1);
//                                Toast.makeText(getApplicationContext(), getString(R.string.wrong_register), Toast.LENGTH_LONG)
//                                        .show();
//                                //go to the login
////                            login();
//                            } else {
////                            loading.setVisibility(View.GONE);
//                                mFirebaseUser = mAuth.getCurrentUser();
//                                // call the api to register the email and password
//                                sendDataTOServer();
//                            }
//
//                        }
//                    });

        }


    }

    /**
     * here to extract the data from the server and get it
     *
     * @param response the json object is returned from the server
     * @throws JSONException the json exception
     */
    @SuppressLint("SetTextI18n")
    private void extractJson(JSONObject response) throws JSONException {

        String status = response.getString("status");

        if (!status.equals("false")) {

//            JSONObject dataObject = response.getJSONObject("Data");

            String id = response.getString("user_id");
            pref.setPersonId(id);

            // here to show the third parent
            show(3);

            // to send the email to verify

            sendVerificationEmail();

//            Intent i = new Intent(LoginActivity.this, MainActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(i);


        } else {
            show(1);
//            String message = response.getString("Message");
            Toast.makeText(Register.this, getString(R.string.wrong_register), Toast.LENGTH_LONG).show();

        }
    }


    // here to send verification
    private void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        assert user != null;
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent


                            // after email is sent just logout the user and finish this activity
//                            FirebaseAuth.getInstance().signOut();
//                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
//                            finish();
                        } else {
                            // email not sent, so display message and restart the activity or do whatever you wish to do

                            //restart this activity
//                            overridePendingTransition(0, 0);
//                            finish();
//                            overridePendingTransition(0, 0);
//                            startActivity(getIntent());

                        }
                    }
                });
    }


    // here to get the data of the country

    /**
     * here to send  the data from the server
     */


    @OnClick(R.id.s1IV)
    void show1() {
        show(1);
    }

    @OnClick(R.id.s2IV)
    void show2() {
        show(2);
    }

    @OnClick(R.id.s3IV)
    void show3() {
        show(3);
    }


    //to show and hidden the views
    private void show(int step) {

        mCurrentStep = step;

        if (step == 1) {
            parent1.setVisibility(View.VISIBLE);
            parent2.setVisibility(View.GONE);
            parent3.setVisibility(View.GONE);

            titleTV.setText(getResources().getString(R.string.title_register_one));
        } else if (step == 2) {
            parent1.setVisibility(View.GONE);
            parent2.setVisibility(View.VISIBLE);
            parent3.setVisibility(View.GONE);
            s2IM.setVisibility(View.VISIBLE);
            s2TV.setVisibility(View.GONE);
            titleTV.setText(getResources().getString(R.string.title_register_two));

            if (coInnerData.isEmpty()) {
                mRegisterPresenter.getCountries();
            }


        } else if (step == 3) {
            parent1.setVisibility(View.GONE);
            parent2.setVisibility(View.GONE);
            parent3.setVisibility(View.VISIBLE);

            s3IM.setVisibility(View.VISIBLE);
            s3TV.setVisibility(View.GONE);
            titleTV.setText(getResources().getString(R.string.title_register_three));

        }
    }


    // here to validate the first app
    private boolean validate(int step) {
        if (step == 1) {

            if (fullnameET.getText().toString().isEmpty() || usernameET.getText().toString().isEmpty()
                    || emailET.getText().toString().isEmpty() || passET.getText().toString().isEmpty()
                    || repassET.getText().toString().isEmpty()) {
                error_txt_r_1.setText(getString(R.string.complete_data));

                if (passConfLN.getVisibility() == View.GONE) {
                    passConfLN.setVisibility(View.VISIBLE);
                }

                Toast.makeText(Register.this, getString(R.string.complete_data), Toast.LENGTH_LONG).show();
                return false;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(emailET.getText().toString()).matches()) {
                Toast.makeText(Register.this, getString(R.string.enter_valid_email), Toast.LENGTH_LONG).show();
                error_txt_r_1.setText(getString(R.string.enter_valid_email));
                if (passConfLN.getVisibility() == View.GONE) {
                    passConfLN.setVisibility(View.VISIBLE);
                }
                return false;
            }

            if (!passET.getText().toString().equals(repassET.getText().toString())) {
                Toast.makeText(Register.this, getString(R.string.pass_match), Toast.LENGTH_LONG).show();
                error_txt_r_1.setText(getString(R.string.pass_match));
                if (passConfLN.getVisibility() == View.GONE) {
                    passConfLN.setVisibility(View.VISIBLE);
                }
                return false;
            } else {
                passConfLN.setVisibility(View.VISIBLE);
            }

//            if (!Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\\\S+$).{4,}$")
//                    .matcher(passET.getText().toString().trim()).matches()) {
//                Toast.makeText(this, getString(R.string.complex_pass), Toast.LENGTH_SHORT).show();
//                return false;
//            }

            return true;
        } else if (step == 2) {

            if (phoneET.getText().toString().isEmpty() ||
                    jobET.getText().toString().isEmpty() || birthET.getText().toString().isEmpty()) {
                Toast.makeText(Register.this, getString(R.string.complete_data), Toast.LENGTH_LONG).show();
                return false;
            }

            if (phoneET.getText().toString().length() != 10) {
                Toast.makeText(this, getString(R.string.phone_lenghth), Toast.LENGTH_SHORT).show();
                return false;
            }

            if (coInnerData.isEmpty()) {
                Toast.makeText(this, getString(R.string.select_country_fist), Toast.LENGTH_SHORT).show();
                return false;
            }

            if (citiesData.isEmpty()) {
                Toast.makeText(this, getString(R.string.select_city_fist), Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        } else {
            return true;
        }

    }


    @OnClick(R.id.backIV)
    void back() {
        finish();
    }


    @OnClick(R.id.skipBtn)
    void skip() {
        Intent i = new Intent(Register.this, MainActivity2.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void navigateToMain(RegisterResponse response) {
        if (response.getErrors() == null && response.getSuccess().equalsIgnoreCase("success")) {
            response.getData().setVerified(false);
            pref.setUserLogged(new Gson().toJson(response));

            Intent i = new Intent(Register.this, VerifyActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {


            if (response.getErrors() != null) {
                String errorAll = "";
                for (RegisterResponse.Error error : response.getErrors()) {
                    errorAll += error.getMessage() + "\n";
                }

                Toast.makeText(this, "" + errorAll, Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();fonbo
        if (parent1.getVisibility() == View.VISIBLE) {
            finish();
        } else if (parent2.getVisibility() == View.VISIBLE) {
            show(1);
        }
    }


    @Override
    public void showCountries(CountriesResponse countriesResponse) {
        if (countriesResponse.getSuccess().equalsIgnoreCase("success")) {
            if (countriesResponse.getData().isEmpty() || countriesResponse.getData().isEmpty()) {
                Toast.makeText(this, getString(R.string.empty_countries), Toast.LENGTH_SHORT).show();
                return;
            }
            coInnerData = countriesResponse.getData();
            countrySP.setAdapter(new CustomArrayAdapter(this, R.layout.custom_spinner_item
                    , coInnerData));

            //--------------------here put country code--------------



            countrySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    mRegisterPresenter.getCities(new CityRequest(coInnerData.get(i).getId()));

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }

            });
        }


    }

    @Override
    public void showCities(CitiesModel countriesResponse) {
        if (countriesResponse == null) {
            citiesData.clear();
            cities_sp.setAdapter(new ArrayAdapter<Object>(Register.this,
                    R.layout.spinner_center_item,
                    new String[]{}));
        } else if (countriesResponse.getSuccess().equalsIgnoreCase("success")) {
            citiesData = countriesResponse.getData();

            String[] d = new String[countriesResponse.getData().size()];
            for (int i = 0; i < citiesData.size(); i++) {
                d[i] = citiesData.get(i).getCity_name();
            }


            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(Register.this,
                    R.layout.spinner_center_item,
                    d);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_center_item); // The drop down view
            cities_sp.setAdapter(spinnerArrayAdapter);

        } else {
            citiesData.clear();
            cities_sp.setAdapter(new ArrayAdapter<Object>(Register.this,
                    R.layout.spinner_center_item,
                    new String[]{}));
        }

    }

    @Override
    public void afterVerify(VerifyResponse countriesResponse) {

    }

    @Override
    public void showErrorInRegister(Throwable e) {
        Toast.makeText(this, "" + e.getLocalizedMessage() + "\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.check2)
    void goToService() {
        startActivity(new Intent(getApplicationContext(),Conditions.class));

    }
}
