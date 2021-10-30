package com.hairsoft.Connections;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongodbConnection {

    DB meubanco;
    DBCollection collection;
    BasicDBObject document = new BasicDBObject();

    public MongodbConnection(){
        try{
            Mongo m = new Mongo("localhost", 27017);
            meubanco = m.getDB("meubanco");

            collection = meubanco.getCollection("Pessoa");

            System.out.println("Conexao efetuada com sucesso!");
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