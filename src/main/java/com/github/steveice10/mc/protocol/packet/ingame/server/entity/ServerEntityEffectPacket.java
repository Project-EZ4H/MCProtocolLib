package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.packet.MinecraftPacket;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import java.io.IOException;

public class ServerEntityEffectPacket extends MinecraftPacket {
    private int entityId;
    private int effect;
    private int amplifier;
    private int duration;
    private boolean ambient;
    private boolean showParticles;

    @SuppressWarnings("unused")
    private ServerEntityEffectPacket() {
    }

    public ServerEntityEffectPacket(int entityId, int effect, int amplifier, int duration, boolean ambient, boolean showParticles) {
        this.entityId = entityId;
        this.effect = effect;
        this.amplifier = amplifier;
        this.duration = duration;
        this.ambient = ambient;
        this.showParticles = showParticles;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public int getEffect() {
        return this.effect;
    }

    public int getAmplifier() {
        return this.amplifier;
    }

    public int getDuration() {
        return this.duration;
    }

    public boolean isAmbient() {
        return this.ambient;
    }

    public boolean getShowParticles() {
        return this.showParticles;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.effect = in.readByte();
        this.amplifier = in.readByte();
        this.duration = in.readVarInt();

        int flags = in.readByte();
        this.ambient = (flags & 0x1) == 0x1;
        this.showParticles = (flags & 0x2) == 0x2;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeByte(this.effect);
        out.writeByte(this.amplifier);
        out.writeVarInt(this.duration);

        int flags = 0;
        if(this.ambient) {
            flags |= 0x1;
        }

        if(this.showParticles) {
            flags |= 0x2;
        }

        out.writeByte(flags);
    }
}
