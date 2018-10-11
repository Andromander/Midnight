package com.mushroom.midnight.common.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;

public class RiftAttachment {
    private final BlockPos pos;
    private final float yaw;

    public RiftAttachment(BlockPos pos, float yaw) {
        this.pos = pos;
        this.yaw = yaw;
    }

    public static RiftAttachment read(ByteBuf buf) {
        BlockPos pos = BlockPos.fromLong(buf.readLong());
        float yaw = buf.readFloat();
        return new RiftAttachment(pos, yaw);
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void write(ByteBuf buf) {
        buf.writeLong(this.pos.toLong());
        buf.writeFloat(this.yaw);
    }

    public void apply(EntityRift rift) {
        BlockPos pos = rift.world.getTopSolidOrLiquidBlock(this.pos);
        rift.setPositionAndRotation(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, this.yaw, 0.0F);
    }
}
