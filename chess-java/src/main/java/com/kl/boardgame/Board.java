package com.kl.boardgame;

import com.kl.boardgame.exceptions.BoardException;
import lombok.Getter;

public class Board {
    @Getter
    private final int rows, columns;

    private final Piece[][] pieces;

    public Board(Integer rows, int columns) {
        if (rows < 1 || columns < 1){
            throw new BoardException("Error: To create a board must be at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public Piece piece(Integer row, int column) {
        if (!positionExists(row, column)){
            throw new BoardException("Position not on the board");
        }
        return pieces[row][column];
    }

    public Piece piece(Position position) {
        if (!positionExists(position)){
            throw new BoardException("Position not on the board: " + position);
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        if(thereIsAPiece(position)){
            throw new BoardException("There is already a piece on position " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }

        if (piece(position) == null) {
            return null;
        }

        Piece removedPiece = piece(position);
        pieces[position.getRow()][position.getColumn()] = null;
        return removedPiece;
    }

    private boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position){
        if (!positionExists(position)){
            throw new BoardException("Position not on the board");
        }
        return piece(position) != null;
    }
}
