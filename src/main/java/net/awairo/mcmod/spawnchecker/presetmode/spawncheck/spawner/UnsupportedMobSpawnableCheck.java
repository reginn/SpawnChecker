/*
 * SpawnChecker.
 * 
 * (c) 2014 alalwww
 * https://github.com/alalwww
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL.
 * Please check the contents of the license located in http://www.mod-buildcraft.com/MMPL-1.0.txt
 * 
 * この MOD は、Minecraft Mod Public License (MMPL) 1.0 の条件のもとに配布されています。
 * ライセンスの内容は次のサイトを確認してください。 http://www.mod-buildcraft.com/MMPL-1.0.txt
 */

package net.awairo.mcmod.spawnchecker.presetmode.spawncheck.spawner;

import net.minecraft.util.math.BlockPos;

/**
 * 対応していないMob用.
 * 
 * @author alalwww
 */
final class UnsupportedMobSpawnableCheck implements MobSpawnerSpawnableCheck
{
    @Override
    public boolean isSpawnable(BlockPos pos)
    {
        return false;
    }

    @Override
    public boolean supported()
    {
        return false;
    }

}
