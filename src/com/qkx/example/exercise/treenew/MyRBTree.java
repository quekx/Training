package com.qkx.example.exercise.treenew;

import com.qkx.example.model.RBTreeNode;

/**
 * Created by qkx on 17/3/31.
 */
public class MyRBTree {

    private RBTreeNode root;

    public RBTreeNode getRoot() {
        return root;
    }

    private void query(int val) {
        RBTreeNode x = queryNode(val);
        return;
    }

    private RBTreeNode queryNode(int val) {
        if (root == null) return null;

        RBTreeNode p = root;
        while (p != null) {
            if (p.val == val) {
                break;
            } else if (val < p.val) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return p;
    }

    private void deleteNode(RBTreeNode z) {
        if (z == null) return;

        RBTreeNode.Color removeColor;
        RBTreeNode x;
        RBTreeNode xp;
        if (z.left == null) {
            removeColor = z.color;
            x = z.right;
            xp = z.parent;
            if (z == root) root = x;

            transplant(z, z.right);
        } else if (z.right == null) {
            removeColor = z.color;
            x = z.left;
            xp = z.parent;
            if (z == root) root = x;

            transplant(z, z.left);
        } else {
            RBTreeNode y = min(z.right);
            z.val = y.val;

            removeColor = y.color;
            x = y.right;
            xp = y.parent;
            transplant(y, y.right);
        }

        if (removeColor == RBTreeNode.Color.BLACK) {
            deleteFixUp(x, xp);
        }
    }

    private void deleteFixUp(RBTreeNode x, RBTreeNode xp) {
        if (root == null || xp == null) return;

        while ((x == null || x.color == RBTreeNode.Color.BLACK) && x != root) {
            if (x == xp.left) {
                RBTreeNode xr = xp.right;
                if (xp.color == RBTreeNode.Color.RED) {
                    xr.color = RBTreeNode.Color.RED;
                    x = rotateLeft(xp);
                } else if (xr.color == RBTreeNode.Color.RED) {
                    RBTreeNode d = xr.left;
                    d.color = RBTreeNode.Color.RED;
                    x = rotateLeft(xp);
                } else {
                    xp.color = RBTreeNode.Color.RED;
                    x = rotateLeft(xp);
                }
                if (xp == root) root = xr;
            } else {
                RBTreeNode xl = xp.left;
                if (xp.color == RBTreeNode.Color.RED) {
                    xl.color = RBTreeNode.Color.RED;
                    x = rotateRight(xp);
                } else if (xl.color == RBTreeNode.Color.RED) {
                    RBTreeNode d = xl.right;
                    d.color = RBTreeNode.Color.RED;
                    x = rotateRight(xp);
                } else {
                    xp.color = RBTreeNode.Color.RED;
                    x = rotateRight(xp);
                }
                if (xp == root) root = xl;
            }
            xp = x.parent;
        }
        x.color = RBTreeNode.Color.BLACK;
    }

    private void transplant(RBTreeNode u, RBTreeNode v) {
        if (u == null) return;
        if (u == root) {
            root = v;
            return;
        }

        RBTreeNode xp = u.parent;
        if (u == xp.left) {
            xp.left = v;
        } else {
            xp.right = v;
        }

        if (v != null) {
            v.parent = xp;
        }
    }

    private RBTreeNode min(RBTreeNode x) {
        if (x == null) return null;

        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public void insert(int val) {
        if (root == null) {
            root = new RBTreeNode(val);
            return;
        }

        RBTreeNode p = root;
        RBTreeNode q = null;
        while (p != null) {
            q = p;
            if (val < p.val) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        RBTreeNode x = new RBTreeNode(val);
        x.parent = q;
        x.color = RBTreeNode.Color.RED;
        if (val < q.val) {
            q.left = x;
        } else {
            q.right = x;
        }

        insertFixUp(x);
    }

    private void insertFixUp(RBTreeNode x) {
        if (x == null) return;

        while (x.color == RBTreeNode.Color.RED
                && x.parent != null && x.parent.color == RBTreeNode.Color.RED) {
            RBTreeNode xp = x.parent;

            RBTreeNode xpp = xp.parent;
            if (x == xp.left) {
                if (xp == xpp.left) {
                    if (xpp.right == null || xpp.right.color == RBTreeNode.Color.BLACK) {
                        xp.color = RBTreeNode.Color.BLACK;
                        xpp.color = RBTreeNode.Color.RED;
                        x = rotateRight(xpp);
                        if (xpp == root) root = x;
                    } else {
                        xpp.color = RBTreeNode.Color.RED;
                        xpp.right.color = RBTreeNode.Color.BLACK;
                        xp.color = RBTreeNode.Color.BLACK;
                        x = xpp;
                    }
                } else {
                    rotateRight(xp);
                    x = xp;
                }
            } else {
                if (x == xp.right) {
                    if (xpp.left == null || xpp.left.color == RBTreeNode.Color.BLACK) {
                        xp.color = RBTreeNode.Color.BLACK;
                        xpp.color = RBTreeNode.Color.RED;
                        x = rotateLeft(xpp);
                        if (xpp == root) root = x;
                    } else {
                        xpp.color = RBTreeNode.Color.RED;
                        xpp.left.color = RBTreeNode.Color.BLACK;
                        xp.color = RBTreeNode.Color.BLACK;
                        x = xpp;
                    }
                } else {
                    rotateLeft(xp);
                    x = xp;
                }
            }
        }

        root.color = RBTreeNode.Color.BLACK;
    }

    private RBTreeNode rotateLeft(RBTreeNode x) {
        if (x == null) return null;
        if (x.right == null) return x;

        RBTreeNode xr = x.right;
        RBTreeNode xp = x.parent;

        x.right = xr.left;
        xr.left = x;
        x.parent = xr;
        xr.parent = xp;
        if (xp != null) {
            if (x == xp.left) {
                xp.left = xr;
            } else {
                xp.right = xr;
            }
        }

        return xr;
    }

    private RBTreeNode rotateRight(RBTreeNode x) {
        if (x == null) return null;
        if (x.left == null) return x;

        RBTreeNode xl = x.left;
        RBTreeNode xp = x.parent;

        x.left = xl.right;
        xl.right = x;
        x.parent = xl;
        xl.parent = xp;
        if (xp != null) {
            if (x == xp.left) {
                xp.left = xl;
            } else {
                xp.right = xl;
            }
        }

        return xl;
    }

}
