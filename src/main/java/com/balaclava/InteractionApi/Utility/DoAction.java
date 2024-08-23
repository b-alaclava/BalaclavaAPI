package com.balaclava.InteractionApi.Utility;

import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.Point;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.widgets.Widget;
import net.runelite.client.RuneLite;
import net.runelite.client.eventbus.Subscribe;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class DoAction {
    static boolean consume = false;
    static Client client = RuneLite.getInjector().getInstance(Client.class);

    static Random random = new Random();

    public static void click(int x, int y) {
        consume = true;
        sendMouseEventClick(new Point(x,y));
    }

    public static void action(int p1, int p2, MenuAction menuAction, int id, int itemId, String target,int x,int y){
        click(x,y);
        client.menuAction(p1,p2,menuAction,id,itemId,"Balaclava",target);
    }
    private static void sendMouseEventClick(Point p) {
        Client client = RuneLite.getInjector().getInstance(Client.class);
        assert client.isClientThread();
        if (client.isStretchedEnabled()) {
            final Dimension stretched = client.getStretchedDimensions();
            final Dimension real = client.getRealDimensions();
            final double width = (stretched.width / real.getWidth());
            final double height = (stretched.height / real.getHeight());
            final Point point = new Point((int) (p.getX() * width), (int) (p.getY() * height));
            mouseEvent(501, point);
            mouseEvent(502, point);
            mouseEvent(500, point);
            return;
        }
        mouseEvent(501, p);
        mouseEvent(502, p);
        mouseEvent(500, p);
    }
    private static void mouseEvent(int id, Point point) {
        Client client = RuneLite.getInjector().getInstance(Client.class);
        MouseEvent e = new MouseEvent(
                client.getCanvas(), id,
                System.currentTimeMillis(),
                0, point.getX(), point.getY(),
                1, false, 1
        );
        client.getCanvas().dispatchEvent(e);
    }


    //---keyboard
    @Subscribe public void onMenuOptionClicked(MenuOptionClicked event){
        System.out.println("-----------------------------------");
        System.out.println("Param0: " + event.getParam0());
        System.out.println("Param1: " + event.getParam1());
        System.out.println("opcode: " + event.getMenuAction() + " -> " + event.getMenuAction().getId());
        System.out.println("Identifier: " + event.getId());
        System.out.println("ItemId: " + event.getItemId());
        System.out.println("MenuOption: " + event.getMenuOption());
        System.out.println("MenuTarget: " + event.getMenuTarget());
        System.out.println("-----------------------------------");
        consumeClicks(event);
    }

    public static void consumeClicks(MenuOptionClicked event){
        if (event.getMenuEntry().getIdentifier() == 0 && event.getMenuEntry().getParam0() == 0 && event.getParam1() == 0 && event.getItemId() == -1 && event.getMenuOption().equals("Walk here") && event.getId() == 0) {
            event.consume();
            return;
        }

        if(consume && !event.getMenuOption().equals("Balaclava")){
            event.consume();
           consume = false;
        }
    }

    public static Point getClickPoint(Widget widget){
        Point originalLocation = widget.getCanvasLocation();
        int width = widget.getWidth();
        int height = widget.getHeight();


        int modifiedX = originalLocation.getX() + random.nextInt(width);
        int modifiedY = originalLocation.getY() + random.nextInt(height);

        return new Point(modifiedX,modifiedY);


    }
}
