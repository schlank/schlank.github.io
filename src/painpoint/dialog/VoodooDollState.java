package painpoint.dialog;

public enum VoodooDollState {

    HAPPY_UNPINNED("voodoo-doll-happy.png", false),
    HAPPY_PINNED("voodoo-doll-happy-pinned.png", true),
    SAD_PINNED("voodoo-doll-sad-pinned.png", true),
    SAD_UNPINNED("voodoo-doll-sad-unpinned.png", false);

    String mImagePath;
    boolean mPinned;

    VoodooDollState(String imagePath, boolean pinned) {
        mPinned = pinned;
        mImagePath = imagePath;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public boolean isPinned() {
        return mPinned;
    }
}
