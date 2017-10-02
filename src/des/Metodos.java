package des;
import java.util.ArrayList;
import java.util.Scanner;

public class Metodos {
    ArrayList<String> Keys = new ArrayList<String>();
    private String[] ks = new String[16];

    public void obtenerK(){        
        Clave subClaves[] = obtenerCs();
        int[] cP2 = {14,17,11,24,1,5,3,28,15,6,21,10,23,19,12,4,26,8,16,7,27,20,13,2,41,52,31,37,47,55,30,40,51,45,33,48,44,49,39,56,34,53,46,42,50,36,29,32};
        for(int i=0; i<16;i++){
            String c = subClaves[i].getL() + subClaves[i].getR();
            String k="";
            for(int j=0; j<48; j++){
                k = k+c.charAt(cP2[j]-1);
            }
            Keys.add(k); 
        }
        for (int i = 0; i < 16; i++) {
            System.out.println("K"+(i+1)+" "+Keys.get(i));
        }        
        System.out.println("");
        System.out.println("");
    }
    
    private Clave[] obtenerCs(){
        String claveA;
        String claveB;
        Clave[] subClaves = new Clave[16];
        Clave c0 = new Clave();
        int[] cP1 = {57,49,41,33,25,17,9,1,58,50,42,34,26,18,10,2,59,51,43,35,27,19,11,3,60,52,44,36,63,55,47,39,31,23,15,7,62,54,46,38,30,22,14,6,61,53,45,37,29,21,13,5,28,20,12,4};
        
        System.out.println("Ingrese clave secreta en hexadecimal");
        Scanner leer = new Scanner(System.in);
        claveA = leer.next();
        Conversion conv = new Conversion();
        claveB = conv.convertirC(claveA);
        
        for(int i=0; i<56; i++){
            if(i<28){
                c0.setL(c0.getL()+claveB.charAt(cP1[i]-1));
            }
            else{
                c0.setR(c0.getR()+claveB.charAt(cP1[i]-1));
            }
        }
        
        for(int i=0; i<16; i++){
            Clave nueva = new Clave();
            if(i==0||i==1||i==8||i==15){
                nueva.setL(c0.getL().substring(1));
                nueva.setR(c0.getR().substring(1));
                nueva.setL(nueva.getL() + c0.getL().charAt(0));
                nueva.setR(nueva.getR() + c0.getR().charAt(0));
            }
            else{
                nueva.setL(c0.getL().substring(2));
                nueva.setR(c0.getR().substring(2));
                nueva.setL(nueva.getL() + c0.getL().substring(0, 2));
                nueva.setR(nueva.getR() + c0.getR().substring(0,2));
            }     
            c0= nueva;
            subClaves[i]= nueva;
        }
        return subClaves;
    }   
    
}
