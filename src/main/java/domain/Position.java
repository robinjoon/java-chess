package domain;

public enum Position {
    A1(1, 1), A2(2, 1), A3(3, 1), A4(4, 1), A5(5, 1), A6(6, 1), A7(7, 1), A8(8, 1),
    B1(1, 2), B2(2, 2), B3(3, 2), B4(4, 2), B5(5, 2), B6(6, 2), B7(7, 2), B8(8, 2),
    C1(1, 3), C2(2, 3), C3(3, 3), C4(4, 3), C5(5, 3), C6(6, 3), C7(7, 3), C8(8, 3),
    D1(1, 4), D2(2, 4), D3(3, 4), D4(4, 4), D5(5, 4), D6(6, 4), D7(7, 4), D8(8, 4),
    E1(1, 5), E2(2, 5), E3(3, 5), E4(4, 5), E5(5, 5), E6(6, 5), E7(7, 5), E8(8, 5),
    F1(1, 6), F2(2, 6), F3(3, 6), F4(4, 6), F5(5, 6), F6(6, 6), F7(7, 6), F8(8, 6),
    G1(1, 7), G2(2, 7), G3(3, 7), G4(4, 7), G5(5, 7), G6(6, 7), G7(7, 7), G8(8, 7),
    H1(1, 8), H2(2, 8), H3(3, 8), H4(4, 8), H5(5, 8), H6(6, 8), H7(7, 8), H8(8, 8);

    private final int row;
    private final int column;

    Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}

