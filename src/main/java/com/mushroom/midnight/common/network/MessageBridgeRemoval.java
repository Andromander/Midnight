package com.mushroom.midnight.common.network;

import com.mushroom.midnight.Midnight;
import com.mushroom.midnight.common.world.RiftTrackerHandler;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBridgeRemoval implements IMessage {
    private int handlerId;

    public MessageBridgeRemoval() {
    }

    public MessageBridgeRemoval(int handlerId) {
        this.handlerId = handlerId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.handlerId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.handlerId);
    }

    public static class Handler implements IMessageHandler<MessageBridgeRemoval, IMessage> {
        @Override
        public IMessage onMessage(MessageBridgeRemoval message, MessageContext ctx) {
            Midnight.proxy.handleMessage(ctx, p -> RiftTrackerHandler.getClient().removeBridge(message.handlerId));
            return null;
        }
    }
}
