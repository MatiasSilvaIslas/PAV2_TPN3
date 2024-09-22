package frgp.utn.edu.com.parking;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import frgp.utn.edu.com.parking.ui.parking.ParkingFragment;

public class ParkingDialogAlert  {

    public static AlertDialog CreateAlertDialog(Context context, LayoutInflater inflater, User user ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = inflater.inflate(R.layout.register_parking, null);

        EditText et_Matricula = (EditText) view.findViewById(R.id.txtNroMatricula);
        EditText et_Tiempo = (EditText) view.findViewById(R.id.txtTiempo);
        Button cancelar = view.findViewById(R.id.btnCancelar);
        Button registrar = view.findViewById(R.id.btnRegistrar);

        builder.setView(view);

        AlertDialog alertDialog = builder.create();

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(v.getContext(), "administracion", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();

                String matricula = et_Matricula.getText().toString();
                String tiempo = et_Tiempo.getText().toString();

                if (!matricula.isEmpty() && !tiempo.isEmpty()) {
                    ContentValues registro = new ContentValues();
                    registro.put("matricula", matricula);
                    registro.put("tiempo", tiempo);
                    registro.put("user", user.getName());

                    db.insert("parkings", null, registro);

                    Toast.makeText(v.getContext(), "Datos guardados", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(v.getContext(), "Debes llenar Matricula y Tiempo", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });

        return alertDialog;
    }

}
