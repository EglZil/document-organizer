/**
 * Implemented DocumentOrganizer class.
 * @author Egle Zilenaite
 */

import lt.itakademija.*;

import java.util.ArrayList;
import java.util.List;

public class MyDocumentOrganizer implements DocumentOrganizer {

    private DocumentTypeDetector documentTypeDetector;
    private List<Document> documents;
    private long totalLines;

    public MyDocumentOrganizer(DocumentTypeDetector documentTypeDetector) {
        this.documentTypeDetector = documentTypeDetector;
        documents = new ArrayList<>();
        totalLines = 0;
    }

    /** *
     *
     * @return total count of documents, produced by documentProducer.
     */
    @Override
    public long getTotalCount() {
        return documents.size();
    }

    /**
     *
     * @return total lines count in documents.
     */
    @Override
    public long getTotalLinesCount() {
        for (Document document : documents) {
           totalLines += document.getLines().size();
        }
        return totalLines;
    }

    /**
     * The method organizes the documents - according to document type invokes specific method.
     * @param documentProducer provides documents for document organizer.
     * @param documentConsumer invokes specific methods.
     */
    @Override
    public void organize(DocumentProducer documentProducer, DocumentConsumer documentConsumer) {
        if (documentProducer == null || documentConsumer == null) {
            throw new IllegalArgumentException();
        }

        while (true) {
            Document document = documentProducer.get();
            if (document == null) {
                break;
            }
            documents.add(document);
        }

        for (Document document : documents) {
            DocumentType type = documentTypeDetector.detect(document);
            if (type.name().equals("SPAM")) {
                documentConsumer.consumeSpam(document);
            } else if (type.name().equals("ORDINARY")) {
                documentConsumer.consumeOrdinary(document);
            } else if(type.name().equals("IMPORTANT")) {
                documentConsumer.consumeImportant(document);
            } else {
                throw new UnknownDocumentTypeException("message");
            }
        }

    }
}
