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

package net.awairo.mcmod.spawnchecker.presetmode.spawnervisualizer;

import net.awairo.mcmod.spawnchecker.client.marker.SkeletalMarker;
import net.awairo.mcmod.spawnchecker.client.model.MarkerModel;
import net.awairo.mcmod.spawnchecker.presetmode.spawnervisualizer.model.MobSpawnerBlockOutline;

/**
 * スポーナー可視化モードが有効な場合にスポーナーブロックに描画する枠線.
 * 
 * @author alalwww
 */
public class MobSpawnerBlockMarker extends SkeletalMarker<MobSpawnerBlockMarker>
{
    /** スポーナーブロックの外周. */
    private final MobSpawnerBlockOutline model = new MobSpawnerBlockOutline();

    /**
     * Constructor.
     * 
     * @param config スポーナー可視化モードの設定
     */
    public MobSpawnerBlockMarker(SpawnerVisualizerModeConfig config)
    {
        // TODO: 設定ロード
        model.setOffset(0.01d);
    }

    @Override
    protected MarkerModel model()
    {
        model.setColor(argbColor);
        return model;
    }

}
