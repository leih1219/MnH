public class MarketCell extends Cell {
    public MarketCell() {
        super();
        icon = "$";
        isAccessible = true;
    }

    @Override
    public String getIcon() {
        return "$";
    }

    public void setMarketCell() {
        icon = "$";
    }
}
