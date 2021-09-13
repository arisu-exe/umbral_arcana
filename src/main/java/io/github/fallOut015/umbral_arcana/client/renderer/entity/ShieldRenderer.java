package io.github.fallOut015.umbral_arcana.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.fallOut015.umbral_arcana.MainUmbralArcana;
import io.github.fallOut015.umbral_arcana.client.renderer.entity.model.DiscEyesModel;
import io.github.fallOut015.umbral_arcana.client.renderer.entity.model.DiscTranslucentCullModel;
import io.github.fallOut015.umbral_arcana.level.entity.ShieldEntity;
import io.github.fallOut015.umbral_arcana.level.entity.SigilEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

import java.util.HashMap;
import java.util.Map;

public class ShieldRenderer extends EntityRenderer<ShieldEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MainUmbralArcana.MODID, "textures/entity/shield/shield.png");
    private static final ResourceLocation PROTECTIONS = new ResourceLocation(MainUmbralArcana.MODID, "textures/entity/shield/protections.png");

    private final DiscTranslucentCullModel<ShieldEntity> shieldModel;
    private final DiscEyesModel<ShieldEntity> shieldGlowingLayerModel1;
    private final DiscEyesModel<ShieldEntity> shieldGlowingLayerModel2;

    public ShieldRenderer(EntityRendererManager renderManager) {
        super(renderManager);
        this.shieldModel = new DiscTranslucentCullModel<>();
        this.shieldGlowingLayerModel1 = new DiscEyesModel<>();
        this.shieldGlowingLayerModel2 = new DiscEyesModel<>();
    }

    @Override
    public ResourceLocation getTextureLocation(ShieldEntity entity) {
        return TEXTURE;
    }
    @Override
    public void render(ShieldEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

        matrixStackIn.pushPose();

        matrixStackIn.translate(0, entityIn.getCasterEyeHeight(), 0);
        matrixStackIn.mulPose(new Quaternion(Vector3f.YP, entityIn.getYHeadRot(), false));
        matrixStackIn.mulPose(new Quaternion(Vector3f.XP, 90, true));

        IVertexBuilder vertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucentCull(this.getTextureLocation(entityIn)));
        this.shieldModel.renderToBuffer(matrixStackIn, vertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 0.7f);

        IVertexBuilder vertexbuilderlayer = bufferIn.getBuffer(RenderType.eyes(PROTECTIONS));
        matrixStackIn.translate(0, 0.0625f, 0);
        this.shieldGlowingLayerModel1.disc.yRot = (float) entityIn.tickCount / 10f;
        this.shieldGlowingLayerModel1.renderToBuffer(matrixStackIn, vertexbuilderlayer, 15728640, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, (float) (0.5d * Math.sin((float) entityIn.tickCount * 0.1f) + 0.5d));
        matrixStackIn.translate(0, 0.0625f, 0);
        this.shieldGlowingLayerModel2.disc.yRot = (float) entityIn.tickCount / 15f;
        this.shieldGlowingLayerModel2.renderToBuffer(matrixStackIn, vertexbuilderlayer, 15728640, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, (float) (0.5d * Math.sin((float) entityIn.tickCount * 0.1f) + 0.5d));

        matrixStackIn.popPose();
    }
}