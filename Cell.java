public class Cell {
    protected String icon;
    protected boolean isAccessible;

    public Cell() {
        icon = " ";
        isAccessible = true;
    }

    public Cell(boolean isAccessible) {
        this.isAccessible = isAccessible;
    }

    public Cell(String icon) {
        this.icon = icon;
    }

    public Cell(String icon, boolean isAccessible) {
        this.icon = icon;
        this.isAccessible = isAccessible;
    }

    @Override
    public String toString() {
        return icon;
    }

    public String getIcon() {
        return icon;
    }

    public boolean getAccessibility() {
        return isAccessible;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setAccessible(boolean isAccessible) {
        this.isAccessible = isAccessible;
    }
}
