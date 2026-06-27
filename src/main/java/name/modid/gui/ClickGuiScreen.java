package name.modid.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

public class ClickGuiScreen extends Screen {
    private static final int WINDOW_WIDTH = 277;
    private static final int WINDOW_HEIGHT = 573;
    private static final int GAP = 18;
    private static final int CORNER_RADIUS = 23;
    private static final int BG_COLOR = 0xCC1A2A1A; // полупрозрачный тёмно-зелёный

    private float animationProgress = 0f;
    private long startTime = 0;

    public ClickGuiScreen() {
        super(Component.literal("ClickGUI"));
    }

    @Override
    protected void init() {
        super.init();
        animationProgress = 0f;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void tick() {
        super.tick();
        long elapsed = System.currentTimeMillis() - startTime;
        if (elapsed < 300) {
            animationProgress = Mth.clamp((float) elapsed / 300f, 0f, 1f);
        } else {
            animationProgress = 1f;
        }
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        // Затемнение фона
        context.fill(0, 0, this.width, this.height, 0x66000000);

        int totalWidth = WINDOW_WIDTH * 5 + GAP * 4;
        int startX = (this.width - totalWidth) / 2;
        int startY = (this.height - WINDOW_HEIGHT) / 2;

        float scale = 0.8f + 0.2f * animationProgress;
        int offsetX = (int) ((1 - scale) * WINDOW_WIDTH / 2);
        int offsetY = (int) ((1 - scale) * WINDOW_HEIGHT / 2);

        for (int i = 0; i < 5; i++) {
            int x = startX + i * (WINDOW_WIDTH + GAP) + offsetX;
            int y = startY + offsetY;
            int w = (int) (WINDOW_WIDTH * scale);
            int h = (int) (WINDOW_HEIGHT * scale);

            // В официальных маппингах нет fillRounded, рисуем обычный прямоугольник
            context.fill(x, y, x + w, y + h, BG_COLOR);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) { // ESC
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
