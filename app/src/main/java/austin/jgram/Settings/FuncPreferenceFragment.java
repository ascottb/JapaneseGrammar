package austin.jgram.Settings;

import android.preference.PreferenceFragment;
import android.os.Bundle;

import austin.jgram.R;

public class FuncPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_func);
    }
}
