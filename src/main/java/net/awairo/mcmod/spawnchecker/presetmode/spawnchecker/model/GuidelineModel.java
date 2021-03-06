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

package net.awairo.mcmod.spawnchecker.presetmode.spawnchecker.model;

import static net.awairo.mcmod.spawnchecker.client.marker.RenderingSupport.*;

import net.awairo.mcmod.spawnchecker.client.model.SkeletalMarkerModel;

/**
 * ガイドライン.
 * 
 * @author alalwww
 */
public class GuidelineModel extends SkeletalMarkerModel
{
    private static final double CENTER = 0.5d;

    private double length;
    private double offsetX;
    private double offsetZ;

    /**
     * Constructor.
     */
    public GuidelineModel()
    {
    }

    /**
     * @param length ガイドライン長
     */
    public void setLength(double length)
    {
        this.length = length;
    }

    /**
     * @param offsetX X軸オフセット
     */
    public void setOffsetX(double offsetX)
    {
        this.offsetX = offsetX;
    }

    /**
     * @param offsetZ Z軸オフセット
     */
    public void setOffsetZ(double offsetZ)
    {
        this.offsetZ = offsetZ;
    }

    @Override
    public void render()
    {
        startDrawingLines();
        setGLColor(color);

        final double x = CENTER + offsetX;
        final double z = CENTER + offsetZ;

        addVertex(x, 0, z);
        addVertex(x, length, z);

        draw();
    }

}
