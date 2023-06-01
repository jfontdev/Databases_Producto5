package Databases_modelo;

import java.util.ArrayList;

public class Lista<T> {
    protected ArrayList<T> lista;

    public Lista(){
        lista = new ArrayList<>();
    }

    public int getSize() {
        return this.lista.size();
    }

    public void add(T t) throws Exception{
        this.lista.add(t);
    }

    public void delete(T t){
        this.lista.remove(t);
    }

    public T getAt(int position) {
        return this.lista.get(position);
    }

    public void clear(){
        this.lista.clear();
    }

    public boolean isEmpty(){
        return lista.isEmpty();
    }

    public ArrayList<T> getArrayList(){
        ArrayList<T> arrayList = new ArrayList<>(lista);
        return arrayList;
    }
}
