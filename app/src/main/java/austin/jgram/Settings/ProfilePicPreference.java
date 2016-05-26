package austin.jgram.Settings;

import android.content.Context;
//import android.preference.Preference;
import android.content.res.Configuration;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.preference.Preference;

import java.util.jar.Attributes;

import austin.jgram.R;

/**
 * Created by Austin on 5/25/16.
 */
public class ProfilePicPreference extends Preference {
    public ProfilePicPreference(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        //this(context, attrs, 0);
    }
    public ProfilePicPreference(Context context, AttributeSet attrs, int def) {
        super(context, attrs, def);
        setWidgetLayoutResource(R.layout.profile_pic_preference);
    }
    //public ProfilePicPreference() {}

    @Override
    public void onBindViewHolder(PreferenceViewHolder hold) {
        super.onBindViewHolder(hold);
        hold.itemView.setClickable(false);
        //View button = hold.findViewById(R.id.)
    }

   /* @Override
    protected View onCreateView(ViewGroup parent) {
        //super.onCreateView(parent);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return layoutInflater.inflate(R.layout.profile_pic_preference, parent, false);
    }*/
}
