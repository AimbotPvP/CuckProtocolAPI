# CuckProtocolAPI
CuckProtocolAPI is a basic and lightweight plugin for 1.5.2-1.12 Bukkit used by other plugins to easily intercept and listen to inbound and outbound packets. 

CuckProtocolAPI is nowhere done and has a lot of updates coming including wrappers. It is **NOT** recommended for use as of current.

### Limitations
- None

### Example usage of CuckProtocolAPI
```java
    @EventHandler
    public void onInboundPacketEvent(InboundPacketEvent event) {
        WrappedPacket wrappedPacket = event.getWrappedPacket();

        Packet packet = wrappedPacket.getPacket(); //Gets the packet type

        long time = wrappedPacket.getTime(); //Gets the time the packet was sent

        switch (packet) {
            case FLYING:

                Bukkit.broadcastMessage(String.format("Sent %s flying packet at %f, %f, %f at %s"
                        , event.getPlayer().getName()
                        , wrappedPacket.getValue(0, double.class)
                        , wrappedPacket.getValue(1, double.class)
                        , wrappedPacket.getValue(2, double.class)
                        , time));
                break;
        }
    }
```
