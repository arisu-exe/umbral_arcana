package io.github.fallOut015.umbral_arcana.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.fallOut015.umbral_arcana.MainUmbralArcana;
import io.github.fallOut015.umbral_arcana.client.renderer.entity.model.SigilGlowingLayerModel;
import io.github.fallOut015.umbral_arcana.client.renderer.entity.model.SigilModel;
import io.github.fallOut015.umbral_arcana.level.entity.SigilEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class SigilRenderer extends EntityRenderer<SigilEntity> {
    public static final String MOON = "textures/entity/sigil/moon.png";
    public static final String SUN = "textures/entity/sigil/sun.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(MainUmbralArcana.MODID, "textures/entity/sigil/sigil.png");
    private static final Map<String, ResourceLocation> TEXTURE_MAP = new HashMap<>();

    static {
        TEXTURE_MAP.put(MOON, new ResourceLocation(MainUmbralArcana.MODID, MOON));
        TEXTURE_MAP.put(SUN, new ResourceLocation(MainUmbralArcana.MODID, SUN));
    }

    private final SigilModel<SigilEntity> sigilModel1;
    private final SigilModel<SigilEntity> sigilModel2;
    private final SigilModel<SigilEntity> sigilModel3;
    private final SigilGlowingLayerModel<SigilEntity> sigilGlowingLayerModel;

    public SigilRenderer(EntityRendererManager renderManager) {
        super(renderManager);
        this.sigilModel1 = new SigilModel<>();
        this.sigilModel2 = new SigilModel<>();
        this.sigilModel3 = new SigilModel<>();
        this.sigilGlowingLayerModel = new SigilGlowingLayerModel<>();
    }

    @Override
    public ResourceLocation getTextureLocation(SigilEntity entity) {
        return TEXTURE;
    }
    @Override
    public void render(SigilEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

        matrixStackIn.pushPose();

        matrixStackIn.translate(0, 0.0625f, 0);
        float scale = (float) entityIn.scale(entityIn.tickCount);
        matrixStackIn.scale(scale, 1f, scale);
        IVertexBuilder vertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucentCull(this.getTextureLocation(entityIn)));
        this.sigilModel1.sigil.yRot = (float) entityIn.tickCount / (float) entityIn.getRotationSpeed();
        this.sigilModel2.sigil.yRot = ((float) entityIn.tickCount / (float) entityIn.getRotationSpeed()) * (2f / 3f);
        this.sigilModel3.sigil.yRot = ((float) entityIn.tickCount / (float) entityIn.getRotationSpeed()) * (1f / 3f);
        this.sigilModel1.renderToBuffer(matrixStackIn, vertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 0.7f);
        matrixStackIn.translate(0, 0.01f, 0);
        this.sigilModel2.renderToBuffer(matrixStackIn, vertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 0.8f);
        matrixStackIn.translate(0, 0.01f, 0);
        this.sigilModel3.renderToBuffer(matrixStackIn, vertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 0.9f);

        matrixStackIn.translate(0, 0.0625f - 0.02f, 0);
        IVertexBuilder vertexbuilderlayer = bufferIn.getBuffer(RenderType.eyes(TEXTURE_MAP.get(entityIn.getTexture())));
        this.sigilGlowingLayerModel.renderToBuffer(matrixStackIn, vertexbuilderlayer, 15728640, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, (float) (0.5d * Math.sin((float) entityIn.tickCount * 0.1f) + 0.5d));

        matrixStackIn.popPose();
    }
}