package com.github.buttonpreference2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Toast;

import com.github.kostasdrakonakis.buttonpreference.ButtonPreference;

public class SettingsActivity extends AppCompatPreferenceActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(createIntent(activity));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        setupActionBar();

        ButtonPreference buttonPreference = (ButtonPreference) findPreference(getString(R.string.app_name));
        buttonPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
