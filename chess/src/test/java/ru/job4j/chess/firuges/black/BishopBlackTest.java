package ru.job4j.chess.firuges.black;

import ru.job4j.chess.FigureNotFoundException;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.Logic;
import ru.job4j.chess.OccupiedCellException;
import ru.job4j.chess.firuges.Cell;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.byLessThan;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopBlackTest {
    @Test
    public void whenPosition() {
        BishopBlack bishopBlack = new BishopBlack(Cell.A6);
        assertThat(Cell.A6).isEqualTo(bishopBlack.position());
    }

    @Test
    public void whenCopy() {
        BishopBlack bishopBlack = new BishopBlack(Cell.A6);
        BishopBlack black =  (BishopBlack) bishopBlack.copy(Cell.A6);
        assertThat(Cell.A6).isEqualTo(black.position());
    }

    @Test
    public void whenWay() {
        BishopBlack black = new BishopBlack(Cell.C1);
        Cell[] array = black.way(Cell.G5);
        Cell[] excepted = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(array).isEqualTo(excepted);
    }

    @Test
    public void whenWayException() {
        BishopBlack black = new BishopBlack(Cell.C1);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    black.way(Cell.C2);
                });
        assertThat(exception.getMessage())
                .isEqualTo("Could not way by diagonal from %s to %s", Cell.C1, Cell.C2);
    }
}