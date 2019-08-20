package com.fuzs.gsds.handler;

import com.fuzs.gsds.ai.EntityAIAttackRangedEasyBow;
import com.fuzs.gsds.helper.ReflectionHelper;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SkeletonJoinHandler {

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onJoinWorld(EntityJoinWorldEvent evt) {

        if (evt.getEntity() instanceof AbstractSkeleton) {

            AbstractSkeleton abstractskeleton = (AbstractSkeleton) evt.getEntity();
            boolean flag = abstractskeleton.getEntityWorld().getDifficulty() != EnumDifficulty.HARD && ConfigHandler.slowBowDrawing;
            EntityAIAttackRangedEasyBow<AbstractSkeleton> aiarroweasyattack = new EntityAIAttackRangedEasyBow<>(abstractskeleton, ConfigHandler.chaseSpeedAmp, flag ? 40 : 20, 60, (float) ConfigHandler.maxAttackDistance);
            ItemStack itemstack = abstractskeleton.getHeldItemMainhand();

            if (itemstack.getItem() instanceof ItemBow) {

                for (EntityAITasks.EntityAITaskEntry entityaitasks$entityaitaskentry : abstractskeleton.tasks.taskEntries) {

                    EntityAIBase entityaibase = entityaitasks$entityaitaskentry.action;

                    if (entityaibase instanceof EntityAIAttackRangedBow) {

                        abstractskeleton.tasks.removeTask(entityaibase);
                        abstractskeleton.tasks.addTask(4, aiarroweasyattack);

                    }

                }

            }

            ReflectionHelper.setAiArrowAttack(abstractskeleton, aiarroweasyattack);

        }

    }

}
