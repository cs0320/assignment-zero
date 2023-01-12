package edu.brown.cs32.chessish.board;

/**
 * An abstract action that may be taken by a piece. Because we don't yet know what kind of action, we can't
 * easily infer specific fields that might matter. E.g., we don't know if the piece yet exists on the board,
 * so we don't enforce that all Actions have a current location.
 *
 * Design note: another approach would be to use an Option for the current location, or use "null" to represent
 * the lack of one. However, I prefer this approach where ill-formed Actions are less possible to build. Using an
 * interface here gives us a parent pieceType for all kinds of actions, though, which might be useful.
 *
 * Advanced design note: I also like using records for data classes, since they are immutable etc. but Java disallows
 * inheritance for records. So we can't have this parent Action be a record without making Move, etc. classes.
 */
public interface Action {
    /**
     Execute this action on the current board. This will *MODIFY* the current Board object, rather than returning
     a new Board object, so we should beware of bugs caused by this state change. E.g., if we ever needed to
     *undo* a move, we'd need to keep a bit more history, etc.
     */
    void doAction(Board current) throws IllegalAction;
}
