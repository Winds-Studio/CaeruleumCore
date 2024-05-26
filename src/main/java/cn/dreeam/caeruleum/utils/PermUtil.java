package cn.dreeam.caeruleum.utils;

import cn.dreeam.caeruleum.CaeruleumCore;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PermissionNode;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class PermUtil {

    public static User getUser(UUID uuid) {
        UserManager userManager = CaeruleumCore.getLuckPermsAPI().getUserManager();
        CompletableFuture<User> userFuture = userManager.loadUser(uuid);

        return userFuture.join();
    }

    public static boolean hasLangPerm(UUID uuid) {
        User user = getUser(uuid);
        Set<String> groups = user.getNodes(NodeType.PERMISSION).stream()
                .map(PermissionNode::getPermission)
                .filter(s -> !s.startsWith(CaeruleumCore.config.langPermKeyPrefix()))
                .collect(Collectors.toSet());

        return !groups.isEmpty();
    }
}
