package io.github.fallOut015.umbral_arcana.client.renderer.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class DiscEyesModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer disc;

    public DiscEyesModel() {
        super(RenderType::eyes);

        this.texHeight = 64;
        this.texWidth = 128;

        this.disc = new ModelRenderer(this);
        this.disc.addBox(-32, 0, -32, 64, 0.0625f, 64);
        this.disc.setPos(0, 0, 0);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.disc.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, blue, green, alpha);
    }
}