package archrn.tea_engine.input;

import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.geometry.Vector2Int;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * InputProvider
 *
 * @author archrn
 * @version 0
 * @since 0
 */
public interface InputProvider
{

    // Key shortcuts.

    int MOUSE_LEFT = MouseEvent.BUTTON1;
    int MOUSE_RIGHT = MouseEvent.BUTTON3;
    int KEY_UP = KeyEvent.VK_UP;
    int KEY_DOWN = KeyEvent.VK_DOWN;
    int KEY_LEFT = KeyEvent.VK_LEFT;
    int KEY_RIGHT = KeyEvent.VK_RIGHT;
    int KEY_SHIFT = KeyEvent.VK_SHIFT;
    int KEY_SPACE = KeyEvent.VK_SPACE;
    int KEY_A = KeyEvent.VK_A;
    int KEY_B = KeyEvent.VK_B;
    int KEY_C = KeyEvent.VK_C;
    int KEY_D = KeyEvent.VK_D;
    int KEY_E = KeyEvent.VK_E;
    int KEY_F = KeyEvent.VK_F;
    int KEY_G = KeyEvent.VK_G;
    int KEY_H = KeyEvent.VK_H;
    int KEY_I = KeyEvent.VK_I;
    int KEY_J = KeyEvent.VK_J;
    int KEY_K = KeyEvent.VK_K;
    int KEY_L = KeyEvent.VK_L;
    int KEY_M = KeyEvent.VK_M;
    int KEY_N = KeyEvent.VK_N;
    int KEY_O = KeyEvent.VK_O;
    int KEY_P = KeyEvent.VK_P;
    int KEY_Q = KeyEvent.VK_Q;
    int KEY_R = KeyEvent.VK_R;
    int KEY_S = KeyEvent.VK_S;
    int KEY_T = KeyEvent.VK_T;
    int KEY_U = KeyEvent.VK_U;
    int KEY_V = KeyEvent.VK_V;
    int KEY_W = KeyEvent.VK_W;
    int KEY_X = KeyEvent.VK_X;
    int KEY_Y = KeyEvent.VK_Y;
    int KEY_Z = KeyEvent.VK_Z;

    boolean getKey(int key);

    Vector2Int getMousePosition();

    Vector2 getMousePositionWorld();

}
