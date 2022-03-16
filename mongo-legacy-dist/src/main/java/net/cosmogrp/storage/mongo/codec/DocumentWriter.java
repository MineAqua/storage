package net.cosmogrp.storage.mongo.codec;

import net.cosmogrp.storage.codec.ModelWriter;
import net.cosmogrp.storage.codec.AbstractModelWriter;
import net.cosmogrp.storage.model.Model;
import org.bson.Document;

/**
 * It's a builder for documents
 */
public class DocumentWriter extends AbstractModelWriter<Document> {

    private final Document document;

    private DocumentWriter() {
        document = new Document();
    }

    public static ModelWriter<Document> create() {
        return new DocumentWriter();
    }

    public static ModelWriter<Document> create(Model model) {
        return new DocumentWriter()
                .write("_id", model.getId());
    }

    /**
     * It adds a field to the document.
     *
     * @param field The name of the field to be added to the document.
     * @param value The value to be written.
     * @return Nothing.
     */
    @Override
    protected DocumentWriter write0(String field, Object value) {
        document.append(field, value);
        return this;
    }

    @Override
    public Document end() {
        return document;
    }

}