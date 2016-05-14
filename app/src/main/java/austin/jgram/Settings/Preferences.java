package austin.jgram.Settings;

import android.preference.PreferenceActivity;
import java.util.List;

import austin.jgram.R;

public class Preferences extends PreferenceActivity {

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.settings, target);
    }
    @Override
    protected boolean isValidFragment (String fragmentName) {
        return true;
    }
}