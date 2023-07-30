package ru.job4j.chess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(null, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenThenImpossibleMoveException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        logic.add(bishopBlack);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class,
                () -> {
                    logic.move(bishopBlack.position(), Cell.C6);
                });
        assertThat(exception.getMessage()).isEqualTo("Could not way by diagonal from %s to %s", bishopBlack.position(), Cell.C6);
    }

    @Test
    public void whenThenOccupiedCellException()
    throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack black = new BishopBlack(Cell.C1);
        PawnBlack pawnBlack = new PawnBlack(Cell.F4);
        logic.add(black);
        logic.add(pawnBlack);
        OccupiedCellException exception = assertThrows(OccupiedCellException.class,
                () -> {
                    logic.move(black.position(), Cell.G5);
                });
        assertThat(exception.getMessage()).isEqualTo("the cell is occupied");
    }
}