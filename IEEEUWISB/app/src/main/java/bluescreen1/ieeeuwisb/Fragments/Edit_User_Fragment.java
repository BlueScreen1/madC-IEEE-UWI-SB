package bluescreen1.ieeeuwisb.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bluescreen1.ieeeuwisb.MainActivity;
import bluescreen1.ieeeuwisb.R;

public class Edit_User_Fragment extends DialogFragment{
        View root;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            root = inflater.inflate(R.layout.edituser_dialog, null);
            builder.setView(root)
                    .setPositiveButton("Edit User", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                   //         setData();
                            mListener.onEditUserDialogPositiveClick(Edit_User_Fragment.this);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Edit_User_Fragment.this.getDialog().cancel();
                        }
                    });
            return builder.create();
        }

        public interface EditUserDialogListener {
            public void onEditUserDialogPositiveClick(DialogFragment dialog);
            public void onEditUserDialogNegativeClick(DialogFragment dialog);
        }

        EditUserDialogListener mListener;

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            try {
                mListener = (EditUserDialogListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement NoticeDialogListener");
            }
        }

        public void setData() {
            EditText username = (EditText) root.findViewById(R.id.editusername);
            EditText password = (EditText) root.findViewById(R.id.editpassword1);
            EditText confirm = (EditText) root.findViewById(R.id.editpassword2);
            EditText ieeenum = (EditText) root.findViewById(R.id.editusernumber);
            EditText email = (EditText) root.findViewById(R.id.edituseremail);
            if (username.getText().toString().isEmpty() && email.getText().toString().isEmpty() && password.getText().toString().isEmpty() && confirm.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "Fill in the empty Fields", Toast.LENGTH_SHORT).show();
            }
            else if (username.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "Invalid username", Toast.LENGTH_SHORT).show();
            }
            else if (email.getText().toString().isEmpty() || !(email.getText().toString().contains("@"))){
                Toast.makeText(getActivity(), "Invalid email", Toast.LENGTH_SHORT).show();
            }
            else if (password.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "Invalid password", Toast.LENGTH_SHORT).show();
            }
            else if(!(confirm.getText().toString().equals(password.getText().toString()))){
                Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(),"dm", Toast.LENGTH_SHORT).show();
               MainActivity parent = (MainActivity)getActivity();
              //parent.editData(username.getText().toString(),password.getText().toString(), ieeenum.getText().toString(), email.getText().toString());
            }
        }
    }
