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

    /**
     * 删除调整
     * @param x
     * @param xp
     */
    private void deleteFixUp(RBTreeNode x, RBTreeNode xp) {
        if (root == null || xp == null) return;

        // 退出调整循环条件：当前x为红或者x调整至根节点
        // 情况1：不进入循环
        while ((x == null || x.color == RBTreeNode.Color.BLACK) && x != root) {
            // x为作子节点
            if (x == xp.left) {
                RBTreeNode xr = xp.right;
                RBTreeNode a = xr.left;
                RBTreeNode b = xr.right;
                if (xr.color == RBTreeNode.Color.BLACK) {
                    // 兄弟节点为黑
                    // 情况2、情况3、情况4、情况5、情况6、情况7
                    if (a == null || a.color == RBTreeNode.Color.BLACK) {
                        // 情况2、情况5
                        // xp左旋
                        // 将x指向xr
                        // 将xr(x)颜色设置为xp颜色
                        // 情况2：xp为红，调整完成，x置为红退出循环
                        // 情况5：xp为黑，需要继续下一轮调整
                        rotateLeft(xp);
                        x = xr;
                        xr.color = xp.color;
                    } else if (b != null && b.color == RBTreeNode.Color.RED) {
                        // 情况3，情况6
                        // xp左旋
                        // 将xr颜色置为xp颜色（对应情况3、情况6）
                        // xp颜色置黑
                        // 两种情况均调整完成，x置红，退出循环
                        rotateLeft(xp);
                        xr.color = xp.color;
                        xp.color = RBTreeNode.Color.BLACK;
                        x.color = RBTreeNode.Color.RED;
                    } else if (b == null || b.color == RBTreeNode.Color.BLACK) {
                        // 情况4，情况7
                        // xr右旋
                        // a红置黑
                        // xr黑置红
                        // 调整至情况2、情况5，继续下一轮调整
                        rotateRight(xr);
                        a.color = RBTreeNode.Color.BLACK;
                        xr.color = RBTreeNode.Color.RED;
                    }
                } else {
                    // 兄弟节点为红，父节点必为黑
                    // 情况8
                    // 左旋xp
                    // xp置红
                    // xr置黑
                    // 此时x的父节点xp变为红色，切换至情况2或情况3或情况4，继续下一轮调整
                    rotateLeft(xp);
                    xp.color = RBTreeNode.Color.RED;
                    xr.color = RBTreeNode.Color.BLACK;
                }
            } else {
                // 对称情况
                RBTreeNode xl = xp.left;
                RBTreeNode a = xl.left;
                RBTreeNode b = xl.right;
                if (xl.color == RBTreeNode.Color.BLACK) {
                    if (b == null || b.color == RBTreeNode.Color.BLACK) {
                        rotateRight(xp);
                        x = xl;
                        xl.color = xp.color;
                    } else if (a != null && a.color == RBTreeNode.Color.RED) {
                        rotateRight(xp);
                        xl.color = xp.color;
                        xp.color = RBTreeNode.Color.BLACK;
                        x.color = RBTreeNode.Color.RED;
                    } else if (a == null || a.color == RBTreeNode.Color.BLACK) {
                        rotateRight(xl);
                        b.color = RBTreeNode.Color.BLACK;
                        xl.color = RBTreeNode.Color.RED;
                    }
                } else {
                    rotateRight(xp);
                    xp.color = RBTreeNode.Color.RED;
                    xl.color = RBTreeNode.Color.BLACK;
                }
            }
            // 如果x指向新节点，更新xp
            xp = x != null ? x.parent : xp;
        }
        x.color = RBTreeNode.Color.BLACK;
    }

    /**
     * 用x的子节点移植取代x节点
     *
     * @param x  被取代的节点
     * @param xc 子节点
     */
    private void transplant(RBTreeNode x, RBTreeNode xc) {
        if (x == null) return;
        if (x == root) {
            root = xc;
            return;
        }

        RBTreeNode xp = x.parent;
        if (x == xp.left) {
            xp.left = xc;
        } else {
            xp.right = xc;
        }

        if (xc != null) {
            xc.parent = xp;
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

        if (x == root) {
            root = xr;
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

        if (x == root) {
            root = xl;
        }

        return xl;
    }

}
