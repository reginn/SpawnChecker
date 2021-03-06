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

import static net.awairo.mcmod.spawnchecker.client.marker.RenderingSupport.*;

import com.google.common.base.Supplier;
import org.lwjgl.opengl.GL11;

import net.awairo.mcmod.spawnchecker.client.marker.SkeletalMarker;
import net.awairo.mcmod.spawnchecker.client.model.MarkerModel;
import net.awairo.mcmod.spawnchecker.presetmode.spawnervisualizer.model.MobSpawnerSpawnablePoint;

/**
 * スポーナーのスポーン可否マーカー.
 * 
 * @author alalwww
 */
public class MobSpawnerPointMerker extends SkeletalMarker<MobSpawnerPointMerker>
{
    private final MobSpawnerSpawnablePoint model = new MobSpawnerSpawnablePoint();
    private int inherent;

    private MobSpawnerPointMerker(SpawnerVisualizerModeConfig config)
    {
        // TODO: アニメーションの設定などの読み込み
    }

    /**
     * アニメーション開始位置の個体差を設定します.
     * 
     * <p>この値を用い、全てのマーカーモデルが同じアニメーションとならないようにしています。</p>
     * 
     * @param inherent 個体差値
     * @return このインスタンス
     */
    public MobSpawnerPointMerker setInherent(int inherent)
    {
        this.inherent = inherent;
        return this;
    }

    @Override
    public MobSpawnerPointMerker reset()
    {
        super.reset();
        inherent = 0;
        return this;
    }

    @Override
    public MobSpawnerPointMerker setPosX(double posX)
    {
        return super.setPosX(posX + 0.5);
    }

    @Override
    public MobSpawnerPointMerker setPosY(double posY)
    {
        return super.setPosY(posY + 0.5);
    }

    @Override
    public MobSpawnerPointMerker setPosZ(double posZ)
    {
        return super.setPosZ(posZ + 0.5);
    }

    @Override
    protected MarkerModel model()
    {
        return model;
    }

    @Override
    protected void render(MarkerModel model)
    {
        GL11.glPushMatrix();

        {
            final double tick = inherent + tickCounts + partialTicks;
            final double tremor = Math.sin(tick * 0.06f) * 0.02;

            GL11.glTranslated(
                    posX - manager().viewerPosX,
                    posY - manager().viewerPosY + tremor,
                    posZ - manager().viewerPosZ);

            setGLColor(argbColor);

            // 頂点を上にして
            GL11.glRotatef(90, 1, 0, 0);
            // 適当に回転させる
            GL11.glRotated(inherent + tick, 0, 0, 1);

            model.render();
        }

        GL11.glPopMatrix();
    }

    /**
     * @param config スポーナー可視化モードの設定
     * @return スポーンポイントマーカーのSupplier
     */
    public static Supplier<MobSpawnerPointMerker> supplier(final SpawnerVisualizerModeConfig config)
    {
        return new Supplier<MobSpawnerPointMerker>()
        {
            @Override
            public MobSpawnerPointMerker get()
            {
                return new MobSpawnerPointMerker(config);
            }
        };
    }
}
