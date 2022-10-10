package vscript.util;

import vscript.stage.Window;

/**
 * The {@code interface VFunc} acts as a variable to store code, refered as {@link VFunc#displayOn(Window)}.
 *
 * @author Kodascii
 * @version 1.0
 * @since 1.0
 **/
public interface VFunc {

    /**
     * The method {@code displayOn(Window win)} execute the instructions stored, for a given {@link Window}.
     **/
    public void displayOn(Window win);
}