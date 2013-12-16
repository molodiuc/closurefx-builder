package com.digiarea.closure.model;

import com.digiarea.closure.model.Node;
import com.digiarea.closure.model.Check;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import java.util.List;
import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.dagxp.zippy.ZippyBuffer;
import java.io.IOException;

public class Checks extends Node {

    private ListProperty<Check> check = new SimpleListProperty<Check>(this, "check", FXCollections.observableList(new ArrayList<Check>()));

    public boolean addCheck(Check check) {
        boolean res = getCheck().add(check);
        if (res) {
            if (check != null) {
                check.parent = this;
            }
        }
        return res;
    }

    public boolean removeCheck(Check check) {
        boolean res = getCheck().remove(check);
        if (res) {
            if (check != null) {
                check.parent = this;
            }
        }
        return res;
    }

    public final List<Check> getCheck() {
        return check.get();
    }

    public final void setCheck(List<Check> check) {
        if (check != null) {
            for (Check item : check) {
                item.parent = this;
            }
        }
        this.check.addAll(check);
    }

    public Checks() {
        super();
    }

    public Checks(List<Check> check) {
        super();
        this.check.addAll(check);
    }

    public ListProperty<Check> checkProperty() {
        return check;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    public final int sizeOfChecks(final boolean isExternal) {
        int size = isExternal ? ZippyBuffer.sizeOfRawVarInt(3) : 0;
        if (this.getCheck() != null) {
            size += ZippyBuffer.sizeOfRawVarInt(1);
            final List<Check> check = this.getCheck();
            size += ZippyBuffer.sizeOfRawVarInt(check.size());
            for (Check entry : check) {
                if (entry != null) {
                    size += entry.sizeOfCheck(false);
                } else {
                    size += ZippyBuffer.sizeOfRawVarInt(0);
                }
            }
        }
        size += ZippyBuffer.sizeOfRawVarInt(0);
        return size;
    }

    public final void writeChecks(final ZippyBuffer writer, final boolean isExternal) throws IOException {
        if (isExternal) {
            writer.writeRawVarInt(3);
        }
        if (this.getCheck() != null) {
            writer.writeRawVarInt(1);
            final List<Check> check = this.getCheck();
            writer.writeRawVarInt(check.size());
            for (Check entry : check) {
                if (entry != null) {
                    entry.writeCheck(writer, false);
                } else {
                    writer.writeRawVarInt(0);
                }
            }
        }
        writer.writeRawVarInt(0);
    }

    public static Checks readChecks(final ZippyBuffer reader) throws IOException {
        final Checks packet = new Checks();
        int sector;
        while ((sector = reader.readSector()) != 0) {
            switch(sector) {
                case 1:
                    int checkI = reader.readRawVarInt();
                    final List<Check> check = new ArrayList<Check>(checkI);
                    for (int i = 0; i < checkI; i++) {
                        check.add(Check.readCheck(reader));
                    }
                    packet.setCheck(check);
                    break;
            }
        }
        return packet;
    }

}
