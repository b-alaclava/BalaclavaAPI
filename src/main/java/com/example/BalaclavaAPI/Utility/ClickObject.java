package com.example.BalaclavaAPI.Utility;

import com.example.EthanApi.Collections.query.TileObjectQuery;
import com.example.BalaclavaAPI.Utility.DoAction;
import net.runelite.api.*;
import net.runelite.api.widgets.Widget;
import net.runelite.client.RuneLite;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClickObject {

    public static void objectAction(TileObject object, String... actionlist) {
        if (object == null) {
            System.out.println("null object");
            return;
        }
        ObjectComposition comp = TileObjectQuery.getObjectComposition(object);
        if (comp == null) {
            System.out.println("null comp");

            return;
        }
        if (comp.getActions() == null) {
            System.out.println("null actions");
            return;
        }
        List<String> actions = Arrays.stream(comp.getActions()).collect(Collectors.toList());
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i) == null)
                continue;
            actions.set(i, actions.get(i).toLowerCase());
        }

        int num = -1;
        for (String action : actions) {
            for (String action2 : actionlist) {
                if (action != null && action.equalsIgnoreCase(action2.toLowerCase())) {
                    num = actions.indexOf(action) + 1;
                }
            }
        }

        if (num < 1 || num > 10) {
            return;
        }


        int clickX = -1;
        int clickY = -1;

        if(object.getClickbox() != null) {
             clickX = (int) object.getClickbox().getBounds().getX();
             clickY = (int) object.getClickbox().getBounds().getY();
        }


        if (object instanceof GameObject) {
            GameObject g = (GameObject) object;
            DoAction.action(g.getSceneMinLocation().getX(), g.getSceneMinLocation().getY(), MenuAction.of(2 + num), g.getId(), -1, "", clickX, clickY);
        }else {
            DoAction.action(object.getLocalLocation().getSceneX(), object.getLocalLocation().getSceneY(), MenuAction.of(2+num), object.getId(), -1, "", clickX,clickY);
        }

    }



}
