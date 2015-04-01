package bluescreen1.ieeeuwisb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import bluescreen1.ieeeuwisb.NavigationDrawerFragment.NavItem;

public class myNavAdapter extends ArrayAdapter<NavItem> {

    private final Context context;
    private final int layoutResourceId;
    private NavItem data[] = null;
    private View v;
    private LayoutInflater inflater;

    public myNavAdapter(Context context, int resource, NavItem[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        ImageView imageView = (ImageView) convertView.findViewById(R.id.navDrawerImageView);
        TextView textView = (TextView) convertView.findViewById(R.id.navDrawerTextView);

        NavItem choice = data[position];

        imageView.setImageResource(choice.icon);
        textView.setText(choice.name);

        return v;
    }
}