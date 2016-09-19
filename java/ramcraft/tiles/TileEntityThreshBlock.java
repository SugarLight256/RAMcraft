package ramcraft.tiles;

import net.minecraft.tileentity.TileEntity;

public class TileEntityThreshBlock extends TileEntity {

	private int dir = 0;

	public TileEntityThreshBlock() {

	}

	public void setDir(int newDir) {
		dir = newDir;
	}

	public int getDir() {
		return dir;
	}

}
