package org.example.jpa.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInstructor is a Querydsl query type for Instructor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInstructor extends EntityPathBase<Instructor> {

    private static final long serialVersionUID = -971946366L;

    public static final QInstructor instructor = new QInstructor("instructor");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath desc = createString("desc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath left = createBoolean("left");

    public final StringPath name = createString("name");

    public QInstructor(String variable) {
        super(Instructor.class, forVariable(variable));
    }

    public QInstructor(Path<? extends Instructor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInstructor(PathMetadata metadata) {
        super(Instructor.class, metadata);
    }

}

