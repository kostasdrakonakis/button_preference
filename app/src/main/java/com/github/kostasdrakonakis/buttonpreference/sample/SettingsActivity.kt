package com.github.kostasdrakonakis.buttonpreference.sample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.github.kostasdrakonakis.ButtonPreference

class SettingsActivity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        } else {
            title = savedInstanceState.getCharSequence(TITLE_TAG)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat, pref: Preference): Boolean {
        val args = pref.extras
        val fragment = supportFragmentManager.fragmentFactory.instantiate(
            classLoader,
            pref.fragment
        ).apply {
            arguments = args
            setTargetFragment(caller, 0)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.settings, fragment)
            .addToBackStack(null)
            .commit()
        title = pref.title
        return true
    }

    internal class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.prefs, rootKey)
            val buttonPreference: ButtonPreference? = findPreference(getString(R.string.app_name))
            buttonPreference?.setOnClickListener {
                Toast.makeText(requireContext(), "Hello from Preference", Toast.LENGTH_SHORT).show()
            }
            buttonPreference?.setButtonBackgroundColor(R.color.colorPrimaryDark)
        }
    }

    companion object {
        private fun createIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }

        @JvmStatic
        fun startActivity(activity: Activity) {
            activity.startActivity(createIntent(activity))
        }

        const val TITLE_TAG = "settingsActivityTitle"
    }
}