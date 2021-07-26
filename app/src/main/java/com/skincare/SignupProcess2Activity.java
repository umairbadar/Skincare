package com.skincare;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.skincare.utilities.Loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignupProcess2Activity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private ProgressDialog loader;

    private TextInputLayout layout_name, layout_phone;
    private TextInputEditText et_name, et_phone_number;

    private CheckBox cb_skin_combination, cb_dry_skin, cb_normal_skin, cb_oily_skin, cb_sensitive_skin;

    private Spinner spn_skin_tone;
    private List<String> skinToneList;
    private String selectedSkinTone = "";

    private Spinner spn_skin_type;
    private List<String> skinTypeList;
    private String selectedSkinTye = "";

    private Spinner spn_eye_color;
    private List<String> eyeColorList;
    private String selectedEyeColor = "";

    private Spinner spn_hair_color;
    private List<String> hairColorList;
    private String selectedHairColor = "";

    private String email_address, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_process_2);

        //Initializing Views...
        initViews();
    }

    private void initViews() {

        email_address = getIntent().getStringExtra("email_address");
        password = getIntent().getStringExtra("password");

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        cb_skin_combination = findViewById(R.id.cb_skin_combination);
        cb_dry_skin = findViewById(R.id.cb_dry_skin);
        cb_normal_skin = findViewById(R.id.cb_normal_skin);
        cb_oily_skin = findViewById(R.id.cb_oily_skin);
        cb_sensitive_skin = findViewById(R.id.cb_sensitive_skin);

        layout_name = findViewById(R.id.layout_name);
        et_name = findViewById(R.id.et_name);
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (layout_name.isErrorEnabled())
                    layout_name.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        layout_phone = findViewById(R.id.layout_phone);
        et_phone_number = findViewById(R.id.et_phone_number);
        et_phone_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (layout_phone.isErrorEnabled())
                    layout_phone.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        spn_skin_tone = findViewById(R.id.spn_skin_tone);
        skinToneList = new ArrayList<>();

        spn_skin_tone.setOnTouchListener((view1, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    try {
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    view1.performClick();
                    break;
                default:
                    break;
            }
            return true;
        });

        spn_skin_tone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSkinTone = skinToneList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getSkinTones();

        spn_skin_type = findViewById(R.id.spn_skin_type);
        skinTypeList = new ArrayList<>();

        spn_skin_type.setOnTouchListener((view1, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    try {
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    view1.performClick();
                    break;
                default:
                    break;
            }
            return true;
        });

        spn_skin_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSkinTye = skinTypeList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getSkinTyes();

        spn_eye_color = findViewById(R.id.spn_eye_color);
        eyeColorList = new ArrayList<>();

        spn_eye_color.setOnTouchListener((view1, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    try {
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    view1.performClick();
                    break;
                default:
                    break;
            }
            return true;
        });

        spn_eye_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedEyeColor = eyeColorList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getEyeColors();

        spn_hair_color = findViewById(R.id.spn_hair_color);
        hairColorList = new ArrayList<>();

        spn_hair_color.setOnTouchListener((view1, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    try {
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    view1.performClick();
                    break;
                default:
                    break;
            }
            return true;
        });

        spn_hair_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedHairColor = hairColorList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getHairColors();
    }

    private void getSkinTones() {

        skinToneList.add("Select Skin Tone");
        skinToneList.add("Fair");
        skinToneList.add("Medium");
        skinToneList.add("Light");
        skinToneList.add("Tan");
        skinToneList.add("Dark");
        skinToneList.add("Olive");
        skinToneList.add("Porcelain");
        skinToneList.add("Deep");
        skinToneList.add("Ebony");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, skinToneList) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NotNull ViewGroup parent) {
                View view;
                if (position == 0) {
                    TextView textView = new TextView(getContext());
                    textView.setHeight(0);
                    textView.setVisibility(View.GONE);
                    view = textView;
                } else {
                    view = super.getDropDownView(position, null, parent);
                }
                return view;
            }
        };

        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spn_skin_tone.setAdapter(dataAdapter);
    }

    private void getSkinTyes() {

        skinTypeList.add("Select Skin Type");
        skinTypeList.add("Dry");
        skinTypeList.add("Combination");
        skinTypeList.add("Oily");
        skinTypeList.add("Normal");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, skinTypeList) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NotNull ViewGroup parent) {
                View view;
                if (position == 0) {
                    TextView textView = new TextView(getContext());
                    textView.setHeight(0);
                    textView.setVisibility(View.GONE);
                    view = textView;
                } else {
                    view = super.getDropDownView(position, null, parent);
                }
                return view;
            }
        };

        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spn_skin_type.setAdapter(dataAdapter);
    }

    private void getEyeColors() {

        eyeColorList.add("Select Eye Color");
        eyeColorList.add("Green");
        eyeColorList.add("Brown");
        eyeColorList.add("Blue");
        eyeColorList.add("Hazel");
        eyeColorList.add("Gray");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, eyeColorList) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NotNull ViewGroup parent) {
                View view;
                if (position == 0) {
                    TextView textView = new TextView(getContext());
                    textView.setHeight(0);
                    textView.setVisibility(View.GONE);
                    view = textView;
                } else {
                    view = super.getDropDownView(position, null, parent);
                }
                return view;
            }
        };

        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spn_eye_color.setAdapter(dataAdapter);
    }

    private void getHairColors() {

        hairColorList.add("Select Hair Color");
        hairColorList.add("Blonde");
        hairColorList.add("Black");
        hairColorList.add("Brunette");
        hairColorList.add("Red");
        hairColorList.add("Auburn");
        hairColorList.add("Gray");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, hairColorList) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NotNull ViewGroup parent) {
                View view;
                if (position == 0) {
                    TextView textView = new TextView(getContext());
                    textView.setHeight(0);
                    textView.setVisibility(View.GONE);
                    view = textView;
                } else {
                    view = super.getDropDownView(position, null, parent);
                }
                return view;
            }
        };

        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spn_hair_color.setAdapter(dataAdapter);
    }

    private void loginWithEmailAndPassword(String name, String email_address, String phone, String Password) {

        loader = Loader.show(this);

        mAuth.createUserWithEmailAndPassword(email_address, Password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            String user_id = user.getUid();
                            Map<String, Object> userMap = new HashMap<>();
                            userMap.put("combination_skin", cb_skin_combination.isChecked() ? 1 : 0);
                            userMap.put("dry_skin", cb_dry_skin.isChecked() ? 1 : 0);
                            userMap.put("normal_skin", cb_normal_skin.isChecked() ? 1 : 0);
                            userMap.put("oily_skin", cb_oily_skin.isChecked() ? 1 : 0);
                            userMap.put("sensitive_skin", cb_sensitive_skin.isChecked() ? 1 : 0);
                            userMap.put("eye_color", selectedEyeColor);
                            userMap.put("hair_color", selectedHairColor);
                            userMap.put("skin_tone", selectedSkinTone);
                            userMap.put("skin_type", selectedSkinTye);
                            userMap.put("email", email_address);
                            userMap.put("name", name);
                            userMap.put("phone", phone);
                            userMap.put("userImage", "");
                            userMap.put("userUid", user_id);

                            DocumentReference user_doc_ref = db.collection("Users").document(user_id);
                            user_doc_ref
                                    .set(userMap)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {

                                            if (loader != null && loader.isShowing())
                                                loader.dismiss();

                                            Toast.makeText(SignupProcess2Activity.this, "User Created. Login to Continue!",
                                                    Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {

                                            if (loader != null && loader.isShowing())
                                                loader.dismiss();

                                            Toast.makeText(SignupProcess2Activity.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    } else {

                        if (loader != null && loader.isShowing())
                            loader.dismiss();

                        Toast.makeText(SignupProcess2Activity.this, "Error: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void validations() {

        String name = et_name.getText().toString().trim();
        String phone_number = et_phone_number.getText().toString().trim();

        if (name.isEmpty()) {
            layout_name.setErrorEnabled(true);
            layout_name.setError("Enter Name");
            layout_name.requestFocus();
        } else if (phone_number.isEmpty()) {
            layout_phone.setErrorEnabled(true);
            layout_phone.setError("Enter Phone Number");
            layout_phone.requestFocus();
        } else if (spn_skin_tone.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Select Skin Tone",
                    Toast.LENGTH_LONG).show();
        } else if (spn_skin_type.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Select Skin Type",
                    Toast.LENGTH_LONG).show();
        } else if (spn_eye_color.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Select Eye Color",
                    Toast.LENGTH_LONG).show();
        } else if (spn_hair_color.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Select Hair Color",
                    Toast.LENGTH_LONG).show();
        } else {
            loginWithEmailAndPassword(
                    name,
                    email_address,
                    phone_number,
                    password
            );
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.btn_login) {
            finish();
        } else if (id == R.id.btn_signup) {
            validations();
        }
    }
}