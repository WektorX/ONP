package onp;

import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Sławek Klasa implementująca kalkulator ONP
 */
public class ONP implements Serializable{

    private static final long serialVersionUID = 1L;

    public ONP() {
    }

    private TabStack stack = new TabStack();

    /**
     * Metoda sprawdza czy równanie kończy się znakiem '='
     *
     * @param rownanie równanie do sprawdzenia
     * @return true jeśli równanie jest poprawne, false jeśli niepoprawne
     */
    boolean czyPoprawneRownanie(String rownanie) {
        if (rownanie.endsWith("="))
            return true;
        else
            return false;
    }

    /**
     * Metoda oblicza wartość wyrażenia zapisanego w postaci ONP
     *
     * @param rownanie równanie zapisane w postaci ONP
     * @return wartość obliczonego wyrażenia
     */
    public String calculate(String rownanie) {
        if (czyPoprawneRownanie(rownanie)) {
            stack.setSize(0);
            String wynik = "";
            Double a = 0.0;
            Double b = 0.0;
            for (int i = 0; i < rownanie.length(); i++) {
                if (rownanie.charAt(i) >= '0' && rownanie.charAt(i) <= '9') {
                    wynik += rownanie.charAt(i);
                    if (!(rownanie.charAt(i + 1) >= '0' && rownanie.charAt(i + 1) <= '9')) {
                        stack.push(wynik);
                        wynik = "";
                    }

                } else if (rownanie.charAt(i) == '=') {
                    return stack.pop();
                } else if (rownanie.charAt(i) != ' ') {
                    b = Double.parseDouble(stack.pop());
                    //silnia jest jednoargumetnowa
                    if (rownanie.charAt(i) != '!') {
                        a = Double.parseDouble(stack.pop());
                    }

                    switch (rownanie.charAt(i)) {
                        case ('+'): {
                            stack.push((a + b) + "");
                            break;
                        }
                        case ('-'): {
                            stack.push((a - b) + "");
                            break;
                        }
                        case ('x'):
                            ;
                        case ('*'): {
                            stack.push((a * b) + "");
                            break;
                        }
                        case ('/'): {
                            if (b == 0) {
                                throw new ErrorHandler(" Dzielenie przez zero");
                            }
                            stack.push((a / b) + "");
                            break;
                        }
                        case ('^'): {
                            stack.push(Math.pow(a, b) + "");
                            break;
                        }
                        case ('?'): {
                            if (b == 0) {
                                throw new ErrorHandler(" Dzielenie przez zero");
                            }
                            stack.push(Math.pow(a, (1 / b)) + "");
                            break;
                        }
                        case ('%'): {
                            if (b == 0) {
                                throw new ErrorHandler(" Dzielenie przez zero");
                            }
                            stack.push((a % b) + "");
                            break;
                        }
                        case ('!'): {
                            double liczba = b;
                            if (b == 0.0) {
                                liczba++;
                            } else if (b > 0.0) {
                                for (double j = b - 1.0; j > 0; j--) {
                                    liczba *= j;
                                }
                            } else {
                                throw new ErrorHandler(" Liczba ujemna w silni");
                            }
                            stack.push(liczba + "");
                            break;
                        }
                    }
                }
            }
            return "0.0";
        } else
            return " ";
    }

    /**
     * Metoda zamienia równanie na postać ONP
     *
     * @param rownanie równanie do zamiany na postać ONP
     * @return równanie w postaci ONP
     */
    public String eqToOnp(String rownanie) {
        if (czyPoprawneRownanie(rownanie)) {
            String wynik = "";
            for (int i = 0; i < rownanie.length(); i++) {
                if (rownanie.charAt(i) >= '0' && rownanie.charAt(i) <= '9') {
                    wynik += rownanie.charAt(i);
                    if (!(rownanie.charAt(i + 1) >= '0' && rownanie.charAt(i + 1) <= '9'))
                        wynik += " ";
                } else
                    switch (rownanie.charAt(i)) {
                        case ('+'):
                            ;
                        case ('-'): {
                            while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            String str = "" + rownanie.charAt(i);
                            stack.push(str);
                            break;
                        }
                        case ('%'):
                            ;
                        case ('x'):
                            ;
                        case ('*'):
                            ;
                        case ('/'): {
                            while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")
                                    && !stack.showValue(stack.getSize() - 1).equals("+")
                                    && !stack.showValue(stack.getSize() - 1).equals("-")) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            String str = "" + rownanie.charAt(i);
                            stack.push(str);
                            break;
                        }
                        case ('!'):
                            ;
                        case ('?'):
                            ;
                        case ('^'): {
                            while (stack.getSize() > 0 && stack.showValue(stack.getSize() - 1).equals("^")) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            String str = "" + rownanie.charAt(i);
                            stack.push(str);
                            break;
                        }
                        case ('('): {
                            String str = "" + rownanie.charAt(i);
                            stack.push(str);
                            break;
                        }
                        case (')'): {
                            while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            // zdjęcie ze stosu znaku (
                            stack.pop();
                            break;
                        }
                        case ('='): {
                            while (stack.getSize() > 0) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            wynik += "=";
                            break;
                        }
                        default: {
                            return null;
                        }
                    }
            }
            return wynik;
        } else {
            return null;
        }

    }

    public static void main(String[] args) throws IOException {
        // 7 1 + 4 2 - 2 ^ * =
        String tmp = "";
        ArrayList<Rownanie> listaRownan = new ArrayList<>();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(".\\onp.dat"));

        if (args.length == 0) {
            tmp = "(2+3)*(6-2)^2=";
        } else {
            for (int i = 0; i < args.length; i++) {
                tmp = args[i];
                ONP onp = new ONP();
                System.out.print(tmp + " ");
                try {

                    String rownanieOnp = onp.eqToOnp(tmp);
                    System.out.print(rownanieOnp);
                    String wynik = onp.calculate(rownanieOnp);
                    System.out.println(" " + wynik);
                    Rownanie rownanie = new Rownanie(tmp, rownanieOnp, wynik);
                    listaRownan.add(rownanie);
                    out.writeObject(rownanie);
                }
                catch (Exception ex) {
                    Rownanie rownanie = new Rownanie();
                    rownanie.setRownanie(tmp);
                    rownanie.setRownanieONP(ex.getMessage());
                    rownanie.setWynik("Brak wyniku dla blednego rownania");
                    listaRownan.add(rownanie);
                    out.writeObject(rownanie);
                    System.out.println(ex.getMessage());
                }
                continue;
            }
        }
        out.close();
    }
}
