package vn.edu.ntu.dinhtuyen.customerapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.ntu.dinhtuyen.database.DatabaseClient;
import vn.edu.ntu.dinhtuyen.model.CustomerEntity;

public class ListCustomer extends Fragment {

    RecyclerView rvListCustomer;
    NavController controller;
    ImageButton imgEdit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.list_customer, container, false);
        imgEdit = view.findViewById(R.id.imgEdit);
        rvListCustomer = view.findViewById(R.id.rvListCustomer);
        rvListCustomer.setLayoutManager(new LinearLayoutManager(getActivity()));
        getCustomers();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.customer_mnu, menu);
        MenuItem mnuAdd = menu.findItem(R.id.mnuAdd);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuAdd: controller.navigate(R.id.action_FirstFragment_to_SecondFragment);
                                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void getCustomers() {
        class GetCustomers extends AsyncTask<Void, Void, List<CustomerEntity>> {
            @Override
            protected List<CustomerEntity> doInBackground(Void... voids) {
                List<CustomerEntity> customers = DatabaseClient.getInstance(getContext().getApplicationContext())
                        .getAppDatabase()
                        .customerDao()
                        .findAll();
                return customers;
            }

            @Override
            protected void onPostExecute(List<CustomerEntity> customerEntities) {
                super.onPostExecute(customerEntities);
                CustomerAdapter adapter = new CustomerAdapter(customerEntities);
                rvListCustomer.setAdapter(adapter);
            }
        }

        GetCustomers getCustomers = new GetCustomers();
        getCustomers.execute();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = NavHostFragment.findNavController(ListCustomer.this);
        ((MainActivity) getActivity()).controller = controller;
    }

    private class CustomerViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtName, txtPhone;
        ImageButton imgEdit;
        CustomerEntity customer;
        ImageView imgFriendly;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtName);
            txtPhone = this.itemView.findViewById(R.id.txtPhone);
            imgFriendly = this.itemView.findViewById(R.id.imgFriendly);
            imgEdit = this.itemView.findViewById(R.id.imgEdit);
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", String.valueOf(customer.getId()));
                    controller.navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
                }
            });
        }

        public void bind(CustomerEntity customer) {
            txtName.setText(customer.getCustomerName());
            txtPhone.setText(customer.getCustomerPhone());
            if (customer.isFriendly() == true) {
                imgFriendly.setImageResource(R.drawable.ic_action_like);
            }
            else {
                imgFriendly.setImageResource(R.drawable.ic_action_dislike);
            }
            imgEdit.setImageResource(R.drawable.ic_action_edit);
            this.customer = customer;
        }

    }

    private class CustomerAdapter extends RecyclerView.Adapter<CustomerViewHolder> {

        List<CustomerEntity> customers;

        public CustomerAdapter(List<CustomerEntity> customers) {
            this.customers = customers;
        }

        @NonNull
        @Override
        public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.customer_item, parent, false);
            return new CustomerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
            holder.bind(customers.get(position));
        }

        @Override
        public int getItemCount() {
            return customers.size();
        }
    }
}
