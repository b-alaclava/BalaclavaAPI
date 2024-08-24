package com.example.BalaclavaAPI.InteractionUtility;

import com.example.BalaclavaAPI.Utility.ClickWidget;
import com.example.EthanApi.Collections.Inventory;
import com.example.EthanApi.Collections.WidgetInfoExtended;
import com.example.EthanApi.Collections.Widgets;
import net.runelite.api.NPC;
import net.runelite.api.widgets.Widget;

import java.util.Optional;

public class SpellUtility {

    public static void cast(WidgetInfoExtended spell){
        Optional<Widget> spellWidget = Widgets.search().withParentId(14286851).withId(getSpellId(spell)).first();
        spellWidget.ifPresent(widget -> ClickWidget.widgetAction(widget, "cast"));
    }

    public static void cast(String spellName){
        Optional<Widget> spellWidget = Widgets.search().withParentId(14286851).nameContains(spellName).first();
        spellWidget.ifPresent(widget -> ClickWidget.widgetAction(widget, "cast"));
    }

    public static void castOnNpc(WidgetInfoExtended spell, NPC npc){
        Optional<Widget> spellWidget = Widgets.search().withParentId(14286851).withId(getSpellId(spell)).first();
        spellWidget.ifPresent(widget -> ClickWidget.widgetOnNpc(widget, npc));
    }

    public static void castOnNpc(String spellName, NPC npc){
        if(npc == null){
            return;
        }
        Optional<Widget> spellWidget = Widgets.search().withParentId(14286851).nameContains(spellName).first();
        spellWidget.ifPresent(widget -> ClickWidget.widgetOnNpc(widget, npc));
    }

    public static void castOnWidget(WidgetInfoExtended spell, Widget widget){
        Optional<Widget> spellWidget = Widgets.search().withParentId(14286851).withId(getSpellId(spell)).first();
        spellWidget.ifPresent(toCast -> ClickWidget.widgetOnWidget(toCast,widget));
    }

    public static void castOnInventoryItem(String spellName, int itemId){
        Optional<Widget> spellWidget = Widgets.search().withParentId(14286851).nameContains(spellName).first();
        Optional<Widget> inventoryItem = Inventory.search().withId(itemId).first();

        if(inventoryItem.isEmpty()){
            return;
        }

        spellWidget.ifPresent(toCast -> ClickWidget.widgetOnWidget(toCast,inventoryItem.get()));
    }

    public static void castOnInventoryItem(WidgetInfoExtended spell, int itemId){
        Optional<Widget> spellWidget = Widgets.search().withParentId(14286851).withId(getSpellId(spell)).first();
        Optional<Widget> inventoryItem = Inventory.search().withId(itemId).first();

        if(inventoryItem.isEmpty()){
            return;
        }

        spellWidget.ifPresent(toCast -> ClickWidget.widgetOnWidget(toCast,inventoryItem.get()));
    }



    private static int getSpellId(WidgetInfoExtended spell){

        String input = spell.name();
        String output = input.replace("SPELL_", ""); // Remove "SPELL_" from the input
        output = output.replace("_", " "); // Remove any remaining underscores


        return Widgets.search().withParentId(14286851).nameMatchesWildCardNoCase(output.toLowerCase()).first().get().getId();
    }
}
