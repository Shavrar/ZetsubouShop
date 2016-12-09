package com.supernet.model.Invoice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QInvoice is a Querydsl query type for Invoice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInvoice extends EntityPathBase<Invoice> {

    private static final long serialVersionUID = -1347999288L;

    public static final QInvoice invoice = new QInvoice("invoice");

    public final com.supernet.model.QPersistentObject _super = new com.supernet.model.QPersistentObject(this);

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final NumberPath<Long> cardNumber = createNumber("cardNumber", Long.class);

    public final DatePath<java.sql.Date> expireDate = createDate("expireDate", java.sql.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath password = createString("password");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QInvoice(String variable) {
        super(Invoice.class, forVariable(variable));
    }

    public QInvoice(Path<? extends Invoice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInvoice(PathMetadata metadata) {
        super(Invoice.class, metadata);
    }

}

