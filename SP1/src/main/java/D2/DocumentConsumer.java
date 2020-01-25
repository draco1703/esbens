package D2;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DocumentConsumer implements Runnable {

  BlockingQueue<Document> producedDocuments;
  Document doc;

  public DocumentConsumer(BlockingQueue<Document> producedDocuments) {
    this.producedDocuments = producedDocuments;
  }

  @Override
  public void run() {
    boolean moreDocumentsToConsume = true;
    Document doc;
    ArrayList titles = new ArrayList();
    ArrayList listedDivs = new ArrayList();
    
    int totalDivs = 0;
    while (moreDocumentsToConsume) {
      try {
        doc = producedDocuments.poll(10, TimeUnit.SECONDS);
        if (doc != null) {
          String title = doc.title();
          titles.add(title);
          Elements divs = doc.select("div");
          totalDivs += divs.size();
          listedDivs.add(divs.size());
        } else {
          moreDocumentsToConsume = false;
        }

      } catch (Exception ex) {
        Logger.getLogger(DocumentConsumer.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    System.out.println("Sum of all Divs:" + totalDivs);
    for (int i = 0; i < titles.size(); i++) {
            System.out.println("Title=" + titles.get(i) + "&divs=" + listedDivs.get(i));
        }
  }

}

