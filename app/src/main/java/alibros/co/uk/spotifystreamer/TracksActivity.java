package alibros.co.uk.spotifystreamer;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import java.util.List;

import alibros.co.uk.spotifystreamer.logic.ABSpotify;
import butterknife.ButterKnife;
import kaaes.spotify.webapi.android.models.Track;


public class TracksActivity extends AppCompatActivity {


    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;
    private ABSpotify abSpotify;
    private List<Track> tracks;


    private TracksFragment tracksFragment;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        ButterKnife.inject(this);

        String artistid = getIntent().getStringExtra("ARTISTID");
        String artistname = getIntent().getStringExtra("ARTISTNAME");

        if (null == savedInstanceState) {

            tracksFragment = new TracksFragment();

            Bundle bundle = new Bundle();
            bundle.putString(getString(R.string.artist_id_bundle_key), artistid);
            bundle.putString(getString(R.string.artist_name_bundle_key), artistname);
            tracksFragment.setArguments(bundle);

            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.tracks_fragment_container, tracksFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_track, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
