package frgp.utn.edu.com.parking.ui.parking;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import frgp.utn.edu.com.parking.AdminSQLiteOpenHelper;
import frgp.utn.edu.com.parking.Parking;
import frgp.utn.edu.com.parking.ParkingAdapter;
import frgp.utn.edu.com.parking.ParkingDialogAlert;
import frgp.utn.edu.com.parking.PutExtraConst;
import frgp.utn.edu.com.parking.User;
import frgp.utn.edu.com.parking.databinding.FragmentParkingBinding;

import java.util.ArrayList;

public class ParkingFragment extends Fragment {

    private GridView gvParking;
    private FragmentParkingBinding binding;
    private ImageView AgregarParking;

//    public void ListviewParking(View view) { setContentView(R.layout.listview_parking);}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ParkingViewModel homeViewModel =
                new ViewModelProvider(this).get(ParkingViewModel.class);

        binding = FragmentParkingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        gvParking = binding.gvParking;
        SetParking(GetParkingBD(), this.getContext());

        AgregarParking = binding.btnNuevoParking;
        LayoutInflater lf = this.getLayoutInflater();

        AgregarParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = (User)getActivity().getIntent().getSerializableExtra(PutExtraConst.UserKey);
                Toast.makeText(v.getContext(),"llego", Toast.LENGTH_LONG).show();
                ParkingDialogAlert.CreateAlertDialog(v.getContext(), lf,user).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void SetParking(ArrayList<Parking> parkingList, Context context)
    {
        ParkingAdapter parkingAdapter = new ParkingAdapter(context, parkingList);
        gvParking.setAdapter(parkingAdapter);
    }
    public  void updateParking()
    {
        SetParking(GetParkingBD(), this.getContext());
    }

    private ArrayList<Parking> GetParkingBD()
    {
        User user = (User)getActivity().getIntent().getSerializableExtra(PutExtraConst.UserKey);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.getContext(), "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ArrayList<Parking> parkingList = new ArrayList<>();

        try{
            Cursor fila = db.rawQuery
                    ("select matricula,tiempo from parkings where user = '"+user.getName()+"'", null);

            if (fila.moveToFirst()) {
                 do {
                    parkingList.add(new Parking(1, fila.getString(0), fila.getString(1)));
                 } while (fila.moveToNext());
               }
        }
        catch (Exception e)
        {
            Toast.makeText(this.getContext(), "erro:"+e, Toast.LENGTH_SHORT).show();
        }


        return parkingList;
    }


}