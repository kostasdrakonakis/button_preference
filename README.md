# Button Preference [![Maven Central](https://img.shields.io/badge/Maven-button--preference-brightgreen.svg)](http://search.maven.org/#search%7Cga%7C1%7Ckostasdrakonakis)  [![Build](https://github.com/kostasdrakonakis/button_preference/actions/workflows/android.yml/badge.svg?branch=master)](https://github.com/kostasdrakonakis/button_preference/actions/workflows/android.yml)

This is a Button Preference to be used in the Android PreferenceActivity

Download
--------

Download the latest JAR or grab via Maven:
```xml
<dependency>
  <groupId>com.github.kostasdrakonakis</groupId>
  <artifactId>button-preference</artifactId>
  <version>1.0.3</version>
</dependency>
```
or Gradle:
```groovy
implementation 'com.github.kostasdrakonakis:button-preference:1.0.3'
```
Usage
-----

In the prefs.xml add this:

```xml
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
    <com.github.kostasdrakonakis.ButtonPreference
        android:defaultValue="@string/app_name"
        android:key="@string/app_name"
        android:layout_gravity="center"
        android:title="My Preference Button" />
</PreferenceScreen>
```

and then in your PreferenceActivity

```kotlin
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
```

Customization
-------------

You can specify attributes in prefs.xml directly in the layout:
* preferenceAllCaps //Sets letters to Uppercase if true
* preferenceLayoutPadding //Sets the button padding
* preferenceBackground //Sets the background of the button
* preferenceBackgroundColor //Sets the background color of the Button
* preferenceLayoutColor //Sets the button layout background color
* preferenceVisible //Sets the button Visibility

```xml
    <com.github.kostasdrakonakis.ButtonPreference
        app:preferenceAllCaps="false"
        app:preferenceLayoutPadding="50dp"
        app:preferenceBackground="@mipmap/ic_launcher"
        app:preferenceBackgroundColor="@color/colorAccent"
        app:preferenceLayoutColor="@color/colorPrimaryDark"
        app:preferenceVisible="true" />
```

or programmatically like:

```kotlin
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
```

License
-------

 Copyright 2017 Kostas Drakonakis

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 <http://www.apache.org/licenses/LICENSE-2.0>

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
