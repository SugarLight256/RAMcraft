package ramcraft.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CombineModel extends ModelBase {
	// fields
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer head;
	ModelRenderer head2;
	ModelRenderer head3;

	public CombineModel() {
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this, 28, 8);
		body.addBox(-5F, -10F, -7F, 10, 16, 8);
		body.setRotationPoint(0F, 11F, 3F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 1.570796F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 16);
		leg1.addBox(-2F, 0F, -2F, 4, 6, 4);
		leg1.setRotationPoint(-3F, 18F, 7F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 16);
		leg2.addBox(-2F, 0F, -2F, 4, 6, 4);
		leg2.setRotationPoint(3F, 18F, 7F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 16);
		leg3.addBox(-2F, 18F, -2F, 4, 6, 4);
		leg3.setRotationPoint(-3F, 0F, -5F);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 16);
		leg4.addBox(-2F, 0F, -2F, 4, 6, 4);
		leg4.setRotationPoint(3F, 18F, -5F);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		head = new ModelRenderer(this, 8, 0);
		head.addBox(2F, -3F, -4F, 3, 14, 1);
		head.setRotationPoint(0F, 15F, -6F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, -0.4886922F, 0F, 0F);
		head2 = new ModelRenderer(this, 0, 0);
		head2.addBox(-3F, -3F, -4F, 3, 14, 1);
		head2.setRotationPoint(0F, 15F, -6F);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, -0.4886922F, 0F, 0F);
		head3 = new ModelRenderer(this, 16, 0);
		head3.addBox(7F, -3F, -4F, 3, 14, 1);
		head3.setRotationPoint(0F, 15F, -6F);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, -0.4886922F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		body.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		head.render(f5);
		head2.render(f5);
		head3.render(f5);
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
