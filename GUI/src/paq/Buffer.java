package paq;


import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private char buffer;
    GUIFrame gui = new GUIFrame();
    
    public int MAX_SIZE;
    final List<String> operaciones;
    
    
    
    
    
    Buffer(int max, GUIFrame gui) {
        
        operaciones = new ArrayList<>();
        this.MAX_SIZE = max;
        
        
    }
    
    
    synchronized String consume() {
       notifyAll();
         
        while(operaciones.isEmpty()) {
            try {
                System.out.println("esperando  al buffer");
                wait();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String data = operaciones.get(0);
        operaciones.remove(data);
        //notifyAll();
        
        
        
        return data;
    }
    
    synchronized void produce(String product, int ID) {
        while(operaciones.size() == MAX_SIZE) {
            try {
                System.out.println("productor lleno");
                wait();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        operaciones.add("P"+ID+"                        "+product);
        
       
        
        
        
        notifyAll();
    }
    
    
    synchronized void print(String string) {
        
        System.out.println(string);
        notifyAll();
        
        
    }
    
   
    
}
