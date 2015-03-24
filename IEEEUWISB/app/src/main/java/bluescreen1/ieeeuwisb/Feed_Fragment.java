package bluescreen1.ieeeuwisb;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dane on 3/24/2015.
 */
public class Feed_Fragment extends MainActivity.PlaceholderFragment {

    private static final String[] titles = {"Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"};
    private static final String[] values = {"Lorem ipsum farsdjkgkdf ldaskjh fkashdf sdlkf ahasdyfl nasdkjf dasljf hasdjfasm",
            "Lorem ipsum farsdjkgkdf ldaskjh fkashdf sdlkf ahasdyfl nasdkjf dasljf hasdjfasm",
            "Lorem ipsum farsdjkgkdf ldaskjh fkashdf sdlkf ahasdyfl nasdkjf dasljf hasdjfasm",
            "Lorem ipsum farsdjkgkdf ldaskjh fkashdf sdlkf ahasdyfl nasdkjf dasljf hasdjfasm",
            "Lorem ipsum farsdjkgkdf ldaskjh fkashdf sdlkf ahasdyfl nasdkjf dasljf hasdjfasm",
            "Lorem ipsum farsdjkgkdf ldaskjh fkashdf sdlkf ahasdyfl nasdkjf dasljf hasdjfasm",
            "Lorem ipsum farsdjkgkdf ldaskjh fkashdf sdlkf ahasdyfl nasdkjf dasljf hasdjfasm"};

    //private static FeedItem[] items = new FeedItem[7];
    private ArrayList<FeedItem> items = new ArrayList<FeedItem>();
    private void setData(){
        FeedItem temp;
        int x;
        for(x = 0; x<7; x++){
            temp = new FeedItem(titles[x], values[x]);
            items.add(temp);
        }

    }

    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Feed_Fragment newInstance(int sectionNumber) {
        Feed_Fragment fragment = new Feed_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Feed_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setData();
        View rootView = inflater.inflate(R.layout.feed_layout, container, false);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.feed_spinner);

        ListView feedView = (ListView) rootView.findViewById(R.id.feed_list_view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.feed_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        feedView.setAdapter(new FeedAdapter(getActivity(), R.layout.feed_item, items ));

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private static class FeedItem{
        private String title;
        private String desc;


        private FeedItem(String title, String desc) {
            this.title = title;
            this.desc = desc;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
   private class FeedAdapter extends ArrayAdapter<FeedItem> {
        private Context con;

        private ArrayList<FeedItem> values;
        private LayoutInflater inflater;

       public FeedAdapter(Context context, int res, ArrayList<FeedItem> values){
           super(context, res, values);

           this.con = context;
           this.values = values;
           this.inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       }

       @Override
       public int getCount() {
           return titles.length;
       }

       @Override
       public FeedItem getItem(int position) {
           return values.get(position);
       }

       @Override
       public long getItemId(int position) {
           return position;
       }

       @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
           View vi = convertView;
           if(convertView == null){
               vi = inflater.inflate(R.layout.feed_item, null);
           }


            TextView title = (TextView) vi.findViewById(R.id.feed_item_title);

            title.setText(getItem(position).getTitle());

            TextView desc = (TextView) vi.findViewById(R.id.feed_item_desc);
            desc.setText(getItem(position).getDesc());

            return vi;
        }
   }

}
