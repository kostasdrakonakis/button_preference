# Button Preference [![Maven Central](https://img.shields.io/badge/Maven-button--preference-brightgreen.svg)](http://search.maven.org/#search%7Cga%7C1%7Ckostasdrakonakis) [![Download](https://api.bintray.com/packages/kdrakonakis/maven/button-preference/images/download.svg)](https://bintray.com/kdrakonakis/maven/button-preference/_latestVersion)

This is a Button Preference to be used in the Android PreferenceActivity

Download
--------

Download the latest JAR or grab via Maven:
```xml
<dependency>
  <groupId>com.github.kostasdrakonakis</groupId>
  <artifactId>button-preference</artifactId>
  <version>1.0.0</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.github.kostasdrakonakis:button-preference:1.0.0'
```

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

License
-------

 Copyright 2017 Kostas Drakonakis

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
