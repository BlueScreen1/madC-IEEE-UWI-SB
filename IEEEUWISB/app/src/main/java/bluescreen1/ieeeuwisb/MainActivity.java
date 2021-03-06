package bluescreen1.ieeeuwisb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.ParseUser;

import bluescreen1.ieeeuwisb.Fragments.Account_Fragment;
import bluescreen1.ieeeuwisb.Fragments.Contact_Us_Fragment;
import bluescreen1.ieeeuwisb.Fragments.Feed_Fragment;
import bluescreen1.ieeeuwisb.Fragments.Groups_Fragment;
import bluescreen1.ieeeuwisb.Fragments.IEEE_Fragment;
import bluescreen1.ieeeuwisb.Fragments.Meetings_Fragment;
import bluescreen1.ieeeuwisb.Login.LoginActivity;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    public static final String[] headings = new String[] {"Account", "Feed", "Groups", "IEEE", "Meetings", "Contact Us"};
    private Intent intent;
    private int position = 0;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
              (DrawerLayout) findViewById(R.id.drawer_layout));
        mNavigationDrawerFragment.selectItem(1);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        this.position = position;
        switch (position) {
            case 0:
                fragmentManager.beginTransaction().replace(R.id.container, Account_Fragment.newInstance(position + 1)).commit();
                break;
            case 1:
                fragmentManager.beginTransaction().replace(R.id.container, Feed_Fragment.newInstance(position + 1)).commit();
                break;
            case 2:
                fragmentManager.beginTransaction().replace(R.id.container, Groups_Fragment.newInstance(position + 1)).commit();
                break;
            case 3:
                fragmentManager.beginTransaction().replace(R.id.container , IEEE_Fragment.newInstance(position + 1)).commit();
                break;
            case 4:
                fragmentManager.beginTransaction().replace(R.id.container, Meetings_Fragment.newInstance(position + 1)).commit();
                break;

            case 5:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, Contact_Us_Fragment.newInstance(position + 1))
                        .commit();
                break;


            default:
                fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        mTitle = headings[number-1];
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            ParseUser currentuser = ParseUser.getCurrentUser();
            currentuser.logOut();
            intent = new Intent(this, LoginActivity.class);
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
            return true;
        }
        if(id == R.id.action_refresh) {
            refreshFragment();
            Toast.makeText(this, "Page refreshed", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            case 0:
                fragmentManager.beginTransaction().replace(R.id.container, Account_Fragment.newInstance(position + 1)).commit();
                break;
            case 1:
                fragmentManager.beginTransaction().replace(R.id.container, Feed_Fragment.newInstance(position + 1)).commit();
                break;
            case 2:
                fragmentManager.beginTransaction().replace(R.id.container, Groups_Fragment.newInstance(position + 1)).commit();
                break;
            case 3:
                fragmentManager.beginTransaction().replace(R.id.container , IEEE_Fragment.newInstance(position + 1)).commit();
                break;
            case 4:
                fragmentManager.beginTransaction().replace(R.id.container, Meetings_Fragment.newInstance(position + 1)).commit();
                break;

            case 5:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, Contact_Us_Fragment.newInstance(position + 1))
                        .commit();
                break;


            default:
                fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
                break;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        }


    }
}
