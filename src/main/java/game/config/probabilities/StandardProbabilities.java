package game.config.probabilities;

import java.util.Map;

public class StandardProbabilities extends SymbolProbabilities{
    private int column;
    private int row;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

	public StandardProbabilities() {
		super();
	}

}
