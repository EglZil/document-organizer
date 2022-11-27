import lt.itakademija.Document;
import lt.itakademija.DocumentProducer;

import java.util.Iterator;

public class MyDocumentProducer implements DocumentProducer {

    private Iterator<Document> iterator;

    public MyDocumentProducer(Iterator<Document> iterator) {
        this.iterator = iterator;
    }

    @Override
    public Document get() {
        while (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
}
