package ramcraft.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ThreshBlockModel extends ModelBase {
	// fields
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	ModelRenderer Leg4;
	ModelRenderer Stick;
	ModelRenderer Shape1;

	public ThreshBlockModel() {
		textureWidth = 64;
		textureHeight = 32;

		Leg1 = new ModelRenderer(this, 0, 0);
		Leg1.addBox(0F, 0F, 0F, 9, 1, 1);
		Leg1.setRotationPoint(4F, 16F, 7F);
		Leg1.setTextureSize(64, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0F, 1.041022F);
		Leg2 = new ModelRenderer(this, 0, 2);
		Leg2.addBox(0F, 0F, 0F, 9, 1, 1);
		Leg2.setRotationPoint(4F, 16F, -8F);
		Leg2.setTextureSize(64, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, 0F, 1.047198F);
		Leg3 = new ModelRenderer(this, 0, 4);
		Leg3.addBox(0F, 0F, 0F, 15, 1, 1);
		Leg3.setRotationPoint(5F, 17F, -8F);
		Leg3.setTextureSize(64, 32);
		Leg3.mirror = true;
		setRotation(Leg3, 0F, 0F, 2.622648F);
		Leg4 = new ModelRenderer(this, 0, 6);
		Leg4.addBox(0F, 0F, 0F, 15, 1, 1);
		Leg4.setRotationPoint(5F, 17F, 7F);
		Leg4.setTextureSize(64, 32);
		Leg4.mirror = true;
		setRotation(Leg4, 0F, 0F, 2.622648F);
		Stick = new ModelRenderer(this, 0, 8);
		Stick.addBox(0F, 0F, 0F, 1, 1, 18);
		Stick.setRotationPoint(3F, 16F, -9F);
		Stick.setTextureSize(64, 32);
		Stick.mirror = true;
		setRotation(Stick, 0F, 0F, 0F);
		Shape1 = new ModelRenderer(this, 42, 0);
		Shape1.addBox(0F, 0F, 0F, 3, 1, 9);
		Shape1.setRotationPoint(3F, 16F, -4F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, -0.5340708F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(entity, f, f1, f2, f3, f4, f5);
		Leg1.render(f5);
		Leg2.render(f5);
		Leg3.render(f5);
		Leg4.render(f5);
		Stick.render(f5);
		Shape1.render(f5);
	}

	public void renderModel(float f5) {
		Leg1.render(f5);
		Leg2.render(f5);
		Leg3.render(f5);
		Leg4.render(f5);
		Stick.render(f5);
		Shape1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
