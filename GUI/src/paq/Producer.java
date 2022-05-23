package paq;

import java.time.Clock;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;



public class Producer extends Thread {
    Buffer buffer;
    GUIFrame gui;
    int ID;
    int r1;
    int r2;
    int E;
    private String info[] = new String[2];
    
    
    
   
    
    
    Producer(Buffer buffer, int ID, int r1, int r2, int E, GUIFrame gui) {
        this.buffer = buffer;
        this.ID = ID;
        this.r1=r1;
        this.r2=r2;
        this.E=E;
        this.gui=gui;
        
        
        
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String products = "+-*/";
        Random r = new Random(System.currentTimeMillis());
        String product;
        int cont=0;
        int numbers[] = new int[r2-r1+1];
        
        
        
        for (int i = r1; i <= r2; i++) {
            numbers[cont++]= i;
        }
        
        
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]);
        }
        
        
        
        
        
        for(int i=0 ;  ; i++) {
            product = "("+ Character.toString(products.charAt(r.nextInt(4)))+" "+numbers[r.nextInt(r2-r1+1)]+" "+numbers[r.nextInt(r2-r1+1)]+")";     //INICIAR AQUI !!!!
            this.buffer.produce(product,ID);
        
            String sf1 =String.format("Producer %s produced: %s ",ID, product);  
            
            this.buffer.print(sf1);
            
            gui.tableModel.getDataVector().removeAllElements();
            gui.tableModel.fireTableDataChanged(); 
            gui.jProgressBar1.setMaximum(buffer.MAX_SIZE);
        
            for(int h =0; h < buffer.operaciones.size(); h ++){
                
                info[0]= buffer.operaciones.get(h);
            
                gui.tableModel.addRow(info);

                gui.jProgressBar1.setValue(buffer.operaciones.size());
            }  
            
            
                
            
            
            
            
            try {
                Thread.sleep(E);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    
}
