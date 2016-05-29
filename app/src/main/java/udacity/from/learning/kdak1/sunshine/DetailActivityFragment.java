package udacity.from.learning.kdak1.sunshine;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.TextView;
import android.view.MenuInflater;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    public String log_tag = "SHARE TAG";

    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the acion bar if it is present.
        inflater.inflate(R.menu.detailfragment, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);

        String forecastStr = "You have nothing to share!";

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            forecastStr = intent.getStringExtra(Intent.EXTRA_TEXT) + " #SunshineApp";
        }

        ShareActionProvider myProvider = new ShareActionProvider();
        myProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, forecastStr);
        shareIntent.setType("text/plain");

        Log.v(log_tag, forecastStr);

        if(myProvider != null) {
            myProvider.setShareIntent(shareIntent);
        }
        else {
            Log.e(log_tag, "SHARE INTENT WAS EMPTY DUMBASS");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            String forecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            ((TextView) rootView.findViewById(R.id.forecast_detail)).setText(forecastStr);
        }

        return rootView;
    }


}
