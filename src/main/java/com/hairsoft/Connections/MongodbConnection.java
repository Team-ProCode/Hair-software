package com.hairsoft.Connections;

import com.hairsoft.dialog.Dialog;
import com.mongodb.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MongodbConnection {

    DB mongodb;
    DBCollection collection;
    BasicDBObject document = new BasicDBObject();

    Dialog dialog = new Dialog();

    public void mongodbConnection(){
        try{
            Mongo m=new Mongo("localhost",27017);
            mongodb=m.getDB("teste");


        collection = mongodb.getCollection("Pessoa");


        Dialog.alertDialog(dialog.getTitleDB(), dialog.getMessageDB());
        }catch(Exception e){
        Logger.getLogger(MongodbConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void inserir(String dados){
        document.put("RG", dados);
        collection.insert(document);
    }

    public void mostrar(){
        DBCursor cursor = collection.find();
        while (cursor.hasNext()){
        System.out.println(cursor.next());
        }
    }

    public boolean atualizar(String DadosAntigos, String DadosNovos){
        document.put("RG", DadosAntigos);
        BasicDBObject DocNovo = new BasicDBObject();
        DocNovo.put("RG", DadosNovos);
        collection.findAndModify(document, DocNovo);
        return true;
    }

    public boolean remover(String Dado){
        document.put("RG", Dado);
        collection.remove(document);
        return true;
    }
}
