package com.digiarea.closure.model;

import java.io.IOException;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public enum SourceEntity {

    JSC, 
    SOY, 
    GSS;

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfSourceEntity() {
        int size = 0;
        size += ZippyBuffer.sizeOfRawVarInt(ordinal());
        size += 1;
        return size;
    }

    public final void writeSourceEntity(final ZippyBuffer writer) throws IOException {
        writer.writeRawVarInt(ordinal());
        writer.writeRawVarInt(0);
    }

    public static SourceEntity readSourceEntity(final ZippyBuffer reader) throws IOException {
        final SourceEntity packet = SourceEntity.values()[reader.readRawVarInt()];
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
            }
        }
        return packet;
    }

}
