package frgp.utn.edu.com.parking.ui.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import frgp.utn.edu.com.parking.MainActivity;
import  frgp.utn.edu.com.parking.PutExtraConst;
import  frgp.utn.edu.com.parking.R;
import frgp.utn.edu.com.parking.User;
import frgp.utn.edu.com.parking.databinding.FragmentMyaccountBinding;


public class MyAccountFragment extends Fragment {

    private FragmentMyaccountBinding binding;

    private View.OnClickListener logoutButtonClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            Toast.makeText(getActivity(), "Sesión cerrada", Toast.LENGTH_SHORT).show();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyAccountViewModel galleryViewModel = new ViewModelProvider(this).get(MyAccountViewModel.class);

        binding = FragmentMyaccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Button logout = (Button)view.findViewById(R.id.btnLogout);
        logout.setOnClickListener(logoutButtonClickListener);

        //TextView textView = binding.tvDataAccount;
        //galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        TextView txtNombre = (TextView) view.findViewById(R.id.txtNombre);
        TextView txtCorreo = (TextView) view.findViewById(R.id.txtCorreo);
        User user = (User)getActivity().getIntent().getSerializableExtra(PutExtraConst.UserKey);
        txtNombre.setText(user.getName());
        txtCorreo.setText(user.getEmail());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}