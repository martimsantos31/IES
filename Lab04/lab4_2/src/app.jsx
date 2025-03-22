import { useState } from 'react';
import './app.css';

function Square({ value, onSquareClick }) {
    return (
        <button className="square" onClick={onSquareClick}>
            {value}
        </button>
    );
}

function Board({ xIsNext, squares, onPlay }) {
    function handleClick(i) {
        if (calculateWinner(squares) || squares[i]) {
            return;
        }
        const nextSquares = squares.slice();
        nextSquares[i] = xIsNext ? 'X' : 'O';
        onPlay(nextSquares);
    }

    const winner = calculateWinner(squares);
    let status;
    if (winner) {
        status = 'Winner: ' + winner;
    } else {
        status = 'Next player: ' + (xIsNext ? 'X' : 'O');
    }

    // Render the 6x6 grid
    const grid = Array(6)
        .fill(null)
        .map((_, row) => (
            <div className="board-row" key={row}>
                {Array(6)
                    .fill(null)
                    .map((_, col) => {
                        const index = row * 6 + col;
                        return (
                            <Square
                                key={index}
                                value={squares[index]}
                                onSquareClick={() => handleClick(index)}
                            />
                        );
                    })}
            </div>
        ));

    return (
        <>
            <div className="status">{status}</div>
            {grid}
        </>
    );
}

export default function Game() {
    const [history, setHistory] = useState([Array(36).fill(null)]);
    const [currentMove, setCurrentMove] = useState(0);
    const xIsNext = currentMove % 2 === 0;
    const currentSquares = history[currentMove];

    function handlePlay(nextSquares) {
        const nextHistory = [...history.slice(0, currentMove + 1), nextSquares];
        setHistory(nextHistory);
        setCurrentMove(nextHistory.length - 1);
    }

    function jumpTo(nextMove) {
        setCurrentMove(nextMove);
    }

    const moves = history.map((_, move) => {
        const description = move ? `Go to move #${move}` : 'Go to game start';
        return (
            <li key={move}>
                <button onClick={() => jumpTo(move)}>{description}</button>
            </li>
        );
    });

    return (
        <div className="game">
            <div className="game-board">
                <Board xIsNext={xIsNext} squares={currentSquares} onPlay={handlePlay} />
            </div>
            <div className="game-info">
                <ol>{moves}</ol>
            </div>
        </div>
    );
}

function calculateWinner(squares) {
    const lines = [];
    const gridSize = 6;
    const winCondition = 3;

    // Generate all possible winning combinations
    for (let i = 0; i < gridSize; i++) {
        for (let j = 0; j <= gridSize - winCondition; j++) {
            // Horizontal lines
            lines.push([i * gridSize + j, i * gridSize + j + 1, i * gridSize + j + 2]);
            // Vertical lines
            lines.push([j * gridSize + i, (j + 1) * gridSize + i, (j + 2) * gridSize + i]);
        }
    }

    // Diagonal lines
    for (let i = 0; i <= gridSize - winCondition; i++) {
        for (let j = 0; j <= gridSize - winCondition; j++) {
            lines.push([
                i * gridSize + j,
                (i + 1) * gridSize + j + 1,
                (i + 2) * gridSize + j + 2,
            ]);
            lines.push([
                i * gridSize + j + winCondition - 1,
                (i + 1) * gridSize + j + winCondition - 2,
                (i + 2) * gridSize + j + winCondition - 3,
            ]);
        }
    }

    // Check for a winner
    for (const [a, b, c] of lines) {
        if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
            return squares[a];
        }
    }
    return null;
}




