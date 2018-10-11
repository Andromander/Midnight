package com.mushroom.midnight.common.network;

import com.mushroom.midnight.Midnight;
import com.mushroom.midnight.common.entity.RiftAttachment;
import com.mushroom.midnight.common.entity.RiftBridge;
import com.mushroom.midnight.common.world.RiftTracker;
import com.mushroom.midnight.common.world.RiftTrackerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBridgeCreate implements IMessage {
    private int bridgeId;
    private RiftAttachment attachment;
    private ByteBuf data;

    public MessageBridgeCreate() {
    }

    public MessageBridgeCreate(RiftBridge bridge) {
        this.bridgeId = bridge.getBridgeId();
        this.attachment = bridge.getAttachment();
        this.data = Unpooled.buffer();
        bridge.writeState(this.data);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.bridgeId = buf.readInt();
        this.attachment = RiftAttachment.read(buf);
        this.data = buf.retain();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.bridgeId);
        this.attachment.write(buf);
        buf.writeBytes(this.data);
    }

    public static class Handler implements IMessageHandler<MessageBridgeCreate, IMessage> {
        @Override
        public IMessage onMessage(MessageBridgeCreate message, MessageContext ctx) {
            Midnight.proxy.handleMessage(ctx, player -> {
                RiftTracker trackerHandler = RiftTrackerHandler.getClient();

                RiftBridge bridge = trackerHandler.getBridge(message.bridgeId);
                if (bridge == null) {
                    bridge = new RiftBridge(message.bridgeId, message.attachment);
                    trackerHandler.addBridge(bridge);
                }

                bridge.setAttachment(message.attachment);
                bridge.handleState(message.data);

                message.data.release();
            });

            return null;
        }
    }
}
