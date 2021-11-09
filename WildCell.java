public class WildCell extends Cell {
    public WildCell() {
        super();
        icon = " ";
        isAccessible = true;
    }

    @Override
    public String getIcon() {
        return " ";
    }

    public void setWildCell() {
        icon = " ";
    }
}
