public class ImpassableCell extends Cell {
    public ImpassableCell() {
        super();
        icon = "X";
        isAccessible = false;
    }

    @Override
    public String getIcon() {
        return "X";
    }

    public void setImpassableCell() {
        icon = "X";
    }
}
