//package com.fengwenyi.mp3demo.effective.staticconstructor;
//
//import sun.misc.Cleaner;
//
///**
// * @author : Caixin
// * @date 2019/10/21 15:52
// */
//
//
////An autoCloseable class using a cleaner as a safety net
//
//public class Room implements AutoCloseable {
//
//    private static final Cleaner cleaner = Cleaner.create();
//
//    // Resource that requires  cleaning. Must not refer to Room!
//    private static class State implements Runnable {
//        // Number of junk piles this room
//        int numJunkPiles;
//
//        State(int numJunkPiles) {
//            this.numJunkPiles = numJunkPiles;
//        }
//
//        // Invoked by close method or cleaner
//        @Override
//        public void run() {
//            System.out.println("Cleaning room ");
//            numJunkPiles = 0;
//        }
//    }
//
//    // The state of this room, shared with cleanable
//    private final State state;
//
//    // Our cleanable.Cleans the room when it’s eligible for  gc
//    private final Cleaner.Cleanable cleanable;
//
//    public Room(int numJunkPiles) {
//        state = new State(numJunkPiles),
//        cleanable = cleaner.register(this, state);
//    }
//
//    @Override
//    public void close() {
//        Cleanable.clean();
//    }
//}
//
