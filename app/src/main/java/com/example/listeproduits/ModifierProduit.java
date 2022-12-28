package com.example.listeproduits;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;

public class ModifierProduit extends AppCompatActivity {
    EditText libelle,codebarre, prix;
    Button modifier,camera;
    CheckBox dispo;
    String aa;
    int id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier_produit);


        libelle= findViewById(R.id.libelle);
        codebarre= findViewById(R.id.codebr);
        dispo = findViewById(R.id.dispo);
        prix= findViewById(R.id.price);

        modifier= findViewById(R.id.modifier);
        camera= findViewById(R.id.camera);
        // libelle.setText(lib);

        Intent data = getIntent();

        id = data.getIntExtra("id",0);
        String lib=data.getStringExtra("libelle");

        libelle.setText(lib);

        String code=data.getStringExtra("codeBarre");
        codebarre.setText(code);
        String pr=data.getStringExtra("prix");
        prix.setText(pr);

        boolean disp=data.getBooleanExtra("disponible",false);
        dispo.setChecked(disp);
        aa=data.getStringExtra("image");
        ImageView imageview = (ImageView) findViewById(R.id.image);
        byte[] decodedString = Base64.decode(aa,Base64.DEFAULT);
        Bitmap im= BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
        imageview.setImageBitmap(im);



        if(ContextCompat.checkSelfPermission(ModifierProduit.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ModifierProduit.this,new String[]{
                    Manifest.permission.CAMERA
            },101);
        }
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,1);
                //ImagePicker.Companion.with(MainActivityAjtP.this).cropp().MaxResultSize(1080,1080).start(101);
            }
        });


        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent();
                i.putExtra("id",  id);
                i.putExtra("libelle",  libelle.getText().toString());
                i.putExtra("codeBarre",  codebarre.getText().toString());
                i.putExtra("disponible", dispo.isChecked());
                i.putExtra("prix", prix.getText().toString());
                i.putExtra("image", aa);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });

    }

    @Override
    protected  void  onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == Activity.RESULT_OK)
        {


            Bitmap im = (Bitmap) data.getExtras().get("data");
            ImageView imageview = (ImageView) findViewById(R.id.image);
            imageview.setImageBitmap(im);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            im.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            aa= Base64.encodeToString(byteArray, Base64.DEFAULT);


        }

    }
}