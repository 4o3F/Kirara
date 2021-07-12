package moe.exusiai.kirara.mixin;

import moe.exusiai.kirara.Data;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @ModifyVariable(method = "onGameMessage", at = @At(value = "STORE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;onGameMessage(Lnet/minecraft/network/packet/c2s/play/ChatMessageC2SPacket;)V"))
    private String onChatMessage(String string) {
        String message = string;
        if (!message.startsWith("/")) {
            Matcher matcher = Data.pattern.matcher(message);
            if (matcher.find()) {
                for (int i = 1; i <= matcher.groupCount(); i ++ ) {
                    String item = matcher.group(i).replaceAll(":", "");
                    if (Data.Emojis.get(item) != null) {
                        message = message.replace(":" + item + ":", Data.Emojis.get(item));
                    }
                }
            }
        }
        return message;
    }
}
