package onp;

import java.io.Serializable;
import java.util.InputMismatchException;

/**
 * @author Sławek
 * Klasa implementująca stos za pomocą tablicy
 */
public class  TabStack implements Serializable {
    private String[] stack = new String[20];
    private int size = 0;
    /**
     * Metoda zdejmująca wartość ze stosu
     * @return wartość z góry stosu
     */
    public String pop(){
        if (!isEmpty()) {
            size--;
            return stack[size];
        }
        else {
            throw new IndexOutOfBoundsException(" ---> ujemny indeks stosu");
        }
    }
    /**
     * Metoda zwracająca wartość ze stosu
     * @return wartość z góry stosu
     */
    public String top(){
        if (!isEmpty()) {
            return stack[size-1];
        }
        else {
            throw new IndexOutOfBoundsException(" ---> ujemny indeks stosu");
        }
    }

    /**
     * metoda dokładająca na stos
     * @param a - wartość dokładana do stosu
     */
    public void push(String a){
        if (size < stack.length - 1) {
            stack[size] = a;
            size++;
        }
        else {
            throw new StackOverflowException(" ---> przekroczony rozmiar stosu");
        }
    }
    /**
     * Metoda zwraca tekstową reprezentację stosu
     */
    public String toString(){
        String tmp = "";
        for(int i = 0; i < size; i++)
            tmp += stack[i] + " ";
        return tmp;
    }
    /**
     * Metoda zwraca rozmiar stosu
     * @return size rozmiar stosu
     */
    public int getSize(){
        return size;
    }
    /**
     * Ustawia wartość stosu
     * @param i
     */
    public void setSize(int i){
        if (i <= 20 && i >=0) {
            size = i;
        }
        else {
            throw new InputMismatchException(" ---> podanie rozmiaru stosu spoza mozliwego zakresu <0;20>");
        }
    }
    /**
     * Metoda zwraca bool gdy stos jest pusty
     * @return boolean czy pusty
     */
    public boolean isEmpty(){
        return  size == 0;
    }

    /**
     * Metoda wyświetla s
     * @return boolean czy pusty
     */


    /**
     * Metoda zwraca wartość z określonej pozycji stosu
     * @param i pozycja parametru do zobaczenia
     * @return wartość stosu
     */
    public String showValue(int i){
        if(i < size)
            return stack[i];
        else return null;
    }
}
