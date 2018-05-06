package us.skidrevenant.cuckprotocolapi.packet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import us.skidrevenant.cuckprotocolapi.packet.wrappers.EntityUseAction;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SkidRevenant
 * at 26/04/2018
 */
@Getter
@RequiredArgsConstructor
public class WrappedPacket {

    private final Packet packet; //The packet

    private final Object handle;

    private final List<Object> values; //All declared fields within the packet

    private final long time; //The time of when the packet was sent

    /**
     * Gets a value from {@link List<Object>} at the index {@param index} cast to {@param classType}
     *
     * @author Fyu
     * @see "https://github.com/GitFyu/PacketAPI/blob/master/src/main/java/pw/cinque/packetapi/PacketValues.java#L43"
     * @param index                   The index from the {@link List<Object>}
     * @param classType               The classType of the Object from {@param index}
     * @return                        The value from {@param index} cast to {@param classType}
     * @throws ClassCastException     When attempted to cast the object at {@param index} to a subclass of which it isn't an instance of
     */
    public <T> T getValue(int index, Class<T> classType) {
        return (T) values.get(index);
    }

    public EntityUseAction getEntityUseAction() {
        return this.values.stream()
                .filter(EntityUseAction.class::isInstance)
                .map(EntityUseAction.class::cast)
                .findFirst()
                .orElse(null);
    }
}
