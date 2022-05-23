package paq;


public class ProducerConsumer {

    public static void main(String[] args) {
        
        
        GUIFrame nueva =  new GUIFrame();
        Buffer buffer = new Buffer(3,nueva);
        
        Producer producer1 = new Producer(buffer, 1,0,9,1000,nueva);
        producer1.start();
        
        Producer producer2 = new Producer(buffer, 2,0,9,1000,nueva);
        producer2.start();
        
        Consumer consumer1 = new Consumer(buffer, 1,1000,nueva);
        consumer1.start();
        
       
    }
    
}
