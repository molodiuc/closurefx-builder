package com.digiarea.closure.model;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.zippy.ZippyBuffer;

public class JsPropertyMap extends Node {

    private StringProperty input = new SimpleStringProperty(this, "input");

    private StringProperty output = new SimpleStringProperty(this, "output");

    public final String getInput() {
        return input.get();
    }

    public final void setInput(String input) {
        this.input.set(input);
    }

    public final String getOutput() {
        return output.get();
    }

    public final void setOutput(String output) {
        this.output.set(output);
    }

    public JsPropertyMap() {
        super();
    }

    public JsPropertyMap(String input, String output) {
        super();
        this.input.set(input);
        this.output.set(output);
    }

    public StringProperty inputProperty() {
        return input;
    }

    public StringProperty outputProperty() {
        return output;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfJsPropertyMap(final boolean isExternal) {
        int size = isExternal ? 1 : 0;
        if (this.getInput() != null) {
            size += 1;
            size += ZippyBuffer.sizeOfString(this.getInput());
        }
        if (this.getOutput() != null) {
            size += 1;
            size += ZippyBuffer.sizeOfString(this.getOutput());
        }
        size += 1;
        return size;
    }

    public final void writeJsPropertyMap(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(38);
        }
        if (this.getInput() != null) {
            writer.writeRawVarInt(1);
            writer.writeString(this.getInput());
        }
        if (this.getOutput() != null) {
            writer.writeRawVarInt(2);
            writer.writeString(this.getOutput());
        }
        writer.writeRawVarInt(0);
    }

    public static JsPropertyMap readJsPropertyMap(final ZippyBuffer reader) throws IOException {
        final JsPropertyMap packet = new JsPropertyMap();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    packet.setInput(reader.readString());
                    break;
                case 2:
                    packet.setOutput(reader.readString());
                    break;
            }
        }
        return packet;
    }

}
