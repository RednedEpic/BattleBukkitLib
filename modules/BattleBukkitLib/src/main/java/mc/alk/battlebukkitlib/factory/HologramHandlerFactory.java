package mc.alk.battlebukkitlib.factory;

import mc.alk.battlebukkitlib.handlers.IHologramHandler;

public class HologramHandlerFactory {

    private static HandlerFactory<IHologramHandler> factory = new HandlerFactory<IHologramHandler>();

    public static IHologramHandler getNewInstance() {
        IHologramHandler handler = factory.getNewInstance("HologramHandler");
        return (handler == null) ? IHologramHandler.NULL_HANDLER : handler;
    }

}
