# Button Preference

This is a Button Preference to be used in the Android PreferenceActivity

Download
--------

Download [the latest JAR][2] or grab via Maven:
```xml
<dependency>
  <groupId>com.github.kostasdrakonakis</groupId>
  <artifactId>button_preference</artifactId>
  <version>1.0.0</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.github.kostasdrakonakis:button_preference:1.0.0'
```

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].

Usage
-----

In the prefs.xml add this:

```xml
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <com.matrix.buttonpreference.ButtonPreference
        android:defaultValue="@string/app_name"
        android:key="@string/app_name"
        android:layout_gravity="center"
        android:title="My Preference Button" />
</PreferenceScreen>
```

and then in your PreferenceActivity

```java
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.matrix.buttonpreference.ButtonPreference;

public class SettingsActivity extends AppCompatPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);

        ButtonPreference buttonPreference = (ButtonPreference) findPreference(getString(R.string.app_name));
        buttonPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsActivity.this, "Preference Button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```

Customization
-------------

You can specify attributes in prefs.xml directly in the layout:

```xml

    <com.matrix.buttonpreference.ButtonPreference
        ...
        <!-- Sets letters to Uppercase if true -->
        app:preferenceAllCaps="false"
        <!-- Sets the button padding  -->
        app:preferenceLayoutPadding="50dp"
        <!-- Sets the background of the button -->
        app:preferenceBackground="@mipmap/ic_launcher"
        <!-- Sets the background color of the Button -->
        app:preferenceBackgroundColor="@color/colorAccent"
        <!-- Sets the button layout background color -->
        app:preferenceLayoutColor="@color/colorPrimaryDark"
        <!-- Sets the button Visibility -->
        app:preferenceVisible="true"
        ... />
```

or programmatically like:

```java
   ButtonPreference buttonPreference = (ButtonPreference) findPreference(getString(R.string.app_name));
   buttonPreference.setVisibility(View.VISIBLE);
   buttonPreference.setButtonBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
```
