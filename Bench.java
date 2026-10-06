public class Bench {
    public static void main(String[] a) {
        for (int w = 0; w < 3; w++) run(2000, false); // warm-up
        for (int n : new int[]{100, 1000, 10000}) run(n, true);
    }

    static void run(int n, boolean print) {
        System.gc();
        long m0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        VotingService s = new VotingService();
        long t0 = System.nanoTime();
        for (int i = 0; i < n; i++) s.registerVoter("V" + i, "Name" + i, 18 + (i % 50));
        long t1 = System.nanoTime();
        for (int i = 0; i < n; i++) s.castVote("V" + i, 1 + (i % 3));
        long t2 = System.nanoTime();
        long m1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (print) System.out.printf("n=%d | register avg=%.3f ms/op (total %.1f ms) | vote avg=%.4f ms/op (total %.1f ms) | heap delta=%.2f MB%n",
                n, (t1 - t0) / 1e6 / n, (t1 - t0) / 1e6, (t2 - t1) / 1e6 / n, (t2 - t1) / 1e6, (m1 - m0) / 1048576.0);
    }
}
