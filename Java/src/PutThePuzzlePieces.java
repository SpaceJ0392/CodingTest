import java.util.*;

public class PutThePuzzlePieces {

    public static void main(String[] args) {
        PutThePuzzlePieces p = new PutThePuzzlePieces();
        int ans = p.solution(new int[][] {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}},
                new int[][] {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}});

        System.out.println(ans);
    }

    private static class Block {
        private final int[][] block; // 정규화된 조각 모양 배열
        private final int size;      // 조각을 이루는 칸의 개수

        public Block(List<int[]> idxes) {
            this.size = idxes.size();
            this.block = normalize(idxes);
        }

        // List<int[]> 형태의 좌표 목록을 가장 작은 직사각형 형태의 2차원 배열로 정규화
        private int[][] normalize(List<int[]> arrayList) {
            if (arrayList.isEmpty()) return new int[0][0];

            int minY = Integer.MAX_VALUE;
            int minX = Integer.MAX_VALUE;
            int maxY = Integer.MIN_VALUE;
            int maxX = Integer.MIN_VALUE;

            for (int[] pos : arrayList) {
                minY = Math.min(pos[0], minY);
                minX = Math.min(pos[1], minX);
                maxY = Math.max(pos[0], maxY);
                maxX = Math.max(pos[1], maxX);
            }

            int borderY = maxY - minY + 1;
            int borderX = maxX - minX + 1;
            int[][] norBlock = new int[borderY][borderX];

            for (int[] pos : arrayList) {
                int newY = pos[0] - minY;
                int newX = pos[1] - minX;
                norBlock[newY][newX] = 1;
            }
            return norBlock;
        }
    }

    public int solution(int[][] gameBoard, int[][] table) {
        int answer = 0;
        int len = gameBoard.length;

        // 방문 배열을 생성하여 원본 gameBoard와 table을 훼손하지 않음
        boolean[][] boardVisited = new boolean[len][len];
        boolean[][] tableVisited = new boolean[len][len];

        List<Block> blanks = new ArrayList<>();
        List<Block> blocks = new ArrayList<>();

        // 1. 빈 공간 (blanks) 추출
        for (int y = 0; y < len; y++) {
            for (int x = 0; x < len; x++) {
                // board[y][x] == 0 (빈 공간)이고 아직 방문하지 않았다면
                if (gameBoard[y][x] == 0 && !boardVisited[y][x]) {
                    blanks.add(getBlock(gameBoard, boardVisited, y, x, 0));
                }
                // table[y][x] == 1 (퍼즐 조각)이고 아직 방문하지 않았다면
                if (table[y][x] == 1 && !tableVisited[y][x]) {
                    blocks.add(getBlock(table, tableVisited, y, x, 1));
                }
            }
        }

        // 2. 조각과 빈 공간 매칭
        boolean[] used = new boolean[blocks.size()];
        for (Block target : blanks) {
            find:
            for (int i = 0; i < blocks.size(); i++) {
                if (used[i]) continue;

                Block block = blocks.get(i);

                // 크기가 다르면 회전 비교를 할 필요가 없습니다. (시간 절약)
                if (target.size != block.size) continue;

                // 회전을 시도할 배열 (원본 block.block을 훼손하지 않고 복사본으로 시작)
                int[][] currentShape = block.block;

                // 4회 회전하며 비교
                for (int j = 0; j < 4; j++) {
                    currentShape = rotateArray(currentShape);

                    if (Arrays.deepEquals(target.block, currentShape)) {
                        answer += block.size;
                        used[i] = true;
                        break find;
                    }
                }
            }
        }

        return answer;
    }

    // BFS를 사용하여 연결된 블록을 추출하는 헬퍼 함수 (원본 코드 수정)
    private Block getBlock(int[][] targetTable, boolean[][] visited,
                           int sy, int sx, int target) {

        List<int[]> blockIdxes = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{sy, sx});
        visited[sy][sx] = true; // 시작점 방문 처리

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        int len = targetTable.length;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            blockIdxes.add(now);
            int y = now[0], x = now[1];

            for (int i = 0; i < dy.length; i++) {
                int ny = y + dy[i], nx = x + dx[i];

                if (ny < 0 || ny >= len || nx < 0 || nx >= len ||
                        targetTable[ny][nx] != target || visited[ny][nx]) {
                    continue;
                }

                visited[ny][nx] = true; // 큐에 넣을 때 방문 처리
                queue.add(new int[]{ny, nx});
            }
        }
        return new Block(blockIdxes);
    }

    // 90도 시계 방향 회전 후 새로운 배열을 반환하는 헬퍼 함수 (원본 훼손 X)
    private int[][] rotateArray(int[][] arr) {
        int H = arr.length; // 현재 높이
        int W = arr[0].length; // 현재 너비
        // 회전 후 크기는 [새로운 높이(W)][새로운 너비(H)]
        int[][] rotatedArr = new int[W][H];

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                // (y, x) -> (x, H - 1 - y)로 변환 (90도 시계 방향)
                rotatedArr[x][H - 1 - y] = arr[y][x];
            }
        }
        return rotatedArr;
    }
}
