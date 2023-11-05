package com.str.shootingresulttracker.core;

import io.azam.ulidj.ULID;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.type.descriptor.java.UUIDJavaType;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.SecureRandom;
import java.util.EnumSet;
import java.util.UUID;

import static org.hibernate.generator.EventTypeSets.INSERT_ONLY;

@Slf4j
public class ULIDGenerator implements BeforeExecutionGenerator {

    private final SecureRandom random = new SecureRandom();

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        return UUIDJavaType.PassThroughTransformer.INSTANCE.transform(generateUuid());
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return INSERT_ONLY;
    }

    private UUID generateUuid() {
        var ulid = ULID.randomBinary(random);
        var bufferByte = ByteBuffer.wrap(ulid);
        bufferByte.order(ByteOrder.BIG_ENDIAN);
        return new UUID(bufferByte.getLong(), bufferByte.getLong());
    }
}
