package com.example.parcial31;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView Nombre,Matricula,Id,Apellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Matricula=(TextView)findViewById(R.id.MATRICULAA);
        Nombre=(TextView) findViewById(R.id.NOMBREE);
        Apellido=(TextView) findViewById(R.id.APELLIDOO);
        Id=(TextView) findViewById(R.id.IDD);
    }
    public void Guardar(View view){
        AdminDB base = new AdminDB(this, "escuela", null, 1);
        SQLiteDatabase BaseDatos = base.getWritableDatabase();
        String matricula =Matricula.getText().toString();
        String nombre =Nombre.getText().toString();
        String apellido=Apellido.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("matricula", matricula);
        registro.put("nombre", nombre);
        registro.put("apellido", apellido);
        BaseDatos.insert("alumnos",null, registro);
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        BaseDatos.close();
    }
    public void Buscar(View view){
        AdminDB base = new AdminDB(this,"escuela",null,1);
        SQLiteDatabase BaseDatos=base.getWritableDatabase();
        String matricula =Matricula.getText().toString();
        Cursor fila=BaseDatos.rawQuery("select nombre,apellido from alumnos where matricula="+ matricula,null);
        Nombre.setText(fila.getString(0));
        Apellido.setText(fila.getString(1));
        BaseDatos.close();
    }
    public void Modificiar(View view){
        AdminDB base = new AdminDB(this,"escuela",null,1);
        SQLiteDatabase BaseDatos=base.getWritableDatabase();
        String matricula =Matricula.getText().toString();
        String nombre =Nombre.getText().toString();
        String apellido=Apellido.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("matricula", matricula);
        registro.put("nombre", nombre);
        registro.put("apellido", apellido);
        BaseDatos.update("alumnos",registro,"matricula="+matricula,null);
        BaseDatos.close();
    }
}