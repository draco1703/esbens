package D2;

public class TagCounterTester {
  
  public static void main(String[] args) throws InterruptedException{
    TagCounter tc1 = new TagCounter("http://www.fck.dk");
    tc1.start();
    TagCounter tc2= new TagCounter("http://www.google.com");
    tc2.start();
    TagCounter tc3= new TagCounter("http://politiken.dk/");
    tc3.start();
    
    tc1.join();
    tc2.join();
    tc3.join();
    
    System.out.println("Title: "+tc1.getTitle());
    System.out.println("Div's: "+tc1.getDivCount());
    System.out.println("Body's: "+tc1.getBodyCount());         
    
    
    System.out.println("Title: "+tc2.getTitle());
    System.out.println("Div's: "+tc2.getDivCount());
    System.out.println("Body's: "+tc2.getBodyCount());   
    
    System.out.println("Title: "+tc3.getTitle());
    System.out.println("Div's: "+tc3.getDivCount());
    System.out.println("Body's: "+tc3.getBodyCount());         
  }
}

/**
 * a) A serial collection of 3 pages is slower than a parallel. It has to connect, wait for a response, get the goods, then repeat three times for three different pages. 
 * 
 * b) It already has a run function
 * 
 * c1) sout faster than async
 * c2) add (threadname).join() 
 * 
 * d) I might come back and do this if I find the time
 * 
 **/