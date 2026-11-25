package dev.astrotreats;

import java.util.function.Function;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.food.FoodProperties;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;


public class ModItems {

    public static final Item STAR_BERRY = register("star_berry",Item::new, new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationModifier(0.5f)
                    .build(),
                   Consumables.defaultFood().onConsume(
                    new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0))
                   ).build()
                )
            );
public static final Item MOON_COOKIE = register("moon_cookie",Item::new, new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationModifier(0.6f)
                    .build(),
                     Consumables.defaultFood().onConsume(
                    new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 400, 1))
                   ).build()
                )
            );
public static final Item SOLAR_PEPPER = register("solar_pepper",Item::new, new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationModifier(0.8f)
                    .build(),
                     Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 400, 1)))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400, 0)))
                .build()
                )
            );

    public static Item register(String name , Function <Item.Properties , Item> itemFactory , Item.Properties settings) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("astrotreats", name));
        Item item = itemFactory.apply(settings.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs
            .FOOD_AND_DRINKS).register((output) -> {
            output.accept(STAR_BERRY);
            output.accept(MOON_COOKIE);
            output.accept(SOLAR_PEPPER);

        });
    }
        }