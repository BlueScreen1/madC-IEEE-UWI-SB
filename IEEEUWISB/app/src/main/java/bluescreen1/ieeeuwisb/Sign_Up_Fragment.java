package bluescreen1.ieeeuwisb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Dane on 3/8/2015.
 */
public class Sign_Up_Fragment extends DialogFragment {

    View root;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        root = inflater.inflate(R.layout.sign_up_dialog, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(root)
                // Add action buttons
                .setPositiveButton("Sign Up", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setData();
                        mListener.onSignUpDialogPositiveClick(Sign_Up_Fragment.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Sign_Up_Fragment.this.getDialog().cancel();

                    }
                });
        return builder.create();
    }

    public interface SignUpDialogListener {
        public void onSignUpDialogPositiveClick(DialogFragment dialog);
        public void onSignUpDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    SignUpDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (SignUpDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public void setData(){

        EditText username = (EditText) root.findViewById(R.id.su_username);
        EditText password = (EditText) root.findViewById(R.id.su_password);
        EditText confirm = (EditText) root.findViewById(R.id.su_confirm_password);
        EditText ieeenum = (EditText) root.findViewById(R.id.su_ieee_num);
        EditText email = (EditText) root.findViewById(R.id.su_email);
        LoginActivity parent = (LoginActivity) getActivity();
        parent.signUp(username.getText().toString(), password.getText().toString(),ieeenum.getText().toString(),email.getText().toString() );
    }
}
