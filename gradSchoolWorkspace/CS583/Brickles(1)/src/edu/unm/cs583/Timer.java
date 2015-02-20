package edu.unm.cs583;

class Timer extends Object implements Runnable {
    protected MovablePiece[] list;
    protected int ptr;
    protected BricklesView theView;
    protected Thread theThread;

    public Timer(BricklesView newView) {
        theView = newView;
        list = new MovablePiece[5];
        ptr = -1;
        theThread = new Thread(this);
    }

    public void start() {
        theThread.start();
    }

    public void register(MovablePiece piece) {
        ptr = ptr + 1;
        list[ptr] = piece;
    }

    @Override
	public void run() {
        for ( ; ; ) {
            if (ptr > -1) {
                for (int i = 0; i <= ptr; i++) {
                    list[i].tick();
                }
            }
            theView.repaint();
            try {
                Thread.sleep(75);
            }
            catch (InterruptedException e) { }
        }
    }
}
