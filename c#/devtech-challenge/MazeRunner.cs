using System;
using System.Collections.Generic;
using System.Linq;

namespace MazeRunner
{
    public class MazeSolver
    {
        public static IEnumerable<int> FindPath(int size, List<int> passableIndices, int start, int goal)
        {
            // position index -> [row, column]
            Func<int, int[]> ToMatrixCoordinates = (index) => {
                return new int[] {index / size, index % size};
            };

            // [row, column] -> position index
            Func<int[], int> ToPositionIndex = (coords) => {
                return coords[0] * size + coords[1];
            };

            var pathIndices = new List<int>();

            Action<int, int> AddToPath = (row, col) => {
                var coords = new int[] {row, col};
                pathIndices.Add(ToPositionIndex(coords));
            };

            // represent the maze as a matrix (paths are true, walls are false)
            var maze = new bool[size, size];
            foreach (int index in passableIndices) {
                int i = ToMatrixCoordinates(index)[0];
                int j = ToMatrixCoordinates(index)[1];
                maze[i, j] = true;
            }

            var visited = new bool[size, size];

            int goalRow = ToMatrixCoordinates(goal)[0];
            int goalCol = ToMatrixCoordinates(goal)[1];

            Func<int, int, bool> solve = null; // this is necessary for recursion
            solve = (i, j) => {
                if (i == goalRow && j == goalCol) { AddToPath(i, j); return true; }
                if (!maze[i, j] || visited[i, j]) return false;

                visited[i, j] = true;

                if (i != 0)
                    if (solve(i - 1, j)) { AddToPath(i, j); return true; }
                if (j != 0)
                    if (solve(i, j - 1)) { AddToPath(i, j); return true; }
                if (i != size - 1)
                    if (solve(i + 1, j)) { AddToPath(i, j); return true; }
                if (j != size - 1)
                    if (solve(i, j + 1)) { AddToPath(i, j); return true; }

                return false;
            };

            int startRow = ToMatrixCoordinates(start)[0];
            int startCol = ToMatrixCoordinates(start)[1];

            if (solve(startRow, startCol))
                return pathIndices.AsEnumerable().Reverse();
            return new List<int>();
        }
    }
}

class Program
{
    public static void Main()
    {
        var input = new List<int>() { 0, 1, 6, 7, 8, 13, 18, 19, 21, 22, 23 };
        var solution = MazeRunner.MazeSolver.FindPath(5, input, 18, 1);
        System.Console.WriteLine(String.Join(", ", solution));
    }
}
