package paq;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Consumer extends Thread {
    Buffer buffer;
    GUIFrame gui;
    int ID;
    int r1;
    int r2;
    int E;
    private String info[] = new String[3];
    
    
    Consumer(Buffer buffer, int ID, int E, GUIFrame gui) {
        this.buffer = buffer;
        this.ID=ID;
        this.E=E;
        this.gui=gui;
        
    }
    
    public String solve(String prod){
        if(Character.toString(prod.charAt(27)).equals("/") && Character.toString(prod.charAt(31)).equals("0")){
            return "Division by cero";
        }
        if(Character.toString(prod.charAt(27)).equals("+") ){
            double val = Character.getNumericValue(prod.charAt(29)) + Character.getNumericValue(prod.charAt(31));
            
            return val+"";
            
        }
        if(Character.toString(prod.charAt(27)).equals("-")){
            double val = Character.getNumericValue(prod.charAt(29)) - Character.getNumericValue(prod.charAt(31));
            return val+"";
        }
        if(Character.toString(prod.charAt(27)).equals("*")){
            double val = Character.getNumericValue(prod.charAt(29)) * Character.getNumericValue(prod.charAt(31));
            return val+"";
        }
        if(Character.toString(prod.charAt(27)).equals("/") ){
            float val = Float.parseFloat(String.valueOf(prod.charAt(29))) / Float.parseFloat(String.valueOf(prod.charAt(31)));
            return val+"";
        }
        
        return "unknown error";
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        String product;
        
        for(int i=0 ;  ; i++) {
            product = this.buffer.consume();
            String sf1 =String.format("Consumer %s consumed: %s ",ID, product);  
            
            try {
                Thread.sleep(E);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.buffer.print(sf1);
            
            info[0]= "C"+ID;
            info[1]= product;
            info[2]= solve(product);
            
            gui.tableModel2.addRow(info);
            gui.cont++;
            gui.jTextField5.setText(gui.cont+"");
                
            
        
            
            //Buffer.print("Consumer %s consumed: %c ",ID, product);
            
            
        }
    }
}
