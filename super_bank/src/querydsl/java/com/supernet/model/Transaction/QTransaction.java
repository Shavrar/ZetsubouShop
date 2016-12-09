package com.supernet.model.Transaction;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTransaction is a Querydsl query type for Transaction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTransaction extends EntityPathBase<Transaction> {

    private static final long serialVersionUID = 1492182058L;

    public static final QTransaction transaction = new QTransaction("transaction");

    public final com.supernet.model.QPersistentObject _super = new com.supernet.model.QPersistentObject(this);

    public final NumberPath<Long> Amount = createNumber("Amount", Long.class);

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final DatePath<java.sql.Date> date = createDate("date", java.sql.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> ReciverAccountId = createNumber("ReciverAccountId", Long.class);

    public final NumberPath<Long> reciverAccountId = createNumber("reciverAccountId", Long.class);

    public final NumberPath<Long> SenderAccountId = createNumber("SenderAccountId", Long.class);

    public final NumberPath<Long> senderAccountId = createNumber("senderAccountId", Long.class);

    public QTransaction(String variable) {
        super(Transaction.class, forVariable(variable));
    }

    public QTransaction(Path<? extends Transaction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTransaction(PathMetadata metadata) {
        super(Transaction.class, metadata);
    }

}

