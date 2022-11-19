public class RendererFactory {
    /////////////////////////////
    /*       Constants     */
    /////////////////////////////
    public static String CONSOLE_RENDERER = "console";
    public static String VOID_RENDERER = "none";

    /////////////////////////////////
    /*       Builder Function     */
    ////////////////////////////////
    public Renderer buildRenderer(String type, int size) {
        Renderer render;
        String lowerCaseType = type.toLowerCase();
        if (lowerCaseType.compareTo(CONSOLE_RENDERER ) == 0) {
            render = new ConsoleRenderer(size);
        } else if (lowerCaseType.compareTo(VOID_RENDERER) == 0) {
            render = new VoidRenderer();
        } else {
            render = null;
        }
        return render;
    }
}

