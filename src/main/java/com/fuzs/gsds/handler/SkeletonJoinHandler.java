package com.fuzs.gsds.handler;

import com.fuzs.gsds.ai.EntityAIAttackRangedEasyBow;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SkeletonJoinHandler {

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onJoinWorld(EntityJoinWorldEvent evt) {

        if (evt.getEntity() instanceof AbstractSkeleton) {

            AbstractSkeleton abstractskeleton = (AbstractSkeleton) evt.getEntity();
            ItemStack itemstack = abstractskeleton.getHeldItemMainhand();

            EntityAIAttackRangedEasyBow<AbstractSkeleton> aiarroweasyattack = new EntityAIAttackRangedEasyBow<>(abstractskeleton,
                    ConfigHandler.GENERAL_CONFIG.chaseSpeedAmp.get(), 20, 60, ConfigHandler.GENERAL_CONFIG.maxAttackDistance.get().floatValue());

            if (itemstack.getItem() instanceof ItemBow) {

                EntityAIBase aiarrowattack = null;

                for (EntityAITasks.EntityAITaskEntry entityaitasks$entityaitaskentry : abstractskeleton.tasks.taskEntries) {

                    EntityAIBase entityaibase = entityaitasks$entityaitaskentry.action;

                    if (entityaibase instanceof EntityAIAttackRangedBow) {

                        aiarrowattack = entityaibase;
                        break;

                    }

                }

                if (aiarrowattack != null) {

                    abstractskeleton.tasks.removeTask(aiarrowattack);
                    abstractskeleton.tasks.addTask(4, aiarroweasyattack);

                }

            }

        }

    }

}
