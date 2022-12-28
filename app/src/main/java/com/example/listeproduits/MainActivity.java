package com.example.listeproduits;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    Button btnAjt,btnUpd,btnDel;
    EditText libelle,codebarre,prix;
    CheckBox dipo;
    private ListView listView;
    private ArrayList<Produit> listeDesProduit = new ArrayList<>();
    private ProduitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.liste) ;

        adapter = new ProduitAdapter(MainActivity.this,listeDesProduit);
        listView.setAdapter(adapter);

        btnAjt=findViewById(R.id.ajouter);
        btnAjt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, AjoutProduit.class);
                startActivityForResult(intent, 101);
            }
        });

        btnUpd=findViewById(R.id.modifier);
        ArrayList<Produit> checkedItems = adapter.getChecked();
        btnUpd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ArrayList<Produit> checkedItems = adapter.getChecked();
                int itemCount = checkedItems.size();
                if(checkedItems.isEmpty() || itemCount>1){
                    Toast.makeText(MainActivity.this,"item not selected or u have selected 2 items",Toast.LENGTH_LONG).show();

                }else {
                    Intent intent = new Intent(MainActivity.this, ModifierProduit.class);



                    Produit  info =checkedItems.get(0);

                    intent.putExtra("id", info.getId());
                    intent.putExtra("libelle", info.getLibelle());
                    intent.putExtra("codeBarre",  info.getCodeBarre());
                    intent.putExtra("disponible", info.getDisponible());
                    intent.putExtra("prix", info.getPrix());
                    intent.putExtra("image", info.getImage());
                    startActivityForResult(intent, 1);
                }



            }
        });





    }

    @Override
    protected  void  onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 101 && resultCode == Activity.RESULT_OK)
        {
            Produit t = new Produit();
            String libelle=data.getStringExtra("libelle");
            String codebarre=data.getStringExtra("codeBarre");
            String prix=data.getStringExtra("prix");
            boolean dispo=data.getBooleanExtra("disponible",false);
            String image=data.getStringExtra("image");
            t.setLibelle(libelle);
            t.setCodeBarre(codebarre);
            t.setPrix(prix);
            t.setDisponible(dispo);
            t.setImage(image);
            listeDesProduit.add(t);
            adapter.notifyDataSetChanged();
        }
        else  if (requestCode == 1 && resultCode == Activity.RESULT_OK)
        {
            Produit t = new Produit();
            int id=data.getIntExtra("id",0);
            String libelle=data.getStringExtra("libelle");
            String codebarre=data.getStringExtra("codeBarre");
            String prix=data.getStringExtra("prix");
            boolean dispo=data.getBooleanExtra("disponible",false);
            String image=data.getStringExtra("image");
            t.setId(id);
            t.setLibelle(libelle);
            t.setCodeBarre(codebarre);
            t.setPrix(prix);
            t.setDisponible(dispo);
            t.setImage(image);
            listeDesProduit.set(id,t);
            adapter.notifyDataSetChanged();
        }
    }
    public void deleteProduit(View view)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setMessage("Voulez vous vraiment les supprimés!! ");
        ArrayList<Produit> checkedItems = adapter.getChecked();
        Toast.makeText(this,String.valueOf(checkedItems.size()),Toast.LENGTH_LONG).show();
        int itemCount = checkedItems.size();
        for(int i=itemCount-1; i >= 0; i--){
            adapter.remove(checkedItems.get(i));
        }
        adapter.ClearSelection();
        adapter.notifyDataSetChanged();
    }






}