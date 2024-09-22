package frgp.utn.edu.com.parking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ParkingAdapter extends ArrayAdapter<Parking> {
    public ParkingAdapter(@NonNull Context context, ArrayList<Parking> parkingArrayList) {
        super(context, 0, parkingArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.listview_parking, parent, false);
        }

        Parking parkingModel = getItem(position);
        TextView tvPatente = listitemView.findViewById(R.id.tvPatente);
        TextView tvTiempo = listitemView.findViewById(R.id.tvTiempo);

        tvPatente.setText(parkingModel.getPatente());
        tvTiempo.setText(parkingModel.getTiempo());
        return listitemView;
    }
}
