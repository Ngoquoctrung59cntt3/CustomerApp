package vn.edu.ntu.dinhtuyen.customerapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import vn.edu.ntu.dinhtuyen.database.DatabaseClient;
import vn.edu.ntu.dinhtuyen.model.CustomerEntity;

public class EditCustomer extends Fragment {

    NavController controller;
    EditText edtName, edtPhone;
    Button btnAdd;
    RadioButton rdbFriendly;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.edit_customer, container, false);
        edtName = view.findViewById(R.id.edtName);
        edtPhone = view.findViewById(R.id.edtPhone);
        rdbFriendly = view.findViewById(R.id.rdFriendly);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = NavHostFragment.findNavController(EditCustomer.this);
        ((MainActivity) getActivity()).controller = controller;
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertCustomer();
            }
        });
    }

    private void insertCustomer() {
        final String customerName = edtName.getText().toString().trim();
        final String customerPhone = edtPhone.getText().toString().trim();
        final boolean friendly;

        if (rdbFriendly.isChecked()) {
            friendly = true;
        }
        else {
            friendly = false;
        }

        if (customerName.isEmpty()) {
            edtPhone.setError("CustomerName is not empty!");
            edtPhone.requestFocus();
            return;
        }
        if (customerPhone.isEmpty()) {
            edtPhone.setError("CustomerPhone is not empty!");
            edtPhone.requestFocus();
            return;
        }

        class InsertCustomer extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                CustomerEntity customer = new CustomerEntity();
                customer.setCustomerName(customerName);
                customer.setCustomerPhone(customerPhone);
                customer.setFriendly(friendly);
                DatabaseClient.getInstance(getContext().getApplicationContext()).getAppDatabase()
                        .customerDao()
                        .insert(customer);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity(), "insert success", Toast.LENGTH_SHORT).show();
            }
        }
        InsertCustomer insertCustomer = new InsertCustomer();
        insertCustomer.execute();
    }
}
