package ramcraft.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CombineModel extends ModelBase {
	// fields
	ModelRenderer TANK;
	ModelRenderer pipe;
	ModelRenderer CutterPanel1;
	ModelRenderer CutterPanel2;
	ModelRenderer CutterPanel3;
	ModelRenderer pipeHead;
	ModelRenderer body;
	ModelRenderer caterpillerR;
	ModelRenderer caterpillerL;

	public CombineModel() {
		textureWidth = 128;
		textureHeight = 64;

		TANK = new ModelRenderer(this, 0, 23);
		TANK.addBox(0F, 0F, 0F, 6, 4, 9);
		TANK.setRotationPoint(-5F, 11F, 0F);
		TANK.setTextureSize(128, 64);
		TANK.mirror = true;
		setRotation(TANK, 0F, 0F, 0F);
		pipe = new ModelRenderer(this, 74, 0);
		pipe.addBox(-1F, -1F, 0F, 1, 1, 21);
		pipe.setRotationPoint(0F, 11F, 7F);
		pipe.setTextureSize(128, 64);
		pipe.mirror = true;
		setRotation(pipe, 0F, 2.96706F, 0F);
		CutterPanel1 = new ModelRenderer(this, 0, 0);
		CutterPanel1.addBox(0F, 0F, 0F, 2, 9, 1);
		CutterPanel1.setRotationPoint(-3F, 15F, -8F);
		CutterPanel1.setTextureSize(128, 64);
		CutterPanel1.mirror = true;
		setRotation(CutterPanel1, -0.5235988F, 0F, 0F);
		CutterPanel2 = new ModelRenderer(this, 6, 0);
		CutterPanel2.addBox(0F, 0F, 0F, 2, 9, 1);
		CutterPanel2.setRotationPoint(1F, 15F, -8F);
		CutterPanel2.setTextureSize(128, 64);
		CutterPanel2.mirror = true;
		setRotation(CutterPanel2, -0.5235988F, 0F, 0F);
		// CutterPanel3.mirror = true;
		CutterPanel3 = new ModelRenderer(this, 0, 0);
		CutterPanel3.addBox(0F, 0F, 0F, 2, 9, 1);
		CutterPanel3.setRotationPoint(5F, 15F, -8F);
		CutterPanel3.setTextureSize(128, 64);
		CutterPanel3.mirror = true;
		setRotation(CutterPanel3, -0.5235988F, 0F, 0F);
		CutterPanel3.mirror = false;
		pipeHead = new ModelRenderer(this, 0, 10);
		pipeHead.addBox(-1F, -1F, 19F, 1, 2, 2);
		pipeHead.setRotationPoint(0F, 11F, 7F);
		pipeHead.setTextureSize(128, 64);
		pipeHead.mirror = true;
		setRotation(pipeHead, 0F, 2.96706F, 0F);
		body = new ModelRenderer(this, 38, 0);
		body.addBox(-5F, -10F, -7F, 10, 20, 8);
		body.setRotationPoint(0F, 16F, 3F);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 1.570796F, 0F, 0F);
		caterpillerR = new ModelRenderer(this, 0, 45);
		caterpillerR.addBox(0F, 0F, -9F, 4, 3, 16);
		caterpillerR.setRotationPoint(2F, 21F, 3F);
		caterpillerR.setTextureSize(128, 64);
		caterpillerR.mirror = true;
		setRotation(caterpillerR, 0F, 0F, 0F);
		// caterpillerL.mirror = true;
		caterpillerL = new ModelRenderer(this, 0, 45);
		caterpillerL.addBox(-4F, 0F, -9F, 4, 3, 16);
		caterpillerL.setRotationPoint(-2F, 21F, 3F);
		caterpillerL.setTextureSize(128, 64);
		caterpillerL.mirror = true;
		setRotation(caterpillerL, 0F, 0F, 0F);
		caterpillerL.mirror = false;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		TANK.render(f5);
		pipe.render(f5);
		CutterPanel1.render(f5);
		CutterPanel2.render(f5);
		CutterPanel3.render(f5);
		pipeHead.render(f5);
		body.render(f5);
		caterpillerR.render(f5);
		caterpillerL.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
