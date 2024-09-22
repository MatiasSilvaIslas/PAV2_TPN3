package frgp.utn.edu.com.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtUsuario, txtpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtpassword = findViewById(R.id.txtpassword);
    }

    public void RegisterActivity(View view) {
        setContentView(R.layout.register_user);
    }

    public void LoginActivity(View view) {
        setContentView(R.layout.activity_main);
    }

    public void HomeApp() {
        setContentView(R.layout.home_app);
    }

    public void Registrar(View view) {
        EditText et_name = (EditText) findViewById(R.id.editTextName);
        EditText et_email = (EditText) findViewById(R.id.editTextEmail);
        EditText et_pass = (EditText) findViewById(R.id.editTextPass);
        EditText et_pass2 = (EditText) findViewById(R.id.editTextPass2);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String name = et_name.getText().toString();
        String email = et_email.getText().toString();
        String pass = et_pass.getText().toString();
        String pass2 = et_pass2.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !pass2.isEmpty()) {
            if (pass.equals(pass2)) {
                Cursor fila = db.rawQuery
                        ("select * from users where name = '" + name + "'", null);

                if (fila.moveToFirst()) {
                    Toast.makeText(this, "El nombre de usuario no esta disponible", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues registro = new ContentValues();
                    registro.put("name", name);
                    registro.put("email", email);
                    registro.put("pass", pass);

                    db.insert("users", null, registro);

                    Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show();
                    et_name.setText("");
                    et_email.setText("");
                    et_pass.setText("");
                    et_pass2.setText("");
                    LoginActivity(view);
                }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes llenar todos lo campos", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void validarContrseña(String pass, User user) {
        if (pass.equals(user.getPassword())) {
            Toast.makeText(this, "Contraseña correcta", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainMenuActivity.class);
            intent.putExtra(PutExtraConst.UserKey, user);
            startActivity(intent);
            return;
        } else {
            Toast.makeText(this, "Contraseña incorrecta: " , Toast.LENGTH_SHORT).show();
        }
    }

    public void BuscarUser(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String name = txtUsuario.getText().toString();
        String pass = txtpassword.getText().toString();

        if (!name.isEmpty() && !pass.isEmpty()) {
            Cursor fila = db.rawQuery
                    ("select name,email,pass from users where name = '" + name + "'", null);

            if (fila.moveToFirst()) {
                User user = new User (fila.getString(0),fila.getString(1),fila.getString(2));
                validarContrseña(pass, user);
            } else {
                Toast.makeText(this, "No existe el usuario, debe registrarse", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes llenar todos lo campos", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

}