package net.cosmogrp.storage.mongo;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import net.cosmogrp.storage.dist.RemoteModelService;
import net.cosmogrp.storage.model.Model;
import net.cosmogrp.storage.model.meta.ModelMeta;
import org.jetbrains.annotations.Nullable;
import org.mongojack.JacksonMongoCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class MongoModelService<T extends Model>
        extends RemoteModelService<T> {

    private final JacksonMongoCollection<T> collection;

    public MongoModelService(
            Executor executor,
            ModelMeta<T> modelMeta,
            JacksonMongoCollection<T> collection
    ) {
        super(executor, modelMeta);
        this.collection = collection;
    }

    @Override
    protected void internalSave(T model) {
        collection.replaceOne(
                Filters.eq("_id", model.getId()),
                model, new ReplaceOptions().upsert(true)
        );
    }

    @Override
    protected @Nullable T internalFind(String id) {
        return collection.findOneById(id);
    }

    @Override
    protected List<T> internalFindAll() {
        return collection.find()
                .into(new ArrayList<>());
    }

}